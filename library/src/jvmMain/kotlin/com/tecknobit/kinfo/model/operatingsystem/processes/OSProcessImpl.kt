package com.tecknobit.kinfo.model.operatingsystem.processes

import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.State

/**
 * `OSProcessImpl` The implementation of the `OSProcess` interface.
 * This class provides details about a specific process in the operating system, including its name, ID, state,
 * resource usage, and various performance metrics.
 *
 * @param name The name of the process (e.g., "java", "nginx").
 * @param path The path to the executable of the process.
 * @param commandLine The full command line that was used to start the process.
 * @param arguments The arguments passed to the process when it was started.
 * @param environmentVariables A map of environment variables used by the process.
 * @param currentWorkingDirectory The current working directory of the process.
 * @param user The user running the process.
 * @param userId The ID of the user running the process.
 * @param group The group associated with the process.
 * @param groupId The ID of the group associated with the process.
 * @param state The state of the process (e.g., running, sleeping).
 * @param processId The unique identifier for the process.
 * @param parentProcessId The process ID of the parent process.
 * @param threadCount The number of threads in the process.
 * @param priority The priority of the process.
 * @param virtualSize The virtual memory size of the process, in bytes.
 * @param residentSetSize The resident set size of the process, in bytes (physical memory used).
 * @param kernelTime The amount of time the process has spent in kernel mode, in milliseconds.
 * @param userTime The amount of time the process has spent in user mode, in milliseconds.
 * @param startTime The time when the process started, in milliseconds since the Unix epoch.
 * @param bytesRead The number of bytes read by the process.
 * @param bytesWritten The number of bytes written by the process.
 * @param openFiles The number of open files used by the process.
 * @param softOpenFileLimit The soft limit on the number of files the process can open.
 * @param hardOpenFileLimit The hard limit on the number of files the process can open.
 * @param processCpuLoadCumulative The cumulative CPU load of the process as a percentage.
 * @param processCpuLoadBetweenTicks The CPU load of the process between two ticks, as a percentage.
 * @param bitness The bitness of the process (e.g., 32-bit, 64-bit).
 * @param affinityMask The CPU affinity mask for the process.
 * @param updateAttributes A flag indicating whether the process attributes should be updated.
 * @param threadDetails The list of threads associated with the process.
 * @param minorFaults The number of minor page faults for the process.
 * @param majorFaults The number of major page faults for the process.
 * @param contextSwitches The number of context switches for the process.
 *
 * @author N7ghtm4r3
 *
 * @see OSProcess
 */
class OSProcessImpl(
    override val name: String,
    override val path: String,
    override val commandLine: String,
    override val arguments: List<String>,
    override val environmentVariables: Map<String, String>,
    override val currentWorkingDirectory: String,
    override val user: String,
    override val userId: String,
    override val group: String,
    override val groupId: String,
    override val state: State,
    override val processId: Int,
    override val parentProcessId: Int,
    override val threadCount: Int,
    override val priority: Int,
    override val virtualSize: Long,
    override val residentSetSize: Long,
    override val kernelTime: Long,
    override val userTime: Long,
    override val startTime: Long,
    override val bytesRead: Long,
    override val bytesWritten: Long,
    override val openFiles: Long,
    override val softOpenFileLimit: Long,
    override val hardOpenFileLimit: Long,
    override val processCpuLoadCumulative: Double,
    override val processCpuLoadBetweenTicks: Double,
    override val bitness: Int,
    override val affinityMask: Long,
    override val updateAttributes: Boolean,
    override val threadDetails: List<OSThread>,
    override val minorFaults: Long,
    override val majorFaults: Long,
    override val contextSwitches: Long
) : OSProcess
