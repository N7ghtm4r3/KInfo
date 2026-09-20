Represents a macOS USB device, providing its name, vendor, product information, serial number, and connected devices

`hardware.usbDevices` returns the root USB devices. Descendants are available through each item's `connectedDevices` property.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### name

The name of the USB device

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val name: String = sample.name

println(name) // e.g. USB Keyboard
```

### vendor

The vendor of the USB device

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val vendor: String = sample.vendor

println(vendor) // e.g. Example Devices
```

### vendorId

The vendor ID formatted as four lowercase hexadecimal digits, or `unknown` when unavailable

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val vendorId: String = sample.vendorId

println(vendorId) // e.g. 1234
```

### productId

The product ID formatted as four lowercase hexadecimal digits, or `unknown` when unavailable

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val productId: String = sample.productId

println(productId) // e.g. 5678
```

### serialNumber

The serial number of the USB device

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. EXAMPLE1234
```

### uniqueDeviceId

The native registry entry identifier of the USB device, or `unknown` when unavailable. This identifier is not persistent across reboots

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val uniqueDeviceId: String = sample.uniqueDeviceId

println(uniqueDeviceId) // e.g. 4294968000
```

### connectedDevices

The USB devices connected beneath this device. Each child exposes the same properties and can contain further descendants. The list is empty when no children are found

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices
val sample: MacOsUsbDevice = usbDevices.first()

val connectedDevices: List<UsbDevice> = sample.connectedDevices

println(connectedDevices) // e.g. []
```
