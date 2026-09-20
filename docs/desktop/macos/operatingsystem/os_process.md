# OS process

Represents the current native macOS process, including its identity, execution state, resource usage, and threads

## Original source

The process information are provided by `MacOsOSProcess`, which inherits `OSProcess`

## KInfo's source

Retrieve `operatingSystem` as shown in the [Operating system API](index.md#operatingsystem-api)

```kotlin
val currentProcess: MacOsOSProcess = operatingSystem.procTaskAllInfo
```

Each access to `procTaskAllInfo` collects a new snapshot of the current process and its threads.
The returned object stores those values; reading its properties does not refresh them

!!! Warning

    Retrieving the process throws an `IllegalStateException` when the required native task information cannot be read

## Properties

### name

The name of the current process

```kotlin
val name: String = currentProcess.name

println(name) // e.g. KInfoDemo
```

### path

The path to the process executable, or an empty string when unavailable

```kotlin
val path: String = currentProcess.path

println(path) // e.g. /Applications/KInfoDemo.app/Contents/MacOS/KInfoDemo
```

### commandLine

The first entry in `arguments`, normally the executable path. The macOS implementation does not join the remaining arguments into a full command line

```kotlin
val commandLine: String = currentProcess.commandLine

println(commandLine) // e.g. /Applications/KInfoDemo.app/Contents/MacOS/KInfoDemo
```

### arguments

The process arguments reported by Foundation, including the executable as the first entry

```kotlin
val arguments: List<String> = currentProcess.arguments

println(arguments) // e.g. [/Applications/KInfoDemo.app/Contents/MacOS/KInfoDemo, --mode, demo]
```

### environmentVariables

The environment variables reported for the current process

```kotlin
val environmentVariables: Map<String, String> = currentProcess.environmentVariables

println(environmentVariables) // e.g. {LANG=en_US.UTF-8, APP_MODE=demo}
```

### currentWorkingDirectory

The current working directory, or an empty string when unavailable

```kotlin
val currentWorkingDirectory: String = currentProcess.currentWorkingDirectory

println(currentWorkingDirectory) // e.g. /Users/demo
```

### user

The username associated with the process user ID, or an empty string when the lookup fails

```kotlin
val user: String = currentProcess.user

println(user) // e.g. demo
```

### userId

The process user ID represented as a string

```kotlin
val userId: String = currentProcess.userId

println(userId) // e.g. 501
```

### group

The group name associated with the process group ID, or an empty string when the lookup fails

```kotlin
val group: String = currentProcess.group

println(group) // e.g. staff
```

### groupId

The process group ID represented as a string

```kotlin
val groupId: String = currentProcess.groupId

println(groupId) // e.g. 20
```

### state

The process execution state mapped from its native status. The native process mapping returns `NEW`, `RUNNING`,
`SLEEPING`, `STOPPED`, `ZOMBIE`, or `OTHER`

```kotlin
val state: State = currentProcess.state

println(state) // e.g. RUNNING
```

#### State entries

The `State` enum is shared by processes and threads. The available entries are:

| **State**     | **Description**                                                            |
|---------------|----------------------------------------------------------------------------|
| **NEW**       | Intermediate state in process creation                                     |
| **RUNNING**   | Actively executing                                                         |
| **SLEEPING**  | Interruptible sleep or a native waiting thread                             |
| **WAITING**   | Uninterruptible thread wait                                                |
| **ZOMBIE**    | Intermediate state in process termination                                  |
| **STOPPED**   | Stopped process, or stopped or halted thread                               |
| **OTHER**     | A native state not recognized by the mapper                                |
| **INVALID**   | Shared enum entry not emitted by the native macOS process or thread mapper |
| **SUSPENDED** | Shared enum entry not emitted by the native macOS process or thread mapper |

### processId

The identifier of the current process

```kotlin
val processId: Int = currentProcess.processId

println(processId) // e.g. 4242
```

### parentProcessId

The identifier of the parent process

```kotlin
val parentProcessId: Int = currentProcess.parentProcessId

println(parentProcessId) // e.g. 1
```

### threadCount

The number of threads reported by the native task query. The separately collected `threadDetails` list can contain fewer entries

```kotlin
val threadCount: Int = currentProcess.threadCount

println(threadCount) // e.g. 4
```

### priority

The scheduling priority reported for the process

```kotlin
val priority: Int = currentProcess.priority

println(priority) // e.g. 31
```

### virtualSize

The virtual memory size of the process in bytes

```kotlin
val virtualSize: Long = currentProcess.virtualSize

println(virtualSize) // e.g. 8589934592
```

### residentMemory

The resident memory size of the process in bytes

```kotlin
val residentMemory: Long = currentProcess.residentMemory

println(residentMemory) // e.g. 67108864
```

### privateResidentMemory

The physical memory footprint reported by macOS in bytes. This uses `ri_phys_footprint`, rather than subtracting
shared pages from `residentMemory`. A failed native resource query returns `-1`

```kotlin
val privateResidentMemory: Long = currentProcess.privateResidentMemory

println(privateResidentMemory) // e.g. 58720256
```

### kernelTime

The cumulative time spent by the process in kernel mode in milliseconds, or `-1` when the resource usage query fails

```kotlin
val kernelTime: Long = currentProcess.kernelTime

println(kernelTime) // e.g. 200
```

### userTime

The cumulative time spent by the process in user mode in milliseconds, or `-1` when the resource usage query fails

```kotlin
val userTime: Long = currentProcess.userTime

println(userTime) // e.g. 1800
```

### startTime

The process start time in milliseconds since the Unix epoch

```kotlin
val startTime: Long = currentProcess.startTime

println(startTime) // e.g. 1728903000000
```

### bytesRead

The cumulative disk I/O bytes read by the process, or `-1` when the native resource query fails

```kotlin
val bytesRead: Long = currentProcess.bytesRead

println(bytesRead) // e.g. 10485760
```

### bytesWritten

The cumulative disk I/O bytes written by the process, or `-1` when the native resource query fails

```kotlin
val bytesWritten: Long = currentProcess.bytesWritten

println(bytesWritten) // e.g. 5242880
```

### openFiles

The number of open file descriptors reported for the process

```kotlin
val openFiles: Long = currentProcess.openFiles

println(openFiles) // e.g. 48
```

### softOpenFileLimit

The current soft limit for open file descriptors, or `-1` when the limit query fails

```kotlin
val softOpenFileLimit: Long = currentProcess.softOpenFileLimit

println(softOpenFileLimit) // e.g. 256
```

### hardOpenFileLimit

The current hard limit for open file descriptors, or `-1` when the limit query fails

```kotlin
val hardOpenFileLimit: Long = currentProcess.hardOpenFileLimit

println(hardOpenFileLimit) // e.g. 10240
```

### processCpuLoadCumulative

The ratio of accumulated process CPU time (`userTime + kernelTime`) to elapsed time since the process started.
The value is not normalized by the processor count and can exceed `1.0` when multiple threads use multiple CPUs.
Multiply by `100` to express the value as a percentage of one CPU

A zero elapsed time returns `0.0`. Failed CPU-time queries can produce a negative result

```kotlin
val processCpuLoadCumulative: Double = currentProcess.processCpuLoadCumulative

println(processCpuLoadCumulative) // e.g. 0.2
```

### processCpuLoadBetweenTicks

The ratio of CPU time gained to elapsed time since the previous registered sample. Like the cumulative load,
this value can exceed `1.0`

The first sample, an interval shorter than one second, or a decrease in CPU time uses `processCpuLoadCumulative`
as a fallback. Read `operatingSystem.procTaskAllInfo` again after at least one second to collect another sample;
reading this property on the same process object returns its stored value

```kotlin
val processCpuLoadBetweenTicks: Double = currentProcess.processCpuLoadBetweenTicks

println(processCpuLoadBetweenTicks) // e.g. 0.35
```

### bitness

The process bitness derived from its native flags: `32` or `64`

```kotlin
val bitness: Int = currentProcess.bitness

println(bitness) // e.g. 64
```

### affinityMask

A mask synthesized from the available logical processor count. It does not describe a queried scheduling restriction.
The result is `0` for a nonpositive count and `-1` for 64 or more logical processors

```kotlin
val affinityMask: Long = currentProcess.affinityMask

println(affinityMask) // e.g. 255
```

### updateAttributes

Whether this object supports updating its attributes. The native implementation returns `false`; retrieve
`operatingSystem.procTaskAllInfo` again for a new snapshot

```kotlin
val updateAttributes: Boolean = currentProcess.updateAttributes

println(updateAttributes) // false
```

### threadDetails

The list of [threads](os_thread.md) captured for the current process. Entries whose native details cannot be read
are omitted, and the list can be empty

```kotlin
val threadDetails: List<OSThread> = currentProcess.threadDetails

println(threadDetails)
```

### minorFaults

The native task fault count minus its page-in count

```kotlin
val minorFaults: Long = currentProcess.minorFaults

println(minorFaults) // e.g. 3400
```

### majorFaults

The native task page-in count

```kotlin
val majorFaults: Long = currentProcess.majorFaults

println(majorFaults) // e.g. 28
```

### contextSwitches

The combined context switch count reported by the native task query. The voluntary and involuntary counters are
collected separately, so their sum can differ from this snapshot

```kotlin
val contextSwitches: Long = currentProcess.contextSwitches

println(contextSwitches) // e.g. 17500
```

### voluntaryContextSwitches

The voluntary context switches reported for the current process across its threads, or `-1` when the resource usage query fails

```kotlin
val voluntaryContextSwitches: Long = currentProcess.voluntaryContextSwitches

println(voluntaryContextSwitches) // e.g. 15000
```

### involuntaryContextSwitches

The involuntary context switches reported for the current process across its threads, or `-1` when the resource usage query fails

```kotlin
val involuntaryContextSwitches: Long = currentProcess.involuntaryContextSwitches

println(involuntaryContextSwitches) // e.g. 2500
```
