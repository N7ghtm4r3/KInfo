package com.tecknobit.kinfo.model.desktop.hardware.memory

/**
 * `GlobalMemory` represents the system's memory information.
 * It provides details about the total memory, available memory, page size, virtual memory, and physical memory.
 *
 * @author N7ghtm4r3
 */
interface GlobalMemory {

    /**
     * `total` The total amount of memory in the system (in bytes)
     */
    val total: Long

    /**
     * `available` The amount of available memory in the system (in bytes)
     */
    val available: Long

    /**
     * `pageSize` The system's memory page size (in bytes)
     */
    val pageSize: Long

    /**
     * `virtualMemory` The virtual memory information of the system.
     * It includes details like swap space and memory limits
     */
    val virtualMemory: VirtualMemory

    /**
     * `physicalMemory` The list of physical memory information in the system.
     * It provides details about each physical memory module installed
     */
    val physicalMemory: List<PhysicalMemory>

}
