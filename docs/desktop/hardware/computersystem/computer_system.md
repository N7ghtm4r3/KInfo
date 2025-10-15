The information refer to the computer system

### Original source

The computer system information are retrieved from `Hardware.ComputerSystem` interface

### KInfo's source

```kotlin
val computerSystem = hardware.computerSystem
```

### Properties

#### manufacturer

The manufacturer of the computer system

```kotlin
val manufacturer: String = computerSystem.manufacturer

println(manufacturer) // e.g. Lenovo
```

#### model

The model of the computer system

```kotlin
val model: String = computerSystem.model

println(model) // e.g. 82RB
```

#### serialNumber

The serial number of the computer system

```kotlin
val serialNumber: String = computerSystem.serialNumber

println(serialNumber) // e.g. PF3X1234
```

#### hardwareUUID

The unique hardware identifier for the system

```kotlin
val hardwareUUID: String = computerSystem.hardwareUUID

println(hardwareUUID) // e.g. 00000000-0000-0000-0000-000000000000
```