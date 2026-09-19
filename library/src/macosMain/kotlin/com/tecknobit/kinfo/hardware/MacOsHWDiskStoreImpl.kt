package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.storage.DiskType
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHWDiskStore
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHWPartition

/**
 * The `MacOsHWDiskStoreImpl` class is useful to store a macOS whole-media snapshot
 *
 * A snapshot can represent a physical disk or synthesized media and is not refreshed after construction
 *
 * @property name The BSD name of the whole media
 * @property model The model description obtained from the device characteristics
 * @property serial The serial number obtained from the device characteristics
 * @property size The capacity reported by the media in bytes
 * @property reads The cumulative number of read operations reported by the native statistics
 * @property readBytes The cumulative number of bytes read
 * @property writes The cumulative number of write operations reported by the native statistics
 * @property writesBytes The cumulative number of bytes written
 * @property transferTime The summed read and write durations in milliseconds, with absent timing entries contributing zero
 * @property partitions The associated partitions, APFS volumes, and snapshots exposed as media entries
 * @property timestamp The collection time in milliseconds since the Unix epoch
 * @property diskType The storage category resolved from native media and protocol characteristics
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHWDiskStore
 *
 * @since 1.1.0
 */
data class MacOsHWDiskStoreImpl(
    override val name: String,
    override val model: String,
    override val serial: String,
    override val size: Long,
    override val reads: Long,
    override val readBytes: Long,
    override val writes: Long,
    override val writesBytes: Long,
    override val transferTime: Long,
    override val partitions: List<MacOsHWPartition>,
    override val timestamp: Long,
    override val diskType: DiskType
) : MacOsHWDiskStore {

    /**
     * `currentQueueLength` the unavailable queue length represented by zero, without implying that the disk is idle
     */
    override val currentQueueLength: Long = 0

    /**
     * `updateAttributes` the disabled update flag, always false for this captured snapshot
     */
    override val updateAttributes: Boolean = false

}
