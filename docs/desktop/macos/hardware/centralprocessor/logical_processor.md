# Logical processor

Represents a logical CPU core or thread and its mapping to a physical core and package

## Original source

The macOS logical processor information are provided by `MacOsLogicalProcessor`, which inherits `LogicalProcessor`

## KInfo's source

```kotlin
val logicalProcessors: List<LogicalProcessor> = hardware.processorInfo.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()
```

The examples require a non-empty [logical processor](central_processor.md#logicalprocessors) list; check it before calling `first()`.
Core and package numbers are reconstructed assuming uniformly distributed, contiguous logical processors

## Properties

### processorNumber

The zero-based logical processor number

```kotlin
val processorNumber: Int = sample.processorNumber

println(processorNumber) // e.g. 0
```

### physicalProcessorNumber

The physical core number derived from the native processor counts

```kotlin
val physicalProcessorNumber: Int = sample.physicalProcessorNumber

println(physicalProcessorNumber) // e.g. 0
```

### physicalPackageNumber

The physical CPU package number derived from the native processor counts

```kotlin
val physicalPackageNumber: Int = sample.physicalPackageNumber

println(physicalPackageNumber) // e.g. 0
```

### numaNode

The NUMA node number. The macOS mapping uses `0`

```kotlin
val numaNode: Int = sample.numaNode

println(numaNode) // 0
```

### processorGroup

The processor group number. The macOS mapping uses `0`

```kotlin
val processorGroup: Int = sample.processorGroup

println(processorGroup) // 0
```
