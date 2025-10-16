package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `NetworkParams` Represents the network configuration parameters of a device,
 * providing details about the host name, domain name, DNS servers, and default gateways
 * for both IPv4 and IPv6
 *
 * @author N7ghtm4r3
 */
interface NetworkParams {

    /**
     * `hostName` The name of the device on the network (e.g., "MyComputer")
     */
    val hostName: String

    /**
     * `domainName` The domain name of the network or the device (e.g., "example.com")
     */
    val domainName: String

    /**
     * `dnsServers` A list of DNS server addresses used by the device for domain name resolution
     */
    val dnsServers: Array<String>

    /**
     * `ipv4DefaultGateway` The default gateway for IPv4 traffic, typically the router's IP address
     */
    val ipv4DefaultGateway: String

    /**
     * `ipv6DefaultGateway` The default gateway for IPv6 traffic, typically the router's IPv6 address
     */
    val ipv6DefaultGateway: String

}
