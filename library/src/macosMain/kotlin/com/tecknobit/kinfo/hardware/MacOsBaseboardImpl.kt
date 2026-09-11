package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBaseboard

/**
 * The `MacOsBaseboardImpl` class is useful to store the baseboard information retrieved from macOS
 *
 * @property manufacturer The manufacturer reported by the platform
 * @property model The board identifier or the hardware target identifier used as a fallback
 * @property version The platform-reported version, which may be unavailable or differ from the physical board revision
 * @property serialNumber The logic board serial number rather than the serial number of the computer
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
data class MacOsBaseboardImpl(
    override val manufacturer: String,
    override val model: String,
    override val version: String,
    override val serialNumber: String
) : MacOsBaseboard
