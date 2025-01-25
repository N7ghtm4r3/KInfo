package com.tecknobit.kinfo.model.desktop.operatingsystem.processes

/**
 * `OSProcess` represents a process in the operating system.
 * It provides details about the process, including its name, ID, state, resources usage, and more.
 *
 * @author N7ghtm4r3
 */
interface OSProcess {

    /**
     * `name` The name of the process (e.g., "java", "nginx").
     */
    val name: String

    /**
     * `path` The path to the executable of the process.
     */
    val path: String

    /**
     * `commandLine` The full command line that was used to start the process.
     */
    val commandLine: String

    /**
     * `arguments` The arguments passed to the process when it was started.
     */
    val arguments: List<String>

    /**
     * `environmentVariables` A map of environment variables used by the process.
     */
    val environmentVariables: Map<String, String>

    /**
     * `currentWorkingDirectory` The current working directory of the process.
     */
    val currentWorkingDirectory: String

    /**
     * `user` The user running the process.
     */
    val user: String

    /**
     * `userId` The ID of the user running the process.
     */
    val userId: String

    /**
     * `group` The group associated with the process.
     */
    val group: String

    /**
     * `groupId` The ID of the group associated with the process.
     */
    val groupId: String

    /**
     * `state` The state of the process (e.g., running, sleeping).
     */
    val state: State

    /**
     * `processId` The unique identifier for the process.
     */
    val processId: Int

    /**
     * `parentProcessId` The process ID of the parent process.
     */
    val parentProcessId: Int

    /**
     * `threadCount` The number of threads in the process.
     */
    val threadCount: Int

    /**
     * `priority` The priority of the process.
     */
    val priority: Int

    /**
     * `virtualSize` The virtual memory size of the process, in bytes.
     */
    val virtualSize: Long

    /**
     * `residentSetSize` The resident set size of the process, in bytes (physical memory used).
     */
    val residentSetSize: Long

    /**
     * `kernelTime` The amount of time the process has spent in kernel mode, in milliseconds.
     */
    val kernelTime: Long

    /**
     * `userTime` The amount of time the process has spent in user mode, in milliseconds.
     */
    val userTime: Long

    /**
     * `startTime` The time when the process started, in milliseconds since the Unix epoch.
     */
    val startTime: Long

    /**
     * `bytesRead` The number of bytes read by the process.
     */
    val bytesRead: Long

    /**
     * `bytesWritten` The number of bytes written by the process.
     */
    val bytesWritten: Long

    /**
     * `openFiles` The number of open files used by the process.
     */
    val openFiles: Long

    /**
     * `softOpenFileLimit` The soft limit on the number of files the process can open.
     */
    val softOpenFileLimit: Long

    /**
     * `hardOpenFileLimit` The hard limit on the number of files the process can open.
     */
    val hardOpenFileLimit: Long

    /**
     * `processCpuLoadCumulative` The cumulative CPU load of the process as a percentage.
     */
    val processCpuLoadCumulative: Double

    /**
     * `processCpuLoadBetweenTicks` The CPU load of the process between two ticks, as a percentage.
     */
    val processCpuLoadBetweenTicks: Double

    /**
     * `bitness` The bitness of the process (e.g., 32-bit, 64-bit).
     */
    val bitness: Int

    /**
     * `affinityMask` The CPU affinity mask for the process.
     */
    val affinityMask: Long

    /**
     * `updateAttributes` A flag indicating whether the process attributes should be updated.
     */
    val updateAttributes: Boolean

    /**
     * `threadDetails` The list of threads associated with the process.
     */
    val threadDetails: List<OSThread>

    /**
     * `minorFaults` The number of minor page faults for the process.
     */
    val minorFaults: Long

    /**
     * `majorFaults` The number of major page faults for the process.
     */
    val majorFaults: Long

    /**
     * `contextSwitches` The number of context switches for the process.
     */
    val contextSwitches: Long

}


/**
 * Process and Thread Execution States
 */
enum class State {

    /**
     * Intermediate state in process creation
     */
    NEW,

    /**
     * Actively executing process
     */
    RUNNING,

    /**
     * Interruptible sleep state
     */
    SLEEPING,

    /**
     * Blocked, uninterruptible sleep state
     */
    WAITING,

    /**
     * Intermediate state in process termination
     */
    ZOMBIE,

    /**
     * Stopped by the user, such as for debugging
     */
    STOPPED,

    /**
     * Other or unknown states not defined
     */
    OTHER,

    /**
     * The state resulting if the process fails to update statistics, probably due to termination.
     */
    INVALID,

    /**
     * Special case of waiting if the process has been intentionally suspended (Windows only)
     */
    SUSPENDED

}