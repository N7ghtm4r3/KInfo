Represents a partition or logical media entry on native macOS, providing its identity, filesystem details, capacity, and
mount information

The examples below require a disk with at least one entry in `partitions`. Both lists can be empty; check them before using
`first()`

## Properties

### identification

The BSD name identifying the media entry

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val identification: String = sample.identification

println(identification) // e.g. disk0s1
```

### name

The native media name, with the BSD name used when the description is unavailable

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val name: String = sample.name

println(name) // e.g. Apple_APFS
```

### type

The filesystem kind, or an empty string when no filesystem is reported

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val type: String = sample.type

println(type) // e.g. apfs
```

### uuid

The media UUID (**Universally Unique Identifier**), or an empty string when unavailable

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val uuid: String = sample.uuid

println(uuid) // e.g. 12345678-1234-4234-8234-123456789ABC
```

### size

The reported media capacity in bytes

!!! Note

    APFS volumes and exposed snapshots can share their container's reported capacity. This value does not represent the
    space occupied by a volume, and these sizes must not be summed as separate physical allocations

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val size: Long = sample.size

println(size) // e.g. 494384795648
```

### major

The major device number reported by macOS

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val major: Int = sample.major

println(major) // e.g. 1
```

### minor

The minor device number reported by macOS

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val minor: Int = sample.minor

println(minor) // e.g. 1
```

### mountPoint

The mounted volume path, or `unknown` when no path is available

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val mountPoint: String = sample.mountPoint

println(mountPoint) // e.g. /System/Volumes/Data
```

### label

The volume label, or an empty string when unavailable

```kotlin
val disk: MacOsHWDiskStore = hardware.disks.first()
val sample: HWPartition = disk.partitions.first()

val label: String = sample.label

println(label) // e.g. Macintosh HD - Data
```
