# Global memory

The information refer the system's global memory

### Original source

The global memory information are retrieved from `Hardware.GlobalMemory` interface

### KInfo's source

```kotlin
val globalMemory = hardware.globalMemory
```

### Properties

#### total

The total amount of memory in the system (in bytes)

```kotlin
val total: Long = globalMemory.total

println(total) // e.g. 54281037824
```

#### available

The amount of available memory in the system (in bytes)

```kotlin
val available: Long = globalMemory.available

println(available) // e.g. 13558046720
```

#### pageSize

The system's memory page size (in bytes)

```kotlin
val pageSize: Long = globalMemory.pageSize

println(pageSize) // e.g. 4096
```

#### physicalMemory

List of [physical memory](physical_memory.md), provides details about each physical memory module installed

```kotlin
val physicalMemory: List<PhysicalMemory> = globalMemory.physicalMemory

println(physicalMemory)
```