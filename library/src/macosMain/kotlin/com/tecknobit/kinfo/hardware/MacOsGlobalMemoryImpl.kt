package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGlobalMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsVirtualMemory

data class MacOsGlobalMemoryImpl(
    override val total: Long,
    override val available: Long,
    override val pageSize: Long,
    override val virtualMemory: MacOsVirtualMemory,
    override val physicalMemory: List<MacOsPhysicalMemory>
) : MacOsGlobalMemory