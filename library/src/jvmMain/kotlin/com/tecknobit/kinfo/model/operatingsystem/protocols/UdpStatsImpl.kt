package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats

/**
 * `UdpStatsImpl` Implementation of the `UdpStats` interface.
 * This class provides the statistics for the UDP protocol, including the number of datagrams sent and received,
 * as well as any errors or datagrams with no port.
 *
 * @param datagramsSent The total number of UDP datagrams sent.
 * @param datagramsReceived The total number of UDP datagrams received.
 * @param datagramsNoPort The total number of UDP datagrams that did not have a corresponding port to route to.
 * @param datagramsReceivedErrors The total number of UDP datagrams that were received with errors (e.g., checksum errors).
 *
 * @author N7ghtm4r3
 *
 * @see UdpStats
 */
class UdpStatsImpl(
    override val datagramsSent: Long,
    override val datagramsReceived: Long,
    override val datagramsNoPort: Long,
    override val datagramsReceivedErrors: Long
) : UdpStats
