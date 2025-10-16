Represents statistics related to **TCP** connections, including the number of connections,
the segments sent and received, and error counts

## Properties

### connectionsEstablished

The number of established TCP connections

```kotlin
val connectionsEstablished: Long = statsSample.connectionsEstablished

println(connectionsEstablished) // e.g. 1824
```

### connectionsActive

The number of active (currently open) TCP connections

```kotlin
val connectionsActive: Long = statsSample.connectionsActive

println(connectionsActive) // e.g. 320
```

### connectionsPassive

The number of passive (waiting for incoming requests) TCP connections

```kotlin
val connectionsPassive: Long = statsSample.connectionsPassive

println(connectionsPassive) // e.g. 95
```

### connectionFailures

The number of failed attempts to establish a TCP connection

```kotlin
val connectionFailures: Long = statsSample.connectionFailures

println(connectionFailures) // e.g. 14
```

### connectionsReset

The number of TCP connections that have been reset

```kotlin
val connectionsReset: Long = statsSample.connectionsReset

println(connectionsReset) // e.g. 37
```

### segmentsSent

The number of TCP segments sent

```kotlin
val segmentsSent: Long = statsSample.segmentsSent

println(segmentsSent) // e.g. 15824300
```

### segmentsReceived

The number of TCP segments received

```kotlin
val segmentsReceived: Long = statsSample.segmentsReceived

println(segmentsReceived) // e.g. 17345210
```

### segmentsRetransmitted

The number of TCP segments that have been retransmitted

```kotlin
val segmentsRetransmitted: Long = statsSample.segmentsRetransmitted

println(segmentsRetransmitted) // e.g. 1250
```

### inErrors

The number of incoming errors (e.g., malformed packets)

```kotlin
val inErrors: Long = statsSample.inErrors

println(inErrors) // e.g. 42
```

### outResets

The number of outgoing reset signals (e.g., RST flags)

```kotlin
val outResets: Long = statsSample.outResets

println(outResets) // e.g. 37
```