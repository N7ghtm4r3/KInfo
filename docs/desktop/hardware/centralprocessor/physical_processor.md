Represents a physical processor in the system, providing information about the physical CPU core, its package, 
and efficiency, along with its unique identifier string

## Properties

### physicalPackageNumber

The unique identifier of the logical processor

```kotlin
val physicalProcessors = centralProcessor.physicalProcessors
val sample: PhysicalProcessor = physicalProcessors.first()

val physicalPackageNumber: Int = sample.physicalPackageNumber

println(physicalPackageNumber) // e.g. 0
```

### physicalProcessorNumber

The unique identifier for the physical processor in the package

```kotlin
val physicalProcessors = centralProcessor.physicalProcessors
val sample: PhysicalProcessor = physicalProcessors.first()

val physicalProcessorNumber: Int = sample.physicalProcessorNumber

println(physicalProcessorNumber) // e.g. 7
```

### efficiency

The efficiency level of the physical processor, this value indicates how efficiently the processor operates

```kotlin
val physicalProcessors = centralProcessor.physicalProcessors
val sample: PhysicalProcessor = physicalProcessors.first()

val efficiency: Int = sample.efficiency

println(efficiency) // e.g. 0
```

### idString

A unique string identifier for this physical processor, often used to distinguish between different processors in the system

```kotlin
val physicalProcessors = centralProcessor.physicalProcessors
val sample: PhysicalProcessor = physicalProcessors.first()

val idString: String = sample.idString

println(idString) // e.g. BFEBFBFF000306A9
```