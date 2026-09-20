Represents a logical CPU core or thread in the system providing detailed information about the logical processor's 
mapping to physical processors, NUMA nodes, and processor groups

## Properties

### processorNumber

The unique identifier of the logical processor

```kotlin
val logicalProcessors = centralProcessor.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()

val processorNumber: Int = sample.processorNumber

println(processorNumber) // e.g. 7
```

### physicalProcessorNumber

The physical core number to which this logical processor belongs

```kotlin
val logicalProcessors = centralProcessor.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()

val physicalProcessorNumber: Int = sample.physicalProcessorNumber

println(physicalProcessorNumber) // e.g. 7
```

### physicalPackageNumber

The physical CPU package to which this logical processor belongs

```kotlin
val logicalProcessors = centralProcessor.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()

val physicalPackageNumber: Int = sample.physicalPackageNumber

println(physicalPackageNumber) // e.g. 0
```

### numaNode

The NUMA **(Non-Uniform Memory Access)** node to which this logical processor is assigned

```kotlin
val logicalProcessors = centralProcessor.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()

val numaNode: Int = sample.numaNode

println(numaNode) // e.g. 0
```

### processorGroup

The processor group for systems that support grouping of processors

```kotlin
val logicalProcessors = centralProcessor.logicalProcessors
val sample: LogicalProcessor = logicalProcessors.first()

val processorGroup: Int = sample.processorGroup

println(processorGroup) // e.g. 0
```