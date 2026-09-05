package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsTcpStats

/**
 * The `MacOsTcpStatsImpl` class is useful to provide the cumulative `TCP` statistics read from the
 * macOS `net.inet.tcp.stats` system control
 *
 * The native `connectionsEstablished` variant is the `tcps_connects` counter of all successful
 * connection establishments, not a snapshot of sockets currently established. The native `outResets`
 * variant is the `tcps_sndctrl` counter of sent `SYN`, `FIN`, and `RST` control segments, not a strict
 * count of outgoing reset segments
 *
 * @property connectionsEstablished The total number of successful connection establishments
 * @property connectionsActive The total number of active connection attempts
 * @property connectionsPassive The total number of accepted passive connections
 * @property connectionFailures The total number of embryonic connections dropped
 * @property connectionsReset The total number of established connections dropped
 * @property segmentsSent The total number of data segments sent
 * @property segmentsReceived The total number of data segments received in sequence
 * @property segmentsRetransmitted The total number of data segments retransmitted
 * @property inErrors The total number of packets rejected for checksum, offset, memory, or length errors
 * @property outResets The total number of `SYN`, `FIN`, and `RST` control segments sent
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsTcpStats
 *
 * @since 1.1.0
 */
data class MacOsTcpStatsImpl(
    override val connectionsEstablished: Long,
    override val connectionsActive: Long,
    override val connectionsPassive: Long,
    override val connectionFailures: Long,
    override val connectionsReset: Long,
    override val segmentsSent: Long,
    override val segmentsReceived: Long,
    override val segmentsRetransmitted: Long,
    override val inErrors: Long,
    override val outResets: Long
) : MacOsTcpStats
