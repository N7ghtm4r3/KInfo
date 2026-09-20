Represents a macOS audio device, providing its name, driver version, and codec description

Each item corresponds to a matching native audio service and does not necessarily represent a separate physical sound card.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### driverVersion

The version of the loaded driver associated with the audio service, or `unknown` when unavailable

```kotlin
val soundCards: List<MacOsSoundCard> = hardware.soundCards
val sample: MacOsSoundCard = soundCards.first()

val driverVersion: String = sample.driverVersion

println(driverVersion) // e.g. 1.0.0
```

### name

The name of the audio device

```kotlin
val soundCards: List<MacOsSoundCard> = hardware.soundCards
val sample: MacOsSoundCard = soundCards.first()

val name: String = sample.name

println(name) // e.g. MacBook Pro Speakers
```

### codec

The codec description, returned as an `HDA 0x` identifier or a recognized codec model. The value is `unknown` when the codec cannot be resolved

```kotlin
val soundCards: List<MacOsSoundCard> = hardware.soundCards
val sample: MacOsSoundCard = soundCards.first()

val codec: String = sample.codec

println(codec) // e.g. CS42L83
```
