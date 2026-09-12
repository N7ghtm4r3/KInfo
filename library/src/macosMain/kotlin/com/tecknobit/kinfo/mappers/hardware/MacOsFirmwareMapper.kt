@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.hardware.MacOsFirmwareImpl
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

class MacOsFirmwareMapper : MacOsHardwareMapper<MacOsFirmwareImpl>() {

    override fun mapFromNative(): MacOsFirmwareImpl {
        val isAppleSilicon = Platform.cpuArchitecture == CpuArchitecture.ARM64
        if (isAppleSilicon)
            return mapForSilicon()

        return mapForIntel()
    }

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
                description = efi.readFromRegistry(
                    key = "firmware-abi"
                ).ifBlank { UNKNOWN },
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

    private fun mapForIntel(): MacOsFirmwareImpl {
        val rom = loadRegistryFromPath("IODeviceTree:/rom")

        return MacOsFirmwareImpl(
            manufacturer = "",
            name = "",
            description = "",
            version = "",
            releaseDate = "",
            isAppleSilicon = false
        )
    }

}