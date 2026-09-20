# Network params

Represents the network configuration parameters of a device,
providing details about the host name, domain name, DNS servers, and default gateways
for both IPv4 and IPv6

### Original source

The operating system process information are retrieved from `OperatingSystem.NetworkParams` interface

### KInfo's source

```kotlin
val networkParams = operatingSystem.networkParams
```

### Properties

#### hostName

The name of the device on the network

```kotlin
val hostName: String = currentProcess.hostName

println(hostName) // e.g. MyComputer
```

#### domainName

The domain name of the network or the device

```kotlin
val domainName: String = currentProcess.domainName

println(domainName) // e.g. example.com
```

#### dnsServers

A list of DNS server addresses used by the device for domain name resolution

```kotlin
val dnsServers: Array<String> = currentProcess.dnsServers

println(dnsServers) // e.g. ["8.8.8.8", "8.8.4.4"]
```

#### ipv4DefaultGateway

The default gateway for IPv4 traffic, typically the router's IP address

```kotlin
val ipv4DefaultGateway: String = currentProcess.ipv4DefaultGateway

println(ipv4DefaultGateway) // e.g. 192.168.1.1
```

#### ipv6DefaultGateway

The default gateway for IPv6 traffic, typically the router's IPv6 address

```kotlin
val ipv6DefaultGateway: String = currentProcess.ipv6DefaultGateway

println(ipv6DefaultGateway) // e.g. fe80::1
```