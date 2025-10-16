# File System

The information refer to the file system of the device

### Original source

The file system information are retrieved from `OperatingSystem.FileSystem` interface

### KInfo's source

```kotlin
val fileSystem = operatingSystem.fileSystem
```

### Properties

#### fileStores

List of [file stores](file_store.md) available in the system

```kotlin
val fileStores: List<OSFileStore> = fileSystem.fileStores

println(fileStores)
```