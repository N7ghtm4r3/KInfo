package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.UsbDevice
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsUsbDevice

data class MacOsUsbDeviceImpl(
    override val name: String,
    override val vendor: String,
    override val vendorId: String,
    override val productId: String,
    override val serialNumber: String,
    override val uniqueDeviceId: String,
    override val connectedDevices: List<UsbDevice>
) : MacOsUsbDevice
