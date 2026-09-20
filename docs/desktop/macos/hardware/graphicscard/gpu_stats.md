Represents a snapshot of GPU utilization, memory usage, temperature, power consumption, clock speeds, and fan speed on
native macOS

## API source

The information are retrievable using the [graphics card](graphics_card.md#createstatssession) `createStatsSession()` method

The example below requires at least one entry in `hardware.graphicCards`. Check that the list is not empty before using
`first()`

```kotlin
val graphicCards = hardware.graphicCards
val graphicsCard: MacOsGraphicsCard = graphicCards.first()

val stats: GpuStats = checkNotNull(graphicsCard.createStatsSession())
```

The public return type is nullable. The native implementation returns a snapshot or throws an `IllegalStateException`
when accelerator enumeration fails or no matching accelerator is available

!!! Note

    The properties retain their sampled values. Call `createStatsSession()` again to retrieve updated information.
    Missing native statistics return zero, so a zero value does not confirm that a metric was measured

## Properties

### gpuTicks

The [GPU tick counters](gpu_ticks.md). The native implementation currently returns zero for both counters because these
measurements are unavailable

```kotlin
val gpuTicks: GpuTicks = stats.gpuTicks

println(gpuTicks)
```

### gpuUtilization

The GPU core utilization as a percentage

```kotlin
val gpuUtilization: Double = stats.gpuUtilization

println(gpuUtilization) // e.g. 37.5
```

### vramUsed

The amount of dedicated video memory in use, in bytes, when reported by the accelerator

```kotlin
val vramUsed: Long = stats.vramUsed

println(vramUsed) // e.g. 0
```

### sharedMemoryUsed

The amount of shared system memory used by the GPU, in bytes, when reported by the accelerator

```kotlin
val sharedMemoryUsed: Long = stats.sharedMemoryUsed

println(sharedMemoryUsed) // e.g. 536870912
```

### temperature

The GPU temperature in degrees Celsius

```kotlin
val temperature: Double = stats.temperature

println(temperature) // e.g. 44.0
```

### powerDraw

The GPU power consumption in watts

```kotlin
val powerDraw: Double = stats.powerDraw

println(powerDraw) // e.g. 6.5
```

### coreClockMhz

The GPU core clock speed in megahertz

```kotlin
val coreClockMhz: Long = stats.coreClockMhz

println(coreClockMhz) // e.g. 1398
```

### memoryClockMhz

The GPU memory clock speed in megahertz

```kotlin
val memoryClockMhz: Long = stats.memoryClockMhz

println(memoryClockMhz) // e.g. 0
```

### fanSpeedPercent

The GPU fan speed as a percentage of its maximum

```kotlin
val fanSpeedPercent: Double = stats.fanSpeedPercent

println(fanSpeedPercent) // e.g. 22.0
```
