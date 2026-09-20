@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsSoundCardImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsSoundCardsMapper.Companion.CODEC_PATTERN_REGEX
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreFoundation.CFRelease
import platform.Foundation.CFBridgingRelease
import platform.IOKit.*

/**
 * The `MacOsSoundCardsMapper` class is useful to map macOS audio services to sound card snapshots
 *
 * Each matching `IOAudio2Device` service produces one entry without grouping services by physical hardware
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsSoundCardsMapper : MacOsHardwareMapper<List<MacOsSoundCardImpl>>() {

    private companion object {

        /**
         * `CODEC_PATTERN_REGEX` the case-insensitive pattern matching supported `audio-data` compatibility identifiers
         * with an optional input or output suffix
         */
        val CODEC_PATTERN_REGEX = Regex(
            pattern = "audio-data,(" +
                    "cs(?:35|42)l[0-9]+|" +
                    "max98[0-9]+|" +
                    "tas[0-9]+[a-z]*|" +
                    "sn[0-9]+|" +
                    "ssm[0-9]+" +
                    ")(?:-(?:input|output))?",
            option = RegexOption.IGNORE_CASE
        )

    }

    /**
     * Method used to map the current `IOAudio2Device` services to audio device snapshots
     *
     * Names fall back from `IOAudioDeviceName` to `device name` and may be empty or blank
     * Unavailable driver versions and unsupported codec descriptions use [UNKNOWN]
     *
     * @return the mapped snapshots, or an empty list when no services match, as [List] of [MacOsSoundCardImpl]
     * @throws IllegalStateException If the native audio service enumeration fails
     */
    override fun mapFromNative(): List<MacOsSoundCardImpl> {
        val soundCards = mutableListOf<MacOsSoundCardImpl>()

        useIOServices(
            serviceName = "IOAudio2Device"
        ) { _, service ->
            val soundCard = MacOsSoundCardImpl(
                driverVersion = resolveDriverVersion(
                    service = service
                ),
                name = service.readStringFromRegistryWithFallback(
                    key = "IOAudioDeviceName",
                    fallbackKey = "device name"
                ),
                codec = resolveCodec(
                    service = service
                )
            )
            soundCards.add(soundCard)
        }

        return soundCards
    }

    /**
     * Method used to resolve the loaded kernel extension version for an audio service class
     *
     * The service class identifies the bundle whose `CFBundleVersion` is read from the loaded kernel extensions
     * Missing or incompatible metadata and blank versions produce [UNKNOWN]
     *
     * @param service The borrowed audio service whose implementing kernel extension is queried
     *
     * @return the kernel extension version or [UNKNOWN] as [String]
     */
    @Resolver
    private fun resolveDriverVersion(
        service: io_service_t
    ): String {
        val className = IOObjectCopyClass(service) ?: return UNKNOWN

        val bundleId = try {
            CFBridgingRelease(
                IOObjectCopyBundleIdentifierForClass(className)
            ) as? String
        } finally {
            CFRelease(className)
        } ?: return UNKNOWN

        val loadedKexts = CFBridgingRelease(
            KextManagerCopyLoadedKextInfo(null, null)
        ) as? Map<*, *> ?: return UNKNOWN

        val driver = loadedKexts[bundleId] as? Map<*, *> ?: return UNKNOWN
        return (driver["CFBundleVersion"] as? String)
            ?.takeIf { it.isNotBlank() }
            ?: UNKNOWN
    }

    /**
     * Method used to resolve an audio codec description from native registry properties
     *
     * A valid service-local `IOHDACodecVendorID` is formatted with an `HDA 0x` prefix and eight lowercase hex digits
     * Missing identifiers, zero, and [UInt.MAX_VALUE] fall back to the immediate provider compatibility lookup
     *
     * @param service The borrowed audio service whose codec information is queried
     *
     * @return the formatted HDA identifier, supported codec model, or [UNKNOWN] as [String]
     */
    @Resolver
    private fun resolveCodec(
        service: io_service_t
    ): String {
        val codecId = service.readUIntFromRegistry(
            key = "IOHDACodecVendorID"
        )

        if (codecId != 0u && codecId != UInt.MAX_VALUE) {
            val formattedCodec = codecId.toString(16).padStart(8, '0')

            return "HDA 0x$formattedCodec"
        }

        return resolveCodecFromCompatible(
            service = service
        )
    }

    /**
     * Method used to resolve a supported codec model from the immediate parent in the I/O service plane
     *
     * Null-separated `compatible` entries are matched against [CODEC_PATTERN_REGEX] and normalized to uppercase
     * Exactly one distinct matching model is required, while unavailable parents or absent or conflicting matches
     * produce [UNKNOWN]
     * The acquired parent handle is released after the lookup
     *
     * @param service The borrowed audio service whose immediate provider is inspected
     *
     * @return the unique supported codec model or [UNKNOWN] as [String]
     */
    @Resolver
    private fun resolveCodecFromCompatible(
        service: io_service_t
    ): String {
        return userRegistryParentEntry(
            service = service,
            plane = kIOServicePlane,
            default = UNKNOWN,
            usage = { parent ->
                val compatible = parent.readStringsFromRegistry(
                    key = "compatible"
                )

                val compatibleEntries = compatible.mapNotNull { compatible ->
                    CODEC_PATTERN_REGEX.matchEntire(compatible)
                        ?.groupValues
                        ?.get(1)
                }

                compatibleEntries.map { it.uppercase() }
                    .distinct()
                    .singleOrNull()
                    ?: UNKNOWN
            }
        )!!
    }

}