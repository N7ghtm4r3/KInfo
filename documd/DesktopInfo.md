# KInfo

![Static Badge](https://img.shields.io/badge/desktop-006874?link=https%3A%2F%2Fimg.shields.io%2Fbadge%2Fandroid-4280511051)

The desktop platform works under the hood with the [oshi library](https://github.com/oshi/oshi) to retrieve the information

### Retrievable Information from `DesktopInfo`

### Hardware

| **Category**           | **Property**          | **Description**                                          | **Source**                     |
|------------------------|-----------------------|----------------------------------------------------------|--------------------------------|
| **Computer System**    | `computerSystem`      | The details of the computer system                       | `Hardware.computerSystem`      |
| **CPU**                | `centralProcessor`    | The details of the system's central processor (CPU)      | `Hardware.centralProcessor`    |
| **Memory**             | `globalMemory`        | The details about the system's global memory             | `Hardware.globalMemory`        |
| **Power Sources**      | `powerSources`        | A list of power sources available to the system          | `Hardware.powerSources`        |
| **Disk Storage**       | `diskStores`          | A list of disk storage devices                           | `Hardware.diskStores`          |
| **Logical Volumes**    | `logicalVolumeGroups` | A list of logical volume groups configured on the system | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | `networkIFs`          | A list of network interfaces on the system               | `Hardware.networkIFs`          |
| **Displays**           | `displays`            | A list of display devices connected to the system        | `Hardware.displays`            |
| **Sensors**            | `sensors`             | The details of system sensors                            | `Hardware.sensors`             |
| **Sound Cards**        | `soundCards`          | A list of sound cards available on the system            | `Hardware.soundCards`          |
| **Graphics Cards**     | `graphicsCards`       | A list of graphics cards available on the system         | `Hardware.graphicsCards`       |

### Operating System

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

Copyright © 2025 Tecknobit