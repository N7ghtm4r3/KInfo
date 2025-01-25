package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.LogicalProcessor

/**
 * Implementation of the `LogicalProcessor` interface.
 * This class provides details about a logical CPU core or thread in the system,
 * including its mapping to physical processors, NUMA nodes, and processor groups.
 *
 * @param processorNumber The unique identifier of the logical processor
 * @param physicalProcessorNumber The physical core number to which this logical processor belongs
 * @param physicalPackageNumber The physical CPU package to which this logical processor belongs
 * @param numaNode The NUMA (Non-Uniform Memory Access) node to which this logical processor is assigned
 * @param processorGroup The processor group for systems that support grouping
 *
 * @author N7ghtm4r3
 *
 * @see LogicalProcessor
 */
class LogicalProcessorImpl(
    override val processorNumber: Int,
    override val physicalProcessorNumber: Int,
    override val physicalPackageNumber: Int,
    override val numaNode: Int,
    override val processorGroup: Int
) : LogicalProcessor

