package com.tecknobit.kinfo.model.desktop.hardware.storage

/**
 * `LogicalVolumeGroup` represents a logical volume group in a storage system.
 * It provides details about the name of the volume group, the physical volumes
 * associated with it, and the logical volumes mapped to physical volumes.
 *
 * @author N7ghtm4r3
 */
interface LogicalVolumeGroup {

    /**
     * `name` The name of the logical volume group
     */
    val name: String

    /**
     * `physicalVolumes` A set of physical volumes that are part of the logical volume group
     */
    val physicalVolumes: Set<String>

    /**
     * `logicalVolumes` A map where the key is the logical volume name and the value
     * is a set of physical volume names that are part of the logical volume
     */
    val logicalVolumes: Map<String, Set<String>>

}
