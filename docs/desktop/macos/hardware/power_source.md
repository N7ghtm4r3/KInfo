Represents the internal battery on native macOS, providing details about capacity, electrical measurements, and charging
status

!!! Warning

    Reading `hardware.powerSourceDescription` throws an `IllegalStateException` when the internal battery service is
    unavailable

## Properties

### name

The operating system name of the internal battery, or `unknown` when unavailable

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val name: String = sample.name

println(name) // e.g. InternalBattery-0
```

### deviceName

The device-reported name of the battery

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val deviceName: String = sample.deviceName

println(deviceName) // e.g. bq20z451
```

### remainingCapacityPercent

The remaining charge as a percentage from `0.0` to `100.0`. The system estimate can differ from the ratio between
`currentCapacity` and `maxCapacity`

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val remainingCapacityPercent: Double = sample.remainingCapacityPercent

println(remainingCapacityPercent) // e.g. 75.0
```

### timeRemainingEstimated

The estimated remaining runtime in hours, converted from the native average time to empty. Missing readings use `0.0`

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val timeRemainingEstimated: Double = sample.timeRemainingEstimated

println(timeRemainingEstimated) // e.g. 3.5
```

### timeRemainingInstant

The instantaneous remaining runtime in hours. A missing native reading produces a negative value (`-1.0 / 60.0`)

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val timeRemainingInstant: Double = sample.timeRemainingInstant

println(timeRemainingInstant) // e.g. 3.25
```

### powerUsageRate

The signed power rate in watts, calculated from voltage and amperage. Positive values indicate charging and negative
values indicate discharging

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val powerUsageRate: Double = sample.powerUsageRate

println(powerUsageRate) // e.g. -18.0
```

### voltage

The battery voltage in volts

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val voltage: Double = sample.voltage

println(voltage) // e.g. 12.0
```

### amperage

The signed battery current in amperes. Positive values indicate charging and negative values indicate discharging

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val amperage: Double = sample.amperage

println(amperage) // e.g. -1.5
```

### isPowerOnLine

Whether the device is connected to an external power source

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val isPowerOnLine: Boolean = sample.isPowerOnLine

println(isPowerOnLine) // true or false
```

### isCharging

Whether the battery reports that it is charging

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val isCharging: Boolean = sample.isCharging

println(isCharging) // true or false
```

### isDischarging

Whether the battery current is negative

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val isDischarging: Boolean = sample.isDischarging

println(isDischarging) // true or false
```

### capacityUnits

The units shared by `currentCapacity`, `maxCapacity`, and `designCapacity`. Native macOS uses `CapacityUnits.MAH`

#### CapacityUnits

| **Unit**     | **Description**                                             |
|--------------|-------------------------------------------------------------|
| **MWH**      | Capacity expressed in milliwatt-hours                        |
| **MAH**      | Capacity expressed in milliampere-hours                      |
| **RELATIVE** | Capacity expressed on a shared scale without a physical unit |

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val capacityUnits: CapacityUnits = sample.capacityUnits

println(capacityUnits) // MAH
```

### currentCapacity

The remaining battery capacity in milliampere-hours

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val currentCapacity: Int = sample.currentCapacity

println(currentCapacity) // e.g. 4500
```

### maxCapacity

The full-charge battery capacity in milliampere-hours

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val maxCapacity: Int = sample.maxCapacity

println(maxCapacity) // e.g. 6000
```

### designCapacity

The original design capacity in milliampere-hours

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val designCapacity: Int = sample.designCapacity

println(designCapacity) // e.g. 6200
```

### cycleCount

The reported number of battery charge cycles

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val cycleCount: Int = sample.cycleCount

println(cycleCount) // e.g. 120
```

### chemistry

The battery chemistry description. The native implementation currently returns `unknown`

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val chemistry: String = sample.chemistry

println(chemistry) // unknown
```

### manufacturer

The battery manufacturer name, or `unknown` when unavailable

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val manufacturer: String = sample.manufacturer

println(manufacturer) // e.g. SMP
```

### serialNumber

The battery serial number, or an empty string when unavailable

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. BAT000123456
```

### temperature

The battery temperature in degrees Celsius. A missing native reading is converted to `-273.15` and must not be treated
as a measured temperature

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val temperature: Double = sample.temperature

println(temperature) // e.g. 31.25
```

### updateAttributes

Always `false` for the captured snapshot. Read `hardware.powerSourceDescription` again to retrieve updated information

```kotlin
val sample: MacOsPowerSource = hardware.powerSourceDescription

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // false
```
