package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBluetoothDevice

/**
 * The `MacOsBluetoothDeviceImpl` class is useful to store a snapshot of macOS Bluetooth device information
 *
 * Values are stored as supplied without validation or native refresh
 * The native mapper currently uses `0` for unmatched battery readings instead of the inherited contract's `-1`
 *
 * @property name The device name supplied by the source
 * @property macAddress The Bluetooth device address supplied by the source
 * @property majorDeviceClass The description of the native major device class
 * @property connected Whether the native source reports a baseband connection to the device
 * @property paired Whether the native source reports the device as paired
 * @property batteryLevel The battery percentage supplied by the source, including its unavailable-value fallback
 * @property adapterName The default local controller name supplied by the mapper, without a device-specific association
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsBluetoothDevice
 *
 * @since 1.1.0
 */
data class MacOsBluetoothDeviceImpl(
    override val name: String,
    override val macAddress: String,
    override val majorDeviceClass: String,
    override val connected: Boolean,
    override val paired: Boolean,
    override val batteryLevel: Int,
    override val adapterName: String
) : MacOsBluetoothDevice
