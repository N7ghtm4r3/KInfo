package com.tecknobit.kinfo.model.hardware.memory

import com.tecknobit.kinfo.model.desktop.hardware.memory.GlobalMemory
import com.tecknobit.kinfo.model.desktop.hardware.memory.PhysicalMemory
import com.tecknobit.kinfo.model.desktop.hardware.memory.VirtualMemory

/**
 * Implementation of the `GlobalMemory` interface.
 * This class provides detailed information about the system's global memory, including the total memory, available memory,
 * page size, virtual memory, and physical memory modules.
 *
 * @param globalMemory The source of the global memory information, provided by the `oshi.hardware.GlobalMemory` class.
 *
 * @author N7ghtm4r3
 *
 * @see GlobalMemory
 */
class GlobalMemoryImpl(
    private val globalMemory: oshi.hardware.GlobalMemory
) : GlobalMemory {

    /**
     * `total` The total amount of memory in the system (in bytes)
     */
    override val total: Long = globalMemory.total

    /**
     * `available` The amount of available memory in the system (in bytes)
     */
    override val available: Long = globalMemory.available

    /**
     * `pageSize` The system's memory page size (in bytes)
     */
    override val pageSize: Long = globalMemory.pageSize

    /**
     * `virtualMemory` The virtual memory information of the system, which includes swap space and memory limits
     */
    override val virtualMemory: VirtualMemory by lazy {
        VirtualMemoryImpl(
            virtualMemory = globalMemory.virtualMemory
        )
    }

    /**
     * `physicalMemory` A list of physical memory modules installed in the system, including details about each individual module
     */
    override val physicalMemory: List<PhysicalMemory>
        get() = loadPhysicalMemories(
            sourceList = globalMemory.physicalMemory
        )

    /**
     * Loads the physical memory modules from the source list
     *
     * @param sourceList The source list of `oshi.hardware.PhysicalMemory` objects
     * @return A list of `PhysicalMemory` objects encapsulating physical memory information
     */
    private fun loadPhysicalMemories(
        sourceList: List<oshi.hardware.PhysicalMemory>
    ): List<PhysicalMemory> {
        val result = mutableListOf<PhysicalMemory>()
        sourceList.forEach { physicalMemory ->
            result.add(
                PhysicalMemoryImpl(
                    bankLabel = physicalMemory.bankLabel,
                    capacity = physicalMemory.capacity,
                    clockSpeed = physicalMemory.clockSpeed,
                    manufacturer = physicalMemory.manufacturer,
                    memoryType = physicalMemory.memoryType,
                    partNumber = physicalMemory.partNumber,
                    serialNumber = physicalMemory.serialNumber
                )
            )
        }
        return result
    }

}
