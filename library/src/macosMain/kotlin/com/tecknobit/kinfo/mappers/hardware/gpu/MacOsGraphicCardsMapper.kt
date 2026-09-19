@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.gpu

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsGraphicsCardImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsSplitHardwareMapper
import com.tecknobit.kinfo.mappers.hardware.gpu.MacOsGraphicCardsMapper.Companion.GIGA_TO_MEGABYTE_RATIO
import com.tecknobit.kinfo.mappers.hardware.gpu.MacOsGraphicCardsMapper.Companion.UNAVAILABLE_VRAM
import kotlinx.cinterop.*
import platform.IOKit.*

/**
 * The `MacOsGraphicCardsMapper` class is useful to map macOS graphics services to graphics card snapshots
 *
 * Intel mapping selects PCI graphics controllers, while Apple Silicon mapping enumerates AGX accelerators
 * Native handles are released after their properties have been copied into the returned models
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see com.tecknobit.kinfo.mappers.hardware.MacOsSplitHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsGraphicCardsMapper : MacOsSplitHardwareMapper<List<MacOsGraphicsCardImpl>>() {

    private companion object {

        /**
         * `PCI_GRAPHIC_CONTROLLER_DEVICE_CLASS` the PCI base class identifying display controllers
         */
        const val PCI_GRAPHIC_CONTROLLER_DEVICE_CLASS = 0x03L

        /**
         * `GIGA_TO_MEGABYTE_RATIO` the divisor currently applied to `VRAM,totalMB` by [loadVRam]
         */
        const val GIGA_TO_MEGABYTE_RATIO = 1_048_576L

        /**
         * `UNAVAILABLE_VRAM` the sentinel returned when video-memory properties cannot be resolved
         */
        const val UNAVAILABLE_VRAM = -1L

    }

    /**
     * Method used to map PCI graphics controllers on Intel-based Macs
     *
     * Services without a decodable graphics class code are omitted, and an empty enumeration returns an empty list
     *
     * @return the mapped graphics card snapshots as [List] of [MacOsGraphicsCardImpl]
     * @throws IllegalStateException If the native PCI service enumeration fails
     */
    override fun mapForIntel(): List<MacOsGraphicsCardImpl> {
        val graphicCards = mutableListOf<MacOsGraphicsCardImpl>()

        useIOServices(
            serviceName = "IOPCIDevice",
            consumeServiceIf = { service ->
                isPciGraphicsController(
                    service = service
                )
            }
        ) { _, service ->
            val graphicCard = mapCard(
                service = service,
                isAppleGpu = false
            )

            graphicCards.add(graphicCard)
        }

        return graphicCards
    }

    /**
     * Method used to check whether the decoded PCI base class identifies a graphics controller
     *
     * @param service The borrowed PCI service whose class code is inspected
     *
     * @return whether bits 16 through 23 of the decoded class code equal `0x03` as [Boolean]
     */
    private fun isPciGraphicsController(
        service: io_service_t
    ): Boolean {
        val classCode = service.readNumberFromBinaryEntry(
            key = "class-code"
        )
        if (classCode == null)
            return false

        val deviceClass = ((classCode shr 16) and 0xFFL)
        return deviceClass == PCI_GRAPHIC_CONTROLLER_DEVICE_CLASS
    }

    /**
     * Method used to map AGX accelerator services on Apple Silicon Macs
     *
     * Each matching service produces a snapshot with zero dedicated video memory
     *
     * @return the mapped graphics card snapshots, or an empty list when no services match, as [List] of [MacOsGraphicsCardImpl]
     * @throws IllegalStateException If the native accelerator service enumeration fails
     */
    override fun mapForSilicon(): List<MacOsGraphicsCardImpl> {
        val graphicCards = mutableListOf<MacOsGraphicsCardImpl>()

        useIOServices(
            serviceName = "AGXAccelerator"
        ) { _, service ->
            val graphicCard = mapCard(
                service = service,
                isAppleGpu = true
            )

            graphicCards.add(graphicCard)
        }

        return graphicCards
    }

    /**
     * Method used to copy a graphics service identity, revision, and video-memory value into a snapshot
     *
     * Apple GPU memory is represented by zero, while other services use [resolveVRam]
     * The service remains owned by the caller and is not retained by the snapshot
     *
     * @param service The borrowed graphics service to map
     * @param isAppleGpu Whether to apply the Apple GPU memory and vendor fallback conventions
     *
     * @return the mapped graphics card snapshot as [MacOsGraphicsCardImpl]
     */
    private fun mapCard(
        service: io_service_t,
        isAppleGpu: Boolean
    ): MacOsGraphicsCardImpl {
        val vendorId = service.readNumberFromBinaryEntry(
            key = "vendor-id"
        )

        return MacOsGraphicsCardImpl(
            name = service.readStringFromRegistryOrUnknown(
                key = "model"
            ),
            deviceId = resolveDeviceId(
                service = service
            ),
            vendor = resolveVendor(
                vendorId = vendorId,
                isAppleGpu = isAppleGpu
            ),
            versionInfo = resolveVersionInfo(
                service = service
            ),
            vRam = if (isAppleGpu)
                0L
            else {
                resolveVRam(
                    service = service
                )
            }
        )
    }

    /**
     * Method used to resolve the decimal registry identifier of a graphics service
     *
     * The identifier belongs to the registry entry and is not persistent across system reboots
     *
     * @param service The borrowed graphics service whose registry identifier is requested
     *
     * @return the decimal identifier, or [com.tecknobit.kinfo.UNKNOWN] when the native query fails, as [String]
     */
    @Resolver
    private fun resolveDeviceId(
        service: io_service_t
    ): String {
        return resolveServiceRegistryId(
            service = service
        )
    }

    /**
     * Method used to resolve a graphics vendor name from its numeric identifier
     *
     * Recognized identifiers map to Apple, AMD, NVIDIA, or Intel
     * Other identifiers use Apple when [isAppleGpu] is true, or a hexadecimal representation otherwise
     * A missing identifier always maps to Apple, including for a non-Apple GPU
     *
     * @param vendorId The decoded vendor identifier, or null when unavailable
     * @param isAppleGpu Whether an unrecognized identifier should use the Apple fallback
     *
     * @return the vendor name or hexadecimal identifier as [String]
     */
    @Resolver
    private fun resolveVendor(
        vendorId: Long?,
        isAppleGpu: Boolean
    ): String {
        return when (vendorId) {
            0x106BL -> "Apple"
            0x1002L -> "AMD"
            0x10DEL -> "NVIDIA"
            0x8086L -> "Intel"

            else -> {
                if (isAppleGpu || vendorId == null)
                    "Apple"
                else
                    "0x${vendorId.toString(16)}"
            }
        }
    }

    /**
     * Method used to format the decoded hardware revision of a graphics service
     *
     * @param service The borrowed graphics service whose revision is read
     *
     * @return the revision prefixed with `Revision 0x`, or [UNKNOWN] when unavailable, as [String]
     */
    @Resolver
    private fun resolveVersionInfo(
        service: io_service_t
    ): String {
        val revisionId = service.readNumberFromBinaryEntry(
            key = "revision-id"
        )

        return revisionId?.let { "Revision 0x${it.toString(16)}" } ?: UNKNOWN
    }

    /**
     * Method used to resolve video memory from a graphics service before searching its descendants
     *
     * @param service The borrowed graphics service at which the lookup starts
     *
     * @return the first available [loadVRam] result, or [UNAVAILABLE_VRAM] when none is found, as [Long]
     */
    @Resolver
    private fun resolveVRam(
        service: io_service_t
    ): Long {
        val vRam = loadVRam(
            service = service
        )
        if (vRam != UNAVAILABLE_VRAM)
            return vRam

        return resolveVRamIteratingChildren(
            service = service
        )
    }

    /**
     * Method used to search the descendants of a graphics service for a video-memory value
     *
     * The recursive search follows the I/O service plane and stops at the first available [loadVRam] result
     * Each visited entry and the iterator are released even when the search returns early or throws
     *
     * @param service The borrowed root service whose descendants are inspected
     *
     * @return the first available value, or [UNAVAILABLE_VRAM] when iteration fails to start or finds none, as [Long]
     */
    @Resolver
    private fun resolveVRamIteratingChildren(
        service: io_service_t
    ): Long {
        return memScoped {
            val iterator = alloc<io_iterator_tVar>()
            val result = IORegistryEntryCreateIterator(
                service,
                kIOServicePlane.cstr.ptr,
                kIORegistryIterateRecursively,
                iterator.ptr
            )
            if (result != 0)
                return@memScoped UNAVAILABLE_VRAM

            try {
                while (true) {
                    val child = IOIteratorNext(iterator.value)
                    if (child == IO_OBJECT_NULL)
                        break

                    val vRam = try {
                        loadVRam(
                            service = child
                        )
                    } finally {
                        child.release()
                    }

                    if (vRam != UNAVAILABLE_VRAM)
                        return@memScoped vRam
                }

                UNAVAILABLE_VRAM
            } finally {
                iterator.value.release()
            }
        }
    }

    /**
     * Method used to derive a video-memory value from the properties of one registry entry
     *
     * Both `VRAM,totalsize` and `VRAM,totalMB` must decode successfully
     * The current implementation divides `VRAM,totalMB` by [GIGA_TO_MEGABYTE_RATIO] using integer division
     * This does not convert the reported mebibytes to the byte count required by the graphics card contract
     *
     * @param service The borrowed service whose video-memory properties are read
     *
     * @return the integer quotient, or [UNAVAILABLE_VRAM] when either property is unavailable, as [Long]
     */
    @Loader
    private fun loadVRam(
        service: io_service_t
    ): Long {
        val totalSize = service.readNumberFromBinaryEntry(
            key = "VRAM,totalsize",
            default = UNAVAILABLE_VRAM
        )!!
        if (totalSize == UNAVAILABLE_VRAM)
            return UNAVAILABLE_VRAM

        val totalMb = service.readNumberFromBinaryEntry(
            key = "VRAM,totalMB",
            default = UNAVAILABLE_VRAM
        )!!
        if (totalMb == UNAVAILABLE_VRAM)
            return UNAVAILABLE_VRAM

        return totalMb / GIGA_TO_MEGABYTE_RATIO
    }

    /**
     * Method used to decode registry bytes using little-endian bit positions
     *
     * Missing properties, values shorter than eight bytes, and negative decoded results use [default]
     * Longer values are accepted and reuse shift positions modulo 64
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to read
     * @param default The fallback returned when the bytes cannot produce an accepted value
     *
     * @return the decoded nonnegative value or the possibly null [default] as [Long]
     */
    private fun io_registry_entry_t.readNumberFromBinaryEntry(
        key: String,
        default: Long? = null
    ): Long? {
        val registryNumber = readFromRegistry(
            key = key
        )
        if (registryNumber.size < 8)
            return default

        var number = 0L
        for (index in registryNumber.indices) {
            val byte = registryNumber[index].toLong()
            val unsignedByte = byte and 0xFFL

            number = number or (unsignedByte shl index * 8)
        }

        return if (number >= 0)
            number
        else
            default
    }

}