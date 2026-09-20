Represents a Bluetooth device paired with macOS, providing its identity, connection state, and available battery information

The list includes paired devices that are currently disconnected. Use `connected` to check each device's connection state.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### name

The user-visible name of the Bluetooth device, or `unknown` when unavailable

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val name: String = sample.name

println(name) // e.g. Bluetooth Keyboard
```

### macAddress

The address of the Bluetooth device, or `unknown` when unavailable

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val macAddress: String = sample.macAddress

println(macAddress) // e.g. 00-00-5e-00-53-02
```

### majorDeviceClass

The description of the Bluetooth major device class, or `unknown` when the class is not recognized

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val majorDeviceClass: String = sample.majorDeviceClass

println(majorDeviceClass) // e.g. Peripheral
```

### connected

Whether the Bluetooth device is currently connected

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val connected: Boolean = sample.connected

println(connected) // e.g. false
```

### paired

Whether the Bluetooth device is paired with the system

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val paired: Boolean = sample.paired

println(paired) // e.g. true
```

### batteryLevel

The battery percentage from `0` to `100`, or `-1` when a battery reading cannot be matched to the device

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val batteryLevel: Int = sample.batteryLevel

println(batteryLevel) // e.g. -1 when unavailable
```

### adapterName

The name of the default local Bluetooth controller, or `unknown` when unavailable. The same default controller name is supplied for every device

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices
val sample: MacOsBluetoothDevice = bluetoothDevices.first()

val adapterName: String = sample.adapterName

println(adapterName) // e.g. Example Mac
```
