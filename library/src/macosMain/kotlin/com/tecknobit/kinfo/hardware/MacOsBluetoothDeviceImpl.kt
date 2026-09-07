package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBluetoothDevice

data class MacOsBluetoothDeviceImpl(
    override val name: String,
    override val macAddress: String,
    override val majorDeviceClass: String,
    override val connected: Boolean,
    override val paired: Boolean,
    override val batteryLevel: Int,
    override val adapterName: String
) : MacOsBluetoothDevice
