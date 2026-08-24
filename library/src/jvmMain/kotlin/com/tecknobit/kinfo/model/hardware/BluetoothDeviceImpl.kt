package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.BluetoothDevice

/**
 * The `BluetoothDeviceImpl` class is useful to represent Bluetooth device information retrieved from the system
 *
 * @property name The user-visible name of the Bluetooth device
 * @property macAddress The MAC address of the Bluetooth device
 * @property majorDeviceClass The major device class derived from the Bluetooth `Class of Device` field
 * @property connected Whether the Bluetooth device is currently connected to the system
 * @property paired Whether the Bluetooth device is paired with the system
 * @property batteryLevel The battery percentage from `0` to `100`, or `-1` when unavailable
 * @property adapterName The name of the Bluetooth adapter associated with the device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see BluetoothDevice
 *
 * @since 1.1.0
 */
data class BluetoothDeviceImpl(
    override val name: String,
    override val macAddress: String,
    override val majorDeviceClass: String,
    override val connected: Boolean,
    override val paired: Boolean,
    override val batteryLevel: Int,
    override val adapterName: String
) : BluetoothDevice