package com.tecknobit.kinfo.model.hardware.memory

import com.tecknobit.kinfo.model.desktop.hardware.memory.VirtualMemory

/**
 * Implementation of the `VirtualMemory` interface.
 * This class provides details about the virtual memory statistics of the system,
 * including swap memory usage, virtual memory limits, and the number of swap pages.
 *
 * @param virtualMemory The source object containing virtual memory information from the hardware.
 *
 * @see VirtualMemory
 */
class VirtualMemoryImpl(
    virtualMemory: oshi.hardware.VirtualMemory
) : VirtualMemory {

    /**
     * `swapTotal` The total amount of swap space available in the system (in bytes)
     */
    override val swapTotal: Long = virtualMemory.swapTotal

    /**
     * `swapUsed` The amount of swap space currently being used (in bytes)
     */
    override val swapUsed: Long = virtualMemory.swapUsed

    /**
     * `virtualMax` The maximum amount of virtual memory that the system can use (in bytes)
     */
    override val virtualMax: Long = virtualMemory.virtualMax

    /**
     * `virtualInUse` The amount of virtual memory currently in use (in bytes)
     */
    override val virtualInUse: Long = virtualMemory.virtualInUse

    /**
     * `swapPagesIn` The number of pages that have been swapped in from disk into memory
     */
    override val swapPagesIn: Long = virtualMemory.swapPagesIn

    /**
     * `swapPagesOut` The number of pages that have been swapped out from memory to disk
     */
    override val swapPagesOut: Long = virtualMemory.swapPagesOut

}
