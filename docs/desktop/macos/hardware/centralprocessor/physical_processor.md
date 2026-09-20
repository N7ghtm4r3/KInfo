# Physical processor

Represents a physical CPU core, its package, and its performance class

## Original source

The macOS physical processor information are provided by `MacOsPhysicalProcessor`, which inherits `PhysicalProcessor`

## KInfo's source

```kotlin
val physicalProcessors: List<PhysicalProcessor> = hardware.processorInfo.physicalProcessors
val sample: PhysicalProcessor = physicalProcessors.first()
```

The examples require a non-empty [physical processor](central_processor.md#physicalprocessors) list; check it before calling `first()`.
Core and package numbering assumes evenly distributed cores in package order

## Properties

### physicalPackageNumber

The CPU package number derived from the mapped entry order and native processor counts

```kotlin
val physicalPackageNumber: Int = sample.physicalPackageNumber

println(physicalPackageNumber) // e.g. 0
```

### physicalProcessorNumber

The physical core number within its package, derived from the mapped entry order

```kotlin
val physicalProcessorNumber: Int = sample.physicalProcessorNumber

println(physicalProcessorNumber) // e.g. 0
```

### efficiency

The mapped performance class: `1` for performance cores and `0` for efficiency cores. Missing or unrecognized values also use `0`

```kotlin
val efficiency: Int = sample.efficiency

println(efficiency) // e.g. 0
```

### idString

The native compatibility description of the core. Multiple cores can share this value; it is not a unique hardware identifier

```kotlin
val idString: String = sample.idString

println(idString) // e.g. apple,icestorm
```
