@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSDesktopWindow
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSFileStore
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSVersionInfo
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSOperatingSystemVersion
import platform.Foundation.NSProcessInfo

/**
 * The `MacOsOperatingSystemImpl` class is useful to provide details about the current macOS operating system
 *
 * @property processInfo The process information used to retrieve the operating system details
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
data class MacOsOperatingSystemImpl(
    private val processInfo: NSProcessInfo
) : MacOsOperatingSystem {

    /**
     * `operatingSystemVersion` the version information of the current macOS operating system
     */
    override val operatingSystemVersion: OSVersionInfo
        get() = loadOperatingSystemVersion(
            operatingSystemVersion = processInfo.operatingSystemVersion
        )

    /**
     * `statfs` the macOS file store information
     */
    override val statfs: OSFileStore
        get() = TODO("Not yet implemented")

    /**
     * `cgWindowId` the macOS desktop window information
     */
    override val cgWindowId: OSDesktopWindow
        get() = TODO("Not yet implemented")

    /**
     * `utmpx` the macOS session information
     */
    override val utmpx: OSSession
        get() = TODO("Not yet implemented")

    /**
     * `procTaskAllInfo` the macOS process information
     */
    override val procTaskAllInfo: OSProcess
        get() = TODO("Not yet implemented")

    /**
     * `procThreadInfo` the macOS process thread information
     */
    override val procThreadInfo: OSThread
        get() = TODO("Not yet implemented")

    /**
     * `socketFdInfo` the macOS `IP` connection information
     */
    override val socketFdInfo: IPConnection
        get() = TODO("Not yet implemented")

    /**
     * `rtMsgHdr2` the macOS `IP` route information
     */
    override val rtMsgHdr2: IPRoute
        get() = TODO("Not yet implemented")

    /**
     * `tcpStat` the macOS `TCP` statistics
     */
    override val tcpStat: TcpStats
        get() = TODO("Not yet implemented")

    /**
     * `udpStat` the macOS `UDP` statistics
     */
    override val udpStat: UdpStats
        get() = TODO("Not yet implemented")

    /**
     * Method used to load the macOS operating system version information
     *
     * @param operatingSystemVersion The native operating system version to load
     *
     * @return the loaded version information as [MacOsVersionInfoImpl]
     */
    @Loader
    private fun loadOperatingSystemVersion(
        operatingSystemVersion: CValue<NSOperatingSystemVersion>
    ): MacOsVersionInfoImpl {
        return MacOsVersionInfoImpl(
            operatingSystemVersion = operatingSystemVersion
        )
    }

}