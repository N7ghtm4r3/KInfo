@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsPowerSourceImpl
import com.tecknobit.kinfo.model.desktop.common.hardware.CapacityUnits
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.interpretObjCPointer
import platform.CoreFoundation.CFArrayGetCount
import platform.CoreFoundation.CFArrayGetValueAtIndex
import platform.CoreFoundation.CFRelease
import platform.Foundation.NSDictionary
import platform.IOKit.*
import kotlin.experimental.ExperimentalNativeApi

/**
 * The `MacOsPowerSourceMapper` class is useful to map the internal macOS battery to [MacOsPowerSourceImpl]
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsPowerSourceMapper : MacOsHardwareMapper<MacOsPowerSourceImpl>() {

    /**
     * Method used to read the current internal battery measurements and create a new power source snapshot
     *
     * @return the mapped internal battery snapshot as [MacOsPowerSourceImpl]
     * @throws IllegalStateException If the internal battery service is unavailable
     */
    override fun mapFromNative(): MacOsPowerSourceImpl {
        return useIOService(
            serviceName = "AppleSmartBattery"
        ) { service ->
            val currentCapacity = service.readIntFromRegistry(
                key = "CurrentCapacity"
            )
            val maxCapacity = service.readIntFromRegistry(
                key = "MaxCapacity"
            )
            val remainingCapacityPercent = (currentCapacity.toDouble() / maxCapacity.coerceAtLeast(1)) * 100

            val timeRemainingEstimated = service.readDoubleFromRegistry(
                key = "AvgTimeToEmpty"
            )
            val timeRemainingInstant = service.readDoubleFromRegistry(
                key = "InstantTimeToEmpty",
                default = -1.0
            )

            val amperage = service.readDoubleFromRegistry(
                key = "Amperage"
            ) / 1000
            val voltage = service.readDoubleFromRegistry(
                key = "Voltage"
            ) / 1000

            MacOsPowerSourceImpl(
                name = resolveName(),
                deviceName = service.readStringFromRegistry(
                    key = "DeviceName"
                ),
                remainingCapacityPercent = remainingCapacityPercent,
                timeRemainingEstimated = timeRemainingEstimated / 60,
                timeRemainingInstant = timeRemainingInstant / 60,
                powerUsageRate = voltage * amperage,
                voltage = voltage,
                amperage = amperage,
                isPowerOnLine = service.readBooleanFromRegistry(
                    key = "ExternalConnected"
                ),
                isCharging = service.readBooleanFromRegistry(
                    key = "IsCharging"
                ),
                isDischarging = amperage < 0,
                capacityUnits = CapacityUnits.MAH,
                currentCapacity = service.readIntFromRegistry(
                    key = "AppleRawCurrentCapacity"
                ),
                maxCapacity = service.readIntFromRegistry(
                    key = "AppleRawMaxCapacity"
                ),
                designCapacity = service.readIntFromRegistry(
                    key = "DesignCapacity"
                ),
                cycleCount = service.readIntFromRegistry(
                    key = "CycleCount"
                ),
                chemistry = UNKNOWN,
                manufacturer = service.readStringFromRegistryOrUnknown(
                    key = "Manufacturer"
                ),
                serialNumber = service.readStringFromRegistry(
                    key = "Serial"
                ),
                temperature = service.readDoubleFromRegistry(
                    key = "Temperature"
                ).asCelsius()
            )
        }
    }

    /**
     * Method used to resolve the operating system name of the first internal battery power source
     *
     * @return the internal battery name, or [UNKNOWN] when no matching name is available, as [String]
     */
    @Resolver
    private fun resolveName(): String {
        val info = IOPSCopyPowerSourcesInfo() ?: return UNKNOWN
        val sources = IOPSCopyPowerSourcesList(info)

        try {
            if (sources == null)
                return UNKNOWN

            for (j in 0L until CFArrayGetCount(sources)) {
                val source = CFArrayGetValueAtIndex(sources, j) ?: continue
                val description = IOPSGetPowerSourceDescription(info, source) ?: continue
                val data = interpretObjCPointer<NSDictionary>(description.rawValue)

                if (data.objectForKey(kIOPSTypeKey) == kIOPSInternalBatteryType)
                    return data.objectForKey(kIOPSNameKey) as? String ?: UNKNOWN
            }

            return UNKNOWN
        } finally {
            if (sources != null)
                CFRelease(sources)

            CFRelease(info)
        }
    }

    /**
     * Method used to convert a native battery temperature from tenths of kelvin to degrees Celsius
     *
     * @receiver The native battery temperature in tenths of kelvin
     *
     * @return the converted temperature in degrees Celsius as [Double]
     */
    private fun Double.asCelsius(): Double {
        return ((this / 10) - 273.15)
    }

}
