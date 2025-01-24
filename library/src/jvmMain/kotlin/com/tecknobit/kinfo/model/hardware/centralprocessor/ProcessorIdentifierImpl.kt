package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.ProcessorIdentifier
import oshi.hardware.CentralProcessor

/**
 * `ProcessorIdentifierImpl` is an implementation of the `ProcessorIdentifier` interface.
 * It retrieves CPU identification and characteristics information using the provided `processorIdentifierInfo` from `CentralProcessor`.
 *
 * This class encapsulates the details about the processor, including vendor, model, family, and whether the CPU is 64-bit.
 * It provides the necessary functionality to access CPU-related data for system monitoring and analysis.
 *
 * @param processorIdentifierInfo the underlying processor identifier info object from `CentralProcessor`
 *
 * @author N7ghtm4r3
 *
 * @see ProcessorIdentifier
 */
class ProcessorIdentifierImpl(
    val processorIdentifierInfo: CentralProcessor.ProcessorIdentifier,
) : ProcessorIdentifier {

    /**
     * `cpuVendor` the name of the CPU vendor (e.g., Intel, AMD)
     */
    override val cpuVendor: String = processorIdentifierInfo.vendor

    /**
     * `cpuName` the name of the CPU model (e.g., Intel Core i7)
     */
    override val cpuName: String = processorIdentifierInfo.name

    /**
     * `cpuFamily` the family of the CPU (e.g., Intel Core, AMD Ryzen)
     */
    override val cpuFamily: String = processorIdentifierInfo.family

    /**
     * `cpuModel` the model of the CPU (e.g., 6700K, 3600X)
     */
    override val cpuModel: String = processorIdentifierInfo.model

    /**
     * `cpuStepping` the CPU stepping (e.g., 4, B)
     */
    override val cpuStepping: String = processorIdentifierInfo.stepping

    /**
     * `processorId` a unique identifier for the processor, often specific to the hardware or system
     */
    override val processorId: String = processorIdentifierInfo.processorID

    /**
     * `cpuIdentifier` a string identifier for the CPU, may combine various identifiers like model and vendor
     */
    override val cpuIdentifier: String = processorIdentifierInfo.identifier

    /**
     * `isCpu64bit` flag indicating if the CPU is 64-bit
     */
    override val isCpu64bit: Boolean = processorIdentifierInfo.isCpu64bit

    /**
     * `cpuVendorFreq` the frequency of the CPU as reported by the vendor (in Hz)
     */
    override val cpuVendorFreq: Long = processorIdentifierInfo.vendorFreq

}
