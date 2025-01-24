package com.tecknobit.kinfo.model.hardware.storage

import com.tecknobit.kinfo.model.desktop.hardware.storage.LogicalVolumeGroup

class LogicalVolumeGroupImpl(
    override val name: String,
    override val physicalVolumes: Set<String>,
    override val logicalVolumes: Map<String, Set<String>>
) : LogicalVolumeGroup