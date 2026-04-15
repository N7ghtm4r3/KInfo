package com.tecknobit.kinfo.model.desktop.hardware.graphicscard

interface GpuStats {

    val gpuTicks: GpuTicks

    val gpuUtilization: Double

    val vramUsed: Long

    val sharedMemoryUsed: Long

    val temperature: Double

    val powerDraw: Double

    val coreClockMhz: Long

    val memoryClockMhz: Long

    val fanSpeedPercent: Double

}