package com.tecknobit.kinfo.model.desktop.operatingsystem.processes

/**
 * `OSThread` Represents a thread of an operating system process, providing details about its
 * execution, resource usage, and associated metadata.
 *
 * @author N7ghtm4r3
 *
 * @see OSThread
 */
interface OSThread {

    /**
     * `threadId` The unique identifier for the thread.
     */
    val threadId: Int

    /**
     * `name` The name of the thread (e.g., "main", "worker").
     */
    val name: String

    /**
     * `state` The current state of the thread (e.g., running, sleeping).
     */
    val state: State

    /**
     * `threadCpuLoadCumulative` The cumulative CPU load of the thread as a percentage.
     */
    val threadCpuLoadCumulative: Double

    /**
     * `threadCpuLoadBetweenTicks` The CPU load of the thread between two ticks, as a percentage.
     */
    val threadCpuLoadBetweenTicks: Double

    /**
     * `owningProcessId` The process ID of the process that owns the thread.
     */
    val owningProcessId: Int

    /**
     * `startMemoryAddress` The starting memory address of the thread.
     */
    val startMemoryAddress: Long

    /**
     * `contextSwitches` The number of context switches performed for the thread.
     */
    val contextSwitches: Long

    /**
     * `minorFaults` The number of minor page faults experienced by the thread.
     */
    val minorFaults: Long

    /**
     * `majorFaults` The number of major page faults experienced by the thread.
     */
    val majorFaults: Long

    /**
     * `kernelTime` The total amount of time the thread has spent in kernel mode, in milliseconds.
     */
    val kernelTime: Long

    /**
     * `userTime` The total amount of time the thread has spent in user mode, in milliseconds.
     */
    val userTime: Long

    /**
     * `upTime` The total uptime of the thread, in milliseconds.
     */
    val upTime: Long

    /**
     * `startTime` The start time of the thread, in milliseconds since the Unix epoch.
     */
    val startTime: Long

    /**
     * `priority` The priority level of the thread.
     */
    val priority: Int

    /**
     * `updateAttributes` A flag indicating whether the thread attributes should be updated.
     */
    val updateAttributes: Boolean

}
