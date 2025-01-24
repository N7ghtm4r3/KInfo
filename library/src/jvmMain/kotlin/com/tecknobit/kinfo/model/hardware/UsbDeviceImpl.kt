package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.UsbDevice

/**
 * `UsbDeviceImpl` is the implementation of the `UsbDevice` interface.
 * It provides details about a USB device, such as its name, vendor, serial number, and connected devices.
 *
 * @param name The name of the USB device (e.g., "USB 3.0 Hub").
 * @param vendor The vendor of the USB device (e.g., "Generic", "Logitech").
 * @param vendorId The vendor ID of the USB device, typically a 4-character hexadecimal value.
 * @param productId The product ID of the USB device, typically a 4-character hexadecimal value.
 * @param serialNumber The serial number of the USB device.
 * @param uniqueDeviceId A unique identifier for the USB device.
 * @param connectedDevices A list of other USB devices that are connected to this device (if any).
 *
 * @author N7ghtm4r3
 *
 * @see UsbDevice
 */
class UsbDeviceImpl(
    override val name: String,
    override val vendor: String,
    override val vendorId: String,
    override val productId: String,
    override val serialNumber: String,
    override val uniqueDeviceId: String,
    override val connectedDevices: List<UsbDevice>
) : UsbDevice