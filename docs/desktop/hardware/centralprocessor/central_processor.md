The information refer to the system's central processor (CPU)

### Original source

The central processor information are retrieved from `Hardware.CentralProcessor` interface

### KInfo's source

```kotlin
val centralProcessor = hardware.centralProcessor
```

### Properties

#### maxFreq

The maximum frequency of the processor in hertz

```kotlin
val maxFreq: Long = centralProcessor.maxFreq

println(maxFreq) // e.g. 3793000000
```

#### currentFreq

Array of current frequencies for each logical processor in hertz

```kotlin
val currentFreq: LongArray = centralProcessor.currentFreq

println(currentFreq) // e.g. [3793000000, 3793000000, 3793000000, etc ...]
```

#### logicalProcessors

List of [logical processor](logical_processor.md), each representing a core or a thread

```kotlin
val logicalProcessors: List<LogicalProcessor> = centralProcessor.logicalProcessors

println(logicalProcessors)
```

#### physicalProcessors

List of [physical processor](physical_processor.md), representing the physical cores in the system

```kotlin
val physicalProcessors: List<PhysicalProcessor> = centralProcessor.physicalProcessors

println(physicalProcessors)
```

#### processorCaches

List of [processor caches](processor_cache.md), detailing cache levels, sizes, and types

```kotlin
val processorCaches: List<ProcessorCache> = centralProcessor.processorCaches

println(processorCaches)
```

#### featureFlags

The CPU's supported features, such as instruction sets and extensions

```kotlin
val featureFlags: List<String> = centralProcessor.featureFlags

println(featureFlags) 
// e.g. [PF_COMPARE_EXCHANGE_DOUBLE, PF_MMX_INSTRUCTIONS_AVAILABLE, etc...]
```

#### systemCpuLoadTicks

Array of tick counters representing system-wide CPU load

```kotlin
val systemCpuLoadTicks: LongArray = centralProcessor.systemCpuLoadTicks

println(systemCpuLoadTicks) // e.g. [5529211, 3861243, 143527712, 0]
```

#### processorCpuLoadTicks

Array of tick counters for each logical processor, representing the CPU load distribution

```kotlin
val processorCpuLoadTicks: Array<LongArray> = centralProcessor.processorCpuLoadTicks

println(processorCpuLoadTicks) // e.g. [[167359, 101640], [9273421, 0]]
```

#### logicalProcessorCount

The number of logical processors in the system

```kotlin
val logicalProcessorCount: Int = centralProcessor.logicalProcessorCount

println(logicalProcessorCount) // e.g. 8
```

#### physicalProcessorCount

The number of physical processors (cores) in the system

```kotlin
val physicalProcessorCount: Int = centralProcessor.physicalProcessorCount

println(physicalProcessorCount) // e.g. 4
```

#### physicalPackageCount

The number of physical processor packages in the system

```kotlin
val physicalPackageCount: Int = centralProcessor.physicalPackageCount

println(physicalPackageCount) // e.g. 1
```

#### contextSwitches

The total number of context switches that have occurred

```kotlin
val contextSwitches: Long = centralProcessor.contextSwitches

println(contextSwitches) // e.g. 299982508
```

#### interrupts

The total number of interrupts that have occurred

```kotlin
val interrupts: Long = centralProcessor.interrupts

println(interrupts) // e.g. 206562399
```