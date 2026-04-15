package com.tecknobit.kinfo.model.desktop.hardware.graphicscard

/**
 * The `GpuStats` interface represents a session handle for sampling dynamic GPU metrics.
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
 * @author N7ghtm4r3
 *
 * @since 1.0.6
 */
interface GpuStats {

    /**
     * `gpuTicks` snapshot of cumulative GPU active and idle ticks in opaque, platform-native units. The counters are
     * monotonically increasing
     */
    val gpuTicks: GpuTicks

    /**
     * `gpuUtilization` the instantaneous GPU core utilization as a percentage
     */
    val gpuUtilization: Double

    /**
     * `vramUsed` the amount of dedicated VRAM currently in use in bytes
     */
    val vramUsed: Long

    /**
     * `sharedMemoryUsed` the amount of shared system memory currently used by this GPU in bytes
     */
    val sharedMemoryUsed: Long

    /**
     * `temperature` the temperature in degrees Celsius
     */
    val temperature: Double

    /**
     * `powerDraw` the GPU power consumption
     */
    val powerDraw: Double

    /**
     * `coreClockMhz` the current GPU core clock speed
     */
    val coreClockMhz: Long

    /**
     * `memoryClockMhz` the current GPU memory clock speed
     */
    val memoryClockMhz: Long

    /**
     * `fanSpeedPercent` the GPU fan speed as a percentage of maximum
     */
    val fanSpeedPercent: Double

}