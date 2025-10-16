Represents a graphics card in the system, providing details about the graphics card, including its name, device ID, vendor, 
version information, and VRAM size

## Properties

### name

The name of the graphics card

```kotlin
val graphicsCards = hardware.graphicsCards
val sample: GraphicsCard = graphicsCards.first()

val name: String = sample.name

println(name) // e.g. NVIDIA GeForce RTX 3080
```

### deviceId

The unique device identifier for the graphics card, this ID is typically used to distinguish the card within the system

```kotlin
val graphicsCards = hardware.graphicsCards
val sample: GraphicsCard = graphicsCards.first()

val deviceId: String = sample.deviceId

println(deviceId) // e.g. 1234-5678-9ABCDEF0
```

### vendor

The vendor of the graphics card

```kotlin
val graphicsCards = hardware.graphicsCards
val sample: GraphicsCard = graphicsCards.first()

val vendor: String = sample.vendor

println(vendor) // e.g. NVIDIA
```

### versionInfo

The version information of the graphics card

```kotlin
val graphicsCards = hardware.graphicsCards
val sample: GraphicsCard = graphicsCards.first()

val versionInfo: String = sample.versionInfo

println(versionInfo) // e.g. Driver 531.68 / Rev A1
```

### vRam

The amount of VRAM (video memory) available on the graphics card, in bytes

```kotlin
val graphicsCards = hardware.graphicsCards
val sample: GraphicsCard = graphicsCards.first()

val vRam: Long = sample.vRam

println(vRam) // e.g. 10737418240        
```