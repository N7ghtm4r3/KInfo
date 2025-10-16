package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * Represents a file store (such as a disk or mount point) in the operating system.
 * Provides information about the file system, including the volume, space usage,
 * and attributes related to the file store
 *
 * @author N7ghtm4r3
 */
interface OSFileStore {

    /**
     * `name` The name of the file store
     */
    val name: String

    /**
     * `volume` The volume identifier for the file store
     */
    val volume: String

    /**
     * `label` The label assigned to the file store
     */
    val label: String

    /**
     * `logicalVolume` The logical volume associated with the file store
     */
    val logicalVolume: String

    /**
     * `mount` The mount point of the file store
     */
    val mount: String

    /**
     * `description` A description of the file store
     */
    val description: String

    /**
     * `type` The type of the file store (e.g., NTFS, ext4)
     */
    val type: String

    /**
     * `options` The options associated with the file store
     */
    val options: String

    /**
     * `uuid` The unique identifier for the file store
     */
    val uuid: String

    /**
     * `freeSpace` The amount of free space in bytes
     */
    val freeSpace: Long

    /**
     * `usableSpace` The usable space in bytes
     */
    val usableSpace: Long

    /**
     * `totalSpace` The total space in bytes
     */
    val totalSpace: Long

    /**
     * `freeInodes` The number of free inodes
     */
    val freeInodes: Long

    /**
     * `totalInodes` The total number of inodes
     */
    val totalInodes: Long

    /**
     * `updateAttributes` Whether the file store supports updating attributes
     */
    val updateAttributes: Boolean

}