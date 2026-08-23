package com.tecknobit.kinfo.model.desktop.operatingsystem

interface CgroupInfo {

    val isContainerized: Boolean

    val version: Int

    val cpuQuota: Long

    val cpuPeriod: Long

    val cpuUsage: Long

    val effectiveCpus: Double

    val memoryLimit: Long

    val memoryUsage: Long

    val pidLimit: Long

    val pidCurrent: Long

}