package com.tecknobit.kinfo.model.desktop.hardware.centralprocessor

/**
 * `LogicalProcessor` interface represents a logical CPU core or thread in the system
 * This interface provides detailed information about the logical processor's mapping to physical processors,
 * NUMA nodes, and processor groups
 *
 * @author N7ghtm4r3
 */
interface LogicalProcessor {

    /**
     * `processorNumber` represents the unique identifier of the logical processor
     */
    val processorNumber: Int

    /**
     * `physicalProcessorNumber` specifies the physical core number to which this logical processor belongs
     */
    val physicalProcessorNumber: Int

    /**
     * `physicalPackageNumber` indicates the physical CPU package to which this logical processor belongs
     */
    val physicalPackageNumber: Int

    /**
     * `numaNode` represents the NUMA (Non-Uniform Memory Access) node to which this logical processor is assigned
     */
    val numaNode: Int

    /**
     * `processorGroup` denotes the processor group for systems that support grouping of processors
     */
    val processorGroup: Int

}
