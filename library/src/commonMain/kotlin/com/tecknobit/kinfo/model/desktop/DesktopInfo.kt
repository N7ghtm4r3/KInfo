package com.tecknobit.kinfo.model.desktop

import com.tecknobit.kinfo.model.desktop.hardware.Hardware
import com.tecknobit.kinfo.model.desktop.operatingsystem.OperatingSystem

/**
 * Represents information about the desktop environment.
 * Provides details about the operating system and hardware of the desktop.
 *
 * @author N7ghtm4r3
 */
interface DesktopInfo {

    /**
     * `operatingSystem` The operating system running on the desktop.
     */
    val operatingSystem: OperatingSystem

    /**
     * `hardware` The hardware information of the desktop, including details about CPU, RAM, and other components.
     */
    val hardware: Hardware

}
