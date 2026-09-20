# KInfo

![Static Badge](https://img.shields.io/badge/desktop-006874?link=https%3A%2F%2Fimg.shields.io%2Fbadge%2Fandroid-4280511051)

The desktop platform works under the hood with the [oshi library](https://github.com/oshi/oshi) to retrieve the information

### Retrievable Information from `DesktopInfo`

### Hardware

| **Category**           | **Property**          | **Description**                                          | **Source**                     |
|------------------------|-----------------------|----------------------------------------------------------|--------------------------------|
| **Computer System**    | [`computerSystem`](../docs/desktop/jvm/hardware/computersystem/computer_system.md)      | The details of the computer system                       | `Hardware.computerSystem`      |
| **CPU**                | [`centralProcessor`](../docs/desktop/jvm/hardware/centralprocessor/central_processor.md)    | The details of the system's central processor (CPU)      | `Hardware.centralProcessor`    |
| **Memory**             | [`globalMemory`](../docs/desktop/jvm/hardware/globalmemory/global_memory.md)        | The details about the system's global memory             | `Hardware.globalMemory`        |
| **Power Sources**      | [`powerSources`](../docs/desktop/jvm/hardware/power_source.md)        | A list of power sources available to the system          | `Hardware.powerSources`        |
| **Disk Storage**       | [`diskStores`](../docs/desktop/jvm/hardware/storage/hw_disk_store.md)          | A list of disk storage devices                           | `Hardware.diskStores`          |
| **Logical Volumes**    | [`logicalVolumeGroups`](../docs/desktop/jvm/hardware/logical_volume_group.md) | A list of logical volume groups configured on the system | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | [`networkIFs`](../docs/desktop/jvm/hardware/network_interface.md)          | A list of network interfaces on the system               | `Hardware.networkIFs`          |
| **Displays**           | [`displays`](../docs/desktop/jvm/hardware/display.md)            | A list of display devices connected to the system        | `Hardware.displays`            |
| **Sensors**            | [`sensors`](../docs/desktop/jvm/hardware/sensors/sensors.md)             | The details of system sensors                            | `Hardware.sensors`             |
| **Sound Cards**        | [`soundCards`](../docs/desktop/jvm/hardware/sound_card.md)          | A list of sound cards available on the system            | `Hardware.soundCards`          |
| **Graphics Cards**     | [`graphicsCards`](../docs/desktop/jvm/hardware/graphicscard/graphics_card.md)       | A list of graphics cards available on the system         | `Hardware.graphicsCards`       |
| **Printers**           | [`printers`](../docs/desktop/jvm/hardware/printer.md)            | A list of printers available on the system               | `Hardware.printers`            |

### Operating System

| **Category**         | **Property**            | **Description**                                                                            | **Source**                              |
|----------------------|-------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------|
| **App Info**         | [`name`](../docs/desktop/jvm/operatingsystem/application_info.md#name)                  | The name of the application                                                                | `OperatingSystem.queryInstalledApps`    |
|                      | [`version`](../docs/desktop/jvm/operatingsystem/application_info.md#version)               | The version of the application                                                             |                                         |
|                      | [`vendor`](../docs/desktop/jvm/operatingsystem/application_info.md#vendor)                | The vendor or publisher of the application                                                 |                                         |
|                      | [`timestamp`](../docs/desktop/jvm/operatingsystem/application_info.md#timestamp)             | The installation or last modified timestamp of the application in milliseconds since epoch |                                         |
|                      | [`additionalInfo`](../docs/desktop/jvm/operatingsystem/application_info.md#additionalinfo)        | A map containing additional application details                                            |                                         |
| **OS Info**          | [`family`](../docs/desktop/jvm/operatingsystem/index.md#family)                | The family or type of the operating system                                                 | `OperatingSystem.family`                |
|                      | [`manufacturer`](../docs/desktop/jvm/operatingsystem/index.md#manufacturer)          | The manufacturer of the operating system                                                   | `OperatingSystem.manufacturer`          |
|                      | [`versionInfo`](../docs/desktop/jvm/operatingsystem/os_version_info.md)           | The version information of the operating system                                            | `OperatingSystem.versionInfo`           |
| **System Info**      | [`bitness`](../docs/desktop/jvm/operatingsystem/index.md#bitness)               | The bitness of the operating system                                                        | `OperatingSystem.bitness`               |
|                      | [`systemUptime`](../docs/desktop/jvm/operatingsystem/index.md#systemuptime)          | The system uptime in milliseconds since the operating system started                       | `OperatingSystem.systemUptime`          |
|                      | [`systemBootTime`](../docs/desktop/jvm/operatingsystem/index.md#systemboottime)        | The time in milliseconds when the system was last booted (Unix timestamp)                  | `OperatingSystem.systemBootTime`        |
|                      | [`isElevated`](../docs/desktop/jvm/operatingsystem/index.md#iselevated)            | A flag indicating whether the operating system is running with elevated privileges         | `OperatingSystem.isElevated`            |
| **File System Info** | [`fileSystem`](../docs/desktop/jvm/operatingsystem/filesystem/file_system.md)            | The file system information of the operating system                                        | `OperatingSystem.fileSystem`            |
| **Process Info**     | [`processId`](../docs/desktop/jvm/operatingsystem/index.md#processid)             | The process ID of the currently running operating system process                           | `OperatingSystem.processId`             |
|                      | [`currentProcess`](../docs/desktop/jvm/operatingsystem/os_process.md)        | The currently running process of the operating system                                      | `OperatingSystem.currentProcess`        |
|                      | [`processCount`](../docs/desktop/jvm/operatingsystem/index.md#processcount)          | The total number of processes running on the operating system                              | `OperatingSystem.processCount`          |
| **Thread Info**      | [`threadId`](../docs/desktop/jvm/operatingsystem/index.md#threadid)              | The thread ID of the currently running thread                                              | `OperatingSystem.threadId`              |
|                      | [`currentThread`](../docs/desktop/jvm/operatingsystem/os_thread.md)         | The currently running thread of the operating system                                       | `OperatingSystem.currentThread`         |
|                      | [`threadCount`](../docs/desktop/jvm/operatingsystem/index.md#threadcount)           | The total number of threads running on the operating system                                | `OperatingSystem.threadCount`           |
| **Network Info**     | [`internetProtocolStats`](../docs/desktop/jvm/operatingsystem/internetprotocolstats/internet_protocol_stats.md) | The internet protocol statistics related to network connections                            | `OperatingSystem.internetProtocolStats` |
| **Network Params**   | [`networkParams`](../docs/desktop/jvm/operatingsystem/network_params.md)         | The network parameters of the operating system                                             | `OperatingSystem.networkParams`         |
| **Service Info**     | [`services`](../docs/desktop/jvm/operatingsystem/os_service.md)              | The list of services running on the operating system                                       | `OperatingSystem.services`              |
| **Session Info**     | [`sessions`](../docs/desktop/jvm/operatingsystem/os_session.md)              | The list of user sessions currently active on the operating system                         | `OperatingSystem.sessions`              |

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the
following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/KInfo/issues/new">GitHub</a>

Thank you for your help!

## Donations

If you want support project and developer

| Crypto                                                                                              | Address                                          | Network  |
|-----------------------------------------------------------------------------------------------------|--------------------------------------------------|----------|
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white)   | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp**           | Bitcoin  |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white) | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4**   | Ethereum |
| ![](https://img.shields.io/badge/Solana-000?style=for-the-badge&logo=Solana&logoColor=9945FF)       | **AtPjUnxYFHw3a6Si9HinQtyPTqsdbfdKX3dJ1xiDjbrL** | Solana   |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

Copyright © 2026 Tecknobit