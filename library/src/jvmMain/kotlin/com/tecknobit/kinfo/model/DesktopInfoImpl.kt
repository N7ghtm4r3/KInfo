package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.desktop.hardware.Hardware
import com.tecknobit.kinfo.model.desktop.operatingsystem.OperatingSystem
import com.tecknobit.kinfo.model.hardware.HardwareImpl
import com.tecknobit.kinfo.model.operatingsystem.OperatingSystemImpl
import oshi.SystemInfo

/**
 * Represents an implementation of the `DesktopInfo` interface.
 * Provides concrete information about the operating system and hardware of the desktop.
 *
 * This class lazily initializes the system information and provides the `OperatingSystem` and `Hardware`
 * details by using their respective implementations.
 *
 * @see DesktopInfo
 * @see OperatingSystem
 * @see Hardware
 *
 * @author N7ghtm4r3
 */
class DesktopInfoImpl : DesktopInfo {

    /**
     * `systemInfo` The system information used to initialize both the operating system and hardware components.
     * This is lazily initialized to improve performance
     */
    private val systemInfo by lazy { SystemInfo() }

    /**
     * `operatingSystem` The operating system of the desktop, lazily initialized with the `OperatingSystemImpl`
     */
    override val operatingSystem: OperatingSystem by lazy {
        OperatingSystemImpl(
            systemInfo = systemInfo
        )
    }

    /**
     * `hardware` The hardware information of the desktop, lazily initialized with the `HardwareImpl`
     */
    override val hardware: Hardware by lazy {
        HardwareImpl(systemInfo = systemInfo)
    }

}
