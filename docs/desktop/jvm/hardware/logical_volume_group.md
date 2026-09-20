Represents a logical volume group in a storage system, providing details about the name of the volume group, the physical volumes
associated with it, and the logical volumes mapped to physical volumes

## Properties

### name

The name of the logical volume group

```kotlin
val logicalVolumeGroups = hardware.logicalVolumeGroups
val sample: LogicalVolumeGroup = logicalVolumeGroups.first()

val name: String = sample.name

println(name) // e.g. root
```

### physicalVolumes

A set of physical volumes that are part of the logical volume group

```kotlin
val logicalVolumeGroups = hardware.logicalVolumeGroups
val sample: LogicalVolumeGroup = logicalVolumeGroups.first()

val physicalVolumes: Set<String> = sample.physicalVolumes

println(physicalVolumes) // e.g. ["/dev/sda1"]
```

### logicalVolumes

A map where the key is the logical volume name and the value is a set of physical volume names that are part of the 
logical volume

```kotlin
val logicalVolumeGroups = hardware.logicalVolumeGroups
val sample: LogicalVolumeGroup = logicalVolumeGroups.first()

val logicalVolumes: Map<String, Set<String>> = sample.logicalVolumes

println(logicalVolumes) // e.g. [[root, /dev/sda1]]
```