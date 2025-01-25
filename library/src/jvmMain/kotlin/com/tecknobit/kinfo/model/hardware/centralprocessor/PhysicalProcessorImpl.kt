package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.PhysicalProcessor

/**
 * Implementation of the [PhysicalProcessor] interface, representing a physical processor in the system.
 * This class encapsulates the details about the physical processor's package, number, efficiency,
 * and unique identifier string.
 *
 * @author N7ghtm4r3
 *
 * @see PhysicalProcessor
 */
class PhysicalProcessorImpl(

    /**
     * `physicalPackageNumber` the unique identifier for the physical CPU package this processor belongs to
     */
    override val physicalPackageNumber: Int,

    /**
     * `physicalProcessorNumber` the unique identifier for the physical processor within the package
     */
    override val physicalProcessorNumber: Int,

    /**
     * `efficiency` the efficiency level of the physical processor
     */
    override val efficiency: Int,

    /**
     * `idString` a unique string identifier for this physical processor
     */
    override val idString: String

) : PhysicalProcessor
