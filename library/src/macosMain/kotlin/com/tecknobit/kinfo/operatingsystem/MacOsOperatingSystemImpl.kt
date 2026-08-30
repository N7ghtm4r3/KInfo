@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.MacOsDesktopWindowMapper
import com.tecknobit.kinfo.mappers.MacOsOSProcessMapper
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.UdpStats
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.*
import com.tecknobit.kinfo.utils.resolveCumulativeTime
import kotlinx.cinterop.*
import platform.CoreFoundation.CFArrayGetCount
import platform.CoreFoundation.CFArrayGetValueAtIndex
import platform.CoreGraphics.CGWindowListCopyWindowInfo
import platform.CoreGraphics.kCGNullWindowID
import platform.CoreGraphics.kCGWindowListOptionAll
import platform.CoreGraphics.kCGWindowListOptionOnScreenOnly
import platform.Foundation.NSOperatingSystemVersion
import platform.Foundation.NSProcessInfo
import platform.darwin.USER_PROCESS
import platform.darwin.endutxent
import platform.darwin.getutxent
import platform.darwin.setutxent
import platform.osx.statfs

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
    override val operatingSystemVersion: MacOsVersionInfo
        get() = loadOperatingSystemVersion(
            operatingSystemVersion = processInfo.operatingSystemVersion
        )

    /**
     * `statfs` the macOS file store information
     */
    override val statfs: MacOsFileStore
        get() = loadStatFs()

    /**
     * `visibleWindows` the desktop windows currently visible on macOS
     */
    override val visibleWindows: List<MacOsDesktopWindow>
        get() = loadVisibleWindows()

    /**
     * `allWindows` the desktop windows available on macOS
     */
    override val allWindows: List<MacOsDesktopWindow>
        get() = loadAllWindows()

    /**
     * `utmpx` the first active macOS user session available in the `utmpx` records
     */
    override val utmpx: MacOsOsSession
        get() = loadUserTemporaryExtended()

    /**
     * `procTaskAllInfo` the macOS process information
     */
    override val procTaskAllInfo: MacOsOSProcess
        get() = loadOsProcess()

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

    /**
     * Method used to load the root macOS file store information
     *
     * @return the loaded file store information as [MacOsFileStoreImpl]
     */
    @Loader
    private fun loadStatFs(): MacOsFileStoreImpl {
        return memScoped {
            val statfsBuffer = alloc<statfs>()
            if (statfs("/", statfsBuffer.ptr) != 0)
                error("Error during statfs reading")

            val statfs = statfsBuffer.readValue()
            statfs.useContents {
                MacOsFileStoreImpl(
                    statfs = this
                )
            }
        }
    }

    /**
     * Method used to load the currently visible macOS desktop windows
     *
     * @return the visible desktop windows as [List] of [MacOsDesktopWindow]
     */
    @Loader
    private fun loadVisibleWindows(): List<MacOsDesktopWindow> {
        return loadWindows(
            visibleOnly = true
        )
    }

    /**
     * Method used to load all the available macOS desktop windows
     *
     * @return the available desktop windows as [List] of [MacOsDesktopWindow]
     */
    @Loader
    private fun loadAllWindows(): List<MacOsDesktopWindow> {
        return loadWindows(
            visibleOnly = false
        )
    }

    /**
     * Method used to load the macOS desktop windows according to their visibility
     *
     * @param visibleOnly Whether to load only currently visible desktop windows
     *
     * @return the loaded desktop windows as [List] of [MacOsDesktopWindow]
     */
    @Loader
    private fun loadWindows(
        visibleOnly: Boolean
    ): List<MacOsDesktopWindow> {
        val rawWindows = CGWindowListCopyWindowInfo(
            option = if (visibleOnly)
                kCGWindowListOptionOnScreenOnly
            else
                kCGWindowListOptionAll,
            relativeToWindow = kCGNullWindowID
        )
        val count = CFArrayGetCount(rawWindows)

        val windows = mutableListOf<MacOsDesktopWindow>()
        for (index in 0 until count) {
            val element = CFArrayGetValueAtIndex(
                theArray = rawWindows,
                idx = index
            )

            val macOsDesktopWindowMapper = MacOsDesktopWindowMapper(
                pointer = element,
                index = index
            )

            val windowImpl = macOsDesktopWindowMapper.mapFromNative()
            windows.add(windowImpl)
        }

        return windows
    }

    /**
     * Method used to load the first active macOS user session from the `utmpx` records
     *
     * @return the loaded macOS user session as [MacOsOsSession]
     */
    @Loader
    private fun loadUserTemporaryExtended(): MacOsOsSession {
        setutxent()

        try {
            while (true) {
                val utmpx = getutxent()?.pointed ?: error("Cannot read utmpx")
                if (utmpx.ut_type.toInt() != USER_PROCESS)
                    continue

                val utTv = utmpx.ut_tv
                return MacOsOSSessionImpl(
                    userName = utmpx.ut_user.toKString(),
                    terminalDevice = utmpx.ut_line.toKString(),
                    loginTime = resolveCumulativeTime(
                        seconds = utTv.tv_sec,
                        microseconds = utTv.tv_usec
                    ),
                    host = utmpx.ut_host.toKString()
                )
            }
        } finally {
            endutxent()
        }
    }

    @Loader
    private fun loadOsProcess(): MacOsOSProcess {
        val macOsOSProcessMapper = MacOsOSProcessMapper(
            processInfo = processInfo
        )

        return macOsOSProcessMapper.mapFromNative()
    }

}
