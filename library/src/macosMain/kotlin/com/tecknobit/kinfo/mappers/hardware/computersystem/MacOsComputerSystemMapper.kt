package com.tecknobit.kinfo.mappers.hardware.computersystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsComputerSystemImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBaseboard
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsFirmware

/**
 * The `MacOsComputerSystemMapper` class is useful to map native macOS machine identity and hardware information
 *
 * Machine identity is read from `IOPlatformExpertDevice` using the same keys on Apple Silicon and Intel
 * Firmware and baseboard information are loaded through their dedicated mappers on each invocation
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsComputerSystemMapper : MacOsHardwareMapper<MacOsComputerSystemImpl>() {

    /**
     * Method used to read the current machine identity and map its firmware and baseboard
     *
     * The identity keys are `manufacturer`, `model`, `IOPlatformSerialNumber`, and `IOPlatformUUID`
     * Failed or oversized identity reads return empty strings and trailing null characters are removed
     * The platform expert handle is released on completion, including when nested mapping throws
     *
     * @return the mapped computer system information as [MacOsComputerSystemImpl]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    override fun mapFromNative(): MacOsComputerSystemImpl {
        return useIOService(
            serviceName = IO_PLATFORM_EXPERT_DEVICE_SERVICE
        ) { service ->
            MacOsComputerSystemImpl(
                manufacturer = service.readStringFromRegistry(
                    key = "manufacturer"
                ),
                model = service.readStringFromRegistry(
                    key = "model"
                ),
                serialNumber = service.readStringFromRegistry(
                    key = "IOPlatformSerialNumber"
                ),
                hardwareUUID = service.readStringFromRegistry(
                    key = "IOPlatformUUID"
                ),
                firmware = loadFirmware(),
                baseboard = loadBaseboard()
            )
        }
    }

    /**
     * Method used to load the current macOS baseboard information through [MacOsBaseboardMapper]
     *
     * @return the mapped baseboard information as [MacOsBaseboard]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    @Loader
    private fun loadBaseboard(): MacOsBaseboard {
        val macOsBaseboardMapper = MacOsBaseboardMapper()

        return macOsBaseboardMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS firmware information from native registry properties
     *
     * @return the mapped firmware information as [MacOsFirmware]
     */
    @Loader
    private fun loadFirmware(): MacOsFirmware {
        val macOsFirmwareMapper = MacOsFirmwareMapper()

        return macOsFirmwareMapper.mapFromNative()
    }

}