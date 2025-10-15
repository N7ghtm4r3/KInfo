Represents a power source (e.g., a battery) in the system, providing details like the remaining capacity, power usage, 
voltage, and charging status

## Properties

### name

The name of the power source

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val name: String = sample.name

println(name) // e.g. Battery 1
```

### deviceName

The name of the device associated with the power source

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val deviceName: String = sample.deviceName

println(deviceName) // e.g. InternalBattery-0
```

### remainingCapacityPercent

The remaining capacity of the power source as a percentage of the total capacity

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val remainingCapacityPercent: Double = sample.remainingCapacityPercent

println(remainingCapacityPercent) // e.g. 22.2
```

### timeRemainingEstimated

The estimated time remaining on the power source (in hours)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val timeRemainingEstimated: Double = sample.timeRemainingEstimated

println(timeRemainingEstimated) // e.g. 1
```

### timeRemainingInstant

The instantaneous time remaining on the power source (in hours)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val timeRemainingInstant: Double = sample.timeRemainingInstant

println(timeRemainingInstant) // e.g. 1760539506000
```

### powerUsageRate

The rate of power usage by the power source (in watts)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val powerUsageRate: Double = sample.powerUsageRate

println(powerUsageRate) // e.g. 15.5
```

### voltage

The voltage of the power source (in volts)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val voltage: Double = sample.voltage

println(voltage) // e.g. 11.1
```

### amperage

The amperage of the power source (in amperes)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val amperage: Double = sample.amperage

println(amperage) // e.g. 1.4
```

### isPowerOnLine

Whether the power source is connected to an external power line

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val isPowerOnLine: Boolean = sample.isPowerOnLine

println(isPowerOnLine) // true or false
```

### isCharging

Whether the power source is currently charging

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val isCharging: Boolean = sample.isCharging

println(isCharging) // true or false
```

### isDischarging

Whether the power source is currently discharging

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val isDischarging: Boolean = sample.isDischarging

println(isDischarging) // true or false
```

### capacityUnits

The units for capacity

#### CapacityUnits

Enum with the following entries: `MWH`, `MAH` and `RELATIVE`

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val capacityUnits: CapacityUnits = sample.capacityUnits

println(capacityUnits) // [`MWH`, `MAH`, `RELATIVE`]
```

### currentCapacity

The current capacity of the power source (in mAh or Ah, depending on [capacityUnits](#capacityunits))

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val currentCapacity: Int = sample.currentCapacity

println(currentCapacity) // e.g. 1
```

### maxCapacity

The maximum capacity of the power source (in mAh or Ah, depending on [capacityUnits](#capacityunits))

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val maxCapacity: Int = sample.maxCapacity

println(maxCapacity) // e.g. 1
```

### designCapacity

The designed capacity of the power source (in mAh or Ah, depending on [capacityUnits](#capacityunits))

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val designCapacity: Int = sample.designCapacity

println(designCapacity) // e.g. 1
```

### cycleCount

The number of charge cycles the power source has gone through

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val cycleCount: Int = sample.cycleCount

println(cycleCount) // e.g. 100
```

### chemistry

The chemistry used in the power source

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val chemistry: String = sample.chemistry

println(chemistry) // e.g. Li-ion
```

### manufacturer

The manufacturer of the power source

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val manufacturer: String = sample.manufacturer

println(manufacturer) // e.g. BatteryCo
```

### serialNumber

The serial number of the power source

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. SN123456789
```

### temperature

The temperature of the power source (in Celsius)

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val temperature: Double = sample.temperature

println(temperature) // e.g. 35.5
```

### updateAttributes

Whether the attributes of the power source should be updated

```kotlin
val powerSources = hardware.powerSources
val sample: PowerSource = powerSources.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // e.g. 35.5
```