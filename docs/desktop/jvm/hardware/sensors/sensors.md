Information refer to various sensor data of a computer system.
This includes CPU temperature, fan speeds, and CPU voltage

### Original source

The sensors information are retrieved from `Hardware.Sensors` interface

### KInfo's source

```kotlin
val sensors = hardware.sensors
```

### Properties

#### cpuTemperature

The current temperature of the CPU in degrees Celsius

```kotlin
val cpuTemperature: Double = sensors.cpuTemperature

println(cpuTemperature) // e.g. 65.3
```

#### fanSpeeds

The speeds of the fans in the system, in RPM (Revolutions per Minute)

```kotlin
val fanSpeeds: IntArray = sensors.fanSpeeds

println(fanSpeeds) // e.g. [1200, 1300, 1150]
```

#### cpuVoltage

The current voltage supplied to the CPU, in volts

```kotlin
val cpuVoltage: Double = sensors.cpuVoltage

println(cpuVoltage) // e.g. 1.25
```