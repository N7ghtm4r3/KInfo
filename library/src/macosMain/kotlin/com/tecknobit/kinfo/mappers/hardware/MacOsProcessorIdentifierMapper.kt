package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsProcessorIdentifierImpl
import com.tecknobit.kinfo.utils.hex32
import com.tecknobit.kinfo.utils.queryIntSysCtlByName
import com.tecknobit.kinfo.utils.queryLongSysCtlByName
import com.tecknobit.kinfo.utils.queryStringSysCtlByName

/**
 * The `MacOsProcessorIdentifierMapper` class is useful to define the native macOS processor identification mapping
 *
 * Apple Silicon models use the commercial chip name, while Intel models use the numeric CPU model
 * Processor IDs combine architecture-specific values and do not represent unique hardware serial numbers
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsSplitHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsProcessorIdentifierMapper : MacOsSplitHardwareMapper<MacOsProcessorIdentifierImpl>() {

    /**
     * Method used to map Apple Silicon processor identification from registry properties and system control values
     *
     * The commercial chip name is used for both the name and model, and stepping is reported as [UNKNOWN]
     * The processor ID combines the CPU type and family as two hexadecimal blocks
     * The frequency is decoded from the final performance-core voltage-state record
     *
     * @return the mapped Apple Silicon processor identification as [MacOsProcessorIdentifierImpl]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    override fun mapForSilicon(): MacOsProcessorIdentifierImpl {
        val cpuVendor = useIOService(
            serviceName = IO_PLATFORM_EXPERT_DEVICE_SERVICE,
            usage = { service ->
                service.readStringFromRegistry(
                    key = "manufacturer"
                )
            }
        )

        val product = loadRegistryFromPath("IODeviceTree:/product")
        val pmgr = loadRegistryFromPath("IODeviceTree:/arm-io/pmgr")

        val cpuName = product.readStringFromRegistry(
            key = "product-soc-name"
        )

        val cpuFamily = resolveCpuFamily(
            key = "hw.cpufamily"
        )

        val cpuType = queryIntSysCtlByName(
            name = "hw.cputype",
            default = 0
        )!!.toLong()

        val cpuVendorFreq = pmgr.readFromRegistry(
            key = "voltage-states5-sram"
        )

        val macOsProcessorIdentifier = try {
            MacOsProcessorIdentifierImpl(
                cpuVendor = cpuVendor,
                cpuName = cpuName,
                cpuFamily = cpuFamily.toString(),
                cpuModel = cpuName,
                cpuStepping = UNKNOWN,
                processorId = hex32(cpuType) + hex32(cpuFamily.toLong()),
                cpuIdentifier = "$cpuName Family $cpuFamily",
                isCpu64bit = resolveIs64Bit(),
                cpuVendorFreq = resolveCpuFreq(
                    cpuFreqRaw = cpuVendorFreq
                )
            )
        } finally {
            product.release()
            pmgr.release()
        }

        return macOsProcessorIdentifier
    }

    /**
     * Method used to map Intel processor identification from system control values
     *
     * The processor ID combines the low 32 feature bits with the CPU signature as two hexadecimal blocks
     * Missing textual values use [UNKNOWN], while missing numeric values use zero
     *
     * @return the mapped Intel processor identification as [MacOsProcessorIdentifierImpl]
     */
    override fun mapForIntel(): MacOsProcessorIdentifierImpl {
        val cpuVendor = queryStringSysCtlByName(
            name = "machdep.cpu.vendor",
            default = UNKNOWN
        )!!

        val cpuName = queryStringSysCtlByName(
            name = "machdep.cpu.brand_string",
            default = UNKNOWN
        )!!

        val cpuFamily = resolveCpuFamily(
            key = "machdep.cpu.family"
        ).toString()

        val cpuModel = queryIntSysCtlByName(
            name = "machdep.cpu.model",
            default = 0
        )!!

        val cpuStepping = queryIntSysCtlByName(
            name = "machdep.cpu.stepping",
            default = 0
        ).toString()

        val signature = queryIntSysCtlByName(
            name = "machdep.cpu.signature",
            default = 0
        )!!.toLong()

        val featureBits = queryLongSysCtlByName(
            name = "machdep.cpu.feature_bits",
            default = 0
        )!!

        val cpuVendorFreq = queryLongSysCtlByName(
            name = "hw.cpufrequency",
            default = 0
        )!!

        return MacOsProcessorIdentifierImpl(
            cpuVendor = cpuVendor,
            cpuName = cpuName,
            cpuFamily = cpuFamily,
            cpuModel = cpuModel.toString(),
            cpuStepping = cpuStepping,
            processorId = hex32(featureBits) + hex32(signature),
            cpuIdentifier = "$cpuName Model $cpuModel Family $cpuFamily Stepping $cpuStepping",
            isCpu64bit = resolveIs64Bit(),
            cpuVendorFreq = cpuVendorFreq
        )
    }

    /**
     * Method used to retrieve the processor family from an architecture-specific system control property
     *
     * @param key The system control property name containing the processor family
     *
     * @return the signed family value, or zero when the query fails, as [Int]
     */
    @Resolver
    private fun resolveCpuFamily(
        key: String
    ): Int {
        return queryIntSysCtlByName(
            name = key,
            default = 0
        )!!
    }

    /**
     * Method used to check whether the processor reports 64-bit capability
     *
     * @return whether `hw.cpu64bit_capable` is nonzero, or false when the query fails, as [Boolean]
     */
    @Resolver
    private fun resolveIs64Bit(): Boolean {
        return queryIntSysCtlByName(
            name = "hw.cpu64bit_capable",
            default = 0
        )!! != 0
    }

    /**
     * Method used to decode the frequency from the final eight-byte voltage-state record
     *
     * The first four bytes of the final record are interpreted as an unsigned little-endian frequency in hertz
     * The final record is assumed to contain the maximum frequency without scanning the preceding records
     *
     * @param cpuFreqRaw The raw performance-core voltage-state table read from the registry
     *
     * @return the decoded frequency, or zero when fewer than eight bytes are available, as [Long]
     */
    @Resolver
    private fun resolveCpuFreq(
        cpuFreqRaw: ByteArray
    ): Long {
        if (cpuFreqRaw.size < 8)
            return 0L

        val offset = cpuFreqRaw.size - 8
        var frequency = 0L

        for (index in 0 until 4) {
            val sampleFreq = cpuFreqRaw[offset + index].toLong()
            val normalizedSampleFreq = sampleFreq and 0xFFL
            val shiftedSampleFreq = normalizedSampleFreq shl (index * 8)

            frequency = frequency or shiftedSampleFreq
        }

        return frequency
    }

}