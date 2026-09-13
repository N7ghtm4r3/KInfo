package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsLogicalProcessor

/**
 * The `MacOsLogicalProcessorImpl` class is useful to represent a mapped macOS logical processor
 *
 * NUMA node and processor group values are fixed conventions rather than queried native identifiers
 *
 * @property processorNumber The logical processor index
 * @property physicalProcessorNumber The physical core index assigned by the mapper
 * @property physicalPackageNumber The physical package index assigned by the mapper
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsLogicalProcessor
 *
 * @since 1.1.0
 */
data class MacOsLogicalProcessorImpl(
    override val processorNumber: Int,
    override val physicalProcessorNumber: Int,
    override val physicalPackageNumber: Int
) : MacOsLogicalProcessor {

    /**
     * `numaNode` the conventional NUMA node index, always `0`
     */
    override val numaNode: Int = 0

    /**
     * `processorGroup` the conventional processor group index, always `0`
     */
    override val processorGroup: Int = 0
}
