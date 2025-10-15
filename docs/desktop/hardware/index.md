# Overview

The information refer to the available hardware of the device

## Available information

On **desktop** target the available **hardware** information are the below:

| **Category**           | **Property**          | **Description**                                                                                      | **Source**                     |
|------------------------|-----------------------|------------------------------------------------------------------------------------------------------|--------------------------------|
| **Computer System**    | `computerSystem`      | The details of the computer system                                                                   | `Hardware.computerSystem`      |
| **CPU**                | `centralProcessor`    | The details of the system's central processor (CPU)                                                  | `Hardware.centralProcessor`    |
| **Memory**             | `globalMemory`        | The details about the system's global memory, including total size, available memory, and page size  | `Hardware.globalMemory`        |
| **Power Sources**      | `powerSources`        | A list of power sources available to the system (e.g., battery, AC power)                            | `Hardware.powerSources`        |
| **Disk Storage**       | `diskStores`          | A list of disk storage devices, including information on disk usage, size, and read/write operations | `Hardware.diskStores`          |
| **Logical Volumes**    | `logicalVolumeGroups` | A list of logical volume groups configured on the system                                             | `Hardware.logicalVolumeGroups` |
| **Network Interfaces** | `networkIFs`          | A list of network interfaces on the system, including details such as IP addresses, speed, and state | `Hardware.networkIFs`          |
| **Displays**           | `displays`            | A list of display devices connected to the system (e.g., monitors)                                   | `Hardware.displays`            |
| **Sensors**            | `sensors`             | The details of system sensors (e.g., temperature, fan speed, and voltage)                            | `Hardware.sensors`             |
| **Sound Cards**        | `soundCards`          | A list of sound cards available on the system                                                        | `Hardware.soundCards`          |
| **Graphics Cards**     | `graphicsCards`       | A list of graphics cards available on the system                                                     | `Hardware.graphicsCards`       |

## API source

The information are retrievable using the `DesktopInfo.Hardware` API:

### Hardware

Retrieve a `Hardware` instance from `desktopInfo` instance

```kotlin
val hardware = desktopInfo.hardware
```