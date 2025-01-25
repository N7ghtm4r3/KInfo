package com.tecknobit.kinfo.model.operatingsystem.processes

import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.State

/**
 * `OSThreadImpl` Provides the implementation of the `OSThread` interface, representing a thread
 * of an operating system process, including details about its execution, resource usage, and associated metadata.
 *
 * @param threadId The unique identifier for the thread.
 * @param name The name of the thread (e.g., "main", "worker").
 * @param state The current state of the thread (e.g., running, sleeping).
 * @param threadCpuLoadCumulative The cumulative CPU load of the thread as a percentage.
 * @param threadCpuLoadBetweenTicks The CPU load of the thread between two ticks, as a percentage.
 * @param owningProcessId The process ID of the process that owns the thread.
 * @param startMemoryAddress The starting memory address of the thread.
 * @param contextSwitches The number of context switches performed for the thread.
 * @param minorFaults The number of minor page faults experienced by the thread.
 * @param majorFaults The number of major page faults experienced by the thread.
 * @param kernelTime The total amount of time the thread has spent in kernel mode, in milliseconds.
 * @param userTime The total amount of time the thread has spent in user mode, in milliseconds.
 * @param upTime The total uptime of the thread, in milliseconds.
 * @param startTime The start time of the thread, in milliseconds since the Unix epoch.
 * @param priority The priority level of the thread.
 * @param updateAttributes A flag indicating whether the thread attributes should be updated.
 *
 * @author N7ghtm4r3
 *
 * @see OSThread
 */
class OSThreadImpl(
    override val threadId: Int,
    override val name: String,
    override val state: State,
    override val threadCpuLoadCumulative: Double,
    override val threadCpuLoadBetweenTicks: Double,
    override val owningProcessId: Int,
    override val startMemoryAddress: Long,
    override val contextSwitches: Long,
    override val minorFaults: Long,
    override val majorFaults: Long,
    override val kernelTime: Long,
    override val userTime: Long,
    override val upTime: Long,
    override val startTime: Long,
    override val priority: Int,
    override val updateAttributes: Boolean
) : OSThread
