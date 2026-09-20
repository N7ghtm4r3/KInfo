Represents a file store (such as a disk or mount point) in the operating system, 
providing information about the file system, including the volume, space usage, and attributes related to the file store

## Properties

### name

The name of the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val name: String = sample.name

println(name) // e.g. Local Disk (C:)
```

### volume

The volume identifier for the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val volume: String = sample.volume

println(volume) // e.g. C:
```

### label

The label assigned to the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val label: String = sample.label

println(label) // e.g. os
```

### logicalVolume

The logical volume associated with the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val logicalVolume: String = sample.logicalVolume

println(logicalVolume) // e.g. /dev/sda1
```

### mount

The mount point of the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val mount: String = sample.mount

println(mount) // e.g. F:\\
```

### description

A description of the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val description: String = sample.description

println(description) // e.g. Fixed drive
```

### type

The type of the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val type: String = sample.type

println(type) // e.g. NTFS
```

### options

The options associated with the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val options: String = sample.options

println(options) // e.g. rw,compress
```

### uuid

The unique identifier for the file store

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val uuid: String = sample.uuid

println(uuid) // e.g. 123e4567-e89b-12d3-a456-426614174000
```

### freeSpace

The amount of free space in bytes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val freeSpace: Long = sample.freeSpace

println(freeSpace) // e.g. 150000000000
```

### usableSpace

The usable space in bytes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val usableSpace: Long = sample.usableSpace

println(usableSpace) // e.g. 145000000000
```

### usableSpace

The total space in bytes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val totalSpace: Long = sample.totalSpace

println(totalSpace) // e.g. 500000000000
```

### freeInodes

The number of free inodes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val freeInodes: Long = sample.freeInodes

println(freeInodes) // e.g. 8000000
```

### totalInodes

The total number of inodes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val totalInodes: Long = sample.totalInodes

println(totalInodes) // e.g. 10000000
```

### updateAttributes

Whether the file store supports updating attributes

```kotlin
val fileStores = fileSystem.fileStores
val sample: FileStore = fileStores.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // true or false
```