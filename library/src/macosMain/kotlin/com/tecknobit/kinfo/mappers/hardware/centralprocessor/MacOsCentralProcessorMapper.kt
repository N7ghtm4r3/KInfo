package com.tecknobit.kinfo.mappers.hardware.centralprocessor

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsCentralProcessorImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsCentralProcessorMapper.Companion.INTEL_FEATURE_FLAGS
import com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsCentralProcessorMapper.Companion.SILICON_FEATURE_FLAGS
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsLogicalProcessor
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalProcessor
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorCache
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorIdentifier
import com.tecknobit.kinfo.utils.*
import platform.IOKit.io_service_t

/**
 * The `MacOsCentralProcessorMapper` class is useful to map native macOS processor information
 *
 * Intel frequencies come from system control values, while Apple Silicon frequencies come from registry cluster tables
 * Frequencies are nominal values, and each logical processor receives one value in the mapped list order
 * Processor descriptions and tick counters are read separately during each mapping
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsCentralProcessorMapper : MacOsHardwareMapper<MacOsCentralProcessorImpl>() {

    /**
     * The `Companion` object allows the mapper to share predefined feature names and system control keys
     *
     * @author N7ghtm4r3 - Tecknobit
     */
    private companion object {

        /**
         * `SILICON_FEATURE_FLAGS` the predefined subset of ARM feature names queried under `hw.optional.arm`
         */
        val SILICON_FEATURE_FLAGS = listOf(
            "FEAT_AES",
            "FEAT_PMULL",
            "FEAT_SHA1",
            "FEAT_SHA256",
            "FEAT_SHA512",
            "FEAT_SHA3",
            "FEAT_CRC32",
            "FEAT_LSE",
            "FEAT_DotProd",
            "FEAT_I8MM",
            "FEAT_BF16"
        )

        /**
         * `INTEL_FEATURE_FLAGS` the system control keys containing Intel feature lists, in query order
         */
        val INTEL_FEATURE_FLAGS = listOf(
            "machdep.cpu.features",
            "machdep.cpu.extfeatures",
            "machdep.cpu.leaf7_features"
        )

    }

    /**
     * Method used to assemble processor descriptions, nominal frequencies, features, and initial CPU tick snapshots
     *
     * Architecture-specific mappers provide identification and topology, while Mach queries provide fresh tick counters
     *
     * @return the mapped processor information as [MacOsCentralProcessorImpl]
     * @throws IllegalStateException If a required native service or CPU tick query fails or returns incomplete data
     * @throws ArithmeticException If physical processor mapping finds a zero package count or zero cores per package
     */
    override fun mapFromNative(): MacOsCentralProcessorImpl {
        val processorIdentifier = loadProcessorIdentifier()
        val logicalProcessors = loadLogicalProcessor()
        val isSilicon = isAppleSilicon()
        val nominalFrequencies = if (isSilicon)
            loadSiliconNominalFrequencies()
        else
            emptyMap()

        return MacOsCentralProcessorImpl(
            processorIdentifier = processorIdentifier,
            maxFreq = resolveMaxFreq(
                isSilicon = isSilicon,
                nominalFrequencies = nominalFrequencies,
                processorIdentifier = processorIdentifier
            ),
            currentFreq = resolveCurrentFreq(
                isSilicon = isSilicon,
                logicalProcessors = logicalProcessors,
                nominalFrequencies = nominalFrequencies,
                processorIdentifier = processorIdentifier
            ),
            logicalProcessors = logicalProcessors,
            physicalProcessors = loadPhysicalProcessor(),
            processorCaches = loadProcessorCache(),
            featureFlags = loadFeatureFlags(),
            systemCpuLoadTicks = loadSystemCpuLoadTicks(),
            processorCpuLoadTicks = loadProcessorCpuLoadTicks(),
            physicalPackageCount = resolvePhysicalPackageCount()
        )
    }

    /**
     * Method used to request the macOS processor identification information from its native mapper
     *
     * @return the mapped processor identification information as [MacOsProcessorIdentifier]
     * @throws IllegalStateException If the Apple Silicon platform expert service cannot be loaded
     */
    @Loader
    private fun loadProcessorIdentifier(): MacOsProcessorIdentifier {
        val macOsProcessorIdentifierMapper = MacOsProcessorIdentifierMapper()

        return macOsProcessorIdentifierMapper.mapFromNative()
    }

    /**
     * Method used to resolve the maximum nominal processor frequency for the detected architecture
     *
     * Apple Silicon uses the largest available cluster frequency
     * Intel queries `hw.cpufrequency_max`, falls back to the vendor frequency, and clamps negative values to zero
     *
     * @param isSilicon Whether the processor uses Apple Silicon
     * @param nominalFrequencies The Apple Silicon nominal frequencies in hertz, keyed by performance tier
     * @param processorIdentifier The processor identification supplying the Intel fallback frequency
     *
     * @return the maximum nominal frequency in hertz, or zero when unavailable, as [Long]
     */
    @Resolver
    private fun resolveMaxFreq(
        isSilicon: Boolean,
        nominalFrequencies: Map<Int, Long>,
        processorIdentifier: MacOsProcessorIdentifier
    ): Long {
        if (isSilicon)
            return nominalFrequencies.values.maxOrNull() ?: 0

        return queryLongSysCtlByName(
            name = "hw.cpufrequency_max",
            default = processorIdentifier.cpuVendorFreq
        )!!.coerceAtLeast(
            minimumValue = 0
        )
    }

    /**
     * Method used to resolve one nominal frequency per logical processor in the supplied list order
     *
     * Intel processors share the system-reported nominal frequency
     * Apple Silicon processors use their cluster's maximum nominal frequency, with zero for unavailable mappings
     * These values do not measure frequency changes under load
     *
     * @param isSilicon Whether the processor uses Apple Silicon
     * @param logicalProcessors The logical processor descriptions in output order
     * @param nominalFrequencies The Apple Silicon nominal frequencies in hertz, keyed by performance tier
     * @param processorIdentifier The processor identification supplying the Intel fallback frequency
     *
     * @return the nominal frequencies in hertz, one per logical processor, as [LongArray]
     * @throws IllegalStateException If the Apple Silicon native service matching query fails
     */
    @Resolver
    private fun resolveCurrentFreq(
        isSilicon: Boolean,
        logicalProcessors: List<MacOsLogicalProcessor>,
        nominalFrequencies: Map<Int, Long>,
        processorIdentifier: MacOsProcessorIdentifier
    ): LongArray {
        if (isSilicon)
            return loadSiliconCurrentFreq(
                logicalProcessors = logicalProcessors,
                nominalFrequencies = nominalFrequencies
            )

        val currentFreq = queryLongSysCtlByName(
            name = "hw.cpufrequency",
            default = processorIdentifier.cpuVendorFreq
        )!!.coerceAtLeast(
            minimumValue = 0
        )

        return LongArray(logicalProcessors.size) { currentFreq }
    }

    /**
     * Method used to read the maximum nominal CPU frequency for each Apple Silicon performance tier
     *
     * Cluster tables are discovered through `acc-clusters`, falling back to the legacy E/P tables when absent
     * Missing or malformed voltage-state tables leave the corresponding tier unavailable
     * Multiple valid tables for the same tier contribute their largest frequency
     *
     * @return the available maximum nominal frequencies in hertz keyed by performance tier as [Map]
     */
    @Loader
    private fun loadSiliconNominalFrequencies(): Map<Int, Long> {
        return useRegistryFromPath(
            path = "IODeviceTree:/arm-io/pmgr"
        ) { pmgr ->
            val clusterTables = resolveCpuClusterTables(
                rawClusters = pmgr.readFromRegistry(
                    key = "acc-clusters"
                )
            )
            val frequencies = mutableMapOf<Int, Long>()

            for ((tier, table) in clusterTables) {
                val frequency = resolveCpuTableFrequency(
                    rawFrequency = pmgr.readFromRegistry(
                        key = "voltage-states$table-sram"
                    )
                )

                if (frequency > 0)
                    frequencies[tier] = maxOf(frequencies[tier] ?: 0, frequency)
            }

            frequencies
        }
    }

    /**
     * Method used to associate nominal cluster frequencies with native logical CPU identifiers
     *
     * Registry iteration order does not determine the CPU index
     * Unrecognized cluster types and missing identifiers produce zero for the affected logical processors
     * A mismatch between observed core tiers and frequency tiers leaves all frequencies unavailable
     *
     * @param logicalProcessors The logical processor descriptions in output order
     * @param nominalFrequencies The nominal frequencies in hertz, keyed by power-manager performance tier
     *
     * @return the nominal frequencies in logical processor order, with zero for unavailable mappings, as [LongArray]
     * @throws IllegalStateException If the native service matching query fails
     */
    @Loader
    private fun loadSiliconCurrentFreq(
        logicalProcessors: List<MacOsLogicalProcessor>,
        nominalFrequencies: Map<Int, Long>
    ): LongArray {
        if (nominalFrequencies.isEmpty())
            return LongArray(logicalProcessors.size)

        val processorTiers = mutableMapOf<Int, Int>()
        useIOServices(
            serviceName = IO_PLATFORM_DEVICE_SERVICE,
            consumeServiceIf = { service ->
                val deviceType = service.readStringFromRegistry(
                    key = "device_type"
                )

                deviceType == "cpu"
            }
        ) { _, service ->
            val processorNumber = service.readIntFromRegistry(
                key = "logical-cpu-id",
                default = -1
            )
            if (processorNumber < 0)
                return@useIOServices

            val tier = resolveClusterType(
                service = service
            ) ?: return@useIOServices

            processorTiers[processorNumber] = tier
        }

        return resolveCpuNominalFrequencies(
            processorNumbers = logicalProcessors.map {
                it.processorNumber
            },
            processorTiers = processorTiers,
            nominalFrequencies = nominalFrequencies
        )
    }

    /**
     * Method used to resolve a native CPU cluster type into an ordered performance rank
     *
     * The `cluster-type` values E, P, and S map to ranks zero, one, and two respectively
     *
     * @param service The CPU registry service whose cluster type is read without releasing the service
     *
     * @return the cluster rank, or null for a missing or unrecognized type, as [Int]
     */
    @Resolver
    private fun resolveClusterType(
        service: io_service_t
    ): Int? {
        val clusterType = service.readStringFromRegistry(
            key = "cluster-type"
        )

        return when (clusterType) {
            "E" -> 0
            "P" -> 1
            "S" -> 2

            else -> null
        }
    }

    /**
     * Method used to retrieve the physical processor package count from `hw.packages`
     *
     * @return the package count, or zero when the query fails, as [Int]
     */
    @Loader
    private fun resolvePhysicalPackageCount(): Int {
        return queryIntSysCtlByName(
            name = "hw.packages",
            default = 0
        )!!
    }

    /**
     * Method used to load macOS logical processor descriptions through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsLogicalProcessorMapper]
     *
     * @return the mapped logical processor descriptions as [List] of [MacOsLogicalProcessor]
     */
    @Loader
    private fun loadLogicalProcessor(): List<MacOsLogicalProcessor> {
        val macOsLogicalProcessorMapper = MacOsLogicalProcessorMapper()

        return macOsLogicalProcessorMapper.mapFromNative()
    }

    /**
     * Method used to load macOS physical processor descriptions through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsPhysicalProcessorMapper]
     *
     * Each call creates a mapper and reads the native counts and accepted CPU registry entries again
     *
     * @return the mapped physical processor descriptions as [List] of [MacOsPhysicalProcessor]
     * @throws IllegalStateException If the native service matching query fails
     * @throws ArithmeticException If an accepted entry is mapped with a zero package count or zero cores per package
     */
    @Loader
    private fun loadPhysicalProcessor(): List<MacOsPhysicalProcessor> {
        val macOsPhysicalProcessorMapper = MacOsPhysicalProcessorMapper()

        return macOsPhysicalProcessorMapper.mapFromNative()
    }

    /**
     * Method used to load macOS processor caches with positive mapped sizes through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsProcessorCacheMapper]
     *
     * @return the mapped cache descriptions as [List] of [MacOsProcessorCache]
     */
    @Loader
    private fun loadProcessorCache(): List<MacOsProcessorCache> {
        val macOsProcessorCacheMapper = MacOsProcessorCacheMapper()

        return macOsProcessorCacheMapper.mapFromNative()
    }

    /**
     * Method used to load processor feature names through the architecture-specific reader
     *
     * Apple Silicon reports supported entries from [SILICON_FEATURE_FLAGS], while Intel merges its native feature lists
     *
     * @return the available processor feature names as [List] of [String]
     */
    @Loader
    private fun loadFeatureFlags(): List<String> {
        return if (isAppleSilicon())
            loadSiliconFeatureFlags()
        else
            loadIntelFeatureFlags()
    }

    /**
     * Method used to query the predefined Apple Silicon feature names under `hw.optional.arm`
     *
     * Only entries whose system control value equals one are retained, in [SILICON_FEATURE_FLAGS] order
     * Missing keys are excluded, and features outside the predefined subset are not queried
     *
     * @return the supported feature names from the predefined subset as [List] of [String]
     */
    @Loader
    private fun loadSiliconFeatureFlags(): List<String> {
        return SILICON_FEATURE_FLAGS.filter { feature ->
            val result = queryIntSysCtlByName(
                name = "hw.optional.arm.$feature",
                default = 0
            )

            result == 1
        }
    }

    /**
     * Method used to combine the Intel feature lists returned by the system control keys in [INTEL_FEATURE_FLAGS]
     *
     * Values are split on whitespace, blank entries are discarded, and duplicates retain their first occurrence
     * Failed queries contribute no feature names
     *
     * @return the distinct Intel feature names in discovery order as [List] of [String]
     */
    @Loader
    private fun loadIntelFeatureFlags(): List<String> {
        val retrievedFeatures = INTEL_FEATURE_FLAGS.flatMap { key ->
            val result = queryStringSysCtlByName(
                name = key,
                default = ""
            )!!

            result.split(Regex("\\s+"))
        }

        val filteredRetrievedFeatures = retrievedFeatures.filter { feature ->
            feature.isNotBlank()
        }

        return filteredRetrievedFeatures.distinct()
    }

}
