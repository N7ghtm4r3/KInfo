@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.State
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.UdpStats
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.*
import com.tecknobit.kinfo.utils.toNSString
import kotlinx.cinterop.*
import kotlinx.cinterop.ByteVar
import platform.AppKit.NSRunningApplication
import platform.CoreFoundation.CFArrayGetCount
import platform.CoreFoundation.CFArrayGetValueAtIndex
import platform.CoreGraphics.*
import platform.Foundation.NSDictionary
import platform.Foundation.NSNumber
import platform.Foundation.NSOperatingSystemVersion
import platform.Foundation.NSProcessInfo
import platform.darwin.*
import platform.osx.*
import platform.posix.*

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
                    loginTime = ((utTv.tv_sec * 1000L) + (utTv.tv_usec / 1000L)),
                    host = utmpx.ut_host.toKString()
                )
            }
        } finally {
            endutxent()
        }
    }

    @Loader
    private fun loadOsProcess(): MacOsOSProcess {
        return memScoped {
            val buffer = alloc<proc_taskallinfo>()
            val bufferSize = sizeOf<proc_taskallinfo>().toInt()

            val result = proc_pidinfo(
                pid = getpid(),
                flavor = PROC_PIDTASKALLINFO,
                arg = 0uL,
                buffer = buffer.ptr,
                buffersize = bufferSize
            )
            if (result != bufferSize)
                throw IllegalStateException("Cannot read proc pid info")

            val pbsd = buffer.pbsd
            val ptinfo = buffer.ptinfo
            val processId = pbsd.pbi_pid.toInt()
            val arguments = resolveArguments()
            val userId = pbsd.pbi_uid
            val groupId = pbsd.pbi_gid
            MacOsOsProcessImpl(
                name = pbsd.pbi_name.toKString(),
                path = resolveProcessPath(
                    processId = processId
                ),
                commandLine = arguments[0],
                arguments = arguments,
                environmentVariables = resolveEnvironmentVariables(),
                currentWorkingDirectory = resolveCurrentWorkingDirectory(),
                user = resolveUser(
                    userId = userId
                ),
                userId = userId.toString(),
                group = resolveGroup(
                    groupId = groupId
                ),
                groupId = groupId.toString(),
                state = resolveProcessState(
                    status = pbsd.pbi_status
                ),
                processId = processId,
                parentProcessId = pbsd.pbi_ppid.toInt(),
                threadCount = ptinfo.pti_threadnum,
                priority = ptinfo.pti_priority,
                virtualSize = ptinfo.pti_virtual_size.toLong(),
                residentMemory = ptinfo.pti_resident_size.toLong(),
                privateResidentMemory = 0L,
                kernelTime = ptinfo.pti_total_system.toLong(),
                userTime = ptinfo.pti_total_user.toLong(),
                startTime = (pbsd.pbi_start_tvsec + pbsd.pbi_start_tvusec).toLong(),
                bytesRead = 0L,
                bytesWritten = 0L,
                openFiles = pbsd.pbi_nfiles.toLong(),
                softOpenFileLimit = 0L,
                hardOpenFileLimit = 0L,
                processCpuLoadCumulative = 1.0,
                processCpuLoadBetweenTicks = 1.0,
                bitness = pbsd.pbi_flags.toInt(),
                affinityMask = 0L,
                updateAttributes = false,
                threadDetails = emptyList(),
                minorFaults = ptinfo.pti_faults.toLong(),
                majorFaults = ptinfo.pti_pageins.toLong(),
                contextSwitches = ptinfo.pti_csw.toLong(),
                voluntaryContextSwitches = 0L,
                involuntaryContextSwitches = 0L
            )
        }
    }

    private fun resolveProcessPath(
        processId: Int
    ): String {
        return memScoped {
            val bufferSize = PROC_PIDPATHINFO_MAXSIZE
            val buffer = allocArray<ByteVar>(
                length = bufferSize
            )

            val actual = proc_pidpath(
                pid = processId,
                buffer = buffer,
                buffersize = bufferSize.toUInt()
            )
            if (actual <= 0)
                return ""

            buffer.toKString()
        }
    }

    private fun resolveArguments(): List<String> {
        return processInfo.arguments.map { argument ->
            argument.toString()
        }
    }

    private fun resolveEnvironmentVariables(): Map<String, String> {
        val nativeEnvironmentVariables = processInfo.environment

        return buildMap {
            nativeEnvironmentVariables.forEach { (key, value) ->
                put(key.toString(), value.toString())
            }
        }
    }

    private fun resolveCurrentWorkingDirectory(): String {
        return memScoped {
            val bufferSize = PATH_MAX
            val buffer = allocArray<ByteVar>(bufferSize)

            val result = getcwd(
                buffer,
                bufferSize.toULong()
            ) ?: return@memScoped ""

            result.toKString()
        }
    }

    private fun resolveUser(
        userId: UInt
    ): String {
        val result = getpwuid(userId)?.pointed ?: return ""
        val name = result.pw_name?.toKString()

        return name.orEmpty()
    }

    private fun resolveGroup(
        groupId: UInt
    ): String {
        val result = getgrgid(groupId)?.pointed ?: return ""
        val name = result.gr_name?.toKString()

        return name.orEmpty()
    }

    private fun resolveProcessState(
        status: UInt
    ): State {
        return when (status.toInt()) {
            SIDL -> State.NEW
            SRUN -> State.RUNNING
            SSLEEP -> State.SLEEPING
            SSTOP -> State.STOPPED
            SZOMB -> State.ZOMBIE
            else -> State.OTHER
        }
    }

}
