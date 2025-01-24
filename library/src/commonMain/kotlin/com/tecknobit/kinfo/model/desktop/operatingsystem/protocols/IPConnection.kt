package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `IPConnection` Represents an active IP connection, providing details about the connection's
 * state, local and foreign addresses, ports, and associated process information.
 *
 * @author N7ghtm4r3
 *
 * @see IPConnection
 */
interface IPConnection {

    /**
     * `type` The type of the connection (e.g., TCP, UDP).
     */
    val type: String

    /**
     * `localAddress` The local IP address of the connection as a byte array.
     */
    val localAddress: ByteArray

    /**
     * `localPort` The local port number of the connection.
     */
    val localPort: Int

    /**
     * `foreignAddress` The foreign IP address (remote) of the connection as a byte array.
     */
    val foreignAddress: ByteArray

    /**
     * `foreignPort` The foreign port number (remote) of the connection.
     */
    val foreignPort: Int

    /**
     * `state` The current state of the connection (e.g., ESTABLISHED, LISTENING).
     */
    val state: TcpState

    /**
     * `transmitQueue` The length of the transmit queue for the connection.
     */
    val transmitQueue: Int

    /**
     * `receiveQueue` The length of the receipt queue for the connection.
     */
    val receiveQueue: Int

    /**
     * `owningProcessId` The process ID of the process that owns the connection.
     */
    val owningProcessId: Int

}
