# Overview

The information refer to the device on the native **macOS** target

## Available information

### Hardware

The available [hardware information](hardware/index.md) are the below:

| **Category**           | **Property**                                                      | **Description**                                                                   | **Source**                             |
|------------------------|-------------------------------------------------------------------|-----------------------------------------------------------------------------------|----------------------------------------|
| **Computer System**    | [`computerSystem`](hardware/computersystem/computer_system.md)    | The details of the computer system, firmware, and baseboard                       | `MacOsHardware.computerSystem`         |
| **CPU**                | [`processorInfo`](hardware/centralprocessor/central_processor.md) | The details of the system's central processor (CPU)                               | `MacOsHardware.processorInfo`          |
| **Memory**             | [`globalMemory`](hardware/globalmemory/global_memory.md)          | The details about the system's global, virtual, and physical memory               | `MacOsHardware.globalMemory`           |
| **Power Source**       | [`powerSourceDescription`](hardware/power_source.md)              | The capacity, electrical measurements, and charging state of the internal battery | `MacOsHardware.powerSourceDescription` |
| **Disk Storage**       | [`disks`](hardware/storage/hw_disk_store.md)                      | A list of physical and synthesized disks with their partitions                    | `MacOsHardware.disks`                  |
| **Network Interfaces** | [`networkInterfaces`](hardware/network_interface.md)              | A list of network interfaces, their addresses, and traffic statistics             | `MacOsHardware.networkInterfaces`      |
| **Displays**           | [`displaysInfo`](hardware/display.md)                             | A list of available display identification information                            | `MacOsHardware.displaysInfo`           |
| **USB Devices**        | [`usbDevices`](hardware/usb_device.md)                            | A list of USB devices with their nested connected devices                         | `MacOsHardware.usbDevices`             |
| **Bluetooth Devices**  | [`bluetoothDevices`](hardware/bluetooth_device.md)                | A list of Bluetooth devices paired with the system                                | `MacOsHardware.bluetoothDevices`       |
| **Printers**           | [`printers`](hardware/printer.md)                                 | A list of printing destinations configured on the system                          | `MacOsHardware.printers`               |
| **Sound Cards**        | [`soundCards`](hardware/sound_card.md)                            | A list of audio devices available on the system                                   | `MacOsHardware.soundCards`             |
| **Graphics Cards**     | [`graphicCards`](hardware/graphicscard/graphics_card.md)          | A list of graphics cards available on the system                                  | `MacOsHardware.graphicCards`           |

### Operating system

The available [operating system information](operatingsystem/index.md) are the below:

| **Category**        | **Property**                                                             | **Description**                                                  | **Source**                                    |
|---------------------|--------------------------------------------------------------------------|------------------------------------------------------------------|-----------------------------------------------|
| **OS Info**         | [`operatingSystemVersion`](operatingsystem/os_version_info.md)           | The version, code name, and build number of macOS                | `MacOsOperatingSystem.operatingSystemVersion` |
| **File Store**      | [`statfs`](operatingsystem/filesystem/file_store.md)                     | The file store information for the root filesystem (`/`)         | `MacOsOperatingSystem.statfs`                 |
| **Desktop Windows** | [`visibleWindows`](operatingsystem/os_desktop_window.md)                 | A list of desktop windows currently reported as on-screen        | `MacOsOperatingSystem.visibleWindows`         |
| **Desktop Windows** | [`allWindows`](operatingsystem/os_desktop_window.md)                     | A list of all desktop windows returned by the native enumeration | `MacOsOperatingSystem.allWindows`             |
| **User Session**    | [`utmpx`](operatingsystem/os_session.md)                                 | The first active user session found in the native records        | `MacOsOperatingSystem.utmpx`                  |
| **Process Info**    | [`procTaskAllInfo`](operatingsystem/os_process.md)                       | The current process and its thread information                   | `MacOsOperatingSystem.procTaskAllInfo`        |
| **IP Connections**  | [`socketFdInfo`](operatingsystem/internetprotocolstats/ip_connection.md) | A list of accessible IP connections                              | `MacOsOperatingSystem.socketFdInfo`           |
| **IP Routes**       | [`rtMsgHdr2`](operatingsystem/internetprotocolstats/ip_route.md)         | A list of IP routes from the native routing table                | `MacOsOperatingSystem.rtMsgHdr2`              |
| **TCP Stats**       | [`tcpStat`](operatingsystem/internetprotocolstats/tcp_stats.md)          | The native TCP statistics                                        | `MacOsOperatingSystem.tcpStat`                |
| **UDP Stats**       | [`udpStat`](operatingsystem/internetprotocolstats/udp_stats.md)          | The native UDP statistics                                        | `MacOsOperatingSystem.udpStat`                |

## API source

The information are retrievable using the `MacOsInfo` API:

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

### MacOsInfo

Retrieve a `MacOsInfo` instance from `kInfoState` instance

```kotlin
val macOsInfo = kInfoState.macOsInfo
```

!!! Warning

    You can directly retrieve `macOsInfo` inside the `macosMain` source set. In `commonMain`, use the
    `onMacOs` callback shown in the [common usage](../../usage.md) instead. Accessing `macOsInfo` on another
    platform throws an exception
