package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.hardware.MacOsLogicalProcessorImpl
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsLogicalProcessor
import com.tecknobit.kinfo.utils.queryIntSysCtlByName

/**
 * The `MacOsLogicalProcessorMapper` class is useful to build macOS logical processor descriptions
 * from native processor counts
 *
 * Core and package indices are reconstructed assuming uniformly distributed, contiguous logical processors
 * They are not individual hardware identifiers retrieved from the system
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsLogicalProcessorMapper : MacOsHardwareMapper<List<MacOsLogicalProcessorImpl>>() {

    /**
     * Method used to map `hw.logicalcpu`, `hw.physicalcpu`, and `hw.packages` to logical processor descriptions
     *
     * Each failed count query falls back to `0` and a zero logical count produces an empty list
     * Core and package indices use integer division, while NUMA node and processor group remain `0`
     *
     * @return the reconstructed logical processor descriptions as [List] of [MacOsLogicalProcessor]
     */
    override fun mapFromNative(): List<MacOsLogicalProcessorImpl> {
        val logicalCount = queryIntSysCtlByName(
            name = "hw.logicalcpu",
            default = 0
        )!!
        val physicalCount = queryIntSysCtlByName(
            name = "hw.physicalcpu",
            default = 0
        )!!
        val packageCount = queryIntSysCtlByName(
            name = "hw.packages",
            default = 0
        )!!

        return List(logicalCount) { index ->
            MacOsLogicalProcessorImpl(
                processorNumber = index,
                physicalProcessorNumber = index * physicalCount / logicalCount,
                physicalPackageNumber = index * packageCount / logicalCount
            )
        }
    }

}