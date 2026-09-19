package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsBluetoothDeviceImpl
import platform.IOBluetooth.IOBluetoothDevice
import platform.IOBluetooth.IOBluetoothHostController

/**
 * The `MacOsBluetoothDevicesMapper` class is useful to map paired macOS Bluetooth devices to hardware snapshots
 *
 * Device information comes from [IOBluetoothDevice], while optional battery readings come from matching HID services
 * Each mapping reads the available data again and uses the default local controller name for every device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsBluetoothDevicesMapper : MacOsHardwareMapper<List<MacOsBluetoothDeviceImpl>>() {

    /**
     * Method used to map the paired Bluetooth devices and available HID battery readings to snapshots
     *
     * Null device names and addresses use [UNKNOWN], while non-null values are preserved without normalization
     * Battery lookup compares the native address directly with normalized registry keys, without normalizing the lookup
     * Unmatched battery readings use `0`, contrary to the inherited `-1` fallback
     * The default controller name is shared by every snapshot without resolving a device-specific association
     * HID enumeration is performed even when the paired-device list is empty
     *
     * @return the mapped snapshots, or an empty list when no paired devices are returned, as [List] of [MacOsBluetoothDeviceImpl]
     * @throws IllegalStateException If the HID service enumeration fails
     */
    override fun mapFromNative(): List<MacOsBluetoothDeviceImpl> {
        val nativeBluetoothDevices = loadBluetoothDevices()
        val bluetoothDevices = mutableListOf<MacOsBluetoothDeviceImpl>()
        val batteryLevels = loadBatteryLevels()
        val adapterName = resolveAdapterName()

        nativeBluetoothDevices.forEach { bluetoothDevice ->
            val macAddress = bluetoothDevice.addressString ?: UNKNOWN

            val bluetoothDevice = MacOsBluetoothDeviceImpl(
                name = bluetoothDevice.name ?: UNKNOWN,
                macAddress = macAddress,
                majorDeviceClass = bluetoothDevice.resolveMajorDeviceClass(),
                connected = bluetoothDevice.isConnected(),
                paired = bluetoothDevice.isPaired(),
                batteryLevel = batteryLevels.getOrElse(
                    key = macAddress,
                    defaultValue = { -1 }
                ),
                adapterName = adapterName
            )

            bluetoothDevices.add(bluetoothDevice)
        }

        return bluetoothDevices
    }

    /**
     * Method used to retrieve the Bluetooth devices paired with the system
     *
     * The native list includes devices paired by any user and does not require an active connection
     * A null native list becomes an empty list, and entries of other types are discarded
     *
     * @return the paired native devices as [List] of [IOBluetoothDevice]
     */
    @Loader
    private fun loadBluetoothDevices(): List<IOBluetoothDevice> {
        return IOBluetoothDevice.pairedDevices()
            .orEmpty()
            .filterIsInstance<IOBluetoothDevice>()
    }

    /**
     * Method used to collect battery percentages from selected `AppleDeviceManagementHIDEventService` entries
     *
     * The current predicate accepts services whose `BluetoothDevice` flag reads as `false`, including missing flags
     * Only `BatteryPercent` values from `0` to `100` are stored under their normalized `DeviceAddress` strings
     * Unreadable addresses become empty keys, and later valid readings replace earlier readings for the same key
     * Missing or invalid percentages are omitted, and native handles are released by the enumeration helper
     *
     * @return the battery percentages indexed by normalized registry address, possibly empty, as [Map] of [String] to [Int]
     * @throws IllegalStateException If the HID service enumeration fails
     */
    @Loader
    private fun loadBatteryLevels(): Map<String, Int> {
        val batteryLevels = mutableMapOf<String, Int>()

        useIOServices(
            serviceName = "AppleDeviceManagementHIDEventService",
            consumeServiceIf = { service ->
                !service.readBooleanFromRegistry(
                    key = "BluetoothDevice"
                )
            }
        ) { _, service ->
            val percentage = service.readIntFromRegistry(
                key = "BatteryPercent",
                default = -1
            )

            val address = service.readStringFromRegistry(
                key = "DeviceAddress",
                default = UNKNOWN
            )

            if (percentage in 0..100 && address != UNKNOWN)
                batteryLevels[address.normalize()] = percentage
        }

        return batteryLevels
    }

    /**
     * Method used to trim Bluetooth address text, remove colons and hyphens, and convert it to lowercase
     *
     * No length or hexadecimal validation is performed, and empty results are preserved
     *
     * @receiver The Bluetooth address text to normalize
     *
     * @return the normalized address text as [String]
     */
    private fun String.normalize(): String {
        val normalized = trim()
            .replace(":", "")
            .replace("-", "")
            .lowercase()

        return normalized
    }

    /**
     * Method used to retrieve the friendly name of the default local Bluetooth controller
     *
     * The name identifies the default controller and does not establish its association with an individual device
     *
     * @return the controller name, or [UNKNOWN] when the controller or a non-blank name is unavailable, as [String]
     */
    @Resolver
    private fun resolveAdapterName(): String {
        val bluetoothHostController = IOBluetoothHostController.defaultController()
        val controllerName = bluetoothHostController?.nameAsString()

        if (controllerName.isNullOrBlank())
            return UNKNOWN

        return controllerName
    }

    /**
     * Method used to describe the native major device class with its corresponding English category
     *
     * Codes `0x00` through `0x09` and `0x1F` have explicit descriptions, while other codes use [UNKNOWN]
     * The native value is mapped directly without checking whether an inquiry has supplied valid class information
     *
     * @receiver The native Bluetooth device whose major class is described
     *
     * @return the major device class description or [UNKNOWN] as [String]
     */
    @Resolver
    private fun IOBluetoothDevice.resolveMajorDeviceClass(): String {
        return when (deviceClassMajor.toInt()) {
            0x00 -> "Miscellaneous"
            0x01 -> "Computer"
            0x02 -> "Phone"
            0x03 -> "LAN/Network Access Point"
            0x04 -> "Audio/Video"
            0x05 -> "Peripheral"
            0x06 -> "Imaging"
            0x07 -> "Wearable"
            0x08 -> "Toy"
            0x09 -> "Health"
            0x1F -> "Uncategorized"

            else -> UNKNOWN
        }
    }

}