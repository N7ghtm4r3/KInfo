Represents a graphics card on native macOS, providing its name, registry identifier, vendor, hardware revision, and
video-memory information

The examples below require at least one entry in `hardware.graphicCards`. Check that the list is not empty before using
`first()`

## Properties

### name

The graphics card model name, or `unknown` when unavailable

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val name: String = sample.name

println(name) // e.g. Apple M2
```

### deviceId

The decimal registry entry identifier of the graphics card, or `unknown` when unavailable. This identifier is not
persistent across system reboots

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val deviceId: String = sample.deviceId

println(deviceId) // e.g. 4294968000
```

### vendor

The resolved vendor name or hexadecimal identifier. The native Apple GPU mapper uses `Apple` when the vendor cannot be
resolved

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val vendor: String = sample.vendor

println(vendor) // e.g. Apple
```

### versionInfo

The hardware revision formatted as `Revision 0x...`, or `unknown` when unavailable

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val versionInfo: String = sample.versionInfo

println(versionInfo) // e.g. Revision 0x1
```

### vRam

The graphics card video-memory value. On Apple Silicon, the native mapper returns `0` for unified memory; this does not
mean that the GPU has no memory available. An unavailable reading uses `-1`

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val vRam: Long = sample.vRam

println(vRam) // 0 on Apple Silicon
```

## Methods

### createStatsSession

Retrieves a new [GPU statistics](gpu_stats.md) snapshot. Its properties retain the sampled values; call the method again
to retrieve updated information

```kotlin
val graphicCards = hardware.graphicCards
val sample: MacOsGraphicsCard = graphicCards.first()

val stats: GpuStats? = sample.createStatsSession()

println(stats?.temperature) // e.g. 44.0
```

!!! Warning

    The native implementation throws an `IllegalStateException` if accelerator enumeration fails or the graphics card
    identifier does not match an available accelerator
