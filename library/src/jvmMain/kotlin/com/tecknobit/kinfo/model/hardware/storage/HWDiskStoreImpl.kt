package com.tecknobit.kinfo.model.hardware.storage

import com.tecknobit.kinfo.model.desktop.hardware.storage.HWDiskStore
import com.tecknobit.kinfo.model.desktop.hardware.storage.HWPartition

/**
 * Implementation of the `HWDiskStore` interface.
 * This class provides details about a disk or storage device in the system,
 * including the disk's model, serial number, size, read/write statistics, partitions, and other attributes.
 *
 * @param name The name or identifier of the disk (e.g., "sda", "disk0")
 * @param model The model name of the disk (e.g., "Samsung SSD 860")
 * @param serial The serial number of the disk
 * @param size The total size of the disk (in bytes)
 * @param reads The number of read operations performed on the disk
 * @param readBytes The number of bytes read from the disk
 * @param writes The number of write operations performed on the disk
 * @param writesBytes The number of bytes written to the disk
 * @param currentQueueLength The current length of the disk's I/O request queue
 * @param transferTime The time (in milliseconds) spent transferring data for I/O operations
 * @param partitions A list of partitions on the disk
 * @param timestamp The timestamp when the disk information was last updated
 * @param updateAttributes A flag indicating whether the disk's attributes should be updated
 *
 * @author N7ghtm4r3
 *
 * @see HWDiskStore
 */
class HWDiskStoreImpl(
    override val name: String,
    override val model: String,
    override val serial: String,
    override val size: Long,
    override val reads: Long,
    override val readBytes: Long,
    override val writes: Long,
    override val writesBytes: Long,
    override val currentQueueLength: Long,
    override val transferTime: Long,
    override val partitions: List<HWPartition>,
    override val timestamp: Long,
    override val updateAttributes: Boolean
) : HWDiskStore
