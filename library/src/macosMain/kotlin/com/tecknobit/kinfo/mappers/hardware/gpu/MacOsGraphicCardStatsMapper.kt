package com.tecknobit.kinfo.mappers.hardware.gpu

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsGpuStatsImpl
import com.tecknobit.kinfo.hardware.MacOsGpuTicksImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGpuTicks

/**
 * The `MacOsGraphicCardStatsMapper` class is useful to map accelerator registry statistics to a macOS GPU snapshot
 *
 * The identifier must belong to the `IOAccelerator` entry itself, rather than a related PCI entry
 * Native service handles are released before the snapshot is returned
 *
 * @property deviceId The decimal registry entry identifier of the accelerator service to query
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsGraphicCardStatsMapper(
    private val deviceId: String
) : MacOsHardwareMapper<MacOsGpuStatsImpl>() {

    /**
     * Method used to sample the registry statistics of the accelerator matching [deviceId]
     *
     * The `PerformanceStatistics` dictionary is searched on the service and its ancestors
     * A missing dictionary or entry produces zero for the corresponding metric
     * GPU tick counters use the unavailable values returned by [loadGpuTicks]
     *
     * @return the sampled GPU statistics as [MacOsGpuStatsImpl]
     * @throws IllegalStateException If service enumeration fails or no accelerator matches [deviceId]
     * @throws ClassCastException If a present statistic is incompatible with [platform.Foundation.NSNumber]
     */
    override fun mapFromNative(): MacOsGpuStatsImpl {
        var gpuStats: MacOsGpuStatsImpl? = null

        useIOServices(
            serviceName = "IOAccelerator",
            consumeServiceIf = { service ->
                resolveServiceRegistryId(service) == deviceId
            }
        ) { _, service ->
            val statistics = service.findDictionaryInRegistry(
                key = "PerformanceStatistics"
            )

            gpuStats = MacOsGpuStatsImpl(
                gpuTicks = loadGpuTicks(),
                gpuUtilization = statistics.readDoubleFromDictionary(
                    key = "Device Utilization %"
                ),
                vramUsed = statistics.readLongFromDictionary(
                    key = "vramUsedBytes"
                ),
                sharedMemoryUsed = statistics.readLongFromDictionary(
                    key = "In use system memory"
                ),
                temperature = statistics.readDoubleFromDictionary(
                    key = "Temperature(C)"
                ),
                powerDraw = statistics.readDoubleFromDictionary(
                    key = "Total Power(W)"
                ),
                coreClockMhz = statistics.readLongFromDictionary(
                    key = "Core Clock(MHz)"
                ),
                memoryClockMhz = statistics.readLongFromDictionary(
                    key = "Memory Clock(MHz)"
                ),
                fanSpeedPercent = statistics.readDoubleFromDictionary(
                    key = "Fan Speed(%)"
                )
            )
        }

        return gpuStats ?: throw IllegalStateException("Cannot read GPU stats for $deviceId")
    }

    /**
     * Method used to create the unavailable GPU tick snapshot
     *
     * Both counters are zero because this mapper does not sample native GPU activity counters
     *
     * @return the zero-valued GPU tick snapshot as [MacOsGpuTicks]
     */
    @Loader
    private fun loadGpuTicks(): MacOsGpuTicks {
        return MacOsGpuTicksImpl(
            activeTicks = 0,
            idleTicks = 0
        )
    }

}