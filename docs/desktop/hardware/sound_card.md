Represents a sound card in the system, providing details about the sound card's driver version, name, and codec type.

## Properties

### driverVersion

The version of the driver for the sound card

```kotlin
val soundCards = hardware.soundCards
val sample: SoundCard = soundCards.first()

val driverVersion: String = sample.driverVersion

println(driverVersion) // e.g. 6.0.1.8541
```

### name

The name of the sound card

```kotlin
val soundCards = hardware.soundCards
val sample: SoundCard = soundCards.first()

val name: String = sample.name

println(name) // e.g. Realtek High Definition Audio
```

### codec

The codec used by the sound card

```kotlin
val soundCards = hardware.soundCards
val sample: SoundCard = soundCards.first()

val codec: String = sample.codec

println(codec) // e.g. Realtek ALC1220
```