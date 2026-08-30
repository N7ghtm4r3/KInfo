package com.tecknobit.kinfo.helpers

import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlin.time.Clock

/**
 * The `CpuTicksRegistry` object allows to store and synchronize CPU tick samples by process identifier
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
object CpuTicksRegistry {

    /**
     * `REGISTRY_LOCK` the lock used to synchronize compound registry operations
     */
    private val REGISTRY_LOCK = SynchronizedObject()

    /**
     * `CRUD_LOCK` the lock used to synchronize tick retrieval and registration operations
     */
    private val CRUD_LOCK = SynchronizedObject()

    /**
     * `CPU_TICKS` the latest CPU tick samples associated with their process identifiers
     */
    private val CPU_TICKS: MutableMap<Int, CpuTick> = mutableMapOf()

    /**
     * The `CpuTick` class is useful to represent a process CPU usage sample
     *
     * @property cpuTime The cumulative CPU time in milliseconds
     * @property timestamp The time when the sample was captured in milliseconds since the Unix epoch
     *
     * @author N7ghtm4r3 - Tecknobit
     *
     * @since 1.1.0
     */
    data class CpuTick(
        val cpuTime: Long = 0L,
        val timestamp: Long = 0L
    )

    /**
     * Method used to retrieve the previous CPU tick associated with a process
     *
     * The [defaultValue] is registered and returned when no CPU tick is currently associated with the process
     *
     * @param pid The identifier of the process
     * @param defaultValue The CPU tick to register when no previous sample is available
     *
     * @return the previous or registered CPU tick as [CpuTick]
     */
    fun retrievePreviousCpuTick(
        pid: Int,
        defaultValue: CpuTick
    ): CpuTick {
        return useRegistry {
            CPU_TICKS.getOrPut(
                key = pid,
                defaultValue = { defaultValue }
            )
        }
    }

    /**
     * Method used to register a CPU tick for a process
     *
     * @param processId The identifier of the process
     * @param cpuTick The CPU tick to register
     */
    fun registerCpuTick(
        processId: Int,
        cpuTick: CpuTick
    ) {
        registerCpuTick(
            processId = processId,
            cpuTime = cpuTick.cpuTime,
            timestamp = cpuTick.timestamp
        )
    }

    /**
     * Method used to register cumulative CPU time and its sampling timestamp for a process
     *
     * @param processId The identifier of the process
     * @param cpuTime The cumulative CPU time in milliseconds
     * @param timestamp The sampling timestamp in milliseconds since the Unix epoch
     */
    fun registerCpuTick(
        processId: Int,
        cpuTime: Long,
        timestamp: Long = Clock.System.now().toEpochMilliseconds()
    ) {
        useRegistry {
            CPU_TICKS[processId] = CpuTick(
                cpuTime = cpuTime,
                timestamp = timestamp
            )
        }
    }

    /**
     * Method used to execute an operation with exclusive access to the CPU tick storage
     *
     * @param T The type returned by the operation
     * @param block The operation to execute
     *
     * @return the result of the operation as [T]
     */
    private inline fun <T> useRegistry(
        block: () -> T
    ): T {
        return synchronized(
            lock = CRUD_LOCK,
            block = block
        )
    }

    /**
     * Method used to execute a compound operation with exclusive access to the CPU tick registry
     *
     * @param T The type returned by the operation
     * @param block The operation to execute with the registry as receiver
     *
     * @return the result of the compound operation as [T]
     */
    fun <T> use(
        block: CpuTicksRegistry.() -> T
    ): T {
        return synchronized(
            lock = REGISTRY_LOCK,
            block = { block() }
        )
    }

}
