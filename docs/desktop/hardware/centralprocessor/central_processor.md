# Central processor

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

## Methods

The below methods and are useful to retrieve any available information about the central processor

### getSystemCpuLoadBetweenTicks

Retrieves the system CPU load as a percentage between the current ticks and the specified `oldTickets`

#### Parameters

- **oldTickets** `:LongArray` - The previous CPU ticks for comparison

```kotlin
val systemCpuLoadBetweenTicks: Double = centralProcessor.getSystemCpuLoadBetweenTicks(
    oldTickets = longArrayOf(130000, 2000, 15000, 450000, 100, 0, 0, 0)
)

println(systemCpuLoadBetweenTicks) // e.g. 23.74 
```

### getSystemLoadAverage

Retrieves the system load average for the last `nelem` intervals

#### Parameters

- **nelem** `:Int` - The number of intervals for which to retrieve the load average

```kotlin
val systemLoadAverage: DoubleArray = centralProcessor.getSystemLoadAverage(
    nelem = 10
)

println(systemLoadAverage) // e.g. [0.75, 0.60, 0.55] 
```

### getSystemCpuLoad

Calculates the system CPU load over a specified delay period

#### Parameters

- **delay** `:Long` - The delay period in milliseconds

```kotlin
val systemCpuLoad: Double = centralProcessor.getSystemCpuLoad(
    delay = 1000
)

println(systemCpuLoad) // e.g. 0.42  
```

### getProcessorCpuLoadBetweenTicks

Retrieves the processor CPU load as a percentage between the current ticks and the specified `oldTickets`

#### Parameters

- **oldTickets** `:Array<LongArray>` - The previous CPU ticks for comparison

```kotlin
val processorCpuLoadBetweenTicks: DoubleArray = centralProcessor.getProcessorCpuLoadBetweenTicks(
    oldTickets = arrayOf(
        longArrayOf(130000, 2000, 15000, 450000, 100, 0, 0, 0), // Core 0
        longArrayOf(125000, 1800, 14000, 460000, 80, 0, 0, 0),  // Core 1
        longArrayOf(120000, 1600, 13000, 470000, 60, 0, 0, 0),  // Core 2
        longArrayOf(128000, 1900, 14500, 455000, 90, 0, 0, 0)   // Core 3
    )
)

println(processorCpuLoadBetweenTicks) // e.g. [0.18, 0.23, 0.14, 0.27]
```