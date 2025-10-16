Represents a network interface on the system

## Properties

### name

The name of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val name: String = sample.name

println(name) // e.g. eth0
```

### index

The index of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val index: Int = sample.index

println(index) // e.g. 0
```

### displayName

The display name of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val displayName: String = sample.displayName

println(displayName) // e.g. Intel(R) Ethernet Connection I219-V
```

### ifAlias

The alias of the network interface, if available

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ifAlias: String = sample.ifAlias

println(ifAlias) // e.g. LAN
```

### ifOperStatus

The operational status of the network interface

#### IfOperStatus

| **Status**           | **Description**                                                                     |
|----------------------|-------------------------------------------------------------------------------------|
| **UP**               | Up and operational. Ready to pass packets                                           |
| **DOWN**             | Down and not operational. Not ready to pass packets                                 |
| **TESTING**          | In some test mode                                                                   |
| **UNKNOWN**          | The interface status is unknown                                                     |
| **DORMANT**          | The interface is not up, but is in a pending state, waiting for some external event |
| **NOT_PRESENT**      | Some component is missing                                                           |
| **LOWER_LAYER_DOWN** | Down due to state of lower-layer interface(s)                                       |

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ifOperStatus: IfOperStatus = sample.ifOperStatus

println(ifOperStatus) // e.g. LAN
```

### mtu

The maximum transmission unit (MTU) size of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val mtu: Long = sample.mtu

println(mtu) // e.g. 1500
```

### macaddr

The MAC address of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val macaddr: String = sample.macaddr

println(macaddr) // e.g. 00:1A:2B:3C:4D:5E
```

### ipv4addr

The list of IPv4 addresses assigned to the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ipv4addr: Array<String> = sample.ipv4addr

println(ipv4addr) // e.g. ["192.168.1.10"]
```

### subnetMasks

The list of subnet masks for the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val subnetMasks: Array<Short> = sample.subnetMasks

println(subnetMasks) // e.g. [24]
```

### ipv6addr

The list of IPv6 addresses assigned to the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ipv6addr: Array<String> = sample.ipv6addr

println(ipv6addr) // e.g. ["fe80::21a:2bff:fe3c:4d5e"]
```

### prefixLengths

The list of prefix lengths corresponding to the IPv6 addresses

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val prefixLengths: Array<Short> = sample.prefixLengths

println(prefixLengths) // e.g. [64]
```

### ifType

The type of the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ifType: Int = sample.ifType

println(ifType) // e.g. 6 
```

### ndisPhysicalMediumType

The NDIS physical medium type for the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val ndisPhysicalMediumType: Int = sample.ndisPhysicalMediumType

println(ndisPhysicalMediumType) // e.g. 0 
```

### isConnectorPresent

Whether a connector is present on the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val isConnectorPresent: Boolean = sample.isConnectorPresent

println(isConnectorPresent) // true or false
```

### bytesRecv

The total number of bytes received by the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val bytesRecv: Long = sample.bytesRecv

println(bytesRecv) // e.g. 8123456789
```

### bytesSent

The total number of bytes sent by the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val bytesSent: Long = sample.bytesSent

println(bytesSent) // e.g. 3456789012
```

### packetsRecv

The total number of packets received by the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val packetsRecv: Long = sample.packetsRecv

println(packetsRecv) // e.g. 12345678
```

### packetsSent

The total number of packets sent by the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val packetsSent: Long = sample.packetsSent

println(packetsSent) // e.g. 9876543
```

### inErrors

The total number of input errors on the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val inErrors: Long = sample.inErrors

println(inErrors) // e.g. 2
```

### outErrors

The total number of output errors on the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val outErrors: Long = sample.outErrors

println(outErrors) // e.g. 0
```

### inDrops

The total number of input packets dropped by the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val inDrops: Long = sample.inDrops

println(inDrops) // e.g. 15
```

### collisions

The total number of collisions on the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val collisions: Long = sample.collisions

println(collisions) // e.g. 15
```

### speed

The speed of the network interface in bits per second

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val speed: Long = sample.speed

println(speed) // e.g. 1000000000
```

### timestamp

The timestamp of the last update for the network interface

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val timestamp: Long = sample.timestamp

println(timestamp) // e.g. 1760561106000
```

### isKnownVmMacAddr

Whether the MAC address is known to belong to a virtual machine

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val isKnownVmMacAddr: Boolean = sample.isKnownVmMacAddr

println(isKnownVmMacAddr) // true or false
```

### updateAttributes

Whether the network interface attributes should be updated

```kotlin
val networkIFs: List<NetworkIF> = hardware.networkIFs
val sample: NetworkIF = networkIFs.first()

val updateAttributes: Boolean = sample.updateAttributes

println(updateAttributes) // true or false
```