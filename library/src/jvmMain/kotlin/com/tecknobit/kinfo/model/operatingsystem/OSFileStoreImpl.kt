package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSFileStore

/**
 * Implementation of the [OSFileStore] interface representing a file store in the operating system.
 * This class contains details about the file system such as the volume, free space, and inode information.
 *
 * @param name The name of the file store.
 * @param volume The volume identifier for the file store.
 * @param label The label assigned to the file store.
 * @param logicalVolume The logical volume associated with the file store.
 * @param mount The mount point of the file store.
 * @param description A description of the file store.
 * @param type The type of the file store (e.g., NTFS, ext4).
 * @param options The options associated with the file store.
 * @param uuid The unique identifier for the file store.
 * @param freeSpace The amount of free space in bytes.
 * @param usableSpace The usable space in bytes.
 * @param totalSpace The total space in bytes.
 * @param freeInodes The number of free inodes.
 * @param totalInodes The total number of inodes.
 * @param updateAttributes Whether the file store updates its attributes.
 *
 * @author N7ghtm4r3
 * @see OSFileStore
 */
class OSFileStoreImpl(
    override val name: String,
    override val volume: String,
    override val label: String,
    override val logicalVolume: String,
    override val mount: String,
    override val description: String,
    override val type: String,
    override val options: String,
    override val uuid: String,
    override val freeSpace: Long,
    override val usableSpace: Long,
    override val totalSpace: Long,
    override val freeInodes: Long,
    override val totalInodes: Long,
    override val updateAttributes: Boolean
) : OSFileStore
