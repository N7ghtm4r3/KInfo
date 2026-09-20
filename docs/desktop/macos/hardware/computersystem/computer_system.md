# Computer system

The information refer to the computer system on native macOS

## Original source

The computer system information are retrieved from the `MacOsComputerSystem` interface, which inherits `ComputerSystem`

## KInfo's source

```kotlin
val computerSystem: MacOsComputerSystem = hardware.computerSystem
```

Retrieve `hardware` as shown in the [Hardware API](../index.md#hardware-api). Unavailable machine identity values can be empty strings

## Properties

### manufacturer

The manufacturer of the computer system

```kotlin
val manufacturer: String = computerSystem.manufacturer

println(manufacturer) // e.g. Apple Inc.
```

### model

The model identifier of the computer system

```kotlin
val model: String = computerSystem.model

println(model) // e.g. MacBookAir10,1
```

### serialNumber

The serial number of the computer system

```kotlin
val serialNumber: String = computerSystem.serialNumber

println(serialNumber) // e.g. EXAMPLE123456
```

### hardwareUUID

The hardware UUID of the computer system

```kotlin
val hardwareUUID: String = computerSystem.hardwareUUID

println(hardwareUUID) // e.g. 00000000-0000-0000-0000-000000000000
```

### firmware

The [firmware](firmware.md) information of the computer system

```kotlin
val firmware: Firmware = computerSystem.firmware

println(firmware)
```

### baseboard

The [baseboard](baseboard.md) information of the computer system

```kotlin
val baseboard: Baseboard = computerSystem.baseboard

println(baseboard)
```
