# Firmware

The information refer to the firmware of the computer system

## Original source

The macOS firmware information are provided by `MacOsFirmware`, which inherits `Firmware`

## KInfo's source

```kotlin
val computerSystem = hardware.computerSystem
val firmware: Firmware = computerSystem.firmware
```

See [computer system](computer_system.md) for the containing API. Unavailable registry values can be empty or `unknown`

## Properties

### manufacturer

The manufacturer reported by the native firmware mapping

```kotlin
val manufacturer: String = firmware.manufacturer

println(manufacturer) // e.g. Apple Inc.
```

### name

The bootloader name, with the device type used as a fallback on Apple Silicon

```kotlin
val name: String = firmware.name

println(name) // e.g. iBoot
```

### description

The firmware ABI description from the native registry

```kotlin
val description: String = firmware.description

println(description) // e.g. unknown
```

### version

The firmware version reported by the native registry

```kotlin
val version: String = firmware.version

println(version) // e.g. iBoot-10151.101.3
```

### releaseDate

The mapped release-date value. On Apple Silicon this is the device-tree timestamp, which is not a verified firmware release date

```kotlin
val releaseDate: String = firmware.releaseDate

println(releaseDate) // empty when unavailable
```

### isAppleSilicon

Whether the macOS firmware object uses the Apple Silicon mapping. This property belongs to `MacOsFirmware`, so access it through a safe cast from the inherited `Firmware` type

```kotlin
val isAppleSilicon: Boolean? = (firmware as? MacOsFirmware)?.isAppleSilicon

println(isAppleSilicon) // e.g. true
```
