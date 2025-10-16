Represents the virtual memory statistics of the system, providing detailed information about the swap memory, virtual memory limits, and usage statistics.

### Original source

The baseboard information are retrieved from `Hardware.GlobalMemory.VirtualMemory` interface

### KInfo's source

```kotlin
val virtualMemory = globalMemory.virtualMemory
```

### Properties

#### swapTotal

The total amount of swap space available in the system (in bytes)

```kotlin
val swapTotal: Long = virtualMemory.swapTotal

println(swapTotal) // e.g. 1087007744
```

#### swapUsed

The amount of swap space currently being used (in bytes)

```kotlin
val swapUsed: Long = virtualMemory.swapUsed

println(swapUsed) // e.g. 67686400
```

#### virtualMax

The maximum amount of virtual memory that the system can use (in bytes)

```kotlin
val virtualMax: Long = virtualMemory.virtualMax

println(virtualMax) // e.g. 27368045568
```

#### virtualInUse

The amount of virtual memory currently in use (in bytes)

```kotlin
val virtualInUse: Long = virtualMemory.virtualInUse

println(virtualInUse) // e.g. 13934660608
```

#### swapPagesIn

The number of pages that have been swapped in from disk into memory

```kotlin
val swapPagesIn: Long = virtualMemory.swapPagesIn

println(swapPagesIn) // e.g. 5001722
```

#### swapPagesOut

The number of pages that have been swapped out from memory to disk

```kotlin
val swapPagesOut: Long = virtualMemory.swapPagesOut

println(swapPagesOut) // e.g. 44085
```