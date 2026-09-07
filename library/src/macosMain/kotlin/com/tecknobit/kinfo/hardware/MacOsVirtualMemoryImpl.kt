package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsVirtualMemory

data class MacOsVirtualMemoryImpl(
    override val swapTotal: Long,
    override val swapUsed: Long,
    override val virtualMax: Long,
    override val virtualInUse: Long,
    override val swapPagesIn: Long,
    override val swapPagesOut: Long
) : MacOsVirtualMemory
