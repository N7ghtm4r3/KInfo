package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsUsbDevice

/**
 * The `MacOsUsbDeviceImpl` class is useful to store a snapshot of macOS USB device information
 *
 * Values and the child list are stored as supplied without validation, copying, or native refresh
 *
 * @property name The device name supplied by the source
 * @property vendor The manufacturer name supplied by the source
 * @property vendorId The vendor identifier, normally four hexadecimal digits, or the source fallback
 * @property productId The product identifier, normally four hexadecimal digits, or the source fallback
 * @property serialNumber The serial number supplied by the source, including its unavailable-value fallback
 * @property uniqueDeviceId The registry entry identifier supplied by the native mapper, or its unavailable-value fallback
 * @property connectedDevices The supplied list of USB child devices and their recursively represented descendants
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsUsbDevice
 *
 * @since 1.1.0
 */
data class MacOsUsbDeviceImpl(
    override val name: String,
    override val vendor: String,
    override val vendorId: String,
    override val productId: String,
    override val serialNumber: String,
    override val uniqueDeviceId: String,
    override val connectedDevices: List<MacOsUsbDevice>
) : MacOsUsbDevice
