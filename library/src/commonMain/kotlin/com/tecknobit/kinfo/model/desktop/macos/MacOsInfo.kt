package com.tecknobit.kinfo.model.desktop.macos

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHardware
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem

/**
 * The `MacOsInfo` interface defines the contract to expose macOS operating system and hardware information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsInfo {

    /**
     * `operatingSystem` the operating system information
     */
    val operatingSystem: MacOsOperatingSystem

    /**
     * `hardware` the hardware information
     */
    val hardware: MacOsHardware

}