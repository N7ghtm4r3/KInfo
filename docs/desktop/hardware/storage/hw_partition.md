Represents a hardware partition on a storage device, providing details about the partition such as its identification,
name, type, UUID, size, and mount point

## Properties

### identification

The unique identifier of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val identification: String = sample.identification

println(identification) // e.g. Disk #0, partition #1
```

### name

The unique identifier of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val name: String = sample.name

println(name) // e.g. GPT: Basic Data
```

### type

The type of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val type: String = sample.type

println(type) // e.g. NTFS
```

### uuid

The UUID (**Universally Unique Identifier**) of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val uuid: String = sample.uuid

println(uuid) // e.g. 00000000-0000-0000-0000-000000000000
```

### size

The total size of the partition (in bytes)

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val size: Long = sample.size

println(size) // e.g. 511633784832
```

### major

The major number of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val major: Int = sample.major

println(major) // e.g. 0
```

### minor

The minor number of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val minor: Int = sample.minor

println(minor) // e.g. 1
```

### mountPoint

The mount point of the partition

```kotlin
val diskStores = hardware.diskStores
val diskStore: HWDiskStore = diskStores.first()

val partitions = diskStore.partitions
val sample: HWPartition = partitions.first()

val mountPoint: String = sample.mountPoint

println(mountPoint) // e.g. /home
```