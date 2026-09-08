package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.State
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOSThread

/**
 * The `MacOsOSThreadImpl` class is useful to represent a thread mapped from native macOS information
 *
 * @property threadId The snapshot-local identifier assigned to the thread
 * @property name The native name of the thread, or an empty value when unavailable
 * @property state The execution state of the thread
 * @property threadCpuLoadCumulative The CPU load reported for the thread
 * @property threadCpuLoadBetweenTicks The CPU load between consecutive thread snapshots, currently unavailable
 * @property owningProcessId The identifier of the process owning the thread
 * @property kernelTime The time spent by the thread in kernel mode, in milliseconds
 * @property userTime The time spent by the thread in user mode, in milliseconds
 * @property upTime The estimated uptime of the thread, in milliseconds
 * @property startTime The estimated start time of the thread, in milliseconds since the Unix epoch
 * @property priority The current scheduling priority of the thread
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsOSThread
 *
 * @since 1.1.0
 */
data class MacOsOSThreadImpl(
    override val threadId: Int,
    override val name: String,
    override val state: State,
    override val threadCpuLoadCumulative: Double,
    override val threadCpuLoadBetweenTicks: Double,
    override val owningProcessId: Int,
    override val kernelTime: Long,
    override val userTime: Long,
    override val upTime: Long,
    override val startTime: Long,
    override val priority: Int
) : MacOsOSThread {

    /**
     * `startMemoryAddress` the unsupported thread start memory address
     */
    override val startMemoryAddress: Long = 0L

    /**
     * `contextSwitches` the unsupported number of context switches performed by the thread
     */
    override val contextSwitches: Long = 0L

    /**
     * `minorFaults` the unsupported number of minor page faults experienced by the thread
     */
    override val minorFaults: Long = 0L

    /**
     * `majorFaults` the unsupported number of major page faults experienced by the thread
     */
    override val majorFaults: Long = 0L

    /**
     * `updateAttributes` whether updating the thread attributes is supported
     */
    override val updateAttributes: Boolean = false

}
