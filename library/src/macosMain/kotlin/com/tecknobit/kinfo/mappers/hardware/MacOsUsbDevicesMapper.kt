@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsUsbDeviceImpl
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import platform.IOKit.IOObjectConformsTo
import platform.IOKit.io_service_t
import platform.IOKit.kIOServicePlane

/**
 * The `MacOsUsbDevicesMapper` class is useful to map macOS USB services and their connected devices
 *
 * A null parent filter selects devices for which no USB ancestor is resolved
 * Each child list is loaded through a separate enumeration, so the complete topology is not an atomic snapshot
 *
 * @property parentId The registry identifier of the nearest USB ancestor to match, or null to select root devices
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsUsbDevicesMapper(
    private val parentId: String? = null
) : MacOsHardwareMapper<List<MacOsUsbDeviceImpl>>() {

    /**
     * The companion object allows to share the IOKit class name used to enumerate USB devices
     *
     * @author N7ghtm4r3 - Tecknobit
     */
    companion object {

        /**
         * `USB_HOST_DEVICE` the IOKit class name used to enumerate USB devices and recognize USB ancestors
         */
        const val USB_HOST_DEVICE = "IOUSBHostDevice"

    }

    /**
     * Method used to map USB services accepted by [parentId] and recursively load their connected devices
     *
     * Vendor and product identifiers use four lowercase hexadecimal digits when available
     * A null parent filter selects devices for which no USB ancestor can be resolved
     *
     * @return the mapped devices, or an empty list when no services are accepted, as [List] of [MacOsUsbDeviceImpl]
     * @throws IllegalStateException If a native USB service enumeration fails
     */
    override fun mapFromNative(): List<MacOsUsbDeviceImpl> {
        val macOsUsbDevices = mutableListOf<MacOsUsbDeviceImpl>()

        useIOServices(
            serviceName = USB_HOST_DEVICE,
            consumeServiceIf = { service ->
                val parentId = resolveUsbParentId(
                    service = service
                )

                this.parentId == parentId
            }
        ) { _, service ->
            val macOsUsbDevice = MacOsUsbDeviceImpl(
                name = service.readStringFromRegistryWithFallback(
                    key = "kUSBProductString",
                    fallbackKey = "USB Product Name"
                ),
                vendor = service.readStringFromRegistryWithFallback(
                    key = "kUSBVendorString",
                    fallbackKey = "USB Vendor Name"
                ),
                vendorId = resolveUsbId(
                    service = service,
                    key = "idVendor"
                ),
                productId = resolveUsbId(
                    service = service,
                    key = "idProduct"
                ),
                serialNumber = service.readStringFromRegistryWithFallback(
                    key = "kUSBSerialNumberString",
                    fallbackKey = "USB Serial Number"
                ),
                uniqueDeviceId = resolveServiceRegistryId(
                    service = service
                ),
                connectedDevices = loadConnectedDevices(
                    service = service
                )
            )

            macOsUsbDevices.add(macOsUsbDevice)
        }

        return macOsUsbDevices
    }

    /**
     * Method used to read a USB vendor or product identifier and format it as four lowercase hexadecimal digits
     *
     * @param service The borrowed USB service containing the identifier
     * @param key The registry property name of the identifier to read
     *
     * @return the formatted identifier, or [UNKNOWN] when unavailable or outside the 16-bit range, as [String]
     */
    @Resolver
    private fun resolveUsbId(
        service: io_service_t,
        key: String
    ): String {
        val value = service.readIntFromRegistry(
            key = key,
            default = -1
        )

        return if (value in 0..0xFFFF) {
            value.toString(
                radix = 16
            ).padStart(
                length = 4,
                padChar = '0'
            )
        } else
            UNKNOWN
    }

    /**
     * Method used to look up the registry identifier of the nearest USB ancestor in the I/O service plane
     *
     * Non-USB parent entries are traversed recursively and the supplied service remains owned by the caller
     * Each acquired parent handle is released through [userRegistryParentEntry] when its operation ends
     *
     * @param service The borrowed service whose USB ancestor is requested
     *
     * @return the ancestor identifier, [UNKNOWN] when its identifier cannot be read, or null when traversal
     * cannot find a USB ancestor as [String]
     */
    private fun resolveUsbParentId(
        service: io_service_t
    ): String? {
        return userRegistryParentEntry(
            service = service,
            plane = kIOServicePlane
        ) { parent ->
            memScoped {
                val device = IOObjectConformsTo(
                    parent,
                    USB_HOST_DEVICE.cstr.ptr
                )

                val isUsbDevice = device != 0
                if (isUsbDevice) {
                    resolveServiceRegistryId(
                        service = parent
                    )
                } else {
                    resolveUsbParentId(
                        service = parent
                    )
                }
            }
        }
    }

    /**
     * Method used to load the devices whose nearest USB ancestor matches the supplied service identifier
     *
     * A separate enumeration is performed and each accepted device recursively loads its own children
     * The supplied service remains owned by the caller
     *
     * @param service The borrowed USB service whose connected devices are requested
     *
     * @return the mapped child devices, or an empty list when none are accepted, as [List] of [MacOsUsbDeviceImpl]
     * @throws IllegalStateException If a native USB service enumeration fails
     */
    @Loader
    private fun loadConnectedDevices(
        service: io_service_t
    ): List<MacOsUsbDeviceImpl> {
        val currentParentId = resolveServiceRegistryId(
            service = service
        )

        val macOsUsbDevicesMapper = MacOsUsbDevicesMapper(
            parentId = currentParentId
        )

        return macOsUsbDevicesMapper.mapFromNative()
    }

}