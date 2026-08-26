package com.tecknobit.kinfo

import com.tecknobit.kinfo.model.desktop.macos.MacOsInfo
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHardware
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem
import com.tecknobit.kinfo.operatingsystem.MacOsOperatingSystemImpl
import platform.Foundation.NSProcessInfo

/**
 * The `MacOsInfoImpl` class is useful to provide the information about the current macOS device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
class MacOsInfoImpl : MacOsInfo {

    /**
     * `operatingSystem` the information about the current macOS operating system
     *
     * @since 1.1.0
     */
    override val operatingSystem: MacOsOperatingSystem
        get() = MacOsOperatingSystemImpl(
            processInfo = NSProcessInfo.processInfo
        )

    /**
     * `hardware` the information about the current macOS hardware
     */
    override val hardware: MacOsHardware
        get() = TODO("Not yet implemented")
}