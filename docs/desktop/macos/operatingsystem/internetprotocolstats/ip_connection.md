Represents an IP socket on macOS, providing its protocol, addresses, ports, state, and owning process

`socketFdInfo` collects socket information from accessible processes. Processes and sockets whose information cannot be read are skipped, as are unsupported socket kinds and sockets without a valid IP address. The returned list can therefore be incomplete or empty.

The examples use `operatingSystem` from the [Operating system API](../index.md#operatingsystem-api). They assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### type

The native protocol name, such as `IPPROTO_TCP` or `IPPROTO_UDP`. An unrecognized native protocol is represented by `IPPROTO_RAW`

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val type: String = sample.type

println(type) // e.g. IPPROTO_TCP
```

### localAddress

The local IP address encoded as four bytes for IPv4 or sixteen bytes for IPv6. The example prints the signed bytes of `192.0.2.10`

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val localAddress: ByteArray = sample.localAddress

println(localAddress.contentToString()) // e.g. [-64, 0, 2, 10]
```

### localPort

The local port number, converted to host byte order

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val localPort: Int = sample.localPort

println(localPort) // e.g. 52345
```

### foreignAddress

The remote IP address encoded as four bytes for IPv4 or sixteen bytes for IPv6. The example prints the signed bytes of `198.51.100.20`

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val foreignAddress: ByteArray = sample.foreignAddress

println(foreignAddress.contentToString()) // e.g. [-58, 51, 100, 20]
```

### foreignPort

The remote port number, converted to host byte order

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val foreignPort: Int = sample.foreignPort

println(foreignPort) // e.g. 443
```

### state

The current [TCP state](../tcp_state.md), or `null` for a non-TCP socket. Unrecognized native TCP states use `UNKNOWN`

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val state: TcpState? = sample.state

println(state) // e.g. ESTABLISHED
```

### transmitQueue

The number of complete connections in the native socket queue. This macOS value is a connection count, not a count of bytes waiting to be transmitted

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val transmitQueue: Int = sample.transmitQueue

println(transmitQueue) // e.g. 0
```

### receiveQueue

The number of incomplete connections in the native socket queue. This macOS value is a connection count, not a count of received bytes

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val receiveQueue: Int = sample.receiveQueue

println(receiveQueue) // e.g. 0
```

### owningProcessId

The identifier of the process that owns the socket

```kotlin
val ipConnections: List<MacOsIPConnection> = operatingSystem.socketFdInfo
val sample: MacOsIPConnection = ipConnections.first()

val owningProcessId: Int = sample.owningProcessId

println(owningProcessId) // e.g. 2156
```

