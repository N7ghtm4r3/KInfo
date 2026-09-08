package com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols

/**
 * The `UdpStats` interface defines the contract to provide cumulative `UDP` datagram statistics
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface UdpStats {

    /**
     * `datagramsSent` the total number of `UDP` datagrams sent
     */
    val datagramsSent: Long

    /**
     * `datagramsReceived` the total number of `UDP` datagrams received
     */
    val datagramsReceived: Long

    /**
     * `datagramsNoPort` the total number of received `UDP` datagrams without a matching destination port
     */
    val datagramsNoPort: Long

    /**
     * `datagramsReceivedErrors` the total number of errors detected while receiving `UDP` datagrams
     */
    val datagramsReceivedErrors: Long

}
