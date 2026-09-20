# Central processor

The information refer to the system's central processor (CPU)

## Original source

The central processor information are retrieved from the `MacOsCentralProcessor` interface, which inherits `CentralProcessor`

## KInfo's source

```kotlin
val processorInfo: MacOsCentralProcessor = hardware.processorInfo
```

Retrieve `hardware` as shown in the [Hardware API](../index.md#hardware-api).
The tick properties retain the samples collected when `processorInfo` was retrieved. Read `hardware.processorInfo` again
for new stored samples; CPU load methods read fresh native counters

## Properties

### processorIdentifier

The [processor identifier](processor_identifier.md), including its vendor, model, and capabilities

```kotlin
val processorIdentifier: ProcessorIdentifier = processorInfo.processorIdentifier

println(processorIdentifier)
```

### maxFreq

The maximum nominal processor frequency in hertz, or `0` when unavailable

```kotlin
val maxFreq: Long = processorInfo.maxFreq

println(maxFreq) // e.g. 3204000000
```

### currentFreq

The nominal frequencies in hertz, in [logical processor](logical_processor.md) order. Values of `0` indicate unavailable frequencies; these values do not track frequency changes under load

```kotlin
val currentFreq: LongArray = processorInfo.currentFreq

println(currentFreq.contentToString()) // e.g. [2064000000, 2064000000, 2064000000, 2064000000, 3204000000, 3204000000, 3204000000, 3204000000]
```

### logicalProcessors

List of [logical processors](logical_processor.md), representing the mapped CPU cores or threads

```kotlin
val logicalProcessors: List<LogicalProcessor> = processorInfo.logicalProcessors

println(logicalProcessors)
```

### physicalProcessors

List of [physical processors](physical_processor.md), representing the mapped physical cores

```kotlin
val physicalProcessors: List<PhysicalProcessor> = processorInfo.physicalProcessors

println(physicalProcessors)
```

### processorCaches

List of [processor caches](processor_cache.md), describing their levels, capacities, and types

```kotlin
val processorCaches: List<ProcessorCache> = processorInfo.processorCaches

println(processorCaches)
```

### featureFlags

The supported CPU feature names reported by the native mapping. On Apple Silicon, the mapper queries a predefined subset of feature names

```kotlin
val featureFlags: List<String> = processorInfo.featureFlags

println(featureFlags) // e.g. [FEAT_AES, FEAT_PMULL, FEAT_SHA1, FEAT_SHA256, FEAT_SHA512, FEAT_SHA3, FEAT_CRC32, FEAT_LSE, FEAT_DotProd]
```

### systemCpuLoadTicks

The initial system CPU tick counters in `USER`, `NICE`, `SYSTEM`, `IDLE`, `IOWAIT`, `IRQ`, `SOFTIRQ`, `STEAL` order.
The final four counters are `0` on macOS

```kotlin
val systemCpuLoadTicks: LongArray = processorInfo.systemCpuLoadTicks

println(systemCpuLoadTicks.contentToString()) // e.g. [80000, 0, 40000, 680000, 0, 0, 0, 0]
```

### processorCpuLoadTicks

The initial CPU tick counters for each logical processor in native order. Each row follows the same state order as `systemCpuLoadTicks`

```kotlin
val processorCpuLoadTicks: Array<LongArray> = processorInfo.processorCpuLoadTicks

println(processorCpuLoadTicks.contentDeepToString()) // e.g. [[10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0], [10000, 0, 5000, 85000, 0, 0, 0, 0]]
```

### logicalProcessorCount

The number of entries in `logicalProcessors`

```kotlin
val logicalProcessorCount: Int = processorInfo.logicalProcessorCount

println(logicalProcessorCount) // e.g. 8
```

### physicalProcessorCount

The number of entries in `physicalProcessors`

```kotlin
val physicalProcessorCount: Int = processorInfo.physicalProcessorCount

println(physicalProcessorCount) // e.g. 8
```

### physicalPackageCount

The number of physical CPU packages, or `0` when unavailable

```kotlin
val physicalPackageCount: Int = processorInfo.physicalPackageCount

println(physicalPackageCount) // e.g. 1
```

### contextSwitches

The system-wide context switch count. The macOS implementation does not retrieve this value and returns `0`

```kotlin
val contextSwitches: Long = processorInfo.contextSwitches

println(contextSwitches) // 0
```

### interrupts

The system-wide interrupt count. The macOS implementation does not retrieve this value and returns `0`

```kotlin
val interrupts: Long = processorInfo.interrupts

println(interrupts) // 0
```

## Methods

The below methods retrieve CPU load and system load averages

### getSystemCpuLoadBetweenTicks

Retrieves the system CPU load between an earlier tick snapshot and a fresh native sample. The result ranges from `0.0` to
`1.0`; multiply it by `100` to display a percentage. Identical samples return `0.0`

#### Parameters

- **oldTickets** `:LongArray` - The earlier eight-state snapshot from `systemCpuLoadTicks`, collected during the same boot

```kotlin
val oldTickets: LongArray = processorInfo.systemCpuLoadTicks

// Call after the interval you want to measure
val systemCpuLoadBetweenTicks: Double = processorInfo.getSystemCpuLoadBetweenTicks(
    oldTickets = oldTickets
)

println(systemCpuLoadBetweenTicks) // e.g. 0.3
```

An array with a length other than eight throws an `IllegalArgumentException`

### getSystemLoadAverage

Retrieves the system load averages in one, five, and fifteen minute order. A value of `-1.0` indicates an unavailable
interval. Load averages describe system demand and can exceed `1.0`

#### Parameters

- **nelem** `:Int` - The number of leading intervals to retrieve, from `1` to `3`

```kotlin
val systemLoadAverage: DoubleArray = processorInfo.getSystemLoadAverage(
    nelem = 3
)

println(systemLoadAverage.contentToString()) // e.g. [1.25, 0.95, 0.75]
```

A value outside `1..3` throws an `IllegalArgumentException`

### getSystemCpuLoad

Samples the system CPU load over the specified delay. The result ranges from `0.0` to `1.0`

#### Parameters

- **delay** `:Long` - The nonnegative sampling delay in milliseconds

```kotlin
val systemCpuLoad: Double = processorInfo.getSystemCpuLoad(
    delay = 1000
)

println(systemCpuLoad) // e.g. 0.42
```

!!! Warning

    A positive delay blocks the calling thread while sampling. Use this method outside the UI thread

A negative delay throws an `IllegalArgumentException`. A delay of `0` reads both samples without sleeping;
identical samples return `0.0`

### getProcessorCpuLoadBetweenTicks

Retrieves the CPU load for each logical processor between an earlier tick snapshot and a fresh native sample.
Each result ranges from `0.0` to `1.0` and follows native logical processor order

#### Parameters

- **oldTickets** `:Array<LongArray>` - The earlier snapshot from `processorCpuLoadTicks`, containing one eight-state row
  per logical processor from the same boot

```kotlin
val oldTickets: Array<LongArray> = processorInfo.processorCpuLoadTicks

// Call after the interval you want to measure
val processorCpuLoadBetweenTicks: DoubleArray = processorInfo.getProcessorCpuLoadBetweenTicks(
    oldTickets = oldTickets
)

println(processorCpuLoadBetweenTicks.contentToString()) // e.g. [0.25, 0.2, 0.15, 0.1, 0.5, 0.4, 0.35, 0.45]
```

An invalid snapshot shape or a changed logical processor count throws an `IllegalArgumentException`
