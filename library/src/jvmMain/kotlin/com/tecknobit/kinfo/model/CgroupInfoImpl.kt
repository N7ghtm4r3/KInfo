package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.desktop.operatingsystem.CgroupInfo

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