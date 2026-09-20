@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.macos.hardware.*
import com.tecknobit.kinfo.utils.CPU_TICK_COUNT
import com.tecknobit.kinfo.utils.loadProcessorCpuLoadTicks
import com.tecknobit.kinfo.utils.loadSystemCpuLoadTicks
import com.tecknobit.kinfo.utils.resolveCpuLoadBetweenTicks
import kotlinx.cinterop.*
import platform.Foundation.NSThread
import platform.posix.getloadavg

/**
 * The `MacOsCentralProcessorImpl` class is useful to store macOS processor information and sample CPU load
 *
 * [currentFreq] contains nominal frequencies in hertz; zero represents an unavailable frequency
 * Tick properties retain their initial samples, while CPU load methods read fresh native counters
 * CPU load results range from zero to one, matching the `JVM` implementation
 *
 * @property processorIdentifier The processor identification information
 * @property maxFreq The maximum nominal processor frequency in hertz, or zero when unavailable
 * @property currentFreq The nominal frequencies in hertz, in [logicalProcessors] order, with zero when unavailable
 * @property logicalProcessors The logical processor descriptions
 * @property physicalProcessors The physical processor descriptions
 * @property processorCaches The processor cache descriptions
 * @property featureFlags The feature names reported by the architecture-specific mapper
 * @property systemCpuLoadTicks The initial system tick counters in the [CPU_TICK_COUNT] state order
 * @property processorCpuLoadTicks The initial tick counters in native logical processor order,
 * with the same state order as [systemCpuLoadTicks]
 * @property physicalPackageCount The number of physical processor packages, or zero when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsCentralProcessor
 *
 * @since 1.1.0
 */
data class MacOsCentralProcessorImpl(
    override val processorIdentifier: MacOsProcessorIdentifier,
    override val maxFreq: Long,
    override val currentFreq: LongArray,
    override val logicalProcessors: List<MacOsLogicalProcessor>,
    override val physicalProcessors: List<MacOsPhysicalProcessor>,
    override val processorCaches: List<MacOsProcessorCache>,
    override val featureFlags: List<String>,
    override val systemCpuLoadTicks: LongArray,
    override val processorCpuLoadTicks: Array<LongArray>,
    override val physicalPackageCount: Int
) : MacOsCentralProcessor {

    /**
     * `logicalProcessorCount` the number of stored logical processor descriptions
     */
    override val logicalProcessorCount: Int
        get() = logicalProcessors.size

    /**
     * `physicalProcessorCount` the number of stored physical processor descriptions
     */
    override val physicalProcessorCount: Int
        get() = physicalProcessors.size

    /**
     * `contextSwitches` the unavailable system-wide context switch count, represented by zero
     */
    override val contextSwitches: Long = 0L

    /**
     * `interrupts` the unavailable system-wide interrupt count, represented by zero
     */
    override val interrupts: Long = 0L

    /**
     * Method used to calculate system CPU load between a previous tick snapshot and a fresh native sample
     *
     * Both samples must originate from the same boot and use the state order of [systemCpuLoadTicks]
     * Idle and I/O wait ticks are excluded from busy time, and identical samples produce zero
     *
     * @param oldTickets The previous system snapshot containing [CPU_TICK_COUNT] counters
     *
     * @return the system CPU load between zero and one as [Double]
     * @throws IllegalArgumentException If the previous snapshot does not contain eight counters
     * @throws IllegalStateException If the native query fails or returns incomplete data
     */
    @Bridge
    override fun getSystemCpuLoadBetweenTicks(
        oldTickets: LongArray
    ): Double {
        if (oldTickets.size != CPU_TICK_COUNT)
            throw IllegalArgumentException("The previous CPU tick snapshot must contain $CPU_TICK_COUNT states")

        return resolveCpuLoadBetweenTicks(
            oldTicks = oldTickets,
            currentTicks = loadSystemCpuLoadTicks()
        )
    }

    /**
     * Method used to retrieve the system load averages for the requested time intervals
     *
     * Values follow the one, five, and fifteen minute interval order and use -1 when unavailable
     * Load averages represent system demand and are not limited to the zero-to-one CPU load range
     *
     * @param nelem The number of leading intervals to retrieve, from one to three
     *
     * @return the requested system load averages as [DoubleArray]
     * @throws IllegalArgumentException If the requested interval count is outside one to three
     */
    @Bridge
    override fun getSystemLoadAverage(
        nelem: Int
    ): DoubleArray {
        if (nelem !in 1..3)
            throw IllegalArgumentException("The load average must contain between one and three intervals")

        return memScoped {
            val average = allocArray<DoubleVar>(nelem)
            val count = getloadavg(average, nelem)

            DoubleArray(nelem) { index ->
                if (index < count)
                    average[index]
                else
                    -1.0
            }
        }
    }

    /**
     * Method used to sample system CPU load across the specified delay
     *
     * The calling thread sleeps between native samples when the delay is positive
     * A zero delay reads both samples without sleeping, and identical samples produce zero
     *
     * @param delay The nonnegative sampling delay in milliseconds
     *
     * @return the system CPU load between zero and one as [Double]
     * @throws IllegalArgumentException If the sampling delay is negative
     * @throws IllegalStateException If either native query fails or returns incomplete data
     */
    @Bridge
    override fun getSystemCpuLoad(
        delay: Long
    ): Double {
        if (delay < 0L)
            throw IllegalArgumentException("The sampling delay must not be negative")

        val oldTicks = loadSystemCpuLoadTicks()
        if (delay > 0L)
            NSThread.sleepForTimeInterval(delay.toDouble() / 1000)

        return getSystemCpuLoadBetweenTicks(
            oldTickets = oldTicks
        )
    }

    /**
     * Method used to calculate each logical processor's CPU load against a fresh native tick snapshot
     *
     * Previous samples must originate from the same boot and follow [processorCpuLoadTicks] processor and state order
     * Idle and I/O wait ticks are excluded from busy time, and identical processor samples produce zero
     *
     * @param oldTickets The previous snapshot containing one eight-state row per logical processor
     *
     * @return the CPU loads between zero and one in native logical processor order as [DoubleArray]
     * @throws IllegalArgumentException If the previous snapshot has an invalid shape or the processor count has changed
     * @throws IllegalStateException If the native query fails or returns a missing or incomplete buffer
     */
    @Bridge
    override fun getProcessorCpuLoadBetweenTicks(
        oldTickets: Array<LongArray>
    ): DoubleArray {
        if (oldTickets.size != logicalProcessorCount || oldTickets.any { it.size != CPU_TICK_COUNT })
            throw IllegalArgumentException("The previous CPU ticks must contain $logicalProcessorCount processors with $CPU_TICK_COUNT states each")

        val currentTicks = loadProcessorCpuLoadTicks()
        if (currentTicks.size != oldTickets.size)
            throw IllegalArgumentException("The logical processor count changed between CPU tick snapshots")

        return DoubleArray(currentTicks.size) { index ->
            resolveCpuLoadBetweenTicks(
                oldTicks = oldTickets[index],
                currentTicks = currentTicks[index]
            )
        }
    }

    /**
     * Method used to compare processor snapshots by their stored values
     *
     * Frequency and tick arrays are compared by content, including nested processor tick arrays
     *
     * @param other The object to compare with this snapshot
     *
     * @return whether the other object is a [MacOsCentralProcessorImpl] with equal stored values as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsCentralProcessorImpl

        if (maxFreq != other.maxFreq) return false
        if (physicalPackageCount != other.physicalPackageCount) return false
        if (processorIdentifier != other.processorIdentifier) return false
        if (!currentFreq.contentEquals(other.currentFreq)) return false
        if (logicalProcessors != other.logicalProcessors) return false
        if (physicalProcessors != other.physicalProcessors) return false
        if (processorCaches != other.processorCaches) return false
        if (featureFlags != other.featureFlags) return false
        if (!systemCpuLoadTicks.contentEquals(other.systemCpuLoadTicks)) return false
        if (!processorCpuLoadTicks.contentDeepEquals(other.processorCpuLoadTicks)) return false

        return true
    }

    /**
     * Method used to calculate a hash code from the stored processor values
     *
     * Frequency and tick arrays contribute their contents, consistently with [equals]
     *
     * @return the hash code of the stored processor values as [Int]
     */
    override fun hashCode(): Int {
        var result = maxFreq.hashCode()
        result = 31 * result + physicalPackageCount
        result = 31 * result + processorIdentifier.hashCode()
        result = 31 * result + currentFreq.contentHashCode()
        result = 31 * result + logicalProcessors.hashCode()
        result = 31 * result + physicalProcessors.hashCode()
        result = 31 * result + processorCaches.hashCode()
        result = 31 * result + featureFlags.hashCode()
        result = 31 * result + systemCpuLoadTicks.contentHashCode()
        result = 31 * result + processorCpuLoadTicks.contentDeepHashCode()
        return result
    }
}
