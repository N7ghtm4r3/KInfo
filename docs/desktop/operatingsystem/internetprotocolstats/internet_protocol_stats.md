# Internal Protocol Stats

Represent the statistics of the network protocol including **TCP** and **UDP** statistics
for both **IPv4** and **IPv6**, as well as the list of active **IP** connections

### Original source

The internet protocol stats information are retrieved from `OperatingSystem.InternetProtocolStats` interface

### KInfo's source

```kotlin
val internetProtocolStats = operatingSystem.internetProtocolStats
```

### Properties

#### tcpV4Stats

The TCP statistics for IPv4 protocol, including information about connections, retransmissions, etc.

The `tcpV4Stats` property is represented by the [TcpStats](tcp_stats.md) interface

```kotlin
val tcpV4Stats: TcpStats = internetProtocolStats.tcpV4Stats

println(tcpV4Stats.connectionsEstablished) // e.g. 1000
```

#### tcpV6Stats

The TCP statistics for IPv6 protocol, including information about connections, retransmissions, etc.

The `tcpV6Stats` property is represented by the [TcpStats](tcp_stats.md) interface

```kotlin
val tcpV6Stats: TcpStats = internetProtocolStats.tcpV6Stats

println(tcpV6Stats.connectionsEstablished) // e.g. 1000
```

#### udpV4Stats

The UDP statistics for IPv4 protocol, including information about datagrams sent and received, etc

The `udpV4Stats` property is represented by the [UdpStats](udp_stats.md) interface

```kotlin
val udpV4Stats: UdpStats = internetProtocolStats.udpV4Stats

println(udpV4Stats.datagramsSent) // e.g. 452300
```

#### udpV6Stats

The UDP statistics for IPv6 protocol, including information about datagrams sent and received, etc

The `udpV6Stats` property is represented by the [UdpStats](udp_stats.md) interface

```kotlin
val udpV6Stats: UdpStats = internetProtocolStats.udpV6Stats

println(udpV6Stats.datagramsSent) // e.g. 452300
```

#### ipConnections

List of [active IP connections](ip_connection.md), each representing a current network connection with its details

```kotlin
val ipConnections: List<IPConnection> = internetProtocolStats.ipConnections

println(ipConnections)
```