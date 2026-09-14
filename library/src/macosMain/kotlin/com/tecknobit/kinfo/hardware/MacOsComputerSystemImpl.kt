package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBaseboard
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsComputerSystem
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsFirmware

/**
 * The `MacOsComputerSystemImpl` class is useful to store macOS machine identity and hardware information
 *
 * @property manufacturer The manufacturer reported by the platform expert device
 * @property model The model identifier of the Mac
 * @property serialNumber The serial number of the Mac, distinct from the logic board serial number
 * @property hardwareUUID The hardware UUID reported for the Mac
 * @property firmware The mapped macOS firmware information
 * @property baseboard The mapped macOS logic board information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsComputerSystem
 *
 * @since 1.1.0
 */
data class MacOsComputerSystemImpl(
    override val manufacturer: String,
    override val model: String,
    override val serialNumber: String,
    override val hardwareUUID: String,
    override val firmware: MacOsFirmware,
    override val baseboard: MacOsBaseboard
) : MacOsComputerSystem