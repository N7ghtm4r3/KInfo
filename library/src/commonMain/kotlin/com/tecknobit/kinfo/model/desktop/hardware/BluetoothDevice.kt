package com.tecknobit.kinfo.model.desktop.hardware

/**
 * The `BluetoothDevice` interface defines the contract to describe a Bluetooth device paired or connected to the system
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface BluetoothDevice {

    /**
     * `name` the user-visible name of the Bluetooth device
     */
    val name: String

    /**
     * `macAddress` the MAC address of the Bluetooth device
     */
    val macAddress: String

    /**
     * `majorDeviceClass` the major device class derived from the Bluetooth `Class of Device` field
     */
    val majorDeviceClass: String

    /**
     * `connected` whether the Bluetooth device is currently connected to the system
     */
    val connected: Boolean

    /**
     * `paired` whether the Bluetooth device is paired with the system
     */
    val paired: Boolean

    /**
     * `batteryLevel` the battery percentage from `0` to `100`, or `-1` when unavailable
     */
    val batteryLevel: Int

    /**
     * `adapterName` the name of the Bluetooth adapter associated with the device
     */
    val adapterName: String

}