package com.tecknobit.kinfo.mappers.hardware.disks

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsHWDiskStoreImpl
import com.tecknobit.kinfo.hardware.MacOsHWPartitionImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.common.hardware.storage.DiskType
import platform.IOKit.io_service_t
import kotlin.time.Clock

/**
 * The `MacOsHWDisksStoreMapper` class is useful to map macOS whole media to disk snapshots
 *
 * Physical disks and synthesized whole media are included, with their registry statistics and associated
 * partitions, volumes, and exposed snapshots
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsHWDisksStoreMapper : MacOsHardwareMapper<List<MacOsHWDiskStoreImpl>>() {

    /**
     * Method used to collect the current whole-media identities, statistics, and associated partitions
     *
     * Each disk receives its own collection timestamp and the native values are read sequentially
     *
     * @return the collected disk snapshots as [List] of [MacOsHWDiskStoreImpl]
     * @throws IllegalStateException If an IOKit media enumeration fails
     */
    override fun mapFromNative(): List<MacOsHWDiskStoreImpl> {
        val disks = mutableListOf<MacOsHWDiskStoreImpl>()

        useIOServices(
            serviceName = IO_MEDIA_SERVICE,
            consumeServiceIf = { media ->
                media.readBooleanFromRegistry(
                    key = "Whole"
                )
            }
        ) { _, media ->
            val name = media.readStringFromRegistry(
                key = "BSD Name"
            )
            val size = media.readLongFromRegistry(
                key = "Size"
            )

            val characteristics = media.findDictionaryInRegistry(
                key = "Device Characteristics"
            )
            val mediumType = characteristics.readStringFromDictionary(
                key = "Medium Type"
            )

            val statistics = media.findDictionaryInRegistry(
                key = "Statistics"
            )

            disks.add(
                MacOsHWDiskStoreImpl(
                    name = name,
                    model = characteristics.readStringFromDictionaryOrUnknown(
                        key = "Product Name"
                    ),
                    serial = characteristics.readStringFromDictionaryOrUnknown(
                        key = "Serial Number"
                    ),
                    size = size,
                    reads = statistics.readLongFromDictionary(
                        key = "Operations (Read)"
                    ),
                    readBytes = statistics.readLongFromDictionary(
                        key = "Bytes (Read)"
                    ),
                    writes = statistics.readLongFromDictionary(
                        key = "Operations (Write)"
                    ),
                    writesBytes = statistics.readLongFromDictionary(
                        key = "Bytes (Write)"
                    ),
                    transferTime = resolveTransferTime(
                        statistics = statistics
                    ),
                    partitions = loadPartitions(
                        diskName = name
                    ),
                    timestamp = Clock.System.now().toEpochMilliseconds(),
                    diskType = resolveDiskType(
                        media = media,
                        mediumType = mediumType
                    )
                )
            )
        }

        return disks
    }

    /**
     * Method used to convert the cumulative read and write durations from nanoseconds to milliseconds
     *
     * The durations are added before conversion and do not represent elapsed wall-clock busy time
     * Missing timing entries in a non-null statistics dictionary contribute zero
     *
     * @param statistics The native statistics dictionary containing cumulative read and write durations
     *
     * @return the combined transfer duration in milliseconds as [Long]
     */
    @Resolver
    private fun resolveTransferTime(
        statistics: Map<String, *>?
    ): Long {
        val readTimeNs = statistics.readLongFromDictionary(
            key = "Total Time (Read)"
        )

        val writeTimeNs = statistics.readLongFromDictionary(
            key = "Total Time (Write)"
        )

        return (readTimeNs + writeTimeNs) / 1_000_000L
    }

    /**
     * Method used to load the media entries associated with the specified whole disk
     *
     * @param diskName The BSD name of the whole disk whose partitions are requested
     *
     * @return the associated partitions, volumes, and exposed snapshots as [List] of [MacOsHWPartitionImpl]
     * @throws IllegalStateException If the IOKit media enumeration fails
     */
    @Loader
    private fun loadPartitions(
        diskName: String
    ): List<MacOsHWPartitionImpl> {
        val macOsHWPartitionMapper = MacOsHWPartitionMapper(
            diskName = diskName
        )

        return macOsHWPartitionMapper.mapFromNative()
    }

    /**
     * Method used to classify a disk from its interconnect, removability, and medium description
     *
     * A virtual interconnect takes precedence over removability, followed by solid-state and rotational media
     * Unrecognized medium descriptions produce [DiskType.Unknown]
     *
     * @param media The borrowed media entry whose protocol characteristics and removability are inspected
     * @param mediumType The native medium description used to identify solid-state or rotational storage
     *
     * @return the resolved storage category as [DiskType]
     */
    @Resolver
    private fun resolveDiskType(
        media: io_service_t,
        mediumType: String
    ): DiskType {
        val protocolCharacteristics = media.findDictionaryInRegistry(
            key = "Protocol Characteristics"
        )
        val interconnect = protocolCharacteristics.readStringFromDictionary(
            key = "Physical Interconnect"
        )
        if (interconnect == "Virtual Interface")
            return DiskType.Virtual

        return when {
            media.readBooleanFromRegistry("Removable") -> DiskType.Removable

            mediumType.contains("Solid State") -> DiskType.SSD

            mediumType.contains("Rotational") -> DiskType.HDD

            else -> DiskType.Unknown
        }
    }

}