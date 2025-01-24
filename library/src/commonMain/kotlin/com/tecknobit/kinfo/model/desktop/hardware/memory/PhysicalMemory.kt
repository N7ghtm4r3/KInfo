package com.tecknobit.kinfo.model.desktop.hardware.memory

/**
 * Represents the details of a physical memory module installed in the system.
 * This interface provides information such as the memory's bank label, capacity,
 * clock speed, manufacturer, memory type, part number, and serial number.
 *
 * @author N7ghtm4r3
 */
interface PhysicalMemory {

    /**
     * `bankLabel` The label or identifier of the memory bank where the module is located.
     */
    val bankLabel: String

    /**
     * `capacity` The total capacity of the memory module (in bytes).
     */
    val capacity: Long

    /**
     * `clockSpeed` The clock speed of the memory module (in MHz).
     */
    val clockSpeed: Long

    /**
     * `manufacturer` The manufacturer of the memory module (e.g., Corsair, Kingston).
     */
    val manufacturer: String

    /**
     * `memoryType` The type of memory (e.g., DDR4, DDR3).
     */
    val memoryType: String

    /**
     * `partNumber` The part number of the memory module.
     */
    val partNumber: String

    /**
     * `serialNumber` The serial number of the memory module.
     */
    val serialNumber: String

}
