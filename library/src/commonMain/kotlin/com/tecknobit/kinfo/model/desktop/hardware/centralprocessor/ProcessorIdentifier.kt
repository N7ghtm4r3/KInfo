package com.tecknobit.kinfo.model.desktop.hardware.centralprocessor

/**
 * `ProcessorIdentifier` interface defines the properties related to the CPU identifier and its characteristics.
 * These properties allow retrieval of information such as the CPU vendor, name, family, model, stepping, and more.
 *
 * This interface provides the necessary details to identify and describe the processor's specifications, including
 * whether the CPU is 64-bit and its vendor's frequency.
 *
 * @author N7ghtm4r3
 */
interface ProcessorIdentifier {

    /**
     * `cpuVendor` the name of the CPU vendor (e.g., Intel, AMD)
     */
    val cpuVendor: String

    /**
     * `cpuName` the name of the CPU model (e.g., Intel Core i7)
     */
    val cpuName: String

    /**
     * `cpuFamily` the family of the CPU (e.g., Intel Core, AMD Ryzen)
     */
    val cpuFamily: String

    /**
     * `cpuModel` the model of the CPU (e.g., 6700K, 3600X)
     */
    val cpuModel: String

    /**
     * `cpuStepping` the CPU stepping (e.g., 4, B)
     */
    val cpuStepping: String

    /**
     * `processorId` a unique identifier for the processor, often specific to the hardware or system
     */
    val processorId: String

    /**
     * `cpuIdentifier` a string identifier for the CPU, may combine various identifiers like model and vendor
     */
    val cpuIdentifier: String

    /**
     * `isCpu64bit` flag indicating if the CPU is 64-bit
     */
    val isCpu64bit: Boolean

    /**
     * `cpuVendorFreq` the frequency of the CPU as reported by the vendor (in Hz)
     */
    val cpuVendorFreq: Long

}
