package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.CgroupInfo

/**
 * The `CgroupInfoImpl` class is useful to represent the cgroup resource limits and usage metrics for the current
 * process
 *
 * @property isContainerized Whether the current process is running in a containerized environment
 * @property version The cgroup version, `1` for cgroup v1, `2` for cgroup v2, or `0` when not in a cgroup
 * @property cpuQuota The cgroup CPU quota in microseconds, or `-1` when unlimited
 * @property cpuPeriod The cgroup CPU period in microseconds, using `100000` as the standard default when not explicitly
 * set
 * @property cpuUsage The total cgroup CPU usage in nanoseconds
 * @property effectiveCpus The CPU quota divided by the CPU period, or `-1.0` when unlimited
 * @property memoryLimit The cgroup memory limit in bytes, or [Long.MAX_VALUE] when unlimited
 * @property memoryUsage The current cgroup memory usage in bytes
 * @property pidLimit The maximum number of process identifiers allowed in the cgroup, or `-1` when unlimited
 * @property pidCurrent The current number of process identifiers in the cgroup
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see CgroupInfo
 *
 * @since 1.1.0
 */
data class CgroupInfoImpl(
    override val isContainerized: Boolean,
    override val version: Int,
    override val cpuQuota: Long,
    override val cpuPeriod: Long,
    override val cpuUsage: Long,
    override val effectiveCpus: Double,
    override val memoryLimit: Long,
    override val memoryUsage: Long,
    override val pidLimit: Long,
    override val pidCurrent: Long
) : CgroupInfo