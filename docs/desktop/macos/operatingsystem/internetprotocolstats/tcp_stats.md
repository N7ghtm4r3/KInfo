Represents cumulative macOS TCP statistics, including connection attempts, successful establishments, transmitted data, and errors

Read `operatingSystem.tcpStat` again to retrieve a new snapshot. A failed native statistics query throws `IllegalStateException`. macOS can return zeroed counters when `net.inet.tcp.disable_access_to_stats` is enabled, even when the query succeeds.

The examples use `operatingSystem` from the [Operating system API](../index.md#operatingsystem-api).

## Properties

### connectionsEstablished

The cumulative number of successful connection establishments. This value does not count the sockets currently in the `ESTABLISHED` state

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val connectionsEstablished: Long = tcpStats.connectionsEstablished

println(connectionsEstablished) // e.g. 1824
```

### connectionsActive

The cumulative number of active connection attempts

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val connectionsActive: Long = tcpStats.connectionsActive

println(connectionsActive) // e.g. 2000
```

### connectionsPassive

The cumulative number of accepted passive connections

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val connectionsPassive: Long = tcpStats.connectionsPassive

println(connectionsPassive) // e.g. 95
```

### connectionFailures

The cumulative number of connections dropped before establishment

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val connectionFailures: Long = tcpStats.connectionFailures

println(connectionFailures) // e.g. 14
```

### connectionsReset

The cumulative number of established connections dropped

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val connectionsReset: Long = tcpStats.connectionsReset

println(connectionsReset) // e.g. 37
```

### segmentsSent

The cumulative number of TCP data segments sent

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val segmentsSent: Long = tcpStats.segmentsSent

println(segmentsSent) // e.g. 15824300
```

### segmentsReceived

The cumulative number of TCP data segments received in sequence

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val segmentsReceived: Long = tcpStats.segmentsReceived

println(segmentsReceived) // e.g. 17345210
```

### segmentsRetransmitted

The cumulative number of TCP data segments retransmitted

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val segmentsRetransmitted: Long = tcpStats.segmentsRetransmitted

println(segmentsRetransmitted) // e.g. 1250
```

### inErrors

The sum of packets rejected for bad checksums, invalid header offsets, insufficient memory, or insufficient length

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val inErrors: Long = tcpStats.inErrors

println(inErrors) // e.g. 42
```

### outResets

The cumulative number of sent TCP control segments, including `SYN`, `FIN`, and `RST`. This macOS value includes more than outgoing resets

```kotlin
val tcpStats: MacOsTcpStats = operatingSystem.tcpStat

val outResets: Long = tcpStats.outResets

println(outResets) // e.g. 3800
```

