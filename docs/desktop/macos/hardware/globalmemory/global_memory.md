# Global memory

The information refer to the system's global memory

## Original source

The global memory information are retrieved from the `MacOsGlobalMemory` interface, which inherits `GlobalMemory`

## KInfo's source

```kotlin
val globalMemory: MacOsGlobalMemory = hardware.globalMemory
```

Retrieve `hardware` as shown in the [Hardware API](../index.md#hardware-api). The values are collected by separate native queries

!!! Warning

    Reading `globalMemory` throws an `IllegalStateException` when the required swap usage or swap page statistics cannot be read

## Properties

### total

The total physical memory in bytes, or `0` when the native query fails

```kotlin
val total: Long = globalMemory.total

println(total) // e.g. 17179869184
```

### available

The estimated available memory in bytes, calculated from free and inactive pages. A failed native query returns `0`

```kotlin
val available: Long = globalMemory.available

println(available) // e.g. 4294967296
```

### pageSize

The system's memory page size in bytes, or `0` when the native query fails

```kotlin
val pageSize: Long = globalMemory.pageSize

println(pageSize) // e.g. 16384
```

### virtualMemory

The [virtual memory](virtual_memory.md) and swap statistics

```kotlin
val virtualMemory: VirtualMemory = globalMemory.virtualMemory

println(virtualMemory)
```

### physicalMemory

List of [physical memory](physical_memory.md) entries. On Apple Silicon, the list describes unified memory rather than separate removable modules

```kotlin
val physicalMemory: List<PhysicalMemory> = globalMemory.physicalMemory

println(physicalMemory)
```
