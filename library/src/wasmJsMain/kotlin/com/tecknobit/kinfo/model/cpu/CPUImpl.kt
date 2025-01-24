package com.tecknobit.kinfo.model.cpu

import com.tecknobit.kinfo.model.web.cpu.CPU

/**
 * Implements the `CPU` interface, providing the CPU architecture information.
 *
 * This class extracts the CPU architecture from the provided `CPU` object,
 * ensuring a null-safe value is used via the `safeValue()` method from the `WebInfoItem` interface.
 *
 * @param parsedCPU The `CPU` object containing the parsed CPU information, used to extract the architecture.
 *
 * @see CPU
 *
 * @author N7ghtm4r3
 */
class CPUImpl(
    parsedCPU: com.tecknobit.kinfo.model.CPU
) : CPU {

    /**
     * `architecture` The architecture of the CPU (e.g., x86, ARM).
     * This value is safely extracted from the provided `parsedCPU` object.
     */
    override val architecture: String = parsedCPU.architecture.safeValue()

}
