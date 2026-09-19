@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.disks

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsHWPartitionImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.CoreFoundation.CFRelease
import platform.DiskArbitration.*
import platform.Foundation.CFBridgingRelease
import platform.Foundation.NSURL
import platform.IOKit.io_service_t

/**
 * The `MacOsHWPartitionMapper` class is useful to map media entries associated with a macOS whole disk
 *
 * The result may contain physical partitions, APFS volumes, and snapshots exposed as media entries
 *
 * @property diskName The BSD name of the whole disk used to select associated media entries
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsHWPartitionsMapper(
    private val diskName: String
) : MacOsHardwareMapper<List<MacOsHWPartitionImpl>>() {

    /**
     * Method used to collect non-whole media whose associated whole disk matches [diskName]
     *
     * Entries without an available Disk Arbitration description are skipped
     * APFS media sizes describe shared capacity and must not be summed as separate physical allocations
     *
     * @return the associated media descriptions as [List] of [MacOsHWPartitionImpl]
     * @throws IllegalStateException If the IOKit media enumeration fails
     */
    override fun mapFromNative(): List<MacOsHWPartitionImpl> {
        val partitions = mutableListOf<MacOsHWPartitionImpl>()

        useIOServices(
            serviceName = IO_MEDIA_SERVICE,
            consumeServiceIf = { media ->
                !media.readBooleanFromRegistry(
                    key = "Whole"
                )
            }
        ) { _, partition ->
            val partitionDescription = partition.readDiskDescription() ?: return@useIOServices

            val identification = partition.readStringFromRegistry(
                key = "BSD Name"
            )
            val size = partition.readLongFromRegistry(
                key = "Size"
            )
            val major = partition.readIntFromRegistry(
                key = "BSD Major"
            )
            val minor = partition.readIntFromRegistry(
                key = "BSD Minor"
            )

            val partition = MacOsHWPartitionImpl(
                identification = identification,
                name = partitionDescription.readStringFromDictionary(
                    key = "DAMediaName",
                    default = identification
                ),
                type = partitionDescription.readStringFromDictionary(
                    key = "DAVolumeKind"
                ),
                uuid = partition.readStringFromRegistry(
                    key = "UUID"
                ),
                size = size,
                major = major,
                minor = minor,
                mountPoint = resolveMountPoint(
                    partitionDescription = partitionDescription
                ),
                label = partitionDescription.readStringFromDictionary(
                    key = "DAVolumeName"
                )
            )
            partitions.add(partition)
        }

        return partitions
    }

    /**
     * Method used to read the Disk Arbitration description of a media entry associated with [diskName]
     *
     * The created session and disk references are released before returning while the receiver remains borrowed
     *
     * @receiver The borrowed IOKit media entry whose description is requested
     *
     * @return the description, or null when unavailable or associated with another disk, as [Map]
     */
    @Suppress("UNCHECKED_CAST")
    private fun io_service_t.readDiskDescription(): Map<String, *>? {
        val session = DASessionCreate(null) ?: return null

        try {
            val disk = DADiskCreateFromIOMedia(null, session, this)
                ?: return null

            return try {
                if (!disk.isPartitionOfDisk())
                    return null

                val description = DADiskCopyDescription(disk)
                    ?: return null

                CFBridgingRelease(description) as? Map<String, *>
            } finally {
                CFRelease(disk)
            }
        } finally {
            CFRelease(session)
        }
    }

    /**
     * Method used to check whether the associated whole disk has the BSD name [diskName]
     *
     * The copied whole-disk reference is released and the receiver remains owned by the caller
     *
     * @receiver The borrowed Disk Arbitration reference representing the candidate media
     *
     * @return whether the whole-disk name matches, or false when it cannot be resolved, as [Boolean]
     */
    private fun DADiskRef.isPartitionOfDisk(): Boolean {
        val wholeDisk = DADiskCopyWholeDisk(this) ?: return false

        return try {
            val parentDiskName = DADiskGetBSDName(wholeDisk)?.toKString()

            parentDiskName == diskName
        } finally {
            CFRelease(wholeDisk)
        }
    }

    /**
     * Method used to extract the mounted volume path from a Disk Arbitration description
     *
     * @param partitionDescription The optional description containing the volume URL
     *
     * @return the volume path, or [UNKNOWN] when no usable URL or path is available, as [String]
     */
    @Resolver
    private fun resolveMountPoint(
        partitionDescription: Map<String, *>?
    ): String {
        val url = partitionDescription?.get("DAVolumePath") as? NSURL ?: return UNKNOWN

        return url.path ?: UNKNOWN
    }

}