package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGlobalMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsVirtualMemory

/**
 * The `MacOsGlobalMemoryImpl` class is useful to store a snapshot of macOS global memory information
 *
 * @property total The total physical memory in bytes
 * @property available The available memory estimate in bytes
 * @property pageSize The memory page size in bytes
 * @property virtualMemory The mapped virtual memory statistics
 * @property physicalMemory The mapped physical memory modules
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsGlobalMemory
 *
 * @since 1.1.0
 */
data class MacOsGlobalMemoryImpl(
    override val total: Long,
    override val available: Long,
    override val pageSize: Long,
    override val virtualMemory: MacOsVirtualMemory,
    override val physicalMemory: List<MacOsPhysicalMemory>
) : MacOsGlobalMemory