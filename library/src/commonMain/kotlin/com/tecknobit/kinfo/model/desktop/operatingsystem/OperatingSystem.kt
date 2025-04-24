package com.tecknobit.kinfo.model.desktop.operatingsystem

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.InternetProtocolStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.NetworkParams

/**
 * `OperatingSystem` Represents an operating system and its key attributes, such as processes, file system,
 * network parameters, system uptime, and more.
 *
 * Provides methods to interact with processes, file system, and system services.
 *
 * @author N7ghtm4r3
 */
interface OperatingSystem {

    /**
     * `family` The family or type of the operating system (e.g., "Windows", "Linux").
     */
    val family: String

    /**
     * `manufacturer` The manufacturer of the operating system (e.g., "Microsoft", "Apple").
     */
    val manufacturer: String

    /**
     * `versionInfo` The version information of the operating system, including version number, build, etc.
     */
    val versionInfo: OSVersionInfo

    /**
     * `fileSystem` The file system information of the operating system, including file stores and descriptors.
     */
    val fileSystem: FileSystem

    /**
     * `internetProtocolStats` The internet protocol statistics related to network connections,
     * including TCP and UDP statistics and IP connections.
     */
    val internetProtocolStats: InternetProtocolStats

    /**
     * `processId` The process ID of the currently running operating system process.
     */
    val processId: Int

    /**
     * `currentProcess` The currently running process of the operating system.
     */
    val currentProcess: OSProcess

    /**
     * `processCount` The total number of processes running on the operating system.
     */
    val processCount: Int

    /**
     * `threadId` The thread ID of the currently running thread.
     */
    val threadId: Int

    /**
     * `currentThread` The currently running thread of the operating system.
     */
    val currentThread: OSThread

    /**
     * `threadCount` The total number of threads running on the operating system.
     */
    val threadCount: Int

    /**
     * `bitness` The bitness of the operating system (e.g., 32-bit, 64-bit).
     */
    val bitness: Int

    /**
     * `systemUptime` The system uptime in milliseconds since the operating system started.
     */
    val systemUptime: Long

    /**
     * `systemBootTime` The time in milliseconds when the system was last booted (Unix timestamp).
     */
    val systemBootTime: Long

    /**
     * `isElevated` A flag indicating whether the operating system is running with elevated privileges (e.g., as an administrator).
     */
    val isElevated: Boolean

    /**
     * `networkParams` The network parameters of the operating system, including host name, domain name,
     * DNS servers, and default gateways.
     */
    val networkParams: NetworkParams

    /**
     * `services` The list of services running on the operating system.
     */
    val services: List<OSService>

    /**
     * `sessions` The list of user sessions currently active on the operating system.
     */
    val sessions: List<OSSession>

    /**
     * Retrieves the list of all running processes on the operating system.
     *
     * @return A list of all `OSProcess` objects representing the running processes.
     */
    @Bridge
    fun getProcesses(): List<OSProcess>

    /**
     * Retrieves the list of processes specified by their process IDs.
     *
     * @param pids A collection of process IDs to fetch the processes.
     * @return A list of `OSProcess` objects corresponding to the given process IDs.
     */
    @Bridge
    fun getProcesses(
        pids: Collection<Int>
    ): List<OSProcess>

    /**
     * Retrieves a single process by its process ID.
     *
     * @param pid The process ID of the process to retrieve.
     * @return The `OSProcess` object corresponding to the given process ID.
     */
    @Bridge
    fun getProcess(
        pid: Int
    ): OSProcess

    /**
     * Retrieves the desktop windows of the operating system.
     *
     * @param visibleOnly A flag indicating whether to retrieve only the visible desktop windows (`true`)
     *                    or all desktop windows (`false`).
     * @return A list of `OSDesktopWindow` objects representing the desktop windows.
     */
    @Bridge
    fun getOSDesktopWindows(
        visibleOnly: Boolean
    ): List<OSDesktopWindow>

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
     * @param procFile The file to process.
     * @param keys Optional array of keys to include in the outer map. If not provided, all keys found in the file will be returned
     *
     * @return map of keys to their corresponding stats
     */
    @Bridge
    fun parseNestedStatistics(
        procFile: String,
        vararg keys: String
    ): Map<String, Map<String, Long>>

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
    fun parseStatistics(
        procFile: String,
        separator: Regex = Regex("\\s+"),
    ): Map<String, Long>

}
