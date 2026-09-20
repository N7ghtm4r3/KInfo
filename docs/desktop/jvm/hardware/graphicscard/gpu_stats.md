Represents a session handle for sampling dynamic GPU metrics

### Original source

The gpu stats information are retrieved from `Hardware.GraphicsCard.createStatsSession()` method

### KInfo's source

```kotlin
val graphicsCards = hardware.graphicsCards
val graphicsCard: GraphicsCard = graphicsCards.first()

val stats: GpuStats = graphicsCard.createStatsSession()
```

### Properties

#### gpuTicks

A snapshot of cumulative GPU active and idle ticks in opaque, platform-native units. The counters are 
monotonically increasing, represented using [GpuTicks](gpu_ticks.md)

```kotlin
val gpuTicks: GpuTicks = stats.gpuTicks

println(gpuTicks) // e.g. 184523440
```

#### gpuUtilization

The instantaneous GPU core utilization as a percentage

```kotlin
val gpuUtilization: Double = stats.gpuUtilization

println(gpuUtilization) // e.g. 37.5
```

#### vramUsed

The amount of dedicated VRAM currently in use in bytes

```kotlin
val vramUsed: Long = stats.vramUsed

println(vramUsed) // e.g. 4294967296
```

#### sharedMemoryUsed

The amount of shared system memory currently used by this GPU in bytes

```kotlin
val sharedMemoryUsed: Long = stats.sharedMemoryUsed

println(sharedMemoryUsed) // e.g. 536870912
```

#### temperature

The temperature in degrees Celsius

```kotlin
val temperature: Double = stats.temperature

println(temperature) // e.g. 44
```

#### powerDraw

The GPU power consumption

```kotlin
val powerDraw: Double = stats.powerDraw

println(powerDraw) // e.g. 78.4
```

#### coreClockMhz

The current GPU core clock speed

```kotlin
val coreClockMhz: Long = stats.coreClockMhz

println(coreClockMhz) // e.g. 1860
```

#### memoryClockMhz

The current GPU memory clock speed

```kotlin
val memoryClockMhz: Long = stats.memoryClockMhz

println(memoryClockMhz) // e.g. 7000
```

#### fanSpeedPercent

The GPU fan speed as a percentage of maximum

```kotlin
val fanSpeedPercent: Double = stats.fanSpeedPercent

println(fanSpeedPercent) // e.g. 22
```