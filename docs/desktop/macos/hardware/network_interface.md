Represents a network interface on macOS, providing its identity, assigned addresses, and traffic statistics

Each item is a snapshot. Read `hardware.networkInterfaces` again to retrieve updated information.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### name

The BSD name of the network interface, or an empty string when unavailable

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val name: String = sample.name

println(name) // e.g. en0
```

### index

The native index of the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val index: Int = sample.index

println(index) // e.g. 6
```

### displayName

The localized display name of the network interface, falling back to `name` when unavailable

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val displayName: String = sample.displayName

println(displayName) // e.g. Wi-Fi
```

### ifAlias

The alias of the network interface. The macOS implementation currently returns an empty string

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ifAlias: String = sample.ifAlias

println(ifAlias) // empty string
```

### ifOperStatus

The operational status of the network interface. The macOS implementation returns `UP`, `DOWN`, or `UNKNOWN` when the status cannot be determined

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ifOperStatus: IfOperStatus = sample.ifOperStatus

println(ifOperStatus) // e.g. UP
```

### mtu

The maximum transmission unit (MTU) size of the network interface in bytes

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val mtu: Long = sample.mtu

println(mtu) // e.g. 1500
```

### macaddr

The hardware address of the network interface, or an empty string when unavailable

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val macaddr: String = sample.macaddr

println(macaddr) // e.g. 00:00:5e:00:53:01
```

### ipv4addr

The IPv4 addresses assigned to the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ipv4addr: Array<String> = sample.ipv4addr

println(ipv4addr.contentToString()) // e.g. [192.0.2.10]
```

### subnetMasks

The IPv4 prefix lengths, in the same order as `ipv4addr`. A value of `-1` indicates an absent or noncontiguous netmask

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val subnetMasks: Array<Short> = sample.subnetMasks

println(subnetMasks.contentToString()) // e.g. [24]
```

### ipv6addr

The IPv6 addresses assigned to the network interface, including their scope suffixes when present

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ipv6addr: Array<String> = sample.ipv6addr

println(ipv6addr.contentToString()) // e.g. [2001:db8::10]
```

### prefixLengths

The IPv6 prefix lengths, in the same order as `ipv6addr`. A value of `-1` indicates an absent or noncontiguous netmask

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val prefixLengths: Array<Short> = sample.prefixLengths

println(prefixLengths.contentToString()) // e.g. [64]
```

### ifType

The native type of the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ifType: Int = sample.ifType

println(ifType) // e.g. 6
```

### ndisPhysicalMediumType

The NDIS physical medium type. This property is not applicable to the macOS implementation and returns `0`

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val ndisPhysicalMediumType: Int = sample.ndisPhysicalMediumType

println(ndisPhysicalMediumType) // 0
```

### isConnectorPresent

Whether a connector is present on the network interface. The macOS implementation does not query connector presence and returns `false`

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val isConnectorPresent: Boolean = sample.isConnectorPresent

println(isConnectorPresent) // false
```

### bytesRecv

The cumulative number of bytes received by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val bytesRecv: Long = sample.bytesRecv

println(bytesRecv) // e.g. 8123456789
```

### bytesSent

The cumulative number of bytes sent by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val bytesSent: Long = sample.bytesSent

println(bytesSent) // e.g. 3456789012
```

### packetsRecv

The cumulative number of packets received by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val packetsRecv: Long = sample.packetsRecv

println(packetsRecv) // e.g. 12345678
```

### packetsSent

The cumulative number of packets sent by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val packetsSent: Long = sample.packetsSent

println(packetsSent) // e.g. 9876543
```

### inErrors

The cumulative number of input errors reported by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val inErrors: Long = sample.inErrors

println(inErrors) // e.g. 2
```

### outErrors

The cumulative number of output errors reported by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val outErrors: Long = sample.outErrors

println(outErrors) // e.g. 0
```

### inDrops

The cumulative number of incoming packets dropped by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val inDrops: Long = sample.inDrops

println(inDrops) // e.g. 15
```

### collisions

The cumulative number of collisions reported by the network interface

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val collisions: Long = sample.collisions

println(collisions) // e.g. 0
```

### speed

The network interface speed reported by the native statistics in bits per second

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val speed: Long = sample.speed

println(speed) // e.g. 1000000000
```

### timestamp

The time at which the snapshot was mapped, in milliseconds since the Unix epoch

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val timestamp: Long = sample.timestamp

println(timestamp) // e.g. 1760561106000
```

### isKnownVmMacAddr

Whether the MAC address matches one of the known virtual-machine prefixes checked by the implementation

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val isKnownVmMacAddr: Boolean = sample.isKnownVmMacAddr

println(isKnownVmMacAddr) // e.g. false
```

### updateAttributes

Whether this snapshot refreshes its attributes. The macOS implementation returns `false`; read `hardware.networkInterfaces` again to retrieve a new snapshot

```kotlin
val networkInterfaces: List<MacOsNetworkIF> = hardware.networkInterfaces
val sample: MacOsNetworkIF = networkInterfaces.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // false
```
