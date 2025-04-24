package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.operatingsystem.*
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.State
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.InternetProtocolStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.NetworkParams
import com.tecknobit.kinfo.model.operatingsystem.processes.OSProcessImpl
import com.tecknobit.kinfo.model.operatingsystem.processes.OSThreadImpl
import com.tecknobit.kinfo.model.operatingsystem.protocols.InternetProtocolStatsImpl
import com.tecknobit.kinfo.model.operatingsystem.protocols.NetworkParamsImpl
import oshi.SystemInfo
import oshi.driver.windows.registry.InstalledAppsData
import oshi.util.ProcUtil

/**
 * `OperatingSystemImpl` is the implementation of the `OperatingSystem` interface.
 * It uses the `oshi` library to gather system operating information and map
 * this information to the classes and methods provided by our API.
 *
 * @param systemInfo The object containing general information about the operating system.
 */
class OperatingSystemImpl(
    systemInfo: SystemInfo
) : OperatingSystem {

    /**
     * A lazy-initialized property that holds the information about the operating system.
     * This property is retrieved from the `systemInfo` object and represents the operating system details
     * that include file system information, version, network statistics, and more.
     *
     * It is initialized only when it is accessed for the first time, which can help improve performance
     * by deferring the creation of the `operatingSystem` object until it's actually needed.
     */
    private val fileSystemInfo by lazy { systemInfo.operatingSystem }

    /**
     * `family` The family of the operating system (e.g., "Windows", "Linux").
     * This property represents the type of operating system.
     */
    override val family: String = fileSystemInfo.family

    /**
     * `manufacturer` The manufacturer of the operating system (e.g., "Microsoft", "Apple").
     */
    override val manufacturer: String = fileSystemInfo.manufacturer

    /**
     * `versionInfo` The version information of the operating system.
     */
    override val versionInfo: OSVersionInfo by lazy {
        OSVersionInfoImpl(
            osVersionInfo = fileSystemInfo.versionInfo
        )
    }

    /**
     * `fileSystem` The file system information, including the list of file stores and other statistics.
     */
    override val fileSystem: FileSystem by lazy {
        FileSystemImpl(
            fileSystemInfo = fileSystemInfo.fileSystem
        )
    }

    /**
     * `internetProtocolStats` The statistics related to network protocols (TCP/UDP).
     */
    override val internetProtocolStats: InternetProtocolStats by lazy {
        InternetProtocolStatsImpl(
            internetProtocolStatsInfo = fileSystemInfo.internetProtocolStats
        )
    }

    /**
     * `processId` The ID of the current process.
     */
    override val processId: Int = fileSystemInfo.processId

    /**
     * `currentProcess` The current running process.
     */
    override val currentProcess: OSProcess
        get() = initOSProcess(source = fileSystemInfo.currentProcess)

    /**
     * `processCount` The number of active processes running on the system.
     */
    override val processCount: Int = fileSystemInfo.processCount

    /**
     * `threadId` The ID of the current thread.
     */
    override val threadId: Int = fileSystemInfo.threadId

    /**
     * `currentThread` The current running thread.
     */
    override val currentThread: OSThread
        get() = initOSThread(source = fileSystemInfo.currentThread)

    /**
     * `threadCount` The number of threads running on the system.
     */
    override val threadCount: Int = fileSystemInfo.threadCount

    /**
     * `bitness` The bitness of the operating system (e.g., 32-bit, 64-bit).
     */
    override val bitness: Int = fileSystemInfo.bitness

    /**
     * `systemUptime` The system uptime in milliseconds.
     */
    override val systemUptime: Long
        get() = fileSystemInfo.systemUptime

    /**
     * `systemBootTime` The system boot time in milliseconds (UNIX timestamp).
     */
    override val systemBootTime: Long
        get() = fileSystemInfo.systemBootTime

    /**
     * `isElevated` Indicates if the operating system is running in elevated (privileged) mode.
     */
    override val isElevated: Boolean
        get() = fileSystemInfo.isElevated

    /**
     * `networkParams` The network parameters, including hostname, DNS, gateway.
     */
    override val networkParams: NetworkParams by lazy {
        NetworkParamsImpl(
            networkParamsInfo = fileSystemInfo.networkParams
        )
    }

    /**
     * `services` The list of active services on the operating system.
     */
    override val services: List<OSService>
        get() = loadOSServices(
            sourceList = fileSystemInfo.services
        )

    /**
     * `sessions` The list of user sessions active on the operating system.
     */
    override val sessions: List<OSSession>
        get() = loadOSSessions(
            sourceList = fileSystemInfo.sessions
        )

    /**
     * Retrieves the list of all active processes on the system.
     *
     * @return A list of `OSProcess` objects representing the running processes.
     */
    @Bridge
    override fun getProcesses(): List<OSProcess> {
        return loadOSProcesses(
            sourceList = fileSystemInfo.processes
        )
    }

    /**
     * Retrieves the list of processes specified by their PIDs.
     *
     * @param pids A collection of PIDs of the processes to retrieve.
     * @return A list of `OSProcess` objects for the specified processes.
     */
    @Bridge
    override fun getProcesses(
        pids: Collection<Int>
    ): List<OSProcess> {
        return loadOSProcesses(
            sourceList = fileSystemInfo.getProcesses(pids)
        )
    }

    /**
     * Retrieves a single process by its PID.
     *
     * @param pid The PID of the process to retrieve.
     * @return An `OSProcess` object representing the process with the given PID.
     */
    @Bridge
    override fun getProcess(
        pid: Int
    ): OSProcess {
        return initOSProcess(
            source = fileSystemInfo.getProcess(pid)
        )
    }

    /**
     * Retrieves the visible desktop windows of the operating system.
     *
     * @param visibleOnly If `true`, only returns visible windows.
     * @return A list of desktop windows of the operating system.
     */
    @Bridge
    override fun getOSDesktopWindows(
        visibleOnly: Boolean
    ): List<OSDesktopWindow> {
        return loadOSDesktopWindows(
            sourceList = fileSystemInfo.getDesktopWindows(visibleOnly)
        )
    }

    /**
     * Loads a list of processes from a list of OSHI `OSProcess` objects.
     *
     * @param sourceList The list of processes to load.
     * @return A list of `OSProcess` objects.
     */
    private fun loadOSProcesses(
        sourceList: List<oshi.software.os.OSProcess>
    ): List<OSProcess> {
        val result = mutableListOf<OSProcess>()
        sourceList.forEach { process ->
            result.add(initOSProcess(source = process))
        }
        return result
    }

    /**
     * Initializes an `OSProcess` object from an OSHI `OSProcess` object.
     *
     * @param source The `OSProcess` object to initialize the new `OSProcess` from.
     * @return A configured `OSProcess` object.
     */
    private fun initOSProcess(
        source: oshi.software.os.OSProcess
    ): OSProcess {
        return OSProcessImpl(
            name = source.name,
            path = source.path,
            commandLine = source.commandLine,
            arguments = source.arguments,
            environmentVariables = source.environmentVariables,
            currentWorkingDirectory = source.currentWorkingDirectory,
            user = source.user,
            userId = source.userID,
            group = source.group,
            groupId = source.groupID,
            state = State.valueOf(source.state.name),
            processId = source.processID,
            parentProcessId = source.parentProcessID,
            threadCount = source.threadCount,
            priority = source.priority,
            virtualSize = source.virtualSize,
            residentSetSize = source.residentSetSize,
            kernelTime = source.kernelTime,
            userTime = source.userTime,
            startTime = source.startTime,
            bytesRead = source.bytesRead,
            bytesWritten = source.bytesWritten,
            openFiles = source.openFiles,
            softOpenFileLimit = source.softOpenFileLimit,
            hardOpenFileLimit = source.hardOpenFileLimit,
            processCpuLoadCumulative = source.processCpuLoadCumulative,
            processCpuLoadBetweenTicks = source.getProcessCpuLoadBetweenTicks(source),
            bitness = source.bitness,
            affinityMask = source.affinityMask,
            updateAttributes = source.updateAttributes(),
            threadDetails = loadOSThreads(sourceList = source.threadDetails),
            minorFaults = source.minorFaults,
            majorFaults = source.majorFaults,
            contextSwitches = source.contextSwitches
        )
    }

    /**
     * Loads a list of threads from a list of OSHI `OSThread` objects.
     *
     * @param sourceList The list of threads to load.
     * @return A list of `OSThread` objects.
     */
    private fun loadOSThreads(
        sourceList: List<oshi.software.os.OSThread>
    ): List<OSThread> {
        val result = mutableListOf<OSThread>()
        sourceList.forEach { thread ->
            result.add(initOSThread(source = thread))
        }
        return result
    }

    /**
     * Initializes an `OSThread` object from an OSHI `OSThread` object.
     *
     * @param source The `OSThread` object to initialize the new `OSThread` from.
     * @return A configured `OSThread` object.
     */
    private fun initOSThread(
        source: oshi.software.os.OSThread
    ): OSThread {
        return OSThreadImpl(
            threadId = source.threadId,
            name = source.name,
            state = State.valueOf(source.state.name),
            threadCpuLoadCumulative = source.threadCpuLoadCumulative,
            threadCpuLoadBetweenTicks = source.getThreadCpuLoadBetweenTicks(source),
            owningProcessId = source.threadId,
            startMemoryAddress = source.startMemoryAddress,
            contextSwitches = source.contextSwitches,
            minorFaults = source.minorFaults,
            majorFaults = source.majorFaults,
            kernelTime = source.kernelTime,
            userTime = source.userTime,
            upTime = source.upTime,
            startTime = source.startTime,
            priority = source.priority,
            updateAttributes = source.updateAttributes()
        )
    }

    /**
     * Loads a list of services from a list of OSHI `OSService` objects.
     *
     * @param sourceList The list of services to load.
     * @return A list of `OSService` objects.
     */
    private fun loadOSServices(
        sourceList: List<oshi.software.os.OSService>
    ): List<OSService> {
        val result = mutableListOf<OSService>()
        sourceList.forEach { service ->
            result.add(
                OSServiceImpl(
                    name = service.name,
                    processID = service.processID,
                    state = ServiceState.valueOf(service.state.name)
                )
            )
        }
        return result
    }

    /**
     * Loads a list of sessions from a list of OSHI `OSSession` objects.
     *
     * @param sourceList The list of sessions to load.
     * @return A list of `OSSession` objects.
     */
    private fun loadOSSessions(
        sourceList: List<oshi.software.os.OSSession>
    ): List<OSSession> {
        val result = mutableListOf<OSSession>()
        sourceList.forEach { session ->
            result.add(
                OSSessionImpl(
                    userName = session.userName,
                    terminalDevice = session.terminalDevice,
                    loginTime = session.loginTime,
                    host = session.host
                )
            )
        }
        return result
    }

    /**
     * Loads a list of `OSDesktopWindow` objects from the provided list of `oshi.software.os.OSDesktopWindow`.
     *
     * This function iterates through the `sourceList` of desktop windows and maps each item to a new
     * `OSDesktopWindowImpl` instance, which encapsulates details about the window such as its ID, title,
     * command, owning process ID, order, and visibility.
     *
     * @param sourceList A list of `oshi.software.os.OSDesktopWindow` objects to be mapped.
     * @return A list of `OSDesktopWindow` objects, representing the desktop windows.
     */
    private fun loadOSDesktopWindows(
        sourceList: List<oshi.software.os.OSDesktopWindow>
    ): List<OSDesktopWindow> {
        val result = mutableListOf<OSDesktopWindow>()
        sourceList.forEach { desktopWindow ->
            result.add(
                OSDesktopWindowImpl(
                    windowId = desktopWindow.windowId,
                    title = desktopWindow.title,
                    command = desktopWindow.command,
                    owningProcessId = desktopWindow.owningProcessId,
                    order = desktopWindow.order,
                    visible = desktopWindow.isVisible
                )
            )
        }
        return result
    }

    /**
     * Parses `/proc` files with a given structure consisting of a keyed header line followed by a keyed value line.
     *
     * Examples of such files include `/proc/net/netstat` and `/proc/net/snmp`.
     * The returned map has the structure:
     *
     * ```
     * {
     *     "TcpExt": {"SyncookiesSent": 0, "SyncookiesRecv": 4, "SyncookiesFailed": 0, ... },
     *     "IpExt": {"InNoRoutes": 55, "InTruncatedPkts": 0, "InMcastPkts": 27786, "OutMcastPkts": 1435, ... },
     *     "MPTcpExt": {"MPCapableSYNRX": 0, "MPCapableSYNTX": 0, "MPCapableSYNACKRX": 0, ... }
     * }
     * ```
     *
     * Example input file structure:
     * ```
     * TcpExt: SyncookiesSent SyncookiesRecv SyncookiesFailed ...
     * TcpExt: 0 4 0 ...
     * IpExt: InNoRoutes InTruncatedPkts InMcastPkts OutMcastPkts ...
     * IpExt: 55 0 27786 1435 ...
     * MPTcpExt: MPCapableSYNRX MPCapableSYNTX MPCapableSYNACKRX ...
     * MPTcpExt: 0 0 0 ...
     * ```
     *
     * @param procFile The file to process
     * @param keys Optional array of keys to include in the outer map. If not provided, all keys found in the file will be returned
     *
     * @return map of keys to their corresponding stats
     */
    @Bridge
    override fun parseNestedStatistics(
        procFile: String,
        vararg keys: String,
    ): Map<String, Map<String, Long>> {
        return ProcUtil.parseNestedStatistics(procFile, *keys)
    }

    /**
     * Parses `/proc` files formatted as "statistic (long)value" to produce a simple mapping.
     *
     * An example file like `/proc/net/snmp6` might contain content in the following format:
     *
     * ```
     * Ip6InReceives             8026
     * Ip6InHdrErrors            0
     * Icmp6InMsgs               2
     * Icmp6InErrors             0
     * Icmp6OutMsgs              424
     * Udp6IgnoredMulti          5
     * Udp6MemErrors             1
     * UdpLite6InDatagrams       37
     * UdpLite6NoPorts           1
     * ```
     *
     * This would produce a mapping structure like:
     *
     * ```
     * {
     *     "Ip6InReceives": 8026,
     *     "Ip6InHdrErrors": 0,
     *     "Icmp6InMsgs": 2,
     *     "Icmp6InErrors": 0,
     *     ...
     * }
     * ```
     *
     * @param procFile The file to process
     * @param separator A regular expression specifying the separator between the statistic name and its value
     *
     * @return A map of statistics and their associated values.
     */
    @Bridge
    override fun parseStatistics(
        procFile: String,
        separator: Regex,
    ): Map<String, Long> {
        return ProcUtil.parseStatistics(procFile, separator.toPattern())
    }

    @Bridge
    override fun queryInstalledApps(): List<ApplicationInfo> {
        val installedApps = InstalledAppsData.queryInstalledApps()
        val applications = mutableListOf<ApplicationInfoImpl>()
        installedApps.forEach { application ->
            applications.add(
                ApplicationInfoImpl(
                    name = application.name,
                    version = application.version,
                    vendor = application.vendor,
                    timestamp = application.timestamp,
                    additionalInfo = application.additionalInfo
                )
            )
        }
        return applications
    }

}
