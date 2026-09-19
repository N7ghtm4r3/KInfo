package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGpuTicks

/**
 * The `MacOsGpuTicksImpl` class is useful to store a macOS GPU tick snapshot
 *
 * Values are stored without validation or unit conversion
 * The native mapper supplies zero for both counters to represent unavailable measurements
 *
 * @property activeTicks The supplied cumulative active ticks in platform-native units
 * @property idleTicks The supplied cumulative idle ticks in platform-native units
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsGpuTicks
 *
 * @since 1.1.0
 */
data class MacOsGpuTicksImpl(
    override val activeTicks: Long,
    override val idleTicks: Long
) : MacOsGpuTicks
