@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo

import com.tecknobit.kinfo.hardware.MacOsHardwareImpl
import com.tecknobit.kinfo.model.desktop.macos.MacOsInfo
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHardware
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem
import com.tecknobit.kinfo.operatingsystem.MacOsOperatingSystemImpl
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSProcessInfo

/**
 * The `MacOsInfoImpl` class is useful to provide the information about the current macOS device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsInfo
 *
 * @since 1.1.0
 */
class MacOsInfoImpl : MacOsInfo {

    /**
     * `operatingSystem` the operating system information provided by a new implementation on each access
     */
    override val operatingSystem: MacOsOperatingSystem
        get() = MacOsOperatingSystemImpl(
            processInfo = NSProcessInfo.processInfo
        )

    /**
     * `hardware` the hardware information provider created on each access
     */
    override val hardware: MacOsHardware
        get() = MacOsHardwareImpl()
}