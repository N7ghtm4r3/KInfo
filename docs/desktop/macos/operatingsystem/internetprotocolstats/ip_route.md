Represents an entry in the macOS routing table, providing its destination, gateway, outgoing interface, and route flags

`rtMsgHdr2` reads the native routing table and maps its entries. Missing addresses and unsupported address families are represented by empty byte arrays.

The examples use `operatingSystem` from the [Operating system API](../index.md#operatingsystem-api). They assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### destination

The destination address encoded as four bytes for IPv4 or sixteen bytes for IPv6, or an empty array when unavailable or unsupported. The example prints the signed bytes of `198.51.100.0`

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val destination: ByteArray = sample.destination

println(destination.contentToString()) // e.g. [-58, 51, 100, 0]
```

### prefixLength

The prefix length derived from the native netmask. When the mask is absent, host routes use the destination address length in bits and other routes return `-1`

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val prefixLength: Int = sample.prefixLength

println(prefixLength) // e.g. 24
```

### gateway

The next hop address encoded as four bytes for IPv4 or sixteen bytes for IPv6. An empty array indicates an absent or unsupported address, including link-layer gateways for directly attached routes. The example prints the signed bytes of `192.0.2.1`

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val gateway: ByteArray = sample.gateway

println(gateway.contentToString()) // e.g. [-64, 0, 2, 1]
```

### interfaceName

The name of the outgoing network interface, or an empty string when the native name lookup fails

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val interfaceName: String = sample.interfaceName

println(interfaceName) // e.g. en0
```

### interfaceIndex

The outgoing network interface index copied from the native routing entry

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val interfaceIndex: Int = sample.interfaceIndex

println(interfaceIndex) // e.g. 6
```

### metric

The hop-count metric reported by the native routing entry

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val metric: Long = sample.metric

println(metric) // e.g. 0
```

### isGateway

Whether the native route has the gateway flag set

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val isGateway: Boolean = sample.isGateway

println(isGateway) // e.g. true
```

### isHost

Whether the native route has the host flag set, indicating a route to a single host

```kotlin
val ipRoutes: List<MacOsIpRoute> = operatingSystem.rtMsgHdr2
val sample: MacOsIpRoute = ipRoutes.first()

val isHost: Boolean = sample.isHost

println(isHost) // e.g. false
```

