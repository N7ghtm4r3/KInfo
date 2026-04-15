package com.tecknobit.kinfo.model.hardware.graphicscard

import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuStats
import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuTicks

class GpuStatsImpl(
    override val gpuUtilization: Double,
    override val vramUsed: Long,
    override val sharedMemoryUsed: Long,
    override val temperature: Double,
    override val powerDraw: Double,
    override val coreClockMhz: Long,
    override val memoryClockMhz: Long,
    override val fanSpeedPercent: Double,
    override val gpuTicks: GpuTicks,
) : GpuStats