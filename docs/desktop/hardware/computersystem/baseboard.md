The information refer to the baseboard or motherboard of the computer system

### Original source

The baseboard information are retrieved from `Hardware.ComputerSystem.Baseboard` interface

### KInfo's source

```kotlin
val baseboard = computerSystem.baseboard
```

### Properties

#### manufacturer

The manufacturer of the baseboard

```kotlin
val manufacturer: String = baseboard.manufacturer

println(manufacturer) // e.g. Gigabyte
```

#### model

The model name or number of the baseboard

```kotlin
val model: String = baseboard.model

println(model) // e.g. 21CBCTO1WW
```

#### version

The version of the baseboard

```kotlin
val version: String = baseboard.version

println(version) // e.g. often "Not Specified"
```

#### serialNumber

The serial number of the baseboard

```kotlin
val serialNumber: String = baseboard.serialNumber

println(serialNumber) // e.g. SN0000000000
```