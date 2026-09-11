@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.hardware.MacOsBaseboardImpl
import kotlinx.cinterop.ExperimentalForeignApi
import platform.IOKit.IOObjectRelease

/**
 * The `MacOsBaseboardMapper` class is useful to map `IOPlatformExpertDevice` properties to baseboard information
 *
 * The model is read from `board-id`, falling back to the hardware target identifier in `target-type`
 * The manufacturer, version, and logic board serial number are read from `manufacturer`, `version`,
 * and `mlb-serial-number` respectively, with empty strings returned when these lookups fail
 * The platform version does not necessarily identify the physical revision of the logic board
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
class MacOsBaseboardMapper : MacOsHardwareMapper<MacOsBaseboardImpl>() {

    /**
     * Method used to load the platform expert service and map its current baseboard properties
     *
     * @return the mapped baseboard information as [MacOsBaseboardImpl]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    override fun mapFromNative(): MacOsBaseboardImpl {
        val service = loadIOService(
            serviceName = "IOPlatformExpertDevice"
        )

        val macOsBaseboard = MacOsBaseboardImpl(
            manufacturer = service.readFromRegistry(
                key = "manufacturer"
            ),
            model = service.readFromRegistryWithFallback(
                key = "board-id",
                fallbackKey = "target-type"
            ),
            version = service.readFromRegistry(
                key = "version"
            ),
            serialNumber = service.readFromRegistry(
                key = "mlb-serial-number"
            )
        )

        IOObjectRelease(service)

        return macOsBaseboard
    }

}