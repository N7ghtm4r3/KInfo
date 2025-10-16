package com.tecknobit.kinfo.model.desktop.hardware

/**
 * Represents a USB device.
 * This interface provides details about the USB device, including its name, vendor, product information, serial number, and connected devices.
 *
 * @author N7ghtm4r3
 */
interface UsbDevice {

    /**
     * `name` The name of the USB device (e.g., "USB 3.0 Hub")
     */
    val name: String

    /**
     * `vendor` The vendor of the USB device (e.g., "Generic", "Logitech")
     */
    val vendor: String

    /**
     * `vendorId` The vendor ID of the USB device, typically a 4-character hexadecimal value
     */
    val vendorId: String

    /**
     * `productId` The product ID of the USB device, typically a 4-character hexadecimal value
     */
    val productId: String

    /**
     * `serialNumber` The serial number of the USB device
     */
    val serialNumber: String

    /**
     * `uniqueDeviceId` A unique identifier for the USB device
     */
    val uniqueDeviceId: String

    /**
     * `connectedDevices` A list of other USB devices that are connected to this device (if any)
     */
    val connectedDevices: List<UsbDevice>

}
