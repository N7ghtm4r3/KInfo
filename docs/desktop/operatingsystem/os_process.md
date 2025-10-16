# OS process

Represents a process in the operating system, providing details about the process, including its name, ID, state, resources usage, and more

### Original source

The operating system process information are retrieved from `OperatingSystem.OSProcess` interface

### KInfo's source

```kotlin
val currentProcess = operatingSystem.currentProcess
```

### Properties

#### name

The name of the process

```kotlin
val name: String = currentProcess.name

println(name) // e.g. java
```

#### path

The path to the executable of the process

```kotlin
val path: String = currentProcess.path

println(path) // e.g. /usr/bin/java
```

#### commandLine

The full command line that was used to start the process

```kotlin
val commandLine: String = currentProcess.commandLine

println(commandLine) // e.g. /usr/bin/java -jar myapp.jar --port 8080
```

#### arguments

The arguments passed to the process when it was started

```kotlin
val arguments: List<String> = currentProcess.arguments

println(arguments) // e.g. ["-jar", "myapp.jar", "--port", "8080"]
```

#### environmentVariables

A map of environment variables used by the process

```kotlin
val environmentVariables: List<String> = currentProcess.environmentVariables

println(environmentVariables) 
// e.g. {"PATH"="/usr/bin:/bin", "JAVA_HOME"="/usr/lib/jvm/java-17-openjdk"}
```

#### currentWorkingDirectory

The current working directory of the process

```kotlin
val currentWorkingDirectory: String = currentProcess.currentWorkingDirectory

println(currentWorkingDirectory) // e.g. /home/user/app
```

#### user

The user running the process

```kotlin
val user: String = currentProcess.user

println(user) // e.g. user
```

#### userId

The ID of the user running the process

```kotlin
val userId: String = currentProcess.userId

println(userId) // e.g. 1000
```

#### group

The group associated with the process

```kotlin
val group: String = currentProcess.group

println(group) // e.g. users
```

#### groupId

The ID of the group associated with the process

```kotlin
val groupId: String = currentProcess.groupId

println(groupId) // e.g. 1000
```

#### state

The state of the process (e.g., running, sleeping)

###### State entries

| **State**     | **Description**                                                                            |
|---------------|--------------------------------------------------------------------------------------------|
| **NEW**       | Intermediate state in process creation                                                     |
| **RUNNING**   | Actively executing process                                                                 |
| **SLEEPING**  | Interruptible sleep state                                                                  |
| **WAITING**   | Blocked, uninterruptible sleep state                                                       |
| **ZOMBIE**    | Intermediate state in process termination                                                  |
| **STOPPED**   | Stopped by the user, such as for debugging                                                 |
| **OTHER**     | Other or unknown states not defined                                                        |
| **INVALID**   | The state resulting if the process fails to update statistics, probably due to termination |
| **SUSPENDED** | Special case of waiting if the process has been intentionally suspended (**Windows only**) |

```kotlin
val state: State = currentProcess.state

println(state) // e.g. RUNNING
```

#### processId

The unique identifier for the process

```kotlin
val processId: Int = currentProcess.processId

println(processId) // e.g. 2345
```

#### parentProcessId

The process ID of the parent process

```kotlin
val parentProcessId: Int = currentProcess.parentProcessId

println(parentProcessId) // e.g. 1
```

#### threadCount

The number of threads in the process

```kotlin
val threadCount: Int = currentProcess.threadCount

println(threadCount) // e.g. 24
```

#### priority

The priority of the process

```kotlin
val priority: Int = currentProcess.priority

println(priority) // e.g. 10
```

#### virtualSize

The virtual memory size of the process, in bytes

```kotlin
val virtualSize: Long = currentProcess.virtualSize

println(virtualSize) // e.g. 512000000
```

#### residentSetSize

The resident set size of the process, in bytes (physical memory used)

```kotlin
val residentSetSize: Long = currentProcess.residentSetSize

println(residentSetSize) // e.g. 128000000
```

#### kernelTime

The amount of time the process has spent in kernel mode, in milliseconds

```kotlin
val kernelTime: Long = currentProcess.kernelTime

println(kernelTime) // e.g. 4500
```

#### userTime

The amount of time the process has spent in user mode, in milliseconds

```kotlin
val userTime: Long = currentProcess.userTime

println(userTime) // e.g. 18200
```

#### startTime

The time when the process started, in milliseconds since the Unix epoch

```kotlin
val startTime: Long = currentProcess.startTime

println(startTime) // e.g. 1728903000000
```

#### bytesRead

The number of bytes read by the process

```kotlin
val bytesRead: Long = currentProcess.bytesRead

println(bytesRead) // e.g. 10485760
```

#### bytesWritten

The number of bytes written by the process

```kotlin
val bytesWritten: Long = currentProcess.bytesWritten

println(bytesWritten) // e.g. 5242880
```

#### openFiles

The number of open files used by the process

```kotlin
val openFiles: Long = currentProcess.openFiles

println(openFiles) // e.g. 48
```

#### softOpenFileLimit

The soft limit on the number of files the process can open

```kotlin
val softOpenFileLimit: Long = currentProcess.softOpenFileLimit

println(softOpenFileLimit) // e.g. 1024
```

#### hardOpenFileLimit

The hard limit on the number of files the process can open

```kotlin
val hardOpenFileLimit: Long = currentProcess.hardOpenFileLimit

println(hardOpenFileLimit) // e.g. 4096
```

#### processCpuLoadCumulative

The cumulative CPU load of the process as a percentage

```kotlin
val processCpuLoadCumulative: Long = currentProcess.processCpuLoadCumulative

println(processCpuLoadCumulative) // e.g. 12.7
```

#### processCpuLoadBetweenTicks

The CPU load of the process between two ticks, as a percentage

```kotlin
val processCpuLoadBetweenTicks: Long = currentProcess.processCpuLoadBetweenTicks

println(processCpuLoadBetweenTicks) // e.g. 8.4
```

#### bitness

The bitness of the process (e.g., 32-bit, 64-bit)

```kotlin
val bitness: Int = currentProcess.bitness

println(bitness) // e.g. 64
```

#### affinityMask

The CPU affinity mask for the process

```kotlin
val affinityMask: Long = currentProcess.affinityMask

println(affinityMask) // e.g. 255
```

#### updateAttributes

A flag indicating whether the process attributes should be updated

```kotlin
val updateAttributes: Boolean = currentProcess.updateAttributes

println(updateAttributes) // e.g. 255
```

#### threadDetails

The list of [thread](os_thread.md) associated with the process

```kotlin
val threadDetails: List<OSThread> = currentProcess.threadDetails

println(threadDetails)
```

#### minorFaults

The number of minor page faults for the process

```kotlin
val minorFaults: Long = currentProcess.minorFaults

println(minorFaults) // e.g. 3400
```

#### majorFaults

The number of major page faults for the process

```kotlin
val majorFaults: Long = currentProcess.majorFaults

println(majorFaults) // e.g. 28
```

#### contextSwitches

The number of context switches for the process

```kotlin
val contextSwitches: Long = currentProcess.contextSwitches

println(contextSwitches) // e.g. 17500
```