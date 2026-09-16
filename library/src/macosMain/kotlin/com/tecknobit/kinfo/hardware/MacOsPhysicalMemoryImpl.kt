package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalMemory

data class MacOsPhysicalMemoryImpl(
    override val bankLabel: String,
    override val capacity: Long,
    override val clockSpeed: Long,
    override val manufacturer: String,
    override val memoryType: String,
    override val partNumber: String,
    override val serialNumber: String
) : MacOsPhysicalMemory