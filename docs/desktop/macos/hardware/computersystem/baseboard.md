# Baseboard

The information refer to the baseboard or motherboard of the computer system

## Original source

The macOS baseboard information are provided by `MacOsBaseboard`, which inherits `Baseboard`

## KInfo's source

```kotlin
val computerSystem = hardware.computerSystem
val baseboard: Baseboard = computerSystem.baseboard
```

See [computer system](computer_system.md) for the containing API. Unavailable manufacturer, version, and serial-number values can be empty strings

## Properties

### manufacturer

The manufacturer reported by the platform registry

```kotlin
val manufacturer: String = baseboard.manufacturer

println(manufacturer) // e.g. Apple Inc.
```

### model

The board identifier, with the hardware target identifier used when the board identifier is unavailable

```kotlin
val model: String = baseboard.model

println(model) // e.g. j313ap
```

### version

The platform version value. It does not necessarily identify the physical revision of the logic board

```kotlin
val version: String = baseboard.version

println(version) // empty when unavailable
```

### serialNumber

The logic board serial number

```kotlin
val serialNumber: String = baseboard.serialNumber

println(serialNumber) // e.g. EXAMPLEBOARD123
```
