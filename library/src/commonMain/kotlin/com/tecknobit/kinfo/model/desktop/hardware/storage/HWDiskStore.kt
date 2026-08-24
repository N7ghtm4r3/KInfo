package com.tecknobit.kinfo.model.desktop.hardware.storage

/**
 * `HWDiskStore` represents information about a disk or storage device in the system.
 * It provides details about the disk's model, serial number, size, read/write statistics, partitions, and other attributes.
 *
 * @author N7ghtm4r3 - Tecknobit
 */
interface HWDiskStore {

    /**
     * `name` The name or identifier of the disk (e.g., "sda", "disk0")
     */
    val name: String

    /**
     * `model` The model name of the disk (e.g., "Samsung SSD 860")
     */
    val model: String

    /**
     * `serial` The serial number of the disk
     */
    val serial: String

    /**
     * `size` The total size of the disk (in bytes)
     */
    val size: Long

    /**
     * `reads` The number of read operations performed on the disk
     */
    val reads: Long

    /**
     * `readBytes` The number of bytes read from the disk
     */
    val readBytes: Long

    /**
     * `writes` The number of write operations performed on the disk
     */
    val writes: Long

    /**
     * `writesBytes` The number of bytes written to the disk
     */
    val writesBytes: Long

    /**
     * `currentQueueLength` The current length of the disk's I/O request queue
     */
    val currentQueueLength: Long

    /**
     * `transferTime` The time (in milliseconds) spent transferring data for I/O operations
     */
    val transferTime: Long

    /**
     * `partitions` A list of partitions on the disk
     */
    val partitions: List<HWPartition>

    /**
     * `timestamp` The timestamp when the disk information was last updated
     */
    val timestamp: Long

    /**
     * `updateAttributes` A flag indicating whether the disk's attributes should be updated
     */
    val updateAttributes: Boolean

    /**
     * `diskType` The type of the disk, such as SSD, HDD, Removable, Virtual, or Unknown
     *
     * @since 1.1.0
     */
    val diskType: DiskType

}

/**
 * The `DiskType` enum is useful to represent the type of the disk, such as SSD, HDD, Removable, Virtual, or Unknown
 *
 * OSHI derives the disk type from Linux `sysfs` and `udev`, macOS `IOKit`, or Windows `WMI` platform data
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see HWDiskStore
 *
 * @since 1.1.0
 */
enum class DiskType {

    /**
     * `SSD` the disk type used when Linux `queue/rotational` is `0`, or the macOS `Medium Type` contains `Solid State`
     * or `SSD`
     */
    SSD,

    /**
     * `HDD` the disk type used when Linux `queue/rotational` is `1`, the macOS `Medium Type` contains `Rotational`, or
     * the Windows `MediaType` contains `fixed` or `external`
     *
     * Windows uses this type as the default for fixed disks because `Win32_DiskDrive` does not distinguish SSDs from
     * HDDs
     */
    HDD,

    /**
     * `Removable` the disk type used when Linux `removable` is `1`, the macOS `IOMedia` entry is removable, or the
     * Windows `MediaType` contains `removable`
     */
    Removable,

    /**
     * `Virtual` the disk type used for Linux device-mapper disks whose device node starts with `/dev/dm`
     */
    Virtual,

    /**
     * `Unknown` the disk type used when the platform data is absent or not recognized
     */
    Unknown

}