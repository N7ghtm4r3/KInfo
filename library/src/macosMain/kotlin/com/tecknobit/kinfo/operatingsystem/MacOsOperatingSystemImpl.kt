@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsDesktopWindow
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsFileStore
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsVersionInfo
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats
import com.tecknobit.kinfo.utils.toNSString
import kotlinx.cinterop.*
import platform.AppKit.NSRunningApplication
import platform.CoreFoundation.CFArrayGetCount
import platform.CoreFoundation.CFArrayGetValueAtIndex
import platform.CoreGraphics.*
import platform.Foundation.NSDictionary
import platform.Foundation.NSNumber
import platform.Foundation.NSOperatingSystemVersion
import platform.Foundation.NSProcessInfo
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

            val windowImpl = element.toMacOsDesktopWindow(
                index = index
            )
            windows.add(windowImpl)
        }

        return windows
    }

    /**
     * Method used to convert native macOS window information into [MacOsDesktopWindowImpl]
     *
     * @receiver The native pointer containing the desktop window information
     * @param index The position of the desktop window in the stacking order
     *
     * @return the desktop window information as [MacOsDesktopWindowImpl]
     */
    private fun COpaquePointer?.toMacOsDesktopWindow(
        index: Long
    ): MacOsDesktopWindowImpl {
        val dictionary = interpretObjCPointer<NSDictionary>(this.rawValue)

        val windowId = dictionary.objectForKey(kCGWindowNumber.toNSString()) as? NSNumber
        val title = dictionary.objectForKey(kCGWindowOwnerName.toNSString()) as? String
        val owningProcessIdRaw = dictionary.objectForKey(kCGWindowOwnerPID.toNSString()) as? NSNumber
        val owningProcessId = owningProcessIdRaw?.longValue ?: -1
        val visible = dictionary.objectForKey(kCGWindowIsOnscreen.toNSString()) as? NSNumber

        return MacOsDesktopWindowImpl(
            windowId = windowId?.longValue ?: -1,
            title = title.orEmpty(),
            command = resolveCommandFor(
                pid = owningProcessId
            ),
            owningProcessId = owningProcessId,
            order = index.toInt(),
            visible = visible?.boolValue ?: false
        )
    }

    /**
     * Method used to resolve the executable command associated with a process identifier
     *
     * @param pid The identifier of the process owning the desktop window
     *
     * @return the executable command associated with the process as [String]
     */
    private fun resolveCommandFor(
        pid: Long
    ): String {
        val nsRunningApplication = NSRunningApplication.runningApplicationWithProcessIdentifier(
            pid = pid.toInt()
        )

        val executableURL = nsRunningApplication?.executableURL
        return executableURL?.path.orEmpty()
    }

}
