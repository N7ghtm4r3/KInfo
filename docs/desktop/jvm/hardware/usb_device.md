Represents a USB device, providing details about the USB device, including its name, vendor, product information, 
serial number, and connected devices

## Properties

### name

The name of the USB device

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val name: String = sample.name

println(name) // e.g. USB 3.0 Hub
```

### vendor

The vendor of the USB device

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val vendor: String = sample.vendor

println(vendor) // e.g. Logitech
```

### vendorId

The vendor ID of the USB device, typically a 4-character hexadecimal value

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val vendorId: String = sample.vendorId

println(vendorId) // e.g. 046D
```

### productId

The product ID of the USB device, typically a 4-character hexadecimal value

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val productId: String = sample.productId

println(productId) // e.g. 1A2B
```

### serialNumber

The serial number of the USB device

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. SN1234567890
```

### uniqueDeviceId

A unique identifier for the USB device

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val uniqueDeviceId: String = sample.uniqueDeviceId

println(uniqueDeviceId) // e.g. USB\\VID_046D&PID_1A2B\\SN1234567890
```

### connectedDevices

A list of other USB devices that are connected to this device (if any)

```kotlin
val usbDevices = hardware.getUsbDevices(
    tree = // true or false
)
val sample: UsbDevice = usbDevices.first()

val connectedDevices: List<UsbDevice> = sample.connectedDevices

println(connectedDevices) // e.g. []
```