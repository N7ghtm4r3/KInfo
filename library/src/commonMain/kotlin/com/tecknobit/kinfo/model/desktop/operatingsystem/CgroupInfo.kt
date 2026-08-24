package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * The `CgroupInfo` interface defines the contract to expose the cgroup resource limits and usage metrics for the
 * current process
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface CgroupInfo {

    /**
     * `isContainerized` whether the current process is running in a containerized environment
     */
    val isContainerized: Boolean

    /**
     * `version` the cgroup version, `1` for cgroup v1, `2` for cgroup v2, or `0` when not in a cgroup
     */
    val version: Int

    /**
     * `cpuQuota` the cgroup CPU quota in microseconds, or `-1` when unlimited
     */
    val cpuQuota: Long

    /**
     * `cpuPeriod` the cgroup CPU period in microseconds, using `100000` as the standard default when not explicitly set
     */
    val cpuPeriod: Long

    /**
     * `cpuUsage` the total cgroup CPU usage in nanoseconds
     */
    val cpuUsage: Long

    /**
     * `effectiveCpus` the CPU quota divided by the CPU period, or `-1.0` when unlimited
     */
    val effectiveCpus: Double

    /**
     * `memoryLimit` the cgroup memory limit in bytes, or [Long.MAX_VALUE] when unlimited
     */
    val memoryLimit: Long

    /**
     * `memoryUsage` the current cgroup memory usage in bytes
     */
    val memoryUsage: Long

    /**
     * `pidLimit` the maximum number of process identifiers allowed in the cgroup, or `-1` when unlimited
     */
    val pidLimit: Long

    /**
     * `pidCurrent` the current number of process identifiers in the cgroup
     */
    val pidCurrent: Long

}