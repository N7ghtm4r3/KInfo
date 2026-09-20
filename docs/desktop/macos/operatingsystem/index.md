# Operating system

The information refer to the operating system of the device

## Available information

On **macOS** target the available **operating system** information are the below:

| **Category**        | **Property**                                             | **Description**                                                  | **Source**                                    |
|---------------------|----------------------------------------------------------|------------------------------------------------------------------|-----------------------------------------------|
| **OS Info**         | [`operatingSystemVersion`](os_version_info.md)           | The version, code name, and build number of macOS                | `MacOsOperatingSystem.operatingSystemVersion` |
| **File Store**      | [`statfs`](filesystem/file_store.md)                     | The file store information for the root filesystem (`/`)         | `MacOsOperatingSystem.statfs`                 |
| **Desktop Windows** | [`visibleWindows`](os_desktop_window.md)                 | A list of desktop windows currently reported as on-screen        | `MacOsOperatingSystem.visibleWindows`         |
| **Desktop Windows** | [`allWindows`](os_desktop_window.md)                     | A list of all desktop windows returned by the native enumeration | `MacOsOperatingSystem.allWindows`             |
| **User Session**    | [`utmpx`](os_session.md)                                 | The first active user session found in the native records        | `MacOsOperatingSystem.utmpx`                  |
| **Process Info**    | [`procTaskAllInfo`](os_process.md)                       | The current process and its thread information                   | `MacOsOperatingSystem.procTaskAllInfo`        |
| **IP Connections**  | [`socketFdInfo`](internetprotocolstats/ip_connection.md) | A list of accessible IP connections                              | `MacOsOperatingSystem.socketFdInfo`           |
| **IP Routes**       | [`rtMsgHdr2`](internetprotocolstats/ip_route.md)         | A list of IP routes from the native routing table                | `MacOsOperatingSystem.rtMsgHdr2`              |
| **TCP Stats**       | [`tcpStat`](internetprotocolstats/tcp_stats.md)          | The native TCP statistics                                        | `MacOsOperatingSystem.tcpStat`                |
| **UDP Stats**       | [`udpStat`](internetprotocolstats/udp_stats.md)          | The native UDP statistics                                        | `MacOsOperatingSystem.udpStat`                |

## API source

The information are retrievable using the `MacOsInfo.operatingSystem` API:

Retrieve a [MacOsInfo](../index.md#macosinfo) instance before accessing the operating system information

### OperatingSystem API

Retrieve a `MacOsOperatingSystem` instance from `macOsInfo` instance

```kotlin
val operatingSystem: MacOsOperatingSystem = macOsInfo.operatingSystem
```

## Properties

The below properties are readable properties provided by the [operatingSystem](#operatingsystem-api) instance

Read an operating system property again to request updated information. Separate property reads query their data independently

### operatingSystemVersion

The [version information](os_version_info.md) of the current macOS operating system

```kotlin
val operatingSystemVersion: MacOsVersionInfo = operatingSystem.operatingSystemVersion

println(operatingSystemVersion)
```

### statfs

The [file store](filesystem/file_store.md) information for the root filesystem (`/`)

```kotlin
val statfs: MacOsFileStore = operatingSystem.statfs

println(statfs)
```

!!! Warning

    Reading `statfs` throws an `IllegalStateException` when the root filesystem information cannot be read

### visibleWindows

List of [desktop windows](os_desktop_window.md) currently reported as on-screen

```kotlin
val visibleWindows: List<MacOsDesktopWindow> = operatingSystem.visibleWindows

println(visibleWindows)
```

### allWindows

List of [desktop windows](os_desktop_window.md) returned by the native enumeration, including windows that are not on-screen

```kotlin
val allWindows: List<MacOsDesktopWindow> = operatingSystem.allWindows

println(allWindows)
```

### utmpx

The first active [user session](os_session.md) found in the native `utmpx` records

```kotlin
val utmpx: MacOsOsSession = operatingSystem.utmpx

println(utmpx)
```

!!! Warning

    Reading `utmpx` throws an `IllegalStateException` when no active user session can be read

### procTaskAllInfo

The [current process](os_process.md) running the application. Its [threads](os_thread.md) are available through `threadDetails`

```kotlin
val procTaskAllInfo: MacOsOSProcess = operatingSystem.procTaskAllInfo

println(procTaskAllInfo)
```

### socketFdInfo

List of [IP connections](internetprotocolstats/ip_connection.md) from socket descriptors accessible to the application

```kotlin
val socketFdInfo: List<MacOsIPConnection> = operatingSystem.socketFdInfo

println(socketFdInfo)
```

Socket descriptors whose information cannot be read or mapped are omitted from the list

### rtMsgHdr2

List of [IP routes](internetprotocolstats/ip_route.md) read from the native routing table

```kotlin
val rtMsgHdr2: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2

println(rtMsgHdr2)
```

### tcpStat

The native [TCP statistics](internetprotocolstats/tcp_stats.md)

```kotlin
val tcpStat: MacOsTcpStats = operatingSystem.tcpStat

println(tcpStat)
```

Reading `tcpStat` throws an `IllegalStateException` when the native statistics query fails

### udpStat

The native [UDP statistics](internetprotocolstats/udp_stats.md)

```kotlin
val udpStat: MacOsUdpStats = operatingSystem.udpStat

println(udpStat)
```

Reading `udpStat` throws an `IllegalStateException` when the native statistics query fails
