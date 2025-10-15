# Overview

The desktop platform works under the hood with the [oshi library](https://github.com/oshi/oshi) to retrieve the information

## Available information

On **desktop** target are available the below information:

### Hardware

| **Category**           | **Property**          | **Description**                                                                                         | **Source**                     |
|------------------------|-----------------------|---------------------------------------------------------------------------------------------------------|--------------------------------|
| **Computer System**    | `computerSystem`      | The details of the computer system                                                                      | `Hardware.computerSystem`      |
| **CPU**                | `centralProcessor`    | The details of the system's central processor (CPU), including information like cores, speed, and usage | `Hardware.centralProcessor`    |
| **Memory**             | `globalMemory`        | The details about the system's global memory, including total size, available memory, and page size     | `Hardware.globalMemory`        |
| **Power Sources**      | `powerSources`        | A list of power sources available to the system                                                         | `Hardware.powerSources`        |
| **Disk Storage**       | `diskStores`          | A list of disk storage devices                                                                          | `Hardware.diskStores`          |
| **Logical Volumes**    | `logicalVolumeGroups` | A list of logical volume groups configured on the system                                                | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | `networkIFs`          | A list of network interfaces on the system                                                              | `Hardware.networkIFs`          |
| **Displays**           | `displays`            | A list of display devices connected to the system                                                       | `Hardware.displays`            |
| **Sensors**            | `sensors`             | The details of system sensors                                                                           | `Hardware.sensors`             |
| **Sound Cards**        | `soundCards`          | A list of sound cards available on the system                                                           | `Hardware.soundCards`          |
| **Graphics Cards**     | `graphicsCards`       | A list of graphics cards available on the system                                                        | `Hardware.graphicsCards`       |

### Operating System

| **Category**         | **Property**            | **Description**                                                                                                      | **Source**                              |
|----------------------|-------------------------|----------------------------------------------------------------------------------------------------------------------|-----------------------------------------|
| **OS Info**          | `family`                | The family or type of the operating system (e.g., "Windows", "Linux")                                                | `OperatingSystem.family`                |
|                      | `manufacturer`          | The manufacturer of the operating system (e.g., "Microsoft", "Apple")                                                | `OperatingSystem.manufacturer`          |
|                      | `versionInfo`           | The version information of the operating system, including version number, build, etc.                               | `OperatingSystem.versionInfo`           |
| **File System Info** | `fileSystem`            | The file system information of the operating system, including file stores and descriptors                           | `OperatingSystem.fileSystem`            |
| **Network Info**     | `internetProtocolStats` | The internet protocol statistics related to network connections, including TCP and UDP statistics and IP connections | `OperatingSystem.internetProtocolStats` |
| **Process Info**     | `processId`             | The process ID of the currently running operating system process                                                     | `OperatingSystem.processId`             |
|                      | `currentProcess`        | The currently running process of the operating system                                                                | `OperatingSystem.currentProcess`        |
|                      | `processCount`          | The total number of processes running on the operating system                                                        | `OperatingSystem.processCount`          |
| **Thread Info**      | `threadId`              | The thread ID of the currently running thread                                                                        | `OperatingSystem.threadId`              |
|                      | `currentThread`         | The currently running thread of the operating system                                                                 | `OperatingSystem.currentThread`         |
|                      | `threadCount`           | The total number of threads running on the operating system                                                          | `OperatingSystem.threadCount`           |
| **System Info**      | `bitness`               | The bitness of the operating system (e.g., 32-bit, 64-bit)                                                           | `OperatingSystem.bitness`               |
|                      | `systemUptime`          | The system uptime in milliseconds since the operating system started                                                 | `OperatingSystem.systemUptime`          |
|                      | `systemBootTime`        | The time in milliseconds when the system was last booted (Unix timestamp)                                            | `OperatingSystem.systemBootTime`        |
|                      | `isElevated`            | A flag indicating whether the operating system is running with elevated privileges (e.g., as an administrator)       | `OperatingSystem.isElevated`            |
| **Network Params**   | `networkParams`         | The network parameters of the operating system, including host name, domain name, DNS servers, and default gateways  | `OperatingSystem.networkParams`         |
| **Service Info**     | `services`              | The list of services running on the operating system                                                                 | `OperatingSystem.services`              |
| **Session Info**     | `sessions`              | The list of user sessions currently active on the operating system                                                   | `OperatingSystem.sessions`              |
| **Application Info** | `name`                  | The name of the application                                                                                          | `OperatingSystem.queryInstalledApps`    |
|                      | `version`               | The version of the application                                                                                       |                                         |
|                      | `vendor`                | The vendor or publisher of the application                                                                           |                                         |
|                      | `timestamp`             | The installation or last modified timestamp of the application in milliseconds since epoch                           |                                         |
|                      | `additionalInfo`        | A map containing additional application details                                                                      |                                         |

## API source

The information are retrievable using the `DesktopInfo` API:

### Composable context

Retrieve a `KInfoState` instance inside **composable** context

```kotlin
val kInfoState = rememberKInfoState()
```

### Non-composable context

Retrieve a `KInfoState` instance inside **non-composable** context

```kotlin
val kInfoState = KInfoState()
```

### DesktopInfo

Retrieve a `DesktopInfo` instance from `kInfoState` instance

```kotlin

val desktopInfo = kInfoState.desktopInfo 
```

!!! Warning

    You can directly retrieve `desktopInfo` just inside the `desktopMain` module, in the `commonMain` module you have
    to use the [common usage](../usage.md) instead, or the application will crash


## O.E.M Mechanism

When a property is **To Be Filled By O.E.M** you can use the below mechanism to use other value instead that is not 
filled:

```kotlin
val computerSystem = desktopInfo.hardware.computerSystem

val model = computerSystem.model.whenIsToBeFilledByOEM(
    useInstead = {
        your custom logic
    }
)
```

You can just check also if a property is marked as **To Be Filled By O.E.M** with the below method:


```kotlin
val computerSystem = desktopInfo.hardware.computerSystem

val isModelToBeFilledByOEM = computerSystem.model.isToBeFilledByOEM()

if(isModelToBeFilledByOEM) {
    your custom logic
} else {
    your custom logic
}
```