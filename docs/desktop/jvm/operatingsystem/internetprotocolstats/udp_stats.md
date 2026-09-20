Represents the statistics for the `UDP` protocol, including the number of datagrams sent and received,
as well as any errors or datagrams with no port

## Properties

### datagramsSent

The total number of UDP datagrams sent

```kotlin
val datagramsSent: Long = statsSample.datagramsSent

println(datagramsSent) // e.g. 452300
```

### datagramsReceived

The total number of UDP datagrams received

```kotlin
val datagramsReceived: Long = statsSample.datagramsReceived

println(datagramsReceived) // e.g. 439800
```

### datagramsNoPort

The total number of UDP datagrams that did not have a corresponding port to route to

```kotlin
val datagramsNoPort: Long = statsSample.datagramsNoPort

println(datagramsNoPort) // e.g. 120
```

### datagramsNoPort

The total number of UDP datagrams that were received with errors (e.g., checksum errors)

```kotlin
val datagramsReceivedErrors: Long = statsSample.datagramsReceivedErrors

println(datagramsReceivedErrors) // e.g. 8
```