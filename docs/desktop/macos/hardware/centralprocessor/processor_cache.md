# Processor cache

Represents a processor cache, including its level, associativity, line size, capacity, and type

## Original source

The macOS cache information are provided by `MacOsProcessorCache`, which inherits `ProcessorCache`

## KInfo's source

```kotlin
val processorCaches: List<ProcessorCache> = hardware.processorInfo.processorCaches
val sample: ProcessorCache = processorCaches.first()
```

The examples require a non-empty [processor cache](central_processor.md#processorcaches) list; check it before calling `first()`.
The native cache queries do not distinguish performance levels

## Properties

### level

The cache level, such as `1` for L1 or `2` for L2

```kotlin
val level: Byte = sample.level

println(level) // e.g. 1
```

### associativity

The cache associativity, or `0` when unavailable. The macOS L3 mapping uses `0`

```kotlin
val associativity: Byte = sample.associativity

println(associativity) // e.g. 0
```

### lineSize

The global cache line size in bytes, or `0` when unavailable

```kotlin
val lineSize: Short = sample.lineSize

println(lineSize) // e.g. 128
```

### cacheSize

The cache capacity in bytes. Only entries with a positive mapped capacity are included

```kotlin
val cacheSize: Int = sample.cacheSize

println(cacheSize) // e.g. 131072
```

### type

The cache type. macOS maps L1 instruction and data caches to `INSTRUCTION` and `DATA`, and L2/L3 caches to `UNIFIED`

```kotlin
val type: CacheType = sample.type

println(type) // e.g. Instruction
```
