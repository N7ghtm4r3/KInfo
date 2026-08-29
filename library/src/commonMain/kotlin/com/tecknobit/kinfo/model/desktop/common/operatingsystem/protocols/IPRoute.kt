package com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols

/**
 * The `IPRoute` interface defines the contract to represent an entry in the operating system routing table
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface IPRoute {

    /**
     * `destination` the destination network address encoded as four bytes for `IPv4` or sixteen bytes for `IPv6`
     *
     * @since 1.1.0
     */
    val destination: ByteArray

    /**
     * `prefixLength` the number of leading bits in the destination network mask, or `-1` when not available
     *
     * @since 1.1.0
     */
    val prefixLength: Int

    /**
     * `gateway` the next hop address, or an empty array for a directly attached route
     *
     * @since 1.1.0
     */
    val gateway: ByteArray

    /**
     * `interfaceName` the name of the outgoing network interface, or an empty string when not available
     *
     * @since 1.1.0
     */
    val interfaceName: String

    /**
     * `interfaceIndex` the index of the outgoing network interface, or `-1` when not available
     *
     * @since 1.1.0
     */
    val interfaceIndex: Int

    /**
     * `metric` the route cost used to select between routes to the same destination, or `-1` when not available
     *
     * @since 1.1.0
     */
    val metric: Long

    /**
     * `isGateway` whether the route forwards traffic through [gateway]
     *
     * @since 1.1.0
     */
    val isGateway: Boolean

    /**
     * `isHost` whether the route targets a single host rather than a network
     *
     * @since 1.1.0
     */
    val isHost: Boolean
}