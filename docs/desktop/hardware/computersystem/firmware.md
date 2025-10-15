The information refer to the firmware of the computer system

### Original source

The firmware information are retrieved from `Hardware.ComputerSystem.Firmware` interface

### KInfo's source

```kotlin
val firmware = computerSystem.firmware
```

### Properties

#### manufacturer

The manufacturer of the firmware

```kotlin
val manufacturer: String = firmware.manufacturer

println(manufacturer) // e.g. Microsoft
```

#### name

The name of the firmware

```kotlin
val name: String = firmware.name

println(name) // e.g. UEFI
```

#### description

Detailed description of the firmware

```kotlin
val description: String = firmware.description

println(description) // e.g. LENOVO BIOS Rev 1.45 (UEFI)
```

#### version

The version number of the firmware

```kotlin
val version: String = firmware.version

println(version) // e.g. 2.5.3
```

#### releaseDate

The release date of the firmware

```kotlin
val releaseDate: String = firmware.releaseDate

println(releaseDate) // e.g. 06/10/2024
```