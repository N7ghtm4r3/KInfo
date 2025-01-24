package com.tecknobit.kinfo.model.hardware.storage

import com.tecknobit.kinfo.model.desktop.hardware.storage.HWPartition

/**
 * Implementation of the `HWPartition` interface.
 * This class provides details about a hardware partition on a storage device,
 * including the partition's identification, name, type, UUID, size, major/minor numbers, and mount point.
 *
 * @param identification The unique identifier of the partition.
 * @param name The name of the partition.
 * @param type The type of the partition (e.g., ext4, NTFS).
 * @param uuid The UUID (Universally Unique Identifier) of the partition.
 * @param size The total size of the partition (in bytes).
 * @param major The major number of the partition.
 * @param minor The minor number of the partition.
 * @param mountPoint The mount point of the partition (e.g., /, /home).
 *
 * @author N7ghtm4r3
 *
 * @see HWPartition
 */
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
