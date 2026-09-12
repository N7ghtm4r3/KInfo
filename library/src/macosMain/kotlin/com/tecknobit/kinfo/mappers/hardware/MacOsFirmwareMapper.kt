@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.hardware.MacOsFirmwareImpl
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

/**
 * The `MacOsFirmwareMapper` class is useful to map macOS device tree properties to firmware information
 *
 * The mapping is selected from the executable architecture and reads the registry on each invocation
 * Some fields use platform metadata rather than dedicated firmware properties
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 * @see MacOsFirmwareImpl
 *
 * @since 1.1.0
 */
class MacOsFirmwareMapper : MacOsHardwareMapper<MacOsFirmwareImpl>() {

    /**
     * Method used to map firmware properties using the ARM64 or Intel registry paths
     *
     * An x64 executable running through Rosetta uses the Intel mapping even on Apple Silicon hardware
     *
     * @return the mapped firmware information as [MacOsFirmwareImpl]
     */
    override fun mapFromNative(): MacOsFirmwareImpl {
        val isAppleSilicon = Platform.cpuArchitecture == CpuArchitecture.ARM64
        if (isAppleSilicon)
            return mapForSilicon()

        return mapForIntel()
    }

    /**
     * Method used to map Apple Silicon firmware information from the root, chosen, and EFI registry entries
     *
     * The name uses `booter-name`, falling back to the root `device_type`
     * The manufacturer and release date use the root `manufacturer` and `time-stamp` properties
     * The timestamp is not a verified firmware release date
     * The version uses `system-firmware-version`, falling back to the distinct `firmware-version` value
     * The description uses `firmware-abi` with an unknown value when the lookup fails
     * Registry handles are released after mapping, including when model construction throws
     *
     * @return the mapped firmware information with the Apple Silicon flag set as [MacOsFirmwareImpl]
     */
    private fun mapForSilicon(): MacOsFirmwareImpl {
        val root = loadRegistryFromPath("IODeviceTree:/")
        val rom = loadRegistryFromPath("IODeviceTree:/chosen")
        val efi = loadRegistryFromPath("IODeviceTree:/efi")

        val macOsFirmware = try {
            val name = rom.readFromRegistry(
                key = "booter-name"
            ).ifBlank {
                root.readFromRegistry(
                    key = "device_type"
                )
            }

            MacOsFirmwareImpl(
                manufacturer = root.readFromRegistry(
                    key = "manufacturer"
                ),
                name = name,
                description = efi.readFromRegistryOrUnknown(
                    key = "firmware-abi"
                ),
                version = rom.readFromRegistryWithFallback(
                    key = "system-firmware-version",
                    fallbackKey = "firmware-version"
                ),
                releaseDate = root.readFromRegistry(
                    key = "time-stamp"
                ),
                isAppleSilicon = true
            )
        } finally {
            root.release()
            rom.release()
            efi.release()
        }

        return macOsFirmware
    }

    /**
     * Method used to map Intel firmware information from the ROM, chosen, and EFI registry entries
     *
     * The ROM provides `vendor`, `version`, and `release-date`, with empty values when lookups fail
     * The name and description use `booter-name` and `firmware-abi`, with unknown values when lookups fail
     * Registry handles are released after mapping, including when model construction throws
     *
     * @return the mapped firmware information with the Apple Silicon flag unset as [MacOsFirmwareImpl]
     */
    private fun mapForIntel(): MacOsFirmwareImpl {
        val rom = loadRegistryFromPath("IODeviceTree:/rom")
        val romChosen = loadRegistryFromPath("IODeviceTree:/chosen")
        val efi = loadRegistryFromPath("IODeviceTree:/efi")

        val macOsFirmware = try {
            MacOsFirmwareImpl(
                manufacturer = rom.readFromRegistry(
                    key = "vendor"
                ),
                name = romChosen.readFromRegistryOrUnknown(
                    key = "booter-name"
                ),
                description = efi.readFromRegistryOrUnknown(
                    key = "firmware-abi"
                ),
                version = rom.readFromRegistry(
                    key = "version"
                ),
                releaseDate = rom.readFromRegistry(
                    key = "release-date"
                ),
                isAppleSilicon = false
            )
        } finally {
            rom.release()
            romChosen.release()
            efi.release()
        }

        return macOsFirmware
    }

}
