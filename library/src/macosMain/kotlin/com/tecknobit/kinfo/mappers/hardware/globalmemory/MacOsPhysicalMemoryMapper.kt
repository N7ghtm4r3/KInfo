@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.globalmemory

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsPhysicalMemoryImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsSplitHardwareMapper
import com.tecknobit.kinfo.utils.queryLongSysCtlByName
import com.tecknobit.kinfo.utils.resolveFreq
import kotlinx.cinterop.ExperimentalForeignApi
import platform.IOKit.IO_OBJECT_NULL
import platform.IOKit.io_registry_entry_t

/**
 * The `MacOsPhysicalMemoryMapper` class is useful to map unified memory on Apple Silicon and memory slots on Intel
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsSplitHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsPhysicalMemoryMapper : MacOsSplitHardwareMapper<List<MacOsPhysicalMemoryImpl>>() {

    /**
     * Method used to map unified physical memory from the chosen device tree entry
     *
     * @return the single unified memory entry, or an empty list when the registry entry is absent, as [List] of [MacOsPhysicalMemoryImpl]
     */
    override fun mapForSilicon(): List<MacOsPhysicalMemoryImpl> {
        return buildList {
            val physicalMemory = useRegistryFromPath(
                path = IO_DEVICE_TREE_CHOSEN
            ) { service ->
                loadUnifiedPhysicalMemory(
                    service = service
                )
            } ?: return@buildList

            add(physicalMemory)
        }
    }

    /**
     * Method used to map unified memory capacity and registry metadata
     *
     * The bank label is unknown and a failed capacity query produces zero
     * Missing textual properties use [UNKNOWN], while successfully read blank values are preserved
     *
     * @param service The borrowed chosen registry entry
     *
     * @return the unified memory entry, or null for a zero handle, as [MacOsPhysicalMemoryImpl]
     */
    private fun loadUnifiedPhysicalMemory(
        service: io_registry_entry_t
    ): MacOsPhysicalMemoryImpl? {
        if (service == IO_OBJECT_NULL)
            return null

        val capacity = queryLongSysCtlByName(
            name = "hw.memsize",
            default = 0L
        )!!

        return MacOsPhysicalMemoryImpl(
            bankLabel = UNKNOWN,
            capacity = capacity,
            clockSpeed = loadMemoryClockSpeed(),
            manufacturer = service.readStringFromRegistryOrUnknown(
                key = "dram-vendor"
            ),
            memoryType = service.readStringFromRegistryOrUnknown(
                key = "dram-type"
            ),
            partNumber = service.readStringFromRegistryOrUnknown(
                key = "dram-part-number"
            ),
            serialNumber = service.readStringFromRegistryOrUnknown(
                key = "dram-serial-number"
            )
        )
    }

    /**
     * Method used to read the memory frequency from the CPU device tree entry
     *
     * @return the decoded frequency in MHz, or zero for missing or unsupported data, as [Long]
     */
    @Loader
    private fun loadMemoryClockSpeed(): Long {
        val rawFreq = useRegistryFromPath(
            path = "IODeviceTree:/cpus/cpu0@0"
        ) { cpu ->
            cpu.readFromRegistry(
                key = "memory-frequency"
            )
        }

        return resolveMemoryFreq(
            frequencyRaw = rawFreq
        )
    }

    /**
     * Method used to decode a four-byte or eight-byte little-endian frequency
     *
     * @param frequencyRaw The frequency bytes expressed in Hz
     *
     * @return the frequency truncated to MHz, or zero for an unsupported byte count, as [Long]
     */
    @Resolver
    private fun resolveMemoryFreq(
        frequencyRaw: ByteArray
    ): Long {
        val freqSize = frequencyRaw.size
        if (freqSize != 4 && freqSize != 8)
            return 0L

        val freqHz = resolveFreq(
            rawFreq = frequencyRaw
        )

        return freqHz / 1_000_000
    }

    /**
     * Method used to map Intel memory slots from the memory device tree entry
     *
     * @return the mapped nonzero-capacity slots, or an empty list when capacity data is absent, as [List] of [MacOsPhysicalMemoryImpl]
     */
    override fun mapForIntel(): List<MacOsPhysicalMemoryImpl> {
        return useRegistryFromPath(
            path = "IODeviceTree:/memory"
        ) { service ->
            loadIntelPhysicalMemory(
                service = service
            )
        }
    }

    /**
     * Method used to combine parallel Intel memory properties by their original slot indices
     *
     * Each complete eight-byte capacity record identifies one slot; incomplete trailing bytes are ignored
     * The first slot-name entry is skipped because it contains the registry prefix
     * Missing or blank metadata uses [UNKNOWN], and an unparseable speed uses zero
     *
     * @param service The borrowed memory registry entry
     *
     * @return the mapped slots with nonzero capacity as [List] of [MacOsPhysicalMemoryImpl]
     */
    @Loader
    private fun loadIntelPhysicalMemory(
        service: io_registry_entry_t
    ): List<MacOsPhysicalMemoryImpl> {
        val sizes = service.readFromRegistry(
            key = "reg"
        )
        val slots = service.readStringsFromRegistry(
            key = "slot-names"
        )
        val speeds = service.readStringsFromRegistry(
            key = "dimm-speeds"
        )
        val manufacturers = service.readStringsFromRegistry(
            key = "dimm-manufacturer"
        )
        val types = service.readStringsFromRegistry(
            key = "dimm-types"
        )
        val partNumbers = service.readStringsFromRegistry(
            key = "dimm-part-number"
        )
        val serialNumbers = service.readStringsFromRegistry(
            key = "dimm-serial-number"
        )

        return buildList {
            repeat((sizes.size / 8)) { index ->
                val capacity = resolveIntelMemoryCapacity(
                    raw = sizes,
                    offset = index * 8
                )
                if (capacity == 0L)
                    return@repeat

                add(
                    MacOsPhysicalMemoryImpl(
                        bankLabel = slots.resolveIntelNativeRegistryValue(
                            index = index + 1
                        ),
                        capacity = capacity,
                        clockSpeed = speeds.resolveClockSpeedValue(
                            index = index
                        ),
                        manufacturer = manufacturers.resolveIntelNativeRegistryValue(
                            index = index
                        ),
                        memoryType = types.resolveIntelNativeRegistryValue(
                            index = index
                        ),
                        partNumber = partNumbers.resolveIntelNativeRegistryValue(
                            index = index
                        ),
                        serialNumber = serialNumbers.resolveIntelNativeRegistryValue(
                            index = index
                        )
                    )
                )
            }
        }
    }

    /**
     * Method used to decode an Intel memory capacity record
     *
     * The high 32-bit word precedes the low word, with each word encoded in little-endian order
     *
     * @param raw The registry bytes containing the capacity records
     * @param offset The starting byte offset of a complete eight-byte record
     *
     * @return the decoded capacity in bytes as [Long]
     * @throws IndexOutOfBoundsException If the eight-byte record is outside the supplied array
     */
    @Resolver
    private fun resolveIntelMemoryCapacity(
        raw: ByteArray,
        offset: Int
    ): Long {
        var high = 0L
        var low = 0L

        repeat(4) { i ->
            high = high or ((raw[offset + i].toLong() and 0xffL) shl (i * 8))
            low = low or ((raw[offset + 4 + i].toLong() and 0xffL) shl (i * 8))
        }

        return (high shl 32) or low
    }

    /**
     * Method used to resolve memory metadata at the specified index
     *
     * @receiver The decoded registry entries
     * @param index The zero-based entry index
     *
     * @return the original non-blank entry, or [UNKNOWN] for a missing or blank entry, as [String]
     */
    @Resolver
    private fun List<String>.resolveIntelNativeRegistryValue(
        index: Int
    ): String {
        return getOrElse(
            index = index,
            defaultValue = { "" }
        ).ifBlank { UNKNOWN }
    }

    /**
     * Method used to parse the first space-delimited token of a memory speed entry
     *
     * @receiver The decoded memory speed entries
     * @param index The zero-based entry index
     *
     * @return the numeric speed in MHz, or zero when the entry is missing or unparseable, as [Long]
     */
    @Resolver
    private fun List<String>.resolveClockSpeedValue(
        index: Int
    ): Long {
        val rawClockSpeed = resolveIntelNativeRegistryValue(
            index = index
        )

        return try {
            rawClockSpeed.trim().split(" ")[0].toLong()
        } catch (_: NumberFormatException) {
            return 0
        }
    }

}