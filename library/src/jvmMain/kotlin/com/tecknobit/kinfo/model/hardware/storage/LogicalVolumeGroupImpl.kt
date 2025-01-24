package com.tecknobit.kinfo.model.hardware.storage

import com.tecknobit.kinfo.model.desktop.hardware.storage.LogicalVolumeGroup

/**
 * `LogicalVolumeGroupImpl` is an implementation of the `LogicalVolumeGroup` interface.
 * This class provides concrete details about a logical volume group, including the name,
 * physical volumes, and logical volumes mapped to physical volumes.
 *
 * @param name The name of the logical volume group.
 * @param physicalVolumes A set of physical volumes that are part of the logical volume group.
 * @param logicalVolumes A map where the key is the logical volume name and the value
 *                       is a set of physical volumes associated with it.
 *
 * @author N7ghtm4r3
 *
 * @see LogicalVolumeGroup
 */
class LogicalVolumeGroupImpl(
    override val name: String,
    override val physicalVolumes: Set<String>,
    override val logicalVolumes: Map<String, Set<String>>
) : LogicalVolumeGroup
