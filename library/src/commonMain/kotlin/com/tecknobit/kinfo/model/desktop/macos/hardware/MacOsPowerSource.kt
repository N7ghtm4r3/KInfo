package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.PowerSource

/**
 * The `MacOsPowerSource` interface defines the contract to expose macOS power source measurements through [PowerSource]
 *
 * The native implementation reports charge as a percentage, remaining time in hours, power in watts,
 * voltage in volts, current in amperes, and temperature in degrees Celsius
 * Capacity values use the units indicated by [PowerSource.capacityUnits]
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsPowerSource : PowerSource