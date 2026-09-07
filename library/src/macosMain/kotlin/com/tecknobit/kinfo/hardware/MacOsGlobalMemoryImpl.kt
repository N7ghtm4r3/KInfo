package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.memory.PhysicalMemory
import com.tecknobit.kinfo.model.desktop.common.hardware.memory.VirtualMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGlobalMemory

data class MacOsGlobalMemoryImpl(
    override val total: Long,
    override val available: Long,
    override val pageSize: Long,
    override val virtualMemory: VirtualMemory,
    override val physicalMemory: List<PhysicalMemory>
) : MacOsGlobalMemory