package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsProcessorIdentifierImpl
import com.tecknobit.kinfo.mappers.NativeMapper
import com.tecknobit.kinfo.utils.queryIntSysCtlByName
import com.tecknobit.kinfo.utils.queryLongSysCtlByName
import com.tecknobit.kinfo.utils.queryStringSysCtlByName

/**
 * The `MacOsProcessorIdentifierMapper` class is useful to define the native macOS processor identification mapping
 *
 * The mapping is currently unavailable and throws when invoked
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 * @see MacOsHardwareMapper
 * @see MacOsSplitHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsProcessorIdentifierMapper : MacOsSplitHardwareMapper<MacOsProcessorIdentifierImpl>() {

    override fun mapForSilicon(): MacOsProcessorIdentifierImpl {
        val product = loadRegistryFromPath("IODeviceTree:/product")
        val pmgr = loadRegistryFromPath("IODeviceTree:/arm-io/pmgr")

        val cpuVendor = useIOService(
            serviceName = IO_PLATFORM_EXPERT_DEVICE_SERVICE,
            usage = { service ->
                service.readStringFromRegistry(
                    key = "manufacturer"
                )
            }
        )

        val cpuModel = useIOService(
            serviceName = IO_PLATFORM_DEVICE_SERVICE,
            usage = { service ->
                service.readStringFromRegistry(
                    key = "compatible"
                )
            }
        )

        val cpuVendorFreq = pmgr.readFromRegistry(
            key = "voltage-states5-sram"
        )

        val macOsProcessorIdentifier = try {
            MacOsProcessorIdentifierImpl(
                cpuVendor = cpuVendor,
                cpuName = product.readStringFromRegistry(
                    key = "product-soc-name"
                ),
                cpuFamily = resolveCpuFamily(),
                cpuModel = cpuModel,
                cpuStepping = UNKNOWN,
                processorId = "",
                cpuIdentifier = "",
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

    override fun mapForIntel(): MacOsProcessorIdentifierImpl {
        val cpuVendor = queryStringSysCtlByName(
            name = "machdep.cpu.vendor",
            default = UNKNOWN
        )!!

        val cpuName = queryStringSysCtlByName(
            name = "machdep.cpu.brand_string",
            default = UNKNOWN
        )!!

        val cpuModel = queryIntSysCtlByName(
            name = "machdep.cpu.model",
            default = 0
        )!!

        val cpuStepping = queryIntSysCtlByName(
            name = "machdep.cpu.stepping",
            default = 0
        )!!

        val cpuVendorFreq = queryLongSysCtlByName(
            name = "hw.cpufrequency",
            default = 0
        )!!

        return MacOsProcessorIdentifierImpl(
            cpuVendor = cpuVendor,
            cpuName = cpuName,
            cpuFamily = resolveCpuFamily(),
            cpuModel = cpuModel.toString(),
            cpuStepping = cpuStepping.toString(),
            processorId = "",
            cpuIdentifier = "",
            isCpu64bit = resolveIs64Bit(),
            cpuVendorFreq = cpuVendorFreq
        )
    }

    @Resolver
    private fun resolveCpuFamily(): String {
        return queryIntSysCtlByName(
            name = "machdep.cpu.family",
            default = 0
        )!!.toString()
    }

    @Resolver
    private fun resolveIs64Bit(): Boolean {
        return queryIntSysCtlByName(
            name = "hw.cpu64bit_capable",
            default = 0
        )!! != 0
    }

    @Resolver
    private fun resolveCpuFreq(
        cpuFreqRaw: ByteArray
    ): Long {
        if (cpuFreqRaw.size < 8)
            return 0L

        val offset = cpuFreqRaw.size - 8
        var frequency = 0L

        for (index in 0 until 4) {
            frequency = frequency or ((cpuFreqRaw[offset + index].toLong() and 0xFFL) shl (index * 8))
        }

        return frequency
    }

}