Represents an immutable snapshot of cumulative GPU active and idle tick counters in opaque, platform-native units

### Original source

The gpu ticks information are retrieved from `Hardware.GraphicsCard.createStatsSession().gpuTicks()` method

### KInfo's source

```kotlin
val graphicsCards = hardware.graphicsCards
val graphicsCard: GraphicsCard = graphicsCards.first()

val stats: GpuStats = graphicsCard.createStatsSession()
val ticks: GpuTicks = stats.ticks
```

### Properties

#### activeTicks

Current active ticks

```kotlin
val activeTicks: Long = ticks.activeTicks

println(activeTicks) // e.g. 184523440
```

#### idleTicks

The idle ticks count

```kotlin
val idleTicks: Long = stats.idleTicks

println(idleTicks) // e.g. 927814120
```