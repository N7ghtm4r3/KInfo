# OS thread

Represents a thread captured for the current native macOS process, including its execution state and resource usage

## Original source

The native thread information are provided by `MacOsOSThread`, which inherits `OSThread`.
The public `threadDetails` property exposes the common `List<OSThread>` type

## KInfo's source

Retrieve `operatingSystem` as shown in the [Operating system API](index.md#operatingsystem-api).
The examples require a non-empty [thread list](os_process.md#threaddetails); check it before calling `first()`

```kotlin
val currentProcess: MacOsOSProcess = operatingSystem.procTaskAllInfo
val threadDetails: List<OSThread> = currentProcess.threadDetails
val sample: OSThread = threadDetails.first()
```

The thread values belong to the process snapshot. Retrieve `operatingSystem.procTaskAllInfo` again to collect updated
thread information

## Properties

### threadId

The zero-based index assigned during native thread enumeration. This identifier belongs to the snapshot and
is not a persistent native thread ID; skipped entries can leave gaps

```kotlin
val threadId: Int = sample.threadId

println(threadId) // e.g. 0
```

### name

The native thread name, which can be empty

```kotlin
val name: String = sample.name

println(name) // e.g. main
```

### state

The thread execution state. The native mapping returns `RUNNING`, `STOPPED`, `SLEEPING`, `WAITING`, or `OTHER`.
Native waiting threads map to `SLEEPING`, while uninterruptible threads map to `WAITING`

See the shared [state entries](os_process.md#state-entries)

```kotlin
val state: State = sample.state

println(state) // e.g. RUNNING
```

### threadCpuLoadCumulative

The CPU usage value reported by macOS, divided by its native usage scale. Despite the property name, this is
not calculated as CPU time divided by the thread's lifetime. Multiply by `100` to display a percentage of one CPU

```kotlin
val threadCpuLoadCumulative: Double = sample.threadCpuLoadCumulative

println(threadCpuLoadCumulative) // e.g. 0.07
```

### threadCpuLoadBetweenTicks

The ratio of CPU time gained to elapsed time since the previous registered sample for this native thread.
The first sample, an interval shorter than one second, or a decrease in CPU time uses `threadCpuLoadCumulative`
as a fallback

Collect another process snapshot after at least one second to obtain another thread sample. Reading this property
on the same thread object returns its stored value

```kotlin
val threadCpuLoadBetweenTicks: Double = sample.threadCpuLoadBetweenTicks

println(threadCpuLoadBetweenTicks) // e.g. 0.1
```

### owningProcessId

The identifier of the process that owns the thread

```kotlin
val owningProcessId: Int = sample.owningProcessId

println(owningProcessId) // e.g. 4242
```

### startMemoryAddress

The thread start memory address. The native implementation does not retrieve this value and returns `0`

```kotlin
val startMemoryAddress: Long = sample.startMemoryAddress

println(startMemoryAddress) // 0
```

### contextSwitches

The thread context switch count. The native implementation does not retrieve this value and returns `0`

```kotlin
val contextSwitches: Long = sample.contextSwitches

println(contextSwitches) // 0
```

### minorFaults

The thread minor fault count. The native implementation does not retrieve this value and returns `0`

```kotlin
val minorFaults: Long = sample.minorFaults

println(minorFaults) // 0
```

### majorFaults

The thread major fault count. The native implementation does not retrieve this value and returns `0`

```kotlin
val majorFaults: Long = sample.majorFaults

println(majorFaults) // 0
```

### kernelTime

The cumulative time spent by the thread in kernel mode, converted from native nanoseconds to whole milliseconds

```kotlin
val kernelTime: Long = sample.kernelTime

println(kernelTime) // e.g. 20
```

### userTime

The cumulative time spent by the thread in user mode, converted from native nanoseconds to whole milliseconds

```kotlin
val userTime: Long = sample.userTime

println(userTime) // e.g. 120
```

### upTime

The thread uptime in milliseconds. The native implementation does not retrieve this value and returns `0`

```kotlin
val upTime: Long = sample.upTime

println(upTime) // 0
```

### startTime

The thread start time in milliseconds since the Unix epoch. The native implementation does not retrieve this value and returns `0`

```kotlin
val startTime: Long = sample.startTime

println(startTime) // 0
```

### priority

The current scheduling priority reported for the thread

```kotlin
val priority: Int = sample.priority

println(priority) // e.g. 31
```

### updateAttributes

Whether this object supports updating its attributes. The native implementation returns `false`; retrieve a new
process snapshot and read its `threadDetails` for updated thread information

```kotlin
val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // false
```
