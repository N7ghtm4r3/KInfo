package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.*

/**
 * `CentralProcessorImpl` provides an implementation of the [CentralProcessor] interface.
 * This class retrieves information about the system's central processing unit (CPU),
 * including its features, load, and performance metrics, leveraging the OSHI library.
 *
 * @param centralProcessorInfo The [oshi.hardware.CentralProcessor] instance used to fetch CPU details.
 *
 * @author N7ghtm4r3
 *
 * @see CentralProcessor
 */
class CentralProcessorImpl(
    private val centralProcessorInfo: oshi.hardware.CentralProcessor,
) : CentralProcessor {

    /**
     * `processorIdentifier` provides detailed information about the processor,
     * such as vendor, name, stepping, and model.
     */
    override val processorIdentifier: ProcessorIdentifier by lazy {
        ProcessorIdentifierImpl(
            processorIdentifierInfo = centralProcessorInfo.processorIdentifier
        )
    }

    /**
     * `maxFreq` represents the maximum frequency of the processor in hertz
     */
    override val maxFreq: Long = centralProcessorInfo.maxFreq

    /**
     * `currentFreq` contains an array of current frequencies for each logical processor in hertz
     */
    override val currentFreq: LongArray = centralProcessorInfo.currentFreq

    /**
     * `logicalProcessors` list of logical processors, each representing a core or a thread
     */
    override val logicalProcessors: List<LogicalProcessor>
        get() = loadLogicalProcessors(
            sourceList = centralProcessorInfo.logicalProcessors
        )

    /**
     * `physicalProcessors` list of physical processors,
     * representing the physical cores in the system
     */
    override val physicalProcessors: List<PhysicalProcessor>
        get() = loadPhysicalProcessors(
            sourceList = centralProcessorInfo.physicalProcessors
        )

    /**
     * `processorCaches` list of processor caches, detailing cache levels, sizes, and types
     */
    override val processorCaches: List<ProcessorCache>
        get() = loadProcessorCache(
            sourceList = centralProcessorInfo.processorCaches
        )

    /**
     * `featureFlags` lists the CPU's supported features, such as instruction sets and extensions
     */
    override val featureFlags: List<String> = centralProcessorInfo.featureFlags

    /**
     * `systemCpuLoadTicks` contains an array of tick counters representing system-wide CPU load
     */
    override val systemCpuLoadTicks: LongArray = centralProcessorInfo.systemCpuLoadTicks

    /**
     * `processorCpuLoadTicks` contains an array of tick counters for each logical processor,
     * representing the CPU load distribution
     */
    override val processorCpuLoadTicks: Array<LongArray> = centralProcessorInfo.processorCpuLoadTicks

    /**
     * `logicalProcessorCount` the number of logical processors in the system
     */
    override val logicalProcessorCount: Int = centralProcessorInfo.logicalProcessorCount

    /**
     * `physicalProcessorCount` the number of physical processors (cores) in the system
     */
    override val physicalProcessorCount: Int = centralProcessorInfo.physicalProcessorCount

    /**
     * `physicalPackageCount` the number of physical processor packages in the system
     */
    override val physicalPackageCount: Int = centralProcessorInfo.physicalPackageCount

    /**
     * `contextSwitches` the total number of context switches that have occurred
     */
    override val contextSwitches: Long = centralProcessorInfo.contextSwitches

    /**
     * `interrupts` the total number of interrupts that have occurred
     */
    override val interrupts: Long = centralProcessorInfo.interrupts

    /**
     * `getSystemCpuLoadBetweenTicks` calculates the system's CPU load as a percentage,
     * based on the difference between old and current tick counters.
     *
     * @param oldTickets Array of previous tick counters for calculation
     * @return The calculated CPU load as a percentage
     */
    @Bridge
    override fun getSystemCpuLoadBetweenTicks(
        oldTickets: LongArray
    ): Double {
        return centralProcessorInfo.getSystemCpuLoadBetweenTicks(oldTickets)
    }

    /**
     * `getSystemLoadAverage` retrieves the system's load average over a specified number of intervals.
     *
     * @param nelem The number of intervals to retrieve load averages for
     * @return An array of load average values
     */
    @Bridge
    override fun getSystemLoadAverage(
        nelem: Int
    ): DoubleArray {
        return centralProcessorInfo.getSystemLoadAverage(nelem)
    }

    /**
     * `getSystemCpuLoad` calculates the CPU load for the system over a specified delay.
     *
     * @param delay The delay in milliseconds over which the CPU load is measured
     * @return The calculated CPU load as a percentage
     */
    @Bridge
    override fun getSystemCpuLoad(
        delay: Long
    ): Double {
        return centralProcessorInfo.getSystemCpuLoad(delay)
    }

    /**
     * `getProcessorCpuLoadBetweenTicks` calculates the CPU load for each logical processor,
     * based on the difference between old and current tick counters.
     *
     * @param oldTickets Array of previous tick counters for each logical processor
     * @return An array of calculated CPU loads for each logical processor
     */
    @Bridge
    override fun getProcessorCpuLoadBetweenTicks(
        oldTickets: Array<LongArray>
    ): DoubleArray {
        return centralProcessorInfo.getProcessorCpuLoadBetweenTicks(oldTickets)
    }

    /**
     * Loads a list of logical processors with their detailed attributes.
     *
     * @param sourceList The source list of logical processors from OSHI
     * @return A list of [LogicalProcessor] instances
     */
    private fun loadLogicalProcessors(
        sourceList: List<oshi.hardware.CentralProcessor.LogicalProcessor>
    ): List<LogicalProcessor> {
        val result = mutableListOf<LogicalProcessor>()
        sourceList.forEach { processor ->
            result.add(
                LogicalProcessorImpl(
                    processorNumber = processor.processorNumber,
                    physicalProcessorNumber = processor.physicalProcessorNumber,
                    physicalPackageNumber = processor.physicalPackageNumber,
                    numaNode = processor.numaNode,
                    processorGroup = processor.processorGroup
                )
            )
        }
        return result
    }

    /**
     * Loads a list of physical processors with their detailed attributes.
     *
     * @param sourceList The source list of physical processors from OSHI
     * @return A list of [PhysicalProcessor] instances
     */
    private fun loadPhysicalProcessors(
        sourceList: List<oshi.hardware.CentralProcessor.PhysicalProcessor>
    ): List<PhysicalProcessor> {
        val result = mutableListOf<PhysicalProcessor>()
        sourceList.forEach { processor ->
            result.add(
                PhysicalProcessorImpl(
                    physicalPackageNumber = processor.physicalPackageNumber,
                    physicalProcessorNumber = processor.physicalProcessorNumber,
                    efficiency = processor.efficiency,
                    idString = processor.idString,
                )
            )
        }
        return result
    }

    /**
     * Loads a list of processor caches with their attributes such as size and type.
     *
     * @param sourceList The source list of processor caches from OSHI
     * @return A list of [ProcessorCache] instances
     */
    private fun loadProcessorCache(
        sourceList: List<oshi.hardware.CentralProcessor.ProcessorCache>
    ): List<ProcessorCache> {
        val result = mutableListOf<ProcessorCache>()
        sourceList.forEach { cache ->
            result.add(
                ProcessorCacheImpl(
                    level = cache.level,
                    associativity = cache.associativity,
                    lineSize = cache.lineSize,
                    cacheSize = cache.cacheSize,
                    type = CacheType.valueOf(cache.type.name)
                )
            )
        }
        return result
    }

}
