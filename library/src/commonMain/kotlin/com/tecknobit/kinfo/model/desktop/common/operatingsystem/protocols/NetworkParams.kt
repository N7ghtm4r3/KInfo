package com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols

/**
 * The `NetworkParams` interface defines the contract to represent the network configuration of a device
 *
 * It provides the host name, domain name, DNS servers, default gateways, and routing table entries for `IPv4` and
 * `IPv6`
 *
 * @author N7ghtm4r3 - Tecknobit
 */
interface NetworkParams {

    /**
     * `hostName` the name of the device on the network, such as `MyComputer`
     */
    val hostName: String

    /**
     * `domainName` the domain name of the network or device, such as `example.com`
     */
    val domainName: String

    /**
     * `dnsServers` the DNS server addresses used by the device for domain name resolution
     */
    val dnsServers: Array<String>

    /**
     * `ipv4DefaultGateway` the default gateway for `IPv4` traffic, or an empty string when not defined
     */
    val ipv4DefaultGateway: String

    /**
     * `ipv6DefaultGateway` the default gateway for `IPv6` traffic, or an empty string when not defined
     */
    val ipv6DefaultGateway: String

    /**
     * `routes` the `IPv4` and `IPv6` entries available in the operating system routing table
     *
     * @since 1.1.0
     */
    val routes: List<IPRoute>
}
