package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpState

/**
 * `IPConnectionImpl` Implementation of the `IPConnection` interface.
 * This class represents an active IP connection, providing details about the connection's
 * state, local and foreign addresses, ports, and associated process information.
 *
 * @param type The type of the connection (e.g., TCP, UDP).
 * @param localAddress The local IP address of the connection as a byte array.
 * @param localPort The local port number of the connection.
 * @param foreignAddress The foreign IP address (remote) of the connection as a byte array.
 * @param foreignPort The foreign port number (remote) of the connection.
 * @param state The current state of the connection (e.g., ESTABLISHED, LISTENING).
 * @param transmitQueue The length of the transmit queue for the connection.
 * @param receiveQueue The length of the receive queue for the connection.
 * @param owningProcessId The process ID of the process that owns the connection.
 *
 * @author N7ghtm4r3
 *
 * @see IPConnection
 */
class IPConnectionImpl(
    override val type: String,
    override val localAddress: ByteArray,
    override val localPort: Int,
    override val foreignAddress: ByteArray,
    override val foreignPort: Int,
    override val state: TcpState,
    override val transmitQueue: Int,
    override val receiveQueue: Int,
    override val owningProcessId: Int
) : IPConnection
