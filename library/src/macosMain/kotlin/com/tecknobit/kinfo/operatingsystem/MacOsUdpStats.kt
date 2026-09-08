package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsUdpStats

/**
 * The `MacOsUdpStatsImpl` class is useful to represent cumulative native macOS `UDP` datagram statistics
 *
 * @property datagramsSent The total number of `UDP` datagrams sent
 * @property datagramsReceived The total number of `UDP` datagrams received
 * @property datagramsNoPort The total number of received `UDP` datagrams without a matching destination port
 * @property datagramsReceivedErrors The total number of errors detected while receiving `UDP` datagrams
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsUdpStats
 *
 * @since 1.1.0
 */
data class MacOsUdpStatsImpl(
    override val datagramsSent: Long,
    override val datagramsReceived: Long,
    override val datagramsNoPort: Long,
    override val datagramsReceivedErrors: Long
) : MacOsUdpStats
