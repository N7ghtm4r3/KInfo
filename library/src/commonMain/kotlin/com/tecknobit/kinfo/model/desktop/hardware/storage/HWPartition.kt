package com.tecknobit.kinfo.model.desktop.hardware.storage

/**
 * `HWPartition` represents a hardware partition on a storage device.
 * It provides details about the partition such as its identification, name, type, UUID,
 * size, and mount point.
 *
 * @author N7ghtm4r3
 */
interface HWPartition {

    /**
     * `identification` The unique identifier of the partition.
     */
    val identification: String

    /**
     * `name` The name of the partition.
     */
    val name: String

    /**
     * `type` The type of the partition (e.g., ext4, NTFS).
     */
    val type: String

    /**
     * `uuid` The UUID (Universally Unique Identifier) of the partition.
     */
    val uuid: String

    /**
     * `size` The total size of the partition (in bytes).
     */
    val size: Long

    /**
     * `major` The major number of the partition.
     */
    val major: Int

    /**
     * `minor` The minor number of the partition.
     */
    val minor: Int

    /**
     * `mountPoint` The mount point of the partition (e.g., /, /home).
     */
    val mountPoint: String

}
