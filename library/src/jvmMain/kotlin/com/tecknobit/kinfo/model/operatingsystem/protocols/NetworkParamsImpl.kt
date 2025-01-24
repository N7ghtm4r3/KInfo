package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.NetworkParams

/**
 * `NetworkParamsImpl` Implementation of the `NetworkParams` interface.
 * This class provides details about the network parameters of the device,
 * including host name, domain name, DNS servers, and default gateways for IPv4 and IPv6.
 *
 * @param networkParamsInfo An instance of `oshi.software.os.NetworkParams` containing the network information.
 *
 * @author N7ghtm4r3
 *
 * @see NetworkParams
 */
class NetworkParamsImpl(
    networkParamsInfo: oshi.software.os.NetworkParams
) : NetworkParams {

    /**
     * `hostName` The name of the device on the network (e.g., "MyComputer").
     */
    override val hostName: String = networkParamsInfo.hostName

    /**
     * `domainName` The domain name of the network or the device (e.g., "example.com").
     */
    override val domainName: String = networkParamsInfo.domainName

    /**
     * `dnsServers` A list of DNS server addresses used by the device for domain name resolution.
     */
    override val dnsServers: Array<String> = networkParamsInfo.dnsServers

    /**
     * `ipv4DefaultGateway` The default gateway for IPv4 traffic, typically the router's IP address.
     */
    override val ipv4DefaultGateway: String = networkParamsInfo.ipv4DefaultGateway

    /**
     * `ipv6DefaultGateway` The default gateway for IPv6 traffic, typically the router's IPv6 address.
     */
    override val ipv6DefaultGateway: String = networkParamsInfo.ipv6DefaultGateway

}
