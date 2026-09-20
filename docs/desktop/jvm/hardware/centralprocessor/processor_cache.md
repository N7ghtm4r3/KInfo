Represents a processor's cache with its properties like level, associativity, line size, cache size, and cache type

## Properties

### level

The unique identifier of the logical processor

```kotlin
val processorCaches = centralProcessor.processorCaches
val sample: ProcessorCache = processorCaches.first()

val level: Byte = sample.level

println(level) // e.g. 2
```

### associativity

The associativity of the cache

```kotlin
val processorCaches = centralProcessor.processorCaches
val sample: ProcessorCache = processorCaches.first()

val associativity: Byte = sample.associativity

println(associativity) // e.g. 8
```

### lineSize

The size of a cache line in bytes

```kotlin
val processorCaches = centralProcessor.processorCaches
val sample: ProcessorCache = processorCaches.first()

val lineSize: Byte = sample.lineSize

println(lineSize) // e.g. 64
```

### cacheSize

The total size of the cache in bytes

```kotlin
val processorCaches = centralProcessor.processorCaches
val sample: ProcessorCache = processorCaches.first()

val cacheSize: Byte = sample.cacheSize

println(cacheSize) // e.g. 32768
```