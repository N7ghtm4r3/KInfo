Represents a physical or synthesized disk on native macOS, providing its identity, capacity, I/O statistics, and associated
partitions

The examples below require at least one entry in `hardware.disks`. Check that the list is not empty before using `first()`

## Properties

### name

The BSD name of the disk

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val name: String = sample.name

println(name) // e.g. disk0
```

### model

The model description of the disk, or `unknown` when unavailable

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val model: String = sample.model

println(model) // e.g. APPLE SSD AP0512N
```

### serial

The serial number of the disk, or `unknown` when unavailable

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val serial: String = sample.serial

println(serial) // e.g. SSD000123456
```

### size

The total capacity of the disk in bytes

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val size: Long = sample.size

println(size) // e.g. 500277790720
```

### reads

The cumulative number of read operations reported by the disk

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val reads: Long = sample.reads

println(reads) // e.g. 400000
```

### readBytes

The cumulative number of bytes read from the disk

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val readBytes: Long = sample.readBytes

println(readBytes) // e.g. 1638400000
```

### writes

The cumulative number of write operations reported by the disk

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val writes: Long = sample.writes

println(writes) // e.g. 200000
```

### writesBytes

The cumulative number of bytes written to the disk

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val writesBytes: Long = sample.writesBytes

println(writesBytes) // e.g. 819200000
```

### currentQueueLength

The disk I/O queue length. The native implementation currently returns `0` because this measurement is unavailable;
this value does not indicate that the disk is idle

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val currentQueueLength: Long = sample.currentQueueLength

println(currentQueueLength) // 0
```

### transferTime

The sum of cumulative read and write durations in milliseconds. Missing timing entries contribute zero, and the value
does not represent elapsed wall-clock busy time

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val transferTime: Long = sample.transferTime

println(transferTime) // e.g. 120000
```

### partitions

List of [partitions and logical media](hw_partition.md) associated with the disk, including APFS volumes and exposed
snapshots

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val partitions: List<HWPartition> = sample.partitions

println(partitions)
```

### timestamp

The collection time of the disk snapshot in milliseconds since the Unix epoch

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val timestamp: Long = sample.timestamp

println(timestamp) // e.g. 1760000000000
```

### updateAttributes

Always `false` for the captured snapshot. Read `hardware.disks` again to retrieve updated information

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // false
```

### diskType

The storage category resolved from the native disk characteristics

#### DiskType

| **Type**      | **Description**                                    |
|---------------|----------------------------------------------------|
| **SSD**       | A medium identified as solid-state storage          |
| **HDD**       | A medium identified as rotational storage           |
| **Removable** | A removable disk                                   |
| **Virtual**   | A disk using a virtual interconnect                 |
| **Unknown**   | A disk whose storage category cannot be identified |

```kotlin
val disks = hardware.disks
val sample: MacOsHWDiskStore = disks.first()

val diskType: DiskType = sample.diskType

println(diskType) // e.g. SSD
```
