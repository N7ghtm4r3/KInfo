# Physical memory

Represents a physical memory entry, providing its capacity, clock speed, and available registry metadata

## Original source

The macOS physical memory information are provided by `MacOsPhysicalMemory`, which inherits `PhysicalMemory`

## KInfo's source

```kotlin
val physicalMemory: List<PhysicalMemory> = hardware.globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()
```

The examples require a non-empty [physical memory](global_memory.md#physicalmemory) list; check it before calling `first()`.
On Apple Silicon, a single entry describes the unified memory when the required registry entry is available.
Unavailable textual metadata can be `unknown` or empty

## Properties

### bankLabel

The memory bank label. The Apple Silicon unified memory entry uses `unknown`

```kotlin
val bankLabel: String = sample.bankLabel

println(bankLabel) // unknown
```

### capacity

The capacity of the memory entry in bytes

```kotlin
val capacity: Long = sample.capacity

println(capacity) // e.g. 17179869184
```

### clockSpeed

The memory clock speed in MHz, or `0` when unavailable

```kotlin
val clockSpeed: Long = sample.clockSpeed

println(clockSpeed) // e.g. 0 when unavailable
```

### manufacturer

The memory manufacturer reported by the native registry

```kotlin
val manufacturer: String = sample.manufacturer

println(manufacturer) // e.g. unknown
```

### memoryType

The memory type reported by the native registry

```kotlin
val memoryType: String = sample.memoryType

println(memoryType) // e.g. LPDDR4X
```

### partNumber

The memory part number reported by the native registry

```kotlin
val partNumber: String = sample.partNumber

println(partNumber) // e.g. unknown
```

### serialNumber

The memory serial number reported by the native registry

```kotlin
val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. unknown
```
