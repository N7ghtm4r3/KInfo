package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.storage.DiskType
import com.tecknobit.kinfo.model.desktop.common.hardware.storage.HWPartition
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHWDiskStore

data class MacOsHWDiskStoreImpl(
    override val name: String,
    override val model: String,
    override val serial: String,
    override val size: Long,
    override val reads: Long,
    override val readBytes: Long,
    override val writes: Long,
    override val writesBytes: Long,
    override val currentQueueLength: Long,
    override val transferTime: Long,
    override val partitions: List<HWPartition>,
    override val timestamp: Long,
    override val updateAttributes: Boolean,
    override val diskType: DiskType
) : MacOsHWDiskStore
