Represents cumulative macOS UDP statistics, including sent and received datagrams, unmatched destination ports, and receive errors

Read `operatingSystem.udpStat` again to retrieve a new snapshot. A failed native statistics query throws `IllegalStateException`.

The examples use `operatingSystem` from the [Operating system API](../index.md#operatingsystem-api).

## Properties

### datagramsSent

The cumulative number of UDP datagrams sent

```kotlin
val udpStats: MacOsUdpStats = operatingSystem.udpStat

val datagramsSent: Long = udpStats.datagramsSent

println(datagramsSent) // e.g. 452300
```

### datagramsReceived

The cumulative number of UDP datagrams received

```kotlin
val udpStats: MacOsUdpStats = operatingSystem.udpStat

val datagramsReceived: Long = udpStats.datagramsReceived

println(datagramsReceived) // e.g. 439800
```

### datagramsNoPort

The cumulative number of received UDP datagrams without a matching destination port

```kotlin
val udpStats: MacOsUdpStats = operatingSystem.udpStat

val datagramsNoPort: Long = udpStats.datagramsNoPort

println(datagramsNoPort) // e.g. 120
```

### datagramsReceivedErrors

The sum of native receive errors, invalid checksums, and invalid lengths

```kotlin
val udpStats: MacOsUdpStats = operatingSystem.udpStat

val datagramsReceivedErrors: Long = udpStats.datagramsReceivedErrors

println(datagramsReceivedErrors) // e.g. 8
```
