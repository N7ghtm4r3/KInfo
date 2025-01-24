package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `UdpStats` Represents the statistics for the UDP protocol, including the number of datagrams sent and received,
 * as well as any errors or datagrams with no port.
 *
 * @author N7ghtm4r3
 *
 * @see UdpStats
 */
interface UdpStats {

    /**
     * `datagramsSent` The total number of UDP datagrams sent.
     */
    val datagramsSent: Long

    /**
     * `datagramsReceived` The total number of UDP datagrams received.
     */
    val datagramsReceived: Long

    /**
     * `datagramsNoPort` The total number of UDP datagrams that did not have a corresponding port to route to.
     */
    val datagramsNoPort: Long

    /**
     * `datagramsReceivedErrors` The total number of UDP datagrams that were received with errors (e.g., checksum errors).
     */
    val datagramsReceivedErrors: Long

}
