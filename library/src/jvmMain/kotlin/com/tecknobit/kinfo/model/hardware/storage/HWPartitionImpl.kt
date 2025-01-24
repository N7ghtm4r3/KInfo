package com.tecknobit.kinfo.model.hardware.storage

import com.tecknobit.kinfo.model.desktop.hardware.storage.HWPartition

class HWPartitionImpl(
    override val identification: String,
    override val name: String,
    override val type: String,
    override val uuid: String,
    override val size: Long,
    override val major: Int,
    override val minor: Int,
    override val mountPoint: String
) : HWPartition