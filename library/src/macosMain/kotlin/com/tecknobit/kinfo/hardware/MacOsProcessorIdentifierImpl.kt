package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorIdentifier

data class MacOsProcessorIdentifierImpl(
    override val cpuVendor: String,
    override val cpuName: String,
    override val cpuFamily: String,
    override val cpuModel: String,
    override val cpuStepping: String,
    override val processorId: String,
    override val cpuIdentifier: String,
    override val isCpu64bit: Boolean,
    override val cpuVendorFreq: Long
) : MacOsProcessorIdentifier
