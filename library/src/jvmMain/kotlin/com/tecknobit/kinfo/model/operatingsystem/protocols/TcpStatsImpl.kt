package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpStats

/**
 * `TcpStatsImpl` Implementation of the `TcpStats` interface, providing statistics related to TCP connections,
 * such as the number of established connections, errors, and sent/received segments.
 *
 * @param connectionsEstablished The number of established TCP connections.
 * @param connectionsActive The number of active (currently open) TCP connections.
 * @param connectionsPassive The number of passive (waiting for incoming requests) TCP connections.
 * @param connectionFailures The number of failed attempts to establish a TCP connection.
 * @param connectionsReset The number of TCP connections that have been reset.
 * @param segmentsSent The number of TCP segments sent.
 * @param segmentsReceived The number of TCP segments received.
 * @param segmentsRetransmitted The number of TCP segments that have been retransmitted.
 * @param inErrors The number of incoming errors (e.g., malformed packets).
 * @param outResets The number of outgoing reset signals (e.g., RST flags).
 *
 * @author N7ghtm4r3
 *
 * @see TcpStats
 */
class TcpStatsImpl(
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
) : TcpStats
