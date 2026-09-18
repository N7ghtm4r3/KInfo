package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalMemory

/**
 * The `MacOsPhysicalMemoryImpl` class is useful to store a snapshot of macOS physical memory information
 *
 * @property bankLabel The memory bank label
 * @property capacity The memory capacity in bytes
 * @property clockSpeed The memory clock speed in MHz
 * @property manufacturer The memory manufacturer
 * @property memoryType The memory technology
 * @property partNumber The module part number
 * @property serialNumber The module serial number
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsPhysicalMemory
 *
 * @since 1.1.0
 */
data class MacOsPhysicalMemoryImpl(
    override val bankLabel: String,
    override val capacity: Long,
    override val clockSpeed: Long,
    override val manufacturer: String,
    override val memoryType: String,
    override val partNumber: String,
    override val serialNumber: String
) : MacOsPhysicalMemory