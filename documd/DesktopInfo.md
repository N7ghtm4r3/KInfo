# KInfo

![Static Badge](https://img.shields.io/badge/desktop-006874?link=https%3A%2F%2Fimg.shields.io%2Fbadge%2Fandroid-4280511051)

The desktop platform works under the hood with the [oshi library](https://github.com/oshi/oshi) to retrieve the information

### Retrievable Information from `DesktopInfo`

### Hardware

| **Category**           | **Property**          | **Description**                                                                                         | **Source**                     |
|------------------------|-----------------------|---------------------------------------------------------------------------------------------------------|--------------------------------|
| **Computer System**    | `computerSystem`      | The details of the computer system, including manufacturer, model, and firmware                         | `Hardware.computerSystem`      |
| **CPU**                | `centralProcessor`    | The details of the system's central processor (CPU), including information like cores, speed, and usage | `Hardware.centralProcessor`    |
| **Memory**             | `globalMemory`        | The details about the system's global memory, including total size, available memory, and page size     | `Hardware.globalMemory`        |
| **Power Sources**      | `powerSources`        | A list of power sources available to the system (e.g., battery, AC power)                               | `Hardware.powerSources`        |
| **Disk Storage**       | `diskStores`          | A list of disk storage devices, including information on disk usage, size, and read/write operations    | `Hardware.diskStores`          |
| **Logical Volumes**    | `logicalVolumeGroups` | A list of logical volume groups configured on the system                                                | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | `networkIFs`          | A list of network interfaces on the system, including details such as IP addresses, speed, and state    | `Hardware.networkIFs`          |
| **Displays**           | `displays`            | A list of display devices connected to the system (e.g., monitors)                                      | `Hardware.displays`            |
| **Sensors**            | `sensors`             | The details of system sensors (e.g., temperature, fan speed, and voltage)                               | `Hardware.sensors`             |
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