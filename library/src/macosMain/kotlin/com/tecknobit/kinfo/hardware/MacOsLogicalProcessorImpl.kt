package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsLogicalProcessor

data class MacOsLogicalProcessorImpl(
    override val processorNumber: Int,
    override val physicalProcessorNumber: Int,
    override val physicalPackageNumber: Int,
    override val numaNode: Int,
    override val processorGroup: Int
) : MacOsLogicalProcessor
