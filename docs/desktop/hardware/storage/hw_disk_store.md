Represents information about a disk or storage device in the system, providing details about the disk's model, serial number,
size, read/write statistics, partitions, and other attributes

## Properties

### name

The name or identifier of the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val name: String = sample.name

println(name) // e.g. disk0
```

### model

The model name of the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val model: String = sample.model

println(model) // e.g. Samsung SSD 860
```

### serial

The serial number of the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val serial: String = sample.serial

println(serial) // e.g. S4EWNX0M123456A
```

### size

The total size of the disk (in bytes)

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val size: Long = sample.size

println(size) // e.g. 512101820160
```

### reads

The number of read operations performed on the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val reads: Long = sample.reads

println(reads) // e.g. 400000
```

### readBytes

The number of bytes read from the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val readBytes: Long = sample.readBytes

println(readBytes) // e.g. 1000000000
```

### writes

The number of write operations performed on the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val writes: Long = sample.writes

println(writes) // e.g. 800000
```

### writesBytes

The number of bytes written to the disk

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val writesBytes: Long = sample.writesBytes

println(writesBytes) // e.g. 1000000000
```

### currentQueueLength

The current length of the disk's I/O request queue

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val currentQueueLength: Long = sample.currentQueueLength

println(currentQueueLength) // e.g. 0
```

### transferTime

The time (in milliseconds) spent transferring data for I/O operations

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val transferTime: Long = sample.transferTime

println(transferTime) // e.g. 5047355
```

### partitions

List of [partitions](hw_partition.md) on the disk

```kotlin
val partitions: List<HWPartition> = hardware.partitions

println(partitions)
```

### timestamp

The timestamp when the disk information was last updated

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val timestamp: Long = sample.timestamp

println(timestamp) // e.g. 1760544083287
```

### updateAttributes

A flag indicating whether the disk's attributes should be updated

```kotlin
val diskStores = hardware.diskStores
val sample: HWDiskStore = diskStores.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // true or false
```