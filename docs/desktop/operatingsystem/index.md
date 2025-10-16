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

### services

The list of [services](os_service.md) running on the operating system

```kotlin
val services: List<OSService> = operatingSystem.services

println(services)
```

### sessions

The list of user [sessions](os_session.md) currently active on the operating system

```kotlin
val sessions: List<OSSession> = operatingSystem.sessions

println(sessions)
```

## Methods

The below methods are provided by [operatingSystem](#operatingsystem-api) instance, and are useful to interact with the 
operating system to retrieve any available information

### getProcesses

Retrieves the list of all running processes on the operating system

#### Interfaces

- [OSProcess](os_process.md) - Represents a process in the operating system

```kotlin
val processes: List<OSProcess> = operatingSystem.getProcesses()

println(processes)
```

### getProcess

Retrieves a single process by its process ID

#### Parameters

- **pid** `:Int` - The process ID of the process to retrieve

#### Interfaces

- [OSProcess](os_process.md) - Represents a process in the operating system

```kotlin
val process: OSProcess = operatingSystem.getProcess(
    pid = 12202
)

println(process)
```

### getOSDesktopWindows

Retrieves the desktop windows of the operating system

#### Parameters

- **visibleOnly** `:Boolean` - A flag indicating whether to retrieve only the visible desktop windows (`true`) or all desktop windows (`false`)

#### Interfaces
- [OSDesktopWindow](os_desktop_window.md) - Represents a desktop window on the operating system

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)

println(desktopWindows)
```

### parseNestedStatistics

Parses `/proc` files with a given structure consisting of a keyed header line followed by a keyed value line

#### Parameters

- **procFile** `:String` - The file to process
- **keys** `:vararg String` - Optional array of keys to include in the outer map. If not provided, all keys found in the file will be returned

```kotlin
val nestedStatistic: Map<String, Map<String, Long>> = operatingSystem.parseNestedStatistics(
    procFile =, // proc file,
    keys = // requested keys
)

println(nestedStatistic)
// e.g. 
// {
//     "TcpExt": {"SyncookiesSent": 0, "SyncookiesRecv": 4, "SyncookiesFailed": 0, ... },
//     "IpExt": {"InNoRoutes": 55, "InTruncatedPkts": 0, "InMcastPkts": 27786, "OutMcastPkts": 1435, ... },
//     "MPTcpExt": {"MPCapableSYNRX": 0, "MPCapableSYNTX": 0, "MPCapableSYNACKRX": 0, ... }
// }
```

### parseStatistics

Parses `/proc` files formatted as "statistic (long)value" to produce a simple mapping

#### Parameters

- **procFile** `:String` - The file to process
- **separator** `:Regex` - A regular expression specifying the separator between the statistic name and its value

```kotlin
val statistics: Map<String, Long> = operatingSystem.parseStatistics(
    procFile =, // proc file,
    separator = // custom separator
)

println(statistics)
// e.g. 
// {
//     "Ip6InReceives": 8026,
//     "Ip6InHdrErrors": 0,
//     "Icmp6InMsgs": 2,
//     "Icmp6InErrors": 0,
//     ...
// }
```

### queryInstalledApps

Method used to retrieve the current installed applications on the system

#### Interfaces

- [ApplicationInfo](application_info.md) - Represents common information about an installed application

```kotlin
val installedApps: List<ApplicationInfo> = operatingSystem.queryInstalledApps()

println(installedApps)
```

### findInstalledApp

Method used to find an installed application by name

#### Parameters

- **name** `:String` - The name of the application to find

#### Interfaces

- [ApplicationInfo](application_info.md) - Represents common information about an installed application

```kotlin
val installedApp: ApplicationInfo? = operatingSystem.findInstalledApp(
    name = "MyApp"
)

println(installedApp)
```

### findInstalledApps

Method used to find an installed applications list by a filter condition

#### Parameters

- **applicationFilter** `:(ApplicationInfo) -> Boolean` - The filter used to determine whether the application must be included in the retrieved list

#### Interfaces

- [ApplicationInfo](application_info.md) - Represents common information about an installed application

```kotlin
val installedApps: List<ApplicationInfo> = operatingSystem.findInstalledApps(
    applicationFilter = { app ->
        app.version == "1.0.0"
    }
)

println(installedApps)
```