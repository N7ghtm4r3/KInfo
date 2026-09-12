@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.hardware.MacOsFirmwareImpl
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

/**
 * The `MacOsFirmwareMapper` class is useful to map macOS device tree properties to firmware information
 *
 * The mapping detects native ARM64 execution or Rosetta translation and reads the registry on each invocation
 * Some fields use platform metadata rather than dedicated firmware properties
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 *
 * @see com.tecknobit.kinfo.mappers.NativeMapper
 * @see MacOsHardwareMapper
 * @see MacOsSplitHardwareMapper
 */
class MacOsFirmwareMapper : MacOsSplitHardwareMapper<MacOsFirmwareImpl>() {

    /**
     * Method used to map Apple Silicon firmware information from the root, chosen, and EFI registry entries
     *
     * The name uses `booter-name`, falling back to the root `device_type`
     *
     * The manufacturer and release date use the root `manufacturer` and `time-stamp` properties
     * The timestamp is not a verified firmware release date
     *
     * The version uses `system-firmware-version`, falling back to the distinct `firmware-version` value
     *
     * The description uses `firmware-abi` with an unknown value when the lookup fails
     *
     * Registry handles are released after mapping, including when model construction throws
     *
     * @return the mapped firmware information with the Apple Silicon flag set as [MacOsFirmwareImpl]
     */
    override fun mapForSilicon(): MacOsFirmwareImpl {
        val root = loadRegistryFromPath("IODeviceTree:/")
        val rom = loadRegistryFromPath("IODeviceTree:/chosen")
        val efi = loadRegistryFromPath("IODeviceTree:/efi")

        val macOsFirmware = try {
            val name = rom.readStringFromRegistry(
                key = "booter-name"
            ).ifBlank {
                root.readStringFromRegistry(
                    key = "device_type"
                )
            }

            MacOsFirmwareImpl(
                manufacturer = root.readStringFromRegistry(
                    key = "manufacturer"
                ),
                name = name,
                description = efi.readStringFromRegistryOrUnknown(
                    key = "firmware-abi"
                ),
                version = rom.readStringFromRegistryWithFallback(
                    key = "system-firmware-version",
                    fallbackKey = "firmware-version"
                ),
                releaseDate = root.readStringFromRegistry(
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
     *
     * The name and description use `booter-name` and `firmware-abi`, with unknown values when lookups fail
     *
     * Registry handles are released after mapping, including when model construction throws
     *
     * @return the mapped firmware information with the Apple Silicon flag unset as [MacOsFirmwareImpl]
     */
    override fun mapForIntel(): MacOsFirmwareImpl {
        val rom = loadRegistryFromPath("IODeviceTree:/rom")
        val romChosen = loadRegistryFromPath("IODeviceTree:/chosen")
        val efi = loadRegistryFromPath("IODeviceTree:/efi")

        val macOsFirmware = try {
            MacOsFirmwareImpl(
                manufacturer = rom.readStringFromRegistry(
                    key = "vendor"
                ),
                name = romChosen.readStringFromRegistryOrUnknown(
                    key = "booter-name"
                ),
                description = efi.readStringFromRegistryOrUnknown(
                    key = "firmware-abi"
                ),
                version = rom.readStringFromRegistry(
                    key = "version"
                ),
                releaseDate = rom.readStringFromRegistry(
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
