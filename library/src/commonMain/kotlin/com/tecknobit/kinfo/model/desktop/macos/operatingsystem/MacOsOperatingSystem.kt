package com.tecknobit.kinfo.model.desktop.macos.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats

/**
 * The `MacOsOperatingSystem` interface defines the contract to provide macOS operating system details
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsOperatingSystem {

    /**
     * `operatingSystemVersion` the version information of the current macOS operating system
     */
    val operatingSystemVersion: MacOsVersionInfo

    /**
     * `statfs` the file store information of the current macOS operating system
     */
    val statfs: MacOsFileStore

    /**
     * `visibleWindows` the desktop windows currently visible on macOS
     */
    val visibleWindows: List<MacOsDesktopWindow>

    /**
     * `allWindows` the desktop windows available on macOS
     */
    val allWindows: List<MacOsDesktopWindow>

    /**
     * `utmpx` the current macOS session information
     */
    val utmpx: OSSession

    /**
     * `procTaskAllInfo` the current macOS process information
     */
    val procTaskAllInfo: OSProcess

    /**
     * `procThreadInfo` the current macOS thread information
     */
    val procThreadInfo: OSThread

    /**
     * `socketFdInfo` the current macOS `IP` connection information
     */
    val socketFdInfo: IPConnection

    /**
     * `rtMsgHdr2` the current macOS `IP` route information
     */
    val rtMsgHdr2: IPRoute

    /**
     * `tcpStat` the current macOS `TCP` statistics
     */
    val tcpStat: TcpStats

    /**
     * `udpStat` the current macOS `UDP` statistics
     */
    val udpStat: UdpStats
}
