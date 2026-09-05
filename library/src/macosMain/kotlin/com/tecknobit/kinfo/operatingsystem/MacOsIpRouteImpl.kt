package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsIpRoute

/**
 * The `MacOsIpRouteImpl` class is useful to represent an entry in the macOS routing table
 *
 * @property destination The destination network address encoded as four bytes for `IPv4` or
 * sixteen bytes for `IPv6`
 * @property prefixLength The number of leading bits in the destination network mask, or `-1` when
 * not available
 * @property gateway The next hop address, or an empty array for a directly attached route
 * @property interfaceName The name of the outgoing network interface, or an empty string when not
 * available
 * @property interfaceIndex The index of the outgoing network interface, or `-1` when not available
 * @property metric The route cost used to select between routes to the same destination, or `-1`
 * when not available
 * @property isGateway Whether the route forwards traffic through the [gateway]
 * @property isHost Whether the route targets a single host rather than a network
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsIpRoute
 *
 * @since 1.1.0
 */
data class MacOsIpRouteImpl(
    override val destination: ByteArray,
    override val prefixLength: Int,
    override val gateway: ByteArray,
    override val interfaceName: String,
    override val interfaceIndex: Int,
    override val metric: Long,
    override val isGateway: Boolean,
    override val isHost: Boolean
): MacOsIpRoute {

    /**
     * Method used to compare this route with another object
     *
     * @param other The object to compare with this route
     *
     * @return whether the objects represent the same route as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsIpRouteImpl

        if (prefixLength != other.prefixLength) return false
        if (interfaceIndex != other.interfaceIndex) return false
        if (metric != other.metric) return false
        if (isGateway != other.isGateway) return false
        if (isHost != other.isHost) return false
        if (!destination.contentEquals(other.destination)) return false
        if (!gateway.contentEquals(other.gateway)) return false
        if (interfaceName != other.interfaceName) return false

        return true
    }

    /**
     * Method used to compute the hash code from the route properties
     *
     * @return the route hash code as [Int]
     */
    override fun hashCode(): Int {
        var result = prefixLength
        result = 31 * result + interfaceIndex
        result = 31 * result + metric.hashCode()
        result = 31 * result + isGateway.hashCode()
        result = 31 * result + isHost.hashCode()
        result = 31 * result + destination.contentHashCode()
        result = 31 * result + gateway.contentHashCode()
        result = 31 * result + interfaceName.hashCode()
        return result
    }

}
