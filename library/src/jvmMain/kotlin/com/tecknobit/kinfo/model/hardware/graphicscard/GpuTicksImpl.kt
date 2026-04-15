package com.tecknobit.kinfo.model.hardware.graphicscard

import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuTicks

/**
 * Implementation of the `GpuTicks` interface.
 * This class represents an immutable snapshot of cumulative GPU active and idle tick counters in opaque,
 * platform-native units.
 *
 * Integration in [oshi - #3095](https://github.com/oshi/oshi/pull/3095)
 *
 * @property activeTicks The current active ticks
 * @property idleTicks The idle ticks count
 *
 * @author N7ghtm4r3
 *
 * @since 1.0.6
 *
 * @see GpuTicks
 */
class GpuTicksImpl(
    override val activeTicks: Long,
    override val idleTicks: Long,
) : GpuTicks