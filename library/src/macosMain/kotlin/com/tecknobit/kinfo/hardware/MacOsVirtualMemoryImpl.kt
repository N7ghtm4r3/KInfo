package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsVirtualMemory

/**
 * The `MacOsVirtualMemoryImpl` class is useful to store a snapshot of macOS virtual memory information
 *
 * @property swapTotal The total swap space in bytes
 * @property swapUsed The used swap space in bytes
 * @property virtualMax The sum of physical memory and total swap space in bytes
 * @property virtualInUse The sum of used physical memory and used swap space in bytes
 * @property swapPagesIn The cumulative number of pages swapped in
 * @property swapPagesOut The cumulative number of pages swapped out
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsVirtualMemory
 *
 * @since 1.1.0
 */
data class MacOsVirtualMemoryImpl(
    override val swapTotal: Long,
    override val swapUsed: Long,
    override val virtualMax: Long,
    override val virtualInUse: Long,
    override val swapPagesIn: Long,
    override val swapPagesOut: Long
) : MacOsVirtualMemory
