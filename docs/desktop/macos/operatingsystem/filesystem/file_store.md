Represents the file store mounted at `/` on native macOS, providing its volume information, capacity, available space, and
mount options

The `operatingSystem` instance is retrievable using the [Operating system API](../index.md#operatingsystem-api).
The `statfs` property describes only the root file store

!!! Warning

    Reading `operatingSystem.statfs` throws an `IllegalStateException` if the native root file system query fails

!!! Note

    The `name`, `label`, `description`, and `uuid` properties use Foundation volume resource values. They return an empty
    string when the resource dictionary is unavailable, or the string `"null"` when the dictionary lacks the requested entry

## Properties

### name

The localized name of the file store

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val name: String = sample.name

println(name) // e.g. Macintosh HD
```

### volume

The native source from which the file store is mounted

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val volume: String = sample.volume

println(volume) // e.g. /dev/disk3s1s1
```

### label

The name assigned to the volume

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val label: String = sample.label

println(label) // e.g. Macintosh HD
```

### logicalVolume

The separate logical volume identifier. The native implementation returns an empty string; the mounted source is
available through [volume](#volume)

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val logicalVolume: String = sample.logicalVolume

println(logicalVolume) // empty string
```

### mount

The mount point of the file store, which is `/` for the root file store returned by `statfs`

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val mount: String = sample.mount

println(mount) // /
```

### description

The localized description of the volume format

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val description: String = sample.description

println(description) // e.g. APFS
```

### type

The native file system type name

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val type: String = sample.type

println(type) // e.g. apfs
```

### options

The comma-separated mount options recognized by the native implementation. The supported flags are `ro`, `synchronous`,
`noexec`, `nosuid`, `nodev`, `local`, `journaled`, and `nobrowse`. Disabled flags are omitted; a writable mount does not
automatically add `rw`

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val options: String = sample.options

println(options) // e.g. ro,local
```

### uuid

The persistent identifier of the volume

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val uuid: String = sample.uuid

println(uuid) // e.g. 12345678-1234-4234-8234-123456789ABC
```

### freeSpace

The free space in bytes, calculated from the native free block count and file system block size

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val freeSpace: Long = sample.freeSpace

println(freeSpace) // e.g. 150323855360
```

### usableSpace

The space available to non-privileged users in bytes, calculated from the native available block count and file system
block size

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val usableSpace: Long = sample.usableSpace

println(usableSpace) // e.g. 145028546560
```

### totalSpace

The capacity reported for the root file store in bytes, calculated from the native total block count and file system
block size

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val totalSpace: Long = sample.totalSpace

println(totalSpace) // e.g. 494384795648
```

### freeInodes

The number of free file nodes reported by the file system

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val freeInodes: Long = sample.freeInodes

println(freeInodes) // e.g. 8000000
```

### totalInodes

The total number of file nodes reported by the file system

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val totalInodes: Long = sample.totalInodes

println(totalInodes) // e.g. 10000000
```

### updateAttributes

Always `false`, because the instance does not provide an operation to reload its native statistics and volume resources.
Read `operatingSystem.statfs` again to retrieve updated information

```kotlin
val sample: MacOsFileStore = operatingSystem.statfs

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // false
```
