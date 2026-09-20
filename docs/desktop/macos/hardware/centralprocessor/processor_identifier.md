# Processor identifier

The information describe the processor identity and its reported capabilities

## Original source

The macOS processor identification is provided by `MacOsProcessorIdentifier`, which inherits `ProcessorIdentifier`

## KInfo's source

```kotlin
val processorInfo = hardware.processorInfo
val processorIdentifier: ProcessorIdentifier = processorInfo.processorIdentifier
```

See [central processor](central_processor.md) for the containing API

## Properties

### cpuVendor

The CPU vendor reported by macOS

```kotlin
val cpuVendor: String = processorIdentifier.cpuVendor

println(cpuVendor) // e.g. Apple Inc.
```

### cpuName

The commercial name of the processor

```kotlin
val cpuName: String = processorIdentifier.cpuName

println(cpuName) // e.g. Apple M1
```

### cpuFamily

The numeric CPU family value represented as a string

```kotlin
val cpuFamily: String = processorIdentifier.cpuFamily

println(cpuFamily) // e.g. 458787763
```

### cpuModel

The commercial chip name on Apple Silicon, or the numeric CPU model represented as a string on Intel

```kotlin
val cpuModel: String = processorIdentifier.cpuModel

println(cpuModel) // e.g. Apple M1
```

### cpuStepping

The processor stepping value. Apple Silicon uses `unknown`

```kotlin
val cpuStepping: String = processorIdentifier.cpuStepping

println(cpuStepping) // unknown
```

### processorId

The identifier assembled from architecture-specific CPU values. It is not a unique hardware serial number

```kotlin
val processorId: String = processorIdentifier.processorId

println(processorId) // e.g. 0100000c1b588bb3
```

### cpuIdentifier

The descriptive identifier assembled from the processor name and family, with model and stepping information when available

```kotlin
val cpuIdentifier: String = processorIdentifier.cpuIdentifier

println(cpuIdentifier) // e.g. Apple M1 Family 458787763
```

### isCpu64bit

Whether the native query reports 64-bit CPU capability. A failed query returns `false`

```kotlin
val isCpu64bit: Boolean = processorIdentifier.isCpu64bit

println(isCpu64bit) // e.g. true
```

### cpuVendorFreq

The reported nominal processor frequency in hertz, or `0` when unavailable. On Apple Silicon it is derived from the performance-core frequency table

```kotlin
val cpuVendorFreq: Long = processorIdentifier.cpuVendorFreq

println(cpuVendorFreq) // e.g. 3204000000
```
