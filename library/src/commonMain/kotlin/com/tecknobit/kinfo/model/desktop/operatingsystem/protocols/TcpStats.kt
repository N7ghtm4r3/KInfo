package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `TcpStats` Represents statistics related to TCP connections, including the number of connections,
 * the segments sent and received, and error counts
 *
 * @author N7ghtm4r3
 */
interface TcpStats {

    /**
     * `connectionsEstablished` The number of established TCP connections
     */
    val connectionsEstablished: Long

    /**
     * `connectionsActive` The number of active (currently open) TCP connections
     */
    val connectionsActive: Long

    /**
     * `connectionsPassive` The number of passive (waiting for incoming requests) TCP connections
     */
    val connectionsPassive: Long

    /**
     * `connectionFailures` The number of failed attempts to establish a TCP connection
     */
    val connectionFailures: Long

    /**
     * `connectionsReset` The number of TCP connections that have been reset
     */
    val connectionsReset: Long

    /**
     * `segmentsSent` The number of TCP segments sent
     */
    val segmentsSent: Long

    /**
     * `segmentsReceived` The number of TCP segments received
     */
    val segmentsReceived: Long

    /**
     * `segmentsRetransmitted` The number of TCP segments that have been retransmitted
     */
    val segmentsRetransmitted: Long

    /**
     * `inErrors` The number of incoming errors (e.g., malformed packets)
     */
    val inErrors: Long

    /**
     * `outResets` The number of outgoing reset signals (e.g., RST flags)
     */
    val outResets: Long

}
