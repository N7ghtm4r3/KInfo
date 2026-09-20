@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import kotlinx.cinterop.*
import platform.darwin.*

/**
 * `CPU_TICK_COUNT` the number of tick states in the shared processor snapshot format
 *
 * The eight slots follow USER, NICE, SYSTEM, IDLE, IOWAIT, IRQ, SOFTIRQ, STEAL order for `JVM` compatibility
 * macOS supplies the first four counters, while the remaining four slots contain zero
 *
 * @since 1.1.0
 */
internal const val CPU_TICK_COUNT = 8

/**
 * Method used to read a fresh system CPU tick snapshot in USER, NICE, SYSTEM, IDLE, IOWAIT, IRQ, SOFTIRQ, STEAL order
 *
 * Native unsigned counters are widened to [Long] without changing their tick units
 *
 * @return the unsigned native counters, with unsupported states set to zero, as [LongArray]
 * @throws IllegalStateException If the native query fails or returns incomplete data
 *
 * @since 1.1.0
 */
@Loader
internal fun loadSystemCpuLoadTicks(): LongArray {
    return memScoped {
        val info = alloc<host_cpu_load_info>()
        val count = alloc<mach_msg_type_number_tVar> {
            value = HOST_CPU_LOAD_INFO_COUNT
        }

        useMachHost { host ->
            val result = host_statistics(
                host,
                HOST_CPU_LOAD_INFO,
                info.ptr.reinterpret(),
                count.ptr
            )
            if (result != KERN_SUCCESS || count.value < HOST_CPU_LOAD_INFO_COUNT)
                throw IllegalStateException("Unable to read complete system CPU ticks: $result")

            mapCpuTicks { state ->
                info.cpu_ticks[state].toLong()
            }
        }
    }
}

/**
 * Method used to read fresh CPU tick snapshots in native logical processor order
 *
 * The kernel-allocated buffer is copied and released before returning
 * Each row follows the [CPU_TICK_COUNT] state order, preserving unsigned native counters in their original tick units
 * Unsupported states are set to zero
 *
 * @return the eight-state tick snapshots, one per logical processor, as [Array] of [LongArray]
 * @throws IllegalStateException If the native query fails or returns a missing or incomplete buffer
 *
 * @since 1.1.0
 */
@Loader
internal fun loadProcessorCpuLoadTicks(): Array<LongArray> {
    return memScoped {
        val processorCount = alloc<natural_tVar> {
            value = 0u
        }
        val info = alloc<processor_info_array_tVar> {
            value = null
        }
        val infoCount = alloc<mach_msg_type_number_tVar> {
            value = 0u
        }

        useMachHost { host ->
            val result = host_processor_info(
                host,
                PROCESSOR_CPU_LOAD_INFO,
                processorCount.ptr,
                info.ptr,
                infoCount.ptr
            )

            if (result != KERN_SUCCESS)
                throw IllegalStateException("Unable to read processor CPU ticks: $result")

            val buffer = info.value ?: throw IllegalStateException("Missing processor CPU tick buffer")
            try {
                val requiredCount = processorCount.value.toULong() * CPU_STATE_MAX.toULong()
                if (infoCount.value.toULong() < requiredCount)
                    throw IllegalStateException("Incomplete processor CPU tick data")

                Array(processorCount.value.toInt()) { cpu ->
                    val offset = cpu * CPU_STATE_MAX

                    mapCpuTicks { state ->
                        buffer[offset + state].toUInt().toLong()
                    }
                }
            } finally {
                vm_deallocate(
                    mach_task_self_,
                    buffer.rawValue.toLong().toULong(),
                    infoCount.value.toULong() * sizeOf<integer_tVar>().toULong()
                )
            }
        }
    }
}

/**
 * Method used to resolve the busy fraction between two macOS CPU tick snapshots from the same boot
 *
 * Counter differences use unsigned 32-bit arithmetic
 * A wrap is supported when fewer than 2^32 ticks separate each counter pair
 * Idle and I/O wait ticks are excluded from the busy time, and identical samples produce zero
 *
 * @param oldTicks The previous unsigned native counters in the [CPU_TICK_COUNT] state order
 * @param currentTicks The fresh unsigned native counters for the same system or logical processor
 *
 * @return the CPU load between zero and one as [Double]
 * @throws IllegalStateException If either snapshot does not contain eight states
 *
 * @since 1.1.0
 */
@Resolver
internal fun resolveCpuLoadBetweenTicks(
    oldTicks: LongArray,
    currentTicks: LongArray
): Double {
    if (oldTicks.size != CPU_TICK_COUNT || currentTicks.size != CPU_TICK_COUNT)
        throw IllegalStateException("CPU tick snapshots must contain $CPU_TICK_COUNT states")

    var total = 0L
    var idle = 0L
    for (index in currentTicks.indices) {
        val delta = (currentTicks[index] - oldTicks[index]) and 0xFFFFFFFFL
        total += delta
        if (index == 3 || index == 4)
            idle += delta
    }

    if (total == 0L)
        return 0.0

    return (total - idle).toDouble() / total
}

/**
 * Method used to place the four native macOS CPU counters into the shared eight-state snapshot format
 *
 * USER, NICE, SYSTEM, and IDLE counters are read in output order, followed by zero for IOWAIT, IRQ, SOFTIRQ, and STEAL
 *
 * @param readTick The function supplying the counter for each requested native CPU state
 *
 * @return the counters in the [CPU_TICK_COUNT] state order as [LongArray]
 *
 * @since 1.1.0
 */
private inline fun mapCpuTicks(
    readTick: (Int) -> Long
): LongArray {
    return longArrayOf(
        readTick(CPU_STATE_USER),
        readTick(CPU_STATE_NICE),
        readTick(CPU_STATE_SYSTEM),
        readTick(CPU_STATE_IDLE),
        0L, // IOWAIT
        0L, // IRQ
        0L, // SOFTIRQ
        0L // STEAL
    )
}
