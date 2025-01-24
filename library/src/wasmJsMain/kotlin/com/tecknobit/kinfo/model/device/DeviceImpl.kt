package com.tecknobit.kinfo.model.device

import com.tecknobit.kinfo.model.web.device.Device

class DeviceImpl(
    private val parsedDevice: com.tecknobit.kinfo.model.Device
) : Device {

    override val model: String
        get() = parsedDevice.model.safeValue()

    override val type: String
        get() = parsedDevice.type.safeValue()

    override val vendor: String
        get() = parsedDevice.vendor.safeValue()

}