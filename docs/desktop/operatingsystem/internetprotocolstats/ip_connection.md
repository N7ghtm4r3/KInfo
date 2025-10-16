Represents an active IP connection, providing details about the connection's
state, local and foreign addresses, ports, and associated process information

## Properties

### type

The type of the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val type: String = sample.type

println(type) // e.g. TCP
```

### localAddress

The local IP address of the connection as a byte array

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val localAddress: ByteArray = sample.localAddress

println(localAddress) // e.g. [192, 168, 1, 10]
```

### localPort

The local port number of the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val localPort: Int = sample.localPort

println(localPort) // e.g. 52345
```

### foreignPort

The foreign port number (remote) of the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val foreignPort: Int = sample.foreignPort

println(foreignPort) // e.g. 443
```

### state

The current [state](../tcp_state.md) of the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val state: TcpState = sample.state

println(state) // e.g. ESTABLISHED
```

### transmitQueue

The length of the transmit queue for the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val transmitQueue: Int = sample.transmitQueue

println(transmitQueue) // e.g. 0
```

### receiveQueue

The length of the receipt queue for the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val receiveQueue: Int = sample.receiveQueue

println(receiveQueue) // e.g. 128
```

### owningProcessId

The process ID of the process that owns the connection

```kotlin
val ipConnections = internetProtocolStats.ipConnections
val sample: IPConnection = ipConnections.first()

val owningProcessId: Int = sample.owningProcessId

println(owningProcessId) // e.g. 2156
```