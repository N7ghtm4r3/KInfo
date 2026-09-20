# Overview

The desktop platform works under the hood with the [oshi library](https://github.com/oshi/oshi) in the [jvm](jvm/hardware/index.md) target 
and with native interfaces in native targets to retrieve the information

## Available information

On **desktop** target are available the below information:

### Hardware

| **Category**           | **Property**                                                             | **Description**                                          | **Source**                     |
|------------------------|--------------------------------------------------------------------------|----------------------------------------------------------|--------------------------------|
| **Computer System**    | [`computerSystem`](jvm/hardware/computersystem/computer_system.md)       | The details of the computer system                       | `Hardware.computerSystem`      |
| **CPU**                | [`centralProcessor`](jvm/hardware/centralprocessor/central_processor.md) | The details of the system's central processor (CPU)      | `Hardware.centralProcessor`    |
| **Memory**             | [`globalMemory`](jvm/hardware/globalmemory/global_memory.md)             | The details about the system's global memory             | `Hardware.globalMemory`        |
| **Power Sources**      | [`powerSources`](jvm/hardware/power_source.md)                           | A list of power sources available to the system          | `Hardware.powerSources`        |
| **Disk Storage**       | [`diskStores`](jvm/hardware/storage/hw_disk_store.md)                    | A list of disk storage devices                           | `Hardware.diskStores`          |
| **Logical Volumes**    | [`logicalVolumeGroups`](jvm/hardware/logical_volume_group.md)            | A list of logical volume groups configured on the system | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | [`networkIFs`](jvm/hardware/network_interface.md)                        | A list of network interfaces on the system               | `Hardware.networkIFs`          |
| **Displays**           | [`displays`](jvm/hardware/display.md)                                    | A list of display devices connected to the system        | `Hardware.displays`            |
| **Sensors**            | [`sensors`](jvm/hardware/sensors/sensors.md)                             | The details of system sensors                            | `Hardware.sensors`             |
| **Sound Cards**        | [`soundCards`](jvm/hardware/sound_card.md)                               | A list of sound cards available on the system            | `Hardware.soundCards`          |
| **Graphics Cards**     | [`graphicsCards`](jvm/hardware/graphicscard/graphics_card.md)            | A list of graphics cards available on the system         | `Hardware.graphicsCards`       |
| **Printers**           | [`printers`](jvm/hardware/printer.md)                                    | A list of printers available on the system               | `Hardware.printers`            |

### Operating System

| **Category**         | **Property**                                                                                    | **Description**                                                                            | **Source**                              |
|----------------------|-------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------|
| **App Info**         | [`name`](jvm/operatingsystem/application_info.md#name)                                          | The name of the application                                                                | `OperatingSystem.queryInstalledApps`    |
|                      | [`version`](jvm/operatingsystem/application_info.md#version)                                    | The version of the application                                                             |                                         |
|                      | [`vendor`](jvm/operatingsystem/application_info.md#vendor)                                      | The vendor or publisher of the application                                                 |                                         |
|                      | [`timestamp`](jvm/operatingsystem/application_info.md#timestamp)                                | The installation or last modified timestamp of the application in milliseconds since epoch |                                         |
|                      | [`additionalInfo`](jvm/operatingsystem/application_info.md#additionalinfo)                      | A map containing additional application details                                            |                                         |
| **OS Info**          | [`family`](jvm/operatingsystem/index.md#family)                                                 | The family or type of the operating system                                                 | `OperatingSystem.family`                |
|                      | [`manufacturer`](jvm/operatingsystem/index.md#manufacturer)                                     | The manufacturer of the operating system                                                   | `OperatingSystem.manufacturer`          |
|                      | [`versionInfo`](jvm/operatingsystem/os_version_info.md)                                         | The version information of the operating system                                            | `OperatingSystem.versionInfo`           |
| **System Info**      | [`bitness`](jvm/operatingsystem/index.md#bitness)                                               | The bitness of the operating system                                                        | `OperatingSystem.bitness`               |
|                      | [`systemUptime`](jvm/operatingsystem/index.md#systemuptime)                                     | The system uptime in milliseconds since the operating system started                       | `OperatingSystem.systemUptime`          |
|                      | [`systemBootTime`](jvm/operatingsystem/index.md#systemboottime)                                 | The time in milliseconds when the system was last booted (Unix timestamp)                  | `OperatingSystem.systemBootTime`        |
|                      | [`isElevated`](jvm/operatingsystem/index.md#iselevated)                                         | A flag indicating whether the operating system is running with elevated privileges         | `OperatingSystem.isElevated`            |
| **File System Info** | [`fileSystem`](jvm/operatingsystem/filesystem/file_system.md)                                   | The file system information of the operating system                                        | `OperatingSystem.fileSystem`            |
| **Process Info**     | [`processId`](jvm/operatingsystem/index.md#processid)                                           | The process ID of the currently running operating system process                           | `OperatingSystem.processId`             |
|                      | [`currentProcess`](jvm/operatingsystem/os_process.md)                                           | The currently running process of the operating system                                      | `OperatingSystem.currentProcess`        |
|                      | [`processCount`](jvm/operatingsystem/index.md#processcount)                                     | The total number of processes running on the operating system                              | `OperatingSystem.processCount`          |
| **Thread Info**      | [`threadId`](jvm/operatingsystem/index.md#threadid)                                             | The thread ID of the currently running thread                                              | `OperatingSystem.threadId`              |
|                      | [`currentThread`](jvm/operatingsystem/os_thread.md)                                             | The currently running thread of the operating system                                       | `OperatingSystem.currentThread`         |
|                      | [`threadCount`](jvm/operatingsystem/index.md#threadcount)                                       | The total number of threads running on the operating system                                | `OperatingSystem.threadCount`           |
| **Network Info**     | [`internetProtocolStats`](jvm/operatingsystem/internetprotocolstats/internet_protocol_stats.md) | The internet protocol statistics related to network connections                            | `OperatingSystem.internetProtocolStats` |
| **Network Params**   | [`networkParams`](jvm/operatingsystem/network_params.md)                                        | The network parameters of the operating system                                             | `OperatingSystem.networkParams`         |
| **Service Info**     | [`services`](jvm/operatingsystem/os_service.md)                                                 | The list of services running on the operating system                                       | `OperatingSystem.services`              |
| **Session Info**     | [`sessions`](jvm/operatingsystem/os_session.md)                                                 | The list of user sessions currently active on the operating system                         | `OperatingSystem.sessions`              |

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

## Native targets

You can use `KInfo` directly in native targets with **1:1** mapping with native interfaces:

- [macOs](macos/index.md)