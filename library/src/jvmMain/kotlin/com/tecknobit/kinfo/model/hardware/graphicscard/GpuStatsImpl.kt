package com.tecknobit.kinfo.model.hardware.graphicscard

import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuStats
import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuTicks

/**
 * Implementation of the `GpuStats` interface.
 * This represents a session handle for sampling dynamic GPU metrics.
 *
 * The session exposes both instantaneous metrics (temperature, clocks, VRAM, fan speed)
 * and delta-based metrics (GPU utilization and power draw) that may require an initial
 * priming step depending on the backend implementation.
 *
 * The session holds native resources and must be closed after use. It is recommended
 * to keep a single session open for repeated polling to preserve internal state and
 * reduce overhead from reinitializing native subscriptions.
 *
 * Integration in [oshi - #3095](https://github.com/oshi/oshi/pull/3095)
 *
 * @property gpuTicks A snapshot of cumulative GPU active and idle ticks in opaque, platform-native units. The counters are
 * monotonically increasing
 * @property gpuUtilization The instantaneous GPU core utilization as a percentage
 * @property vramUsed The amount of dedicated VRAM currently in use in bytes
 * @property sharedMemoryUsed The amount of shared system memory currently used by this GPU in bytes
 * @property temperature The temperature in degrees Celsius
 * @property powerDraw The GPU power consumption
 * @property coreClockMhz The current GPU core clock speed
 * @property memoryClockMhz The current GPU memory clock speed
 * @property fanSpeedPercent The GPU fan speed as a percentage of maximum
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 *
 * @see GpuStats
 */
class GpuStatsImpl(
    override val gpuTicks: GpuTicks,
    override val gpuUtilization: Double,
    override val vramUsed: Long,
    override val sharedMemoryUsed: Long,
    override val temperature: Double,
    override val powerDraw: Double,
    override val coreClockMhz: Long,
    override val memoryClockMhz: Long,
    override val fanSpeedPercent: Double
) : GpuStats