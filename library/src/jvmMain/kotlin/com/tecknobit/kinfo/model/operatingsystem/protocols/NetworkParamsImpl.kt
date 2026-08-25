package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.NetworkParams

/**
 * The `NetworkParamsImpl` class is useful to provide the network configuration of a device
 *
 * It provides the host name, domain name, DNS servers, default gateways, and routing table entries for `IPv4` and
 * `IPv6`
 *
 * @property networkParamsInfo The OSHI network parameters used to retrieve the network configuration
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NetworkParams
 */
class NetworkParamsImpl(
    private val networkParamsInfo: oshi.software.os.NetworkParams
) : NetworkParams {

    /**
     * `hostName` the name of the device on the network, such as `MyComputer`
     */
    override val hostName: String = networkParamsInfo.hostName

    /**
     * `domainName` the domain name of the network or device, such as `example.com`
     */
    override val domainName: String = networkParamsInfo.domainName

    /**
     * `dnsServers` the DNS server addresses used by the device for domain name resolution
     */
    override val dnsServers: Array<String> = networkParamsInfo.dnsServers

    /**
     * `ipv4DefaultGateway` the default gateway for `IPv4` traffic, or an empty string when not defined
     */
    override val ipv4DefaultGateway: String = networkParamsInfo.ipv4DefaultGateway

    /**
     * `ipv6DefaultGateway` the default gateway for `IPv6` traffic, or an empty string when not defined
     */
    override val ipv6DefaultGateway: String = networkParamsInfo.ipv6DefaultGateway

    /**
     * `routes` the `IPv4` and `IPv6` entries loaded from the operating system routing table
     *
     * @since 1.1.0
     */
    override val routes: List<IPRoute>
        get() = loadRoutes(
            sourceList = networkParamsInfo.routes
        )

    /**
     * Method used to convert raw OSHI route entries into [IPRoute] instances
     *
     * @param sourceList The raw route entries to convert
     *
     * @return the converted route entries as [List] of [IPRoute]
     *
     * @since 1.1.0
     */
    @Loader
    private fun loadRoutes(
        sourceList: List<oshi.software.os.NetworkParams.IPRoute>
    ): List<IPRoute> {
        val result = mutableListOf<IPRouteImpl>()
        sourceList.forEach { iproute ->
            result.add(
                IPRouteImpl(
                    destination = iproute.destination,
                    prefixLength = iproute.prefixLength,
                    gateway = iproute.gateway,
                    interfaceName = iproute.interfaceName,
                    interfaceIndex = iproute.interfaceIndex,
                    metric = iproute.metric,
                    isGateway = iproute.isGateway,
                    isHost = iproute.isHost
                )
            )
        }

        return result
    }

}
