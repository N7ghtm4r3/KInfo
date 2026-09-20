# Virtual memory

The information refer to the swap statistics and virtual memory estimates of the system

## Original source

The macOS virtual memory information are provided by `MacOsVirtualMemory`, which inherits `VirtualMemory`

## KInfo's source

```kotlin
val globalMemory = hardware.globalMemory
val virtualMemory: VirtualMemory = globalMemory.virtualMemory
```

See [global memory](global_memory.md) for the containing API

## Properties

### swapTotal

The total swap space in bytes

```kotlin
val swapTotal: Long = virtualMemory.swapTotal

println(swapTotal) // e.g. 2147483648
```

### swapUsed

The swap space currently in use in bytes

```kotlin
val swapUsed: Long = virtualMemory.swapUsed

println(swapUsed) // e.g. 536870912
```

### virtualMax

The estimated virtual memory capacity in bytes, calculated as total physical memory plus total swap space

```kotlin
val virtualMax: Long = virtualMemory.virtualMax

println(virtualMax) // e.g. 19327352832
```

### virtualInUse

The estimated virtual memory in use in bytes, calculated as total physical memory minus available memory plus used swap space

```kotlin
val virtualInUse: Long = virtualMemory.virtualInUse

println(virtualInUse) // e.g. 13421772800
```

### swapPagesIn

The cumulative number of pages swapped in from disk

```kotlin
val swapPagesIn: Long = virtualMemory.swapPagesIn

println(swapPagesIn) // e.g. 65536
```

### swapPagesOut

The cumulative number of pages swapped out to disk

```kotlin
val swapPagesOut: Long = virtualMemory.swapPagesOut

println(swapPagesOut) // e.g. 98304
```
