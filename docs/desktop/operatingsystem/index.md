# Operating system

The information refer to the operating system of the device

## Available information

On **desktop** target the available **operating system** information are the below:

| **Category**         | **Property**            | **Description**                                                                            | **Source**                              |
|----------------------|-------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------|
| **App Info**         | `name`                  | The name of the application                                                                | `OperatingSystem.queryInstalledApps`    |
|                      | `version`               | The version of the application                                                             |                                         |
|                      | `vendor`                | The vendor or publisher of the application                                                 |                                         |
|                      | `timestamp`             | The installation or last modified timestamp of the application in milliseconds since epoch |                                         |
|                      | `additionalInfo`        | A map containing additional application details                                            |                                         |
| **OS Info**          | `family`                | The family or type of the operating system                                                 | `OperatingSystem.family`                |
|                      | `manufacturer`          | The manufacturer of the operating system                                                   | `OperatingSystem.manufacturer`          |
|                      | `versionInfo`           | The version information of the operating system                                            | `OperatingSystem.versionInfo`           |
| **System Info**      | `bitness`               | The bitness of the operating system                                                        | `OperatingSystem.bitness`               |
|                      | `systemUptime`          | The system uptime in milliseconds since the operating system started                       | `OperatingSystem.systemUptime`          |
|                      | `systemBootTime`        | The time in milliseconds when the system was last booted (Unix timestamp)                  | `OperatingSystem.systemBootTime`        |
|                      | `isElevated`            | A flag indicating whether the operating system is running with elevated privileges         | `OperatingSystem.isElevated`            |
| **File System Info** | `fileSystem`            | The file system information of the operating system                                        | `OperatingSystem.fileSystem`            |
| **Process Info**     | `processId`             | The process ID of the currently running operating system process                           | `OperatingSystem.processId`             |
|                      | `currentProcess`        | The currently running process of the operating system                                      | `OperatingSystem.currentProcess`        |
|                      | `processCount`          | The total number of processes running on the operating system                              | `OperatingSystem.processCount`          |
| **Thread Info**      | `threadId`              | The thread ID of the currently running thread                                              | `OperatingSystem.threadId`              |
|                      | `currentThread`         | The currently running thread of the operating system                                       | `OperatingSystem.currentThread`         |
|                      | `threadCount`           | The total number of threads running on the operating system                                | `OperatingSystem.threadCount`           |
| **Network Info**     | `internetProtocolStats` | The internet protocol statistics related to network connections                            | `OperatingSystem.internetProtocolStats` |
| **Network Params**   | `networkParams`         | The network parameters of the operating system                                             | `OperatingSystem.networkParams`         |
| **Service Info**     | `services`              | The list of services running on the operating system                                       | `OperatingSystem.services`              |
| **Session Info**     | `sessions`              | The list of user sessions currently active on the operating system                         | `OperatingSystem.sessions`              |

## API source

The information are retrievable using the `DesktopInfo.OperatingSystem` API:

### OperatingSystem API

Retrieve a `OperatingSystem` instance from `desktopInfo` instance

```kotlin
val operatingSystem = desktopInfo.operatingSystem
```

## Properties

The below properties are miscellaneous readable properties provided by the [operatingSystem](#operatingsystem-api) instance 

### family

The family or type of the operating system

```kotlin
val family: String = operatingSystem.family

println(family) // e.g. Windows
```

### manufacturer

The manufacturer of the operating system

```kotlin
val manufacturer: String = operatingSystem.manufacturer

println(manufacturer) // e.g. Microsoft
```

### processId

The process ID of the currently running operating system process

```kotlin
val processId: Int = operatingSystem.processId

println(processId) // e.g. 22000
```

### processCount

The total number of processes running on the operating system

```kotlin
val processCount: Int = operatingSystem.processCount

println(processCount) // e.g. 243
```

### threadId

The thread ID of the currently running thread

```kotlin
val threadId: Int = operatingSystem.threadId

println(threadId) // e.g. 13124
```

### threadCount

The total number of threads running on the operating system

```kotlin
val threadCount: Int = operatingSystem.threadCount

println(threadCount) // e.g. 4509
```

### bitness

The bitness of the operating system

```kotlin
val bitness: Int = operatingSystem.bitness

println(bitness) // e.g. 32
```

### systemUptime

The system uptime in seconds since the operating system started

```kotlin
val systemUptime: Long = operatingSystem.systemUptime

println(systemUptime) // e.g. 1078
```

### systemBootTime

The timestamp when the system was last booted (Unix timestamp)

```kotlin
val systemBootTime: Long = operatingSystem.systemBootTime

println(systemBootTime) // e.g. 1760598078
```

### isElevated

A flag indicating whether the operating system is running with elevated privileges (e.g., as an administrator)

```kotlin
val isElevated: Boolean = operatingSystem.isElevated

println(isElevated) // true or false
```