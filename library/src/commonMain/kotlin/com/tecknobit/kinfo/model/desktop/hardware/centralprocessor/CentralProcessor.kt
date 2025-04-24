package com.tecknobit.kinfo.model.desktop.hardware.centralprocessor

import com.tecknobit.kinfo.annotations.Bridge

/**
 * `CentralProcessor` interface provides detailed information and metrics about the central processor of a system
 * It includes properties to retrieve processor specifications, performance data, and load statistics
 *
 * This interface is intended to be implemented by platform-specific classes to provide processor details
 * and performance metrics across different systems
 *
 * @author N7ghtm4r3
 */
interface CentralProcessor {

    /**
     * `processorIdentifier` the identifier of the processor, providing details like name and architecture
     */
    val processorIdentifier: ProcessorIdentifier

    /**
     * `maxFreq` the maximum frequency of the processor in Hz
     */
    val maxFreq: Long

    /**
     * `currentFreq` an array representing the current frequency of each logical processor in Hz
     */
    val currentFreq: LongArray

    /**
     * `logicalProcessors` a list of logical processors available in the system
     */
    val logicalProcessors: List<LogicalProcessor>

    /**
     * `physicalProcessors` a list of physical processors (cores) available in the system
     */
    val physicalProcessors: List<PhysicalProcessor>

    /**
     * `processorCaches` a list of caches associated with the processor
     */
    val processorCaches: List<ProcessorCache>

    /**
     * `featureFlags` a list of supported processor features (eg, virtualization, specific instruction sets)
     */
    val featureFlags: List<String>

    /**
     * `systemCpuLoadTicks` an array representing CPU load ticks for the entire system
     */
    val systemCpuLoadTicks: LongArray

    /**
     * `processorCpuLoadTicks` an array of arrays representing CPU load ticks for each logical processor
     */
    val processorCpuLoadTicks: Array<LongArray>

    /**
     * `logicalProcessorCount` the total number of logical processors in the system
     */
    val logicalProcessorCount: Int

    /**
     * `physicalProcessorCount` the total number of physical processors (cores) in the system
     */
    val physicalProcessorCount: Int

    /**
     * `physicalPackageCount` the total number of physical processor packages in the system
     */
    val physicalPackageCount: Int

    /**
     * `contextSwitches` the number of context switches performed by the processor
     */
    val contextSwitches: Long

    /**
     * `interrupts` the number of interrupts processed by the processor
     */
    val interrupts: Long

    /**
     * Retrieves the system CPU load as a percentage between the current ticks and the specified `oldTickets`
     *
     * @param oldTickets the previous CPU ticks for comparison
     * @return the calculated system CPU load as a percentage
     */
    @Bridge
    fun getSystemCpuLoadBetweenTicks(
        oldTickets: LongArray
    ): Double

    /**
     * Retrieves the system load average for the last `nelem` intervals
     *
     * @param nelem the number of intervals for which to retrieve the load average
     * @return an array of load average values
     */
    @Bridge
    fun getSystemLoadAverage(
        nelem: Int
    ): DoubleArray

    /**
     * Calculates the system CPU load over a specified delay period
     *
     * @param delay the delay period in milliseconds
     * @return the calculated system CPU load as a percentage
     */
    @Bridge
    fun getSystemCpuLoad(
        delay: Long
    ): Double

    /**
     * Retrieves the processor CPU load as a percentage between the current ticks and the specified `oldTickets`
     *
     * @param oldTickets the previous CPU ticks for comparison
     * @return an array of calculated CPU loads for each processor as percentages
     */
    @Bridge
    fun getProcessorCpuLoadBetweenTicks(
        oldTickets: Array<LongArray>
    ): DoubleArray

}
