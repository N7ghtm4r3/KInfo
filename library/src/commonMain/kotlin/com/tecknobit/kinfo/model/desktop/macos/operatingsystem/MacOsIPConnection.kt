package com.tecknobit.kinfo.model.desktop.macos.operatingsystem

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.IPConnection

/**
 * The `MacOsIPConnection` interface defines the contract to provide the details of an `IP` connection on macOS
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see IPConnection
 *
 * @since 1.1.0
 */
interface MacOsIPConnection : IPConnection

/**
 * The `SocketInfoProtocol` enum is useful to represent the native protocol associated with a macOS socket
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
enum class SocketInfoProtocol {

    /**
     * `IPPROTO_IP` the `IP` protocol
     */
    IPPROTO_IP,

    /**
     * `IPPROTO_ICMP` the Internet Control Message Protocol for `IPv4`
     */
    IPPROTO_ICMP,

    /**
     * `IPPROTO_IGMP` the Internet Group Management Protocol
     */
    IPPROTO_IGMP,

    /**
     * `IPPROTO_TCP` the Transmission Control Protocol
     */
    IPPROTO_TCP,

    /**
     * `IPPROTO_UDP` the User Datagram Protocol
     */
    IPPROTO_UDP,

    /**
     * `IPPROTO_IPV6` the `IPv6` protocol
     */
    IPPROTO_IPV6,

    /**
     * `IPPROTO_GRE` the Generic Routing Encapsulation protocol
     */
    IPPROTO_GRE,

    /**
     * `IPPROTO_ESP` the Encapsulating Security Payload protocol
     */
    IPPROTO_ESP,

    /**
     * `IPPROTO_AH` the Authentication Header protocol
     */
    IPPROTO_AH,

    /**
     * `IPPROTO_ICMPV6` the Internet Control Message Protocol for `IPv6`
     */
    IPPROTO_ICMPV6,

    /**
     * `IPPROTO_SCTP` the Stream Control Transmission Protocol
     */
    IPPROTO_SCTP,

    /**
     * `IPPROTO_RAW` the raw `IP` protocol or the fallback for an unrecognized native protocol
     */
    IPPROTO_RAW

}