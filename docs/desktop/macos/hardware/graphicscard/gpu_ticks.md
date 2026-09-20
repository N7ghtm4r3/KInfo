Represents GPU active and idle tick counters on native macOS

!!! Note

    The native implementation currently returns `0` for both counters because these measurements are unavailable.
    These values cannot be used to calculate GPU utilization; use [gpuUtilization](gpu_stats.md#gpuutilization) when
    reported by the accelerator

## API source

The information are retrievable using the [GPU statistics](gpu_stats.md) `gpuTicks` property

The example below requires at least one entry in `hardware.graphicCards`. Check that the list is not empty before using
`first()`

```kotlin
val graphicCards = hardware.graphicCards
val graphicsCard: MacOsGraphicsCard = graphicCards.first()

val stats: GpuStats = checkNotNull(graphicsCard.createStatsSession())
val ticks: GpuTicks = stats.gpuTicks
```

## Properties

### activeTicks

The cumulative active ticks in platform-native units. The native mapper currently returns `0`

```kotlin
val activeTicks: Long = ticks.activeTicks

println(activeTicks) // 0
```

### idleTicks

The cumulative idle ticks in platform-native units. The native mapper currently returns `0`

```kotlin
val idleTicks: Long = ticks.idleTicks

println(idleTicks) // 0
```
