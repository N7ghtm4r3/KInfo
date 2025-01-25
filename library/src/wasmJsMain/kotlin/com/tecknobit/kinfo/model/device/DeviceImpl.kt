package com.tecknobit.kinfo.model.device

import com.tecknobit.kinfo.model.web.device.Device

/**
 * Implements the `Device` interface, providing the device model, type, and vendor information.
 *
 * This class extracts the model, type, and vendor from the provided `com.tecknobit.kinfo.model.Device` object,
 * ensuring null-safe values are used via the `safeValue()` method from the `WebInfoItem` interface.
 *
 * @param parsedDevice The `Device` object containing the parsed device information, used to extract the model, type, and vendor.
 *
 * @see Device
 *
 * @author N7ghtm4r3
 */
class DeviceImpl(
    parsedDevice: com.tecknobit.kinfo.model.Device
) : Device {

    /**
     * `model` The model of the device (e.g., iPhone 12, Galaxy S21).
     * This value is safely extracted from the provided `parsedDevice` object.
     */
    override val model: String = parsedDevice.model.safeValue()

    /**
     * `type` The type of the device (e.g., smartphone, tablet, laptop).
     * This value is safely extracted from the provided `parsedDevice` object.
     */
    override val type: String = parsedDevice.type.safeValue()

    /**
     * `vendor` The vendor of the device (e.g., Apple, Samsung, Lenovo).
     * This value is safely extracted from the provided `parsedDevice` object.
     */
    override val vendor: String = parsedDevice.vendor.safeValue()

}
