package com.tecknobit.kinfo.model.desktop.hardware.centralprocessor

/**
 * Represents a physical processor in the system.
 * This interface provides information about the physical CPU core, its package,
 * and efficiency, along with its unique identifier string.
 *
 * @author N7ghtm4r3
 */
interface PhysicalProcessor {

    /**
     * The unique identifier for the physical CPU package this processor belongs to
     */
    val physicalPackageNumber: Int

    /**
     * The unique identifier for the physical processor in the package
     */
    val physicalProcessorNumber: Int

    /**
     * The efficiency level of the physical processor.
     * This value indicates how efficiently the processor operates
     */
    val efficiency: Int

    /**
     * A unique string identifier for this physical processor, often used to distinguish
     * between different processors in the system
     */
    val idString: String

}
