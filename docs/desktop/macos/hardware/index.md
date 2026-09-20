# Hardware

The information refer to the available hardware of the device

## Available information

On **macOS** target the available **hardware** information are the below:

| **Category**           | **Property**                                             | **Description**                                                                   | **Source**                             |
|------------------------|----------------------------------------------------------|-----------------------------------------------------------------------------------|----------------------------------------|
| **Computer System**    | [`computerSystem`](computersystem/computer_system.md)    | The details of the computer system, firmware, and baseboard                       | `MacOsHardware.computerSystem`         |
| **CPU**                | [`processorInfo`](centralprocessor/central_processor.md) | The details of the system's central processor (CPU)                               | `MacOsHardware.processorInfo`          |
| **Memory**             | [`globalMemory`](globalmemory/global_memory.md)          | The details about the system's global, virtual, and physical memory               | `MacOsHardware.globalMemory`           |
| **Power Source**       | [`powerSourceDescription`](power_source.md)              | The capacity, electrical measurements, and charging state of the internal battery | `MacOsHardware.powerSourceDescription` |
| **Disk Storage**       | [`disks`](storage/hw_disk_store.md)                      | A list of physical and synthesized disks with their partitions                    | `MacOsHardware.disks`                  |
| **Network Interfaces** | [`networkInterfaces`](network_interface.md)              | A list of network interfaces, their addresses, and traffic statistics             | `MacOsHardware.networkInterfaces`      |
| **Displays**           | [`displaysInfo`](display.md)                             | A list of available display identification information                            | `MacOsHardware.displaysInfo`           |
| **USB Devices**        | [`usbDevices`](usb_device.md)                            | A list of USB devices with their nested connected devices                         | `MacOsHardware.usbDevices`             |
| **Bluetooth Devices**  | [`bluetoothDevices`](bluetooth_device.md)                | A list of Bluetooth devices paired with the system                                | `MacOsHardware.bluetoothDevices`       |
| **Printers**           | [`printers`](printer.md)                                 | A list of printing destinations configured on the system                          | `MacOsHardware.printers`               |
| **Sound Cards**        | [`soundCards`](sound_card.md)                            | A list of audio devices available on the system                                   | `MacOsHardware.soundCards`             |
| **Graphics Cards**     | [`graphicCards`](graphicscard/graphics_card.md)          | A list of graphics cards available on the system                                  | `MacOsHardware.graphicCards`           |

## API source

The information are retrievable using the `MacOsInfo.hardware` API:

Retrieve a [MacOsInfo](../index.md#macosinfo) instance before accessing the hardware information

### Hardware API

Retrieve a `MacOsHardware` instance from `macOsInfo` instance

```kotlin
val hardware: MacOsHardware = macOsInfo.hardware
```

## Properties

The below properties are readable properties provided by the [hardware](#hardware-api) instance

Reading a hardware property retrieves a new snapshot of the corresponding information. Read the property again to
retrieve updated information

### computerSystem

The [computer system](computersystem/computer_system.md) identity, firmware, and baseboard information

```kotlin
val computerSystem: MacOsComputerSystem = hardware.computerSystem

println(computerSystem)
```

### processorInfo

The [central processor](centralprocessor/central_processor.md) identity, topology, frequencies, caches, and CPU load ticks

```kotlin
val processorInfo: MacOsCentralProcessor = hardware.processorInfo

println(processorInfo)
```

### globalMemory

The [global memory](globalmemory/global_memory.md) information, including virtual and physical memory

```kotlin
val globalMemory: MacOsGlobalMemory = hardware.globalMemory

println(globalMemory)
```

### powerSourceDescription

The internal [power source](power_source.md) capacity, electrical measurements, and charging state

```kotlin
val powerSourceDescription: MacOsPowerSource = hardware.powerSourceDescription

println(powerSourceDescription)
```

!!! Warning

    Reading `powerSourceDescription` throws an `IllegalStateException` when the internal battery service is unavailable

### disks

List of physical and synthesized [disks](storage/hw_disk_store.md) with their I/O statistics and associated partitions

```kotlin
val disks: List<MacOsHWDiskStore> = hardware.disks

println(disks)
```

### networkInterfaces

List of [network interfaces](network_interface.md) with their identities, assigned addresses, and traffic statistics

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces

println(networkInterfaces)
```

### displaysInfo

List of available [display identification information](display.md)

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo

println(displaysInfo)
```

### usbDevices

List of [USB devices](usb_device.md) with their nested connected devices

```kotlin
val usbDevices: List<MacOsUsbDevice> = hardware.usbDevices

println(usbDevices)
```

### bluetoothDevices

List of [Bluetooth devices](bluetooth_device.md) paired with the system, including disconnected devices

```kotlin
val bluetoothDevices: List<MacOsBluetoothDevice> = hardware.bluetoothDevices

println(bluetoothDevices)
```

The `connected` property indicates whether a device is currently connected. A `batteryLevel` of `-1` indicates that
a battery reading is unavailable

### printers

List of [printers](printer.md) configured on the system

```kotlin
val printers: List<MacOsPrinter> = hardware.printers

println(printers)
```

### soundCards

List of [sound cards](sound_card.md) with their names, driver versions, and codec descriptions

```kotlin
val soundCards: List<MacOsSoundCard> = hardware.soundCards

println(soundCards)
```

### graphicCards

List of [graphics cards](graphicscard/graphics_card.md) with their identities, hardware revisions, and video-memory values

```kotlin
val graphicCards: List<MacOsGraphicsCard> = hardware.graphicCards

println(graphicCards)
```
