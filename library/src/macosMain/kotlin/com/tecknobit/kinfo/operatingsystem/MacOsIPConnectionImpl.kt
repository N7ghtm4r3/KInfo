package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpState
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsIPConnection

/**
 * The `MacOsIPConnectionImpl` class is useful to provide the details of an `IP` connection on macOS
 *
 * @property type The protocol type of the connection
 * @property localAddress The local `IP` address of the connection as raw bytes
 * @property localPort The local port of the connection
 * @property foreignAddress The foreign `IP` address of the connection as raw bytes
 * @property foreignPort The foreign port of the connection
 * @property state The current `TCP` state, or `null` when the connection does not use `TCP`
 * @property transmitQueue The number of complete connections in the socket queue
 * @property receiveQueue The number of incomplete connections in the socket queue
 * @property owningProcessId The identifier of the process owning the connection
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsIPConnection
 *
 * @since 1.1.0
 */
data class MacOsIPConnectionImpl(
    override val type: String,
    override val localAddress: ByteArray,
    override val localPort: Int,
    override val foreignAddress: ByteArray,
    override val foreignPort: Int,
    override val state: TcpState?,
    override val transmitQueue: Int,
    override val receiveQueue: Int,
    override val owningProcessId: Int
) : MacOsIPConnection {

    /**
     * Method used to check whether this connection has the same details as [other]
     *
     * @param other The instance to compare with this connection
     *
     * @return whether the instances have the same connection details as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsIPConnectionImpl

        if (localPort != other.localPort) return false
        if (foreignPort != other.foreignPort) return false
        if (transmitQueue != other.transmitQueue) return false
        if (receiveQueue != other.receiveQueue) return false
        if (owningProcessId != other.owningProcessId) return false
        if (type != other.type) return false
        if (!localAddress.contentEquals(other.localAddress)) return false
        if (!foreignAddress.contentEquals(other.foreignAddress)) return false
        if (state != other.state) return false

        return true
    }

    /**
     * Method used to calculate the hash code from the connection details
     *
     * @return the calculated hash code as [Int]
     */
    override fun hashCode(): Int {
        var result = localPort
        result = 31 * result + foreignPort
        result = 31 * result + transmitQueue
        result = 31 * result + receiveQueue
        result = 31 * result + owningProcessId
        result = 31 * result + type.hashCode()
        result = 31 * result + localAddress.contentHashCode()
        result = 31 * result + foreignAddress.contentHashCode()
        result = 31 * result + (state?.hashCode() ?: 0)
        return result
    }

}