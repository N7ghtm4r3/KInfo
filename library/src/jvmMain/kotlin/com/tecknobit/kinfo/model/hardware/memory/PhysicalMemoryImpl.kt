package com.tecknobit.kinfo.model.hardware.memory

import com.tecknobit.kinfo.model.desktop.hardware.memory.PhysicalMemory

/**
 * Implementation of the `PhysicalMemory` interface.
 * This class provides detailed information about a physical memory module installed in the system,
 * including the bank label, capacity, clock speed, manufacturer, memory type, part number, and serial number.
 *
 * @param bankLabel The label or identifier of the memory bank where the module is located.
 * @param capacity The total capacity of the memory module (in bytes).
 * @param clockSpeed The clock speed of the memory module (in MHz).
 * @param manufacturer The manufacturer of the memory module (e.g., Corsair, Kingston).
 * @param memoryType The type of memory (e.g., DDR4, DDR3).
 * @param partNumber The part number of the memory module.
 * @param serialNumber The serial number of the memory module.
 *
 * @author N7ghtm4r3
 *
 * @see PhysicalMemory
 */
class PhysicalMemoryImpl(
    override val bankLabel: String,
    override val capacity: Long,
    override val clockSpeed: Long,
    override val manufacturer: String,
    override val memoryType: String,
    override val partNumber: String,
    override val serialNumber: String
) : PhysicalMemory
