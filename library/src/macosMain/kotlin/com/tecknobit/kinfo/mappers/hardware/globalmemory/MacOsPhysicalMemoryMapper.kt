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

class MacOsPhysicalMemoryMapper : MacOsSplitHardwareMapper<List<MacOsPhysicalMemoryImpl>>() {

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

    override fun mapForIntel(): List<MacOsPhysicalMemoryImpl> {
        TODO("Not yet implemented")
    }

}