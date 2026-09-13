package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsPhysicalProcessorImpl
import com.tecknobit.kinfo.utils.queryIntSysCtlByName
import platform.IOKit.io_service_t

class MacOsPhysicalProcessorMapper : MacOsHardwareMapper<List<MacOsPhysicalProcessorImpl>>() {

    override fun mapFromNative(): List<MacOsPhysicalProcessorImpl> {
        val physicalCount = loadPhysicalCount()
        val packages = loadPackagesCount()
        val physicalProcessors = mutableListOf<MacOsPhysicalProcessorImpl>()

        useIOServices(
            serviceName = IO_PLATFORM_DEVICE_SERVICE
        ) { index, service ->
            physicalProcessors.add(
                MacOsPhysicalProcessorImpl(
                    physicalPackageNumber = index.resolvePhysicalPackageNumber(
                        physicalCount = physicalCount,
                        packages = packages
                    ),
                    physicalProcessorNumber = index.resolvePhysicalProcessorNumber(
                        physicalCount = physicalCount,
                        packages = packages
                    ),
                    efficiency = resolveEfficiency(
                        service = service
                    ),
                    idString = resolveId(
                        service = service
                    )
                )
            )
        }

        return physicalProcessors
    }

    @Loader
    private fun loadPhysicalCount(): Int {
        return queryIntSysCtlByName(
            name = "hw.physicalcpu",
            default = 0
        )!!
    }

    @Loader
    private fun loadPackagesCount(): Int {
        return queryIntSysCtlByName(
            name = "hw.packages",
            default = 1
        )!!
    }

    @Resolver
    private fun Int.resolvePhysicalPackageNumber(
        physicalCount: Int,
        packages: Int
    ): Int {
        return this / (physicalCount / packages)
    }

    @Resolver
    private fun Int.resolvePhysicalProcessorNumber(
        physicalCount: Int,
        packages: Int
    ): Int {
        return this % (physicalCount / packages)
    }

    @Resolver
    private fun resolveEfficiency(
        service: io_service_t
    ): Int {
        val clusterType = service.readStringFromRegistry(
            key = "cluster-type"
        )

        return when (clusterType) {
            "P" -> 1
            "E" -> 0

            else -> 0
        }
    }

    @Resolver
    private fun resolveId(
        service: io_service_t
    ): String {
        val compatible = service.readStringFromRegistry(
            key = "compatible"
        )

        return compatible.replace("\u0000", "-")
    }

}