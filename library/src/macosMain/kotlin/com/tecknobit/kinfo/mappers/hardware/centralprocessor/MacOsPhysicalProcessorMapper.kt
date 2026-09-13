package com.tecknobit.kinfo.mappers.hardware.centralprocessor

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsPhysicalProcessorImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.utils.queryIntSysCtlByName
import platform.IOKit.io_service_t

/**
 * The `MacOsPhysicalProcessorMapper` class is useful to map CPU registry entries to physical processor descriptions
 *
 * Package and core numbers are derived from accepted-entry order and assume evenly distributed cores
 * The mapper selects entries whose `device_type` is `cpu` and does not reconstruct Intel ACPI topology
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsPhysicalProcessorMapper : MacOsHardwareMapper<List<MacOsPhysicalProcessorImpl>>() {

    /**
     * Method used to map accepted CPU entries using native counts and per-entry registry properties
     *
     * No matching CPU entries produce an empty list and missing core properties use resolver fallbacks
     * The accepted-entry index is used for numbering rather than a native CPU identifier
     *
     * @return the mapped processor descriptions in accepted-entry order as [List] of [MacOsPhysicalProcessorImpl]
     * @throws IllegalStateException If the IOKit matching query fails
     * @throws ArithmeticException If an accepted entry is mapped with a zero package count or zero cores per package
     */
    override fun mapFromNative(): List<MacOsPhysicalProcessorImpl> {
        val physicalCount = loadPhysicalCount()
        val packages = loadPackagesCount()
        val physicalProcessors = mutableListOf<MacOsPhysicalProcessorImpl>()

        useIOServices(
            serviceName = IO_PLATFORM_DEVICE_SERVICE,
            consumeServiceIf = { service ->
                isCpuEntry(
                    service = service
                )
            }
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

    /**
     * Method used to read `hw.physicalcpu` with zero as the failed-query fallback
     *
     * @return the native count or fallback as [Int]
     */
    @Loader
    private fun loadPhysicalCount(): Int {
        return queryIntSysCtlByName(
            name = "hw.physicalcpu",
            default = 0
        )!!
    }

    /**
     * Method used to read `hw.packages` with one as the failed-query fallback
     *
     * @return the native count or fallback as [Int]
     */
    @Loader
    private fun loadPackagesCount(): Int {
        return queryIntSysCtlByName(
            name = "hw.packages",
            default = 1
        )!!
    }

    /**
     * Method used to derive the package number assuming evenly distributed, package-ordered cores
     *
     * The counts must be positive and evenly divisible for the derived topology to be meaningful
     *
     * @receiver The zero-based accepted-entry index
     * @param physicalCount The physical core count
     * @param packages The physical package count
     *
     * @return the derived package number as [Int]
     * @throws ArithmeticException If the package count or computed cores per package is zero
     */
    @Resolver
    private fun Int.resolvePhysicalPackageNumber(
        physicalCount: Int,
        packages: Int
    ): Int {
        return this / (physicalCount / packages)
    }

    /**
     * Method used to derive the core number within its package assuming evenly distributed, package-ordered cores
     *
     * The counts must be positive and evenly divisible for the derived topology to be meaningful
     *
     * @receiver The zero-based accepted-entry index
     * @param physicalCount The physical core count
     * @param packages The physical package count
     *
     * @return the derived core number within its package as [Int]
     * @throws ArithmeticException If the package count or computed cores per package is zero
     */
    @Resolver
    private fun Int.resolvePhysicalProcessorNumber(
        physicalCount: Int,
        packages: Int
    ): Int {
        return this % (physicalCount / packages)
    }

    /**
     * Method used to check whether the registry entry has `cpu` as its `device_type`
     *
     * @param service The borrowed service handle to inspect
     *
     * @return whether the entry declares the CPU device type as [Boolean]
     */
    private fun isCpuEntry(
        service: io_service_t
    ): Boolean {
        val deviceType = service.readStringFromRegistry(
            key = "device_type"
        )

        return deviceType == "cpu"
    }

    /**
     * Method used to map `cluster-type` to the processor performance class
     *
     * `P` maps to one and `E` maps to zero, with zero also used for missing or unrecognized values
     *
     * @param service The borrowed CPU service handle to inspect
     *
     * @return the resolved performance class as [Int]
     */
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

    /**
     * Method used to read the core compatibility description and replace embedded null characters with hyphens
     *
     * The description is not a unique core identifier and preserves native letter case
     * An unavailable property produces an empty string
     *
     * @param service The borrowed CPU service handle to inspect
     *
     * @return the normalized compatibility description as [String]
     */
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