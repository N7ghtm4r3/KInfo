# OS thread

Represents a thread of an operating system process, providing details about its 
execution, resource usage, and associated metadata

### Original source

The thread information are retrieved from `OperatingSystem.OSThread` interface

### KInfo's source

```kotlin
val currentThread = operatingSystem.currentThread
```

### Properties

#### threadId

The unique identifier for the thread

```kotlin
val threadId: Int = currentThread.threadId

println(threadId) // e.g. 1024
```

#### name

The name of the thread (e.g., "main", "worker")

```kotlin
val name: Int = currentThread.name

println(name) // e.g. main
```

#### state

The current [state](os_process.md#state-entries) of the thread (e.g., running, sleeping)

```kotlin
val state: State = currentThread.state

println(state) // e.g. RUNNING
```

#### threadCpuLoadCumulative

The cumulative CPU load of the thread as a percentage

```kotlin
val threadCpuLoadCumulative: Double = currentThread.threadCpuLoadCumulative

println(threadCpuLoadCumulative) // e.g. 3.8
```

#### threadCpuLoadBetweenTicks

The CPU load of the thread between two ticks, as a percentage

```kotlin
val threadCpuLoadBetweenTicks: Double = currentThread.threadCpuLoadBetweenTicks

println(threadCpuLoadBetweenTicks) // e.g. 1.2
```

#### owningProcessId

The process ID of the process that owns the thread

```kotlin
val owningProcessId: Int = currentThread.owningProcessId

println(owningProcessId) // e.g. 2345
```

#### startMemoryAddress

The starting memory address of the thread

```kotlin
val startMemoryAddress: Long = currentThread.startMemoryAddress

println(startMemoryAddress) // e.g. 140737488355328
```

#### contextSwitches

The number of context switches performed for the thread

```kotlin
val contextSwitches: Long = currentThread.contextSwitches

println(contextSwitches) // e.g. 820
```

#### minorFaults

The number of minor page faults experienced by the thread

```kotlin
val minorFaults: Long = currentThread.minorFaults

println(minorFaults) // e.g. 240
```

#### majorFaults

The number of major page faults experienced by the thread

```kotlin
val majorFaults: Long = currentThread.majorFaults

println(majorFaults) // e.g. 4
```

#### kernelTime

The total amount of time the thread has spent in kernel mode, in milliseconds

```kotlin
val kernelTime: Long = currentThread.kernelTime

println(kernelTime) // e.g. 120
```

#### userTime

The total amount of time the thread has spent in user mode, in milliseconds

```kotlin
val userTime: Long = currentThread.userTime

println(userTime) // e.g. 980
```

#### upTime

The total uptime of the thread, in milliseconds

```kotlin
val upTime: Long = currentThread.upTime

println(upTime) // e.g. 86400000
```

#### startTime

The start time of the thread, in milliseconds since the Unix epoch

```kotlin
val startTime: Long = currentThread.startTime

println(startTime) // e.g. 1728903000000
```

#### priority

The priority level of the thread

```kotlin
val priority: Int = currentThread.priority

println(priority) // e.g. 8
```

#### updateAttributes

A flag indicating whether the thread attributes should be updated

```kotlin
val updateAttributes: Boolean = currentThread.updateAttributes

println(updateAttributes) // true or false
```