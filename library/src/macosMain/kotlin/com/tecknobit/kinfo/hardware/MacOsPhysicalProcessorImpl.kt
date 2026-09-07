package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalProcessor

data class MacOsPhysicalProcessorImpl(
    override val physicalPackageNumber: Int,
    override val physicalProcessorNumber: Int,
    override val efficiency: Int,
    override val idString: String
) : MacOsPhysicalProcessor
