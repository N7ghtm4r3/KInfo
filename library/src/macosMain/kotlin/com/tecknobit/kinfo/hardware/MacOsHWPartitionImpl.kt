package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHWPartition

data class MacOsHWPartitionImpl(
    override val identification: String,
    override val name: String,
    override val type: String,
    override val uuid: String,
    override val size: Long,
    override val major: Int,
    override val minor: Int,
    override val mountPoint: String,
    override val label: String
) : MacOsHWPartition
