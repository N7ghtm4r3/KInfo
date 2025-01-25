package com.tecknobit.kinfo.model.desktop.hardware.memory

/**
 * `VirtualMemory` represents the virtual memory statistics of the system.
 * It provides detailed information about the swap memory, virtual memory limits, and usage statistics.
 *
 * @author N7ghtm4r3
 */
interface VirtualMemory {

    /**
     * `swapTotal` The total amount of swap space available in the system (in bytes).
     */
    val swapTotal: Long

    /**
     * `swapUsed` The amount of swap space currently being used (in bytes).
     */
    val swapUsed: Long

    /**
     * `virtualMax` The maximum amount of virtual memory that the system can use (in bytes).
     */
    val virtualMax: Long

    /**
     * `virtualInUse` The amount of virtual memory currently in use (in bytes).
     */
    val virtualInUse: Long

    /**
     * `swapPagesIn` The number of pages that have been swapped in from disk into memory.
     */
    val swapPagesIn: Long

    /**
     * `swapPagesOut` The number of pages that have been swapped out from memory to disk.
     */
    val swapPagesOut: Long

}