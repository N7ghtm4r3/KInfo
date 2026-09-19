package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.graphicscard.GpuTicks
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGpuStats

/**
 * The `MacOsGpuStatsImpl` class is useful to store a macOS GPU statistics snapshot
 *
 * Values are stored without validation or unit conversion and do not refresh automatically
 * The native mapper supplies zero for unavailable metrics, including both GPU tick counters
 *
 * @property gpuTicks The supplied snapshot of cumulative GPU active and idle ticks
 * @property gpuUtilization The supplied GPU utilization percentage
 * @property vramUsed The supplied dedicated video-memory usage in bytes
 * @property sharedMemoryUsed The supplied shared system-memory usage in bytes
 * @property temperature The supplied GPU temperature in degrees Celsius
 * @property powerDraw The supplied GPU power consumption in watts
 * @property coreClockMhz The supplied GPU core clock frequency in megahertz
 * @property memoryClockMhz The supplied GPU memory clock frequency in megahertz
 * @property fanSpeedPercent The supplied fan speed as a percentage of its maximum
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsGpuStats
 *
 * @since 1.1.0
 */
data class MacOsGpuStatsImpl(
    override val gpuTicks: GpuTicks,
    override val gpuUtilization: Double,
    override val vramUsed: Long,
    override val sharedMemoryUsed: Long,
    override val temperature: Double,
    override val powerDraw: Double,
    override val coreClockMhz: Long,
    override val memoryClockMhz: Long,
    override val fanSpeedPercent: Double
) : MacOsGpuStats