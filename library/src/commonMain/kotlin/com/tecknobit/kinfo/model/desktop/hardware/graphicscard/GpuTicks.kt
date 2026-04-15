package com.tecknobit.kinfo.model.desktop.hardware.graphicscard

/**
 * The `GpuTicks` interface represents an immutable snapshot of cumulative GPU active and idle tick counters in opaque,
 * platform-native units.
 *
 * Integration in [oshi - #3095](https://github.com/oshi/oshi/pull/3095)
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 */
interface GpuTicks {

    /**
     * `activeTicks` current active ticks
     */
    val activeTicks: Long

    /**
     * `idleTicks` idle ticks count
     */
    val idleTicks: Long

}