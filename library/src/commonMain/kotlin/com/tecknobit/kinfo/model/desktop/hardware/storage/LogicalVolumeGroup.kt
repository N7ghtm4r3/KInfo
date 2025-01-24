package com.tecknobit.kinfo.model.desktop.hardware.storage

interface LogicalVolumeGroup {
    val name: String
    val physicalVolumes: Set<String>
    val logicalVolumes: Map<String, Set<String>>
}