# Hardware

The information refer to the available hardware of the device

## Available information

On **desktop** target the available **hardware** information are the below:

| **Category**           | **Property**          | **Description**                                          | **Source**                     |
|------------------------|-----------------------|----------------------------------------------------------|--------------------------------|
| **Computer System**    | `computerSystem`      | The details of the computer system                       | `Hardware.computerSystem`      |
| **CPU**                | `centralProcessor`    | The details of the system's central processor (CPU)      | `Hardware.centralProcessor`    |
| **Memory**             | `globalMemory`        | The details about the system's global memory             | `Hardware.globalMemory`        |
| **Power Sources**      | `powerSources`        | A list of power sources available to the system          | `Hardware.powerSources`        |
| **Disk Storage**       | `diskStores`          | A list of disk storage devices                           | `Hardware.diskStores`          |
| **Logical Volumes**    | `logicalVolumeGroups` | A list of logical volume groups configured on the system | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | `networkIFs`          | A list of network interfaces on the system               | `Hardware.networkIFs`          |
| **Displays**           | `displays`            | A list of display devices connected to the system        | `Hardware.displays`            |
| **Sensors**            | `sensors`             | The details of system sensors                            | `Hardware.sensors`             |
| **Sound Cards**        | `soundCards`          | A list of sound cards available on the system            | `Hardware.soundCards`          |
| **Graphics Cards**     | `graphicsCards`       | A list of graphics cards available on the system         | `Hardware.graphicsCards`       |

## API source

The information are retrievable using the `DesktopInfo.Hardware` API:

### Hardware API

Retrieve a `Hardware` instance from `desktopInfo` instance

```kotlin
val hardware = desktopInfo.hardware
```

## Properties

The below properties are miscellaneous readable properties provided by the [hardware](#hardware-api) instance 

### powerSources

List of [power sources](power_source.md) available in the system

```kotlin
val powerSources: List<PowerSource> = hardware.powerSources

println(powerSources)
```

### diskStores

List of [storage devices](storage/hw_disk_store.md) available in the system

```kotlin
val diskStores: List<HWDiskStore> = hardware.diskStores

println(diskStores)
```

### logicalVolumeGroups

List of [logical volume groups](logical_volume_group.md) on the system

```kotlin
val logicalVolumeGroups: List<LogicalVolumeGroup> = hardware.logicalVolumeGroups

println(logicalVolumeGroups)
```

### networkIFs

List of [network interfaces](network_interface.md) available on the system

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs

println(networkIFs)
```

### displays

List of [displays](display.md) connected to the system

```kotlin
val displays: List<Display> = hardware.displays

println(displays)
```

### soundCards

List of [sound cards](sound_card.md) available on the system

```kotlin
val soundCards: List<SoundCard> = hardware.soundCards

println(soundCards)
```

### graphicsCards

List of [graphic cards](graphic_card.md) available on the system

```kotlin
val graphicsCards: List<GraphicsCard> = hardware.graphicsCards

println(graphicsCards)
```