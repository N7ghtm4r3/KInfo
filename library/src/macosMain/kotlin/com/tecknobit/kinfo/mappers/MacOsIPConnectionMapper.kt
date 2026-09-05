@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpState
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpState.*
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.SocketInfoProtocol
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.SocketInfoProtocol.*
import com.tecknobit.kinfo.operatingsystem.MacOsIPConnectionImpl
import kotlinx.cinterop.*
import platform.osx.*

/**
 * The `MacOsIPConnectionsMapper` class is useful to map native macOS socket information to KInfo connection models
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
class MacOsIPConnectionsMapper : NativeMapper<List<MacOsIPConnectionImpl>>() {

    /**
     * Method used to map the accessible native macOS sockets to their [MacOsIPConnectionImpl] models
     *
     * @return the mapped macOS connections as [List] of [MacOsIPConnectionImpl]
     */
    override fun mapFromNative(): List<MacOsIPConnectionImpl> {
        val macOsConnections = mutableListOf<MacOsIPConnectionImpl>()
        val pids = getPids()

        pids.forEach { pid ->
            val descriptors = resolveSocketDescriptors(
                pid = pid
            )

            descriptors.forEach { descriptor ->
                val macOsIPConnectionImpl = loadSocketInfo(
                    pid = pid,
                    fileDescriptor = descriptor
                )?: return@forEach

                macOsConnections.add(macOsIPConnectionImpl)
            }
        }

        return macOsConnections
    }

    /**
     * Method used to retrieve the identifiers of the processes currently available on macOS
     *
     * @return the available process identifiers as [List] of [Int]
     */
    private fun getPids(): List<Int> {
        return memScoped {
            val pidsCount = proc_listallpids(null, 0)
            if (pidsCount <= 0)
                return@memScoped emptyList()

            val pidsBuffer = allocArray<IntVar>(pidsCount)
            val pidsSize = (pidsCount * sizeOf<IntVar>()).toInt()
            val result = proc_listallpids(pidsBuffer, pidsSize)
            if(result <= 0)
                return@memScoped emptyList()

            val pids = List(result) { index ->
                pidsBuffer[index]
            }

            return pids.filter { pid ->
                pid > 0
            }
        }
    }

    /**
     * Method used to resolve the socket file descriptors owned by a process
     *
     * @param pid The identifier of the process to inspect
     *
     * @return the socket file descriptors as [List] of [Int]
     */
    @Resolver
    private fun resolveSocketDescriptors(
        pid: Int
    ): List<Int> {
        val socketDescriptors = resolveDescriptors(
            pid = pid
        ).filter { descriptor ->
            descriptor.type == PROX_FDTYPE_SOCKET.toUInt()
        }

        return socketDescriptors.map { it.fileDescriptor }
    }

    /**
     * Method used to resolve the file descriptors owned by a process
     *
     * @param pid The identifier of the process to inspect
     *
     * @return the resolved file descriptors as [List] of [Descriptor]
     */
    @Resolver
    private fun resolveDescriptors(
        pid: Int
    ): List<Descriptor> {
        return memScoped {
            val requiredBytes = proc_pidinfo(
                pid,
                PROC_PIDLISTFDS,
                0uL,
                null,
                0
            )
            if(requiredBytes <= 0)
                return@memScoped emptyList()

            val descriptorSize = sizeOf<proc_fdinfo>().toInt()
            val capacity = requiredBytes / descriptorSize
            val buffer = allocArray<proc_fdinfo>(capacity)

            val writtenBytes = proc_pidinfo(
                pid,
                PROC_PIDLISTFDS,
                0uL,
                buffer,
                requiredBytes
            )
            if (writtenBytes <= 0)
                return@memScoped emptyList()

            val descriptorCount = writtenBytes / descriptorSize
            List(descriptorCount) { index ->
                val bufferElement = buffer[index]

                Descriptor(
                    fileDescriptor = bufferElement.proc_fd,
                    type = bufferElement.proc_fdtype
                )
            }
        }
    }

    /**
     * Method used to load and map the native information of a socket file descriptor
     *
     * @param pid The identifier of the process owning the socket
     * @param fileDescriptor The socket file descriptor to load
     *
     * @return the mapped connection, or `null` when its information is unavailable, as [MacOsIPConnectionImpl]
     */
    @Loader
    private fun loadSocketInfo(
        pid: Int,
        fileDescriptor: Int
    ): MacOsIPConnectionImpl? {
        return resolveSocketInfo(
            pid = pid,
            fileDescriptor = fileDescriptor,
            map = { socketInfo ->
                mapSocketInfo(
                    pid = pid,
                    socketInfo = socketInfo
                )
            }
        )
    }

    /**
     * Method used to resolve native socket information within the lifetime of its native memory scope
     *
     * @param pid The identifier of the process owning the socket
     * @param fileDescriptor The socket file descriptor to resolve
     * @param map The transformation applied to the resolved native socket information
     *
     * @return the mapped connection, or `null` when the socket information is unavailable, as [MacOsIPConnectionImpl]
     */
    private inline fun resolveSocketInfo(
        pid: Int,
        fileDescriptor: Int,
        map: (socket_info) -> MacOsIPConnectionImpl?
    ): MacOsIPConnectionImpl? {
        return memScoped {
            val buffer = alloc<socket_fdinfo>()
            val bufferSize = sizeOf<socket_fdinfo>().toInt()

            val result = proc_pidfdinfo(
                pid,
                fileDescriptor,
                PROC_PIDFDSOCKETINFO,
                buffer.ptr,
                bufferSize
            )
            if(result != bufferSize)
                return@memScoped null

            map(buffer.psi)
        }
    }

    /**
     * Method used to map native socket information to a macOS connection model
     *
     * @param pid The identifier of the process owning the socket
     * @param socketInfo The native socket information to map
     *
     * @return the mapped connection, or `null` when the socket has no valid `IP` information, as
     * [MacOsIPConnectionImpl]
     */
    private fun mapSocketInfo(
        pid: Int,
        socketInfo: socket_info
    ): MacOsIPConnectionImpl? {
        val ipInfo = socketInfo.resolveIpInfo() ?: return null
        val protocol = socketInfo.soi_protocol
            .toProtocol()
        val localAddress = ipInfo.toLocalAddress() ?: return null

        return MacOsIPConnectionImpl(
            type = protocol.name,
            localAddress = localAddress,
            localPort = ipInfo
                .insi_lport
                .networkToHostPort(),
            foreignAddress = ipInfo.toForeignAddress(),
            foreignPort = ipInfo
                .insi_fport
                .networkToHostPort(),
            state = if(protocol == IPPROTO_TCP) {
                socketInfo.soi_proto
                    .pri_tcp
                    .tcpsi_state
                    .toTcpState()
            } else
                null,
            transmitQueue = socketInfo
                .soi_qlen
                .toInt(),
            receiveQueue = socketInfo
                .soi_incqlen
                .toInt(),
            owningProcessId = pid
        )
    }

    /**
     * Method used to resolve the protocol-specific internet information of a native socket
     *
     * @receiver The native socket information to inspect
     *
     * @return the internet socket information, or `null` for an unsupported socket kind, as [in_sockinfo]
     */
    private fun socket_info.resolveIpInfo(): in_sockinfo? {
        return when (soi_kind.toUInt()) {
            SOCKINFO_TCP -> soi_proto.pri_tcp.tcpsi_ini
            SOCKINFO_IN -> soi_proto.pri_in

            else -> null
        }
    }

    /**
     * Method used to convert a port from network byte order to host byte order
     *
     * @receiver The port encoded in network byte order
     *
     * @return the converted port as [Int]
     */
    private fun Int.networkToHostPort(): Int {
        val value = this and 0xffff

        return ((value and 0xff) shl 8) or
                ((value ushr 8) and 0xff)
    }

    /**
     * Method used to map a native internet protocol number to its [SocketInfoProtocol]
     *
     * @receiver The native internet protocol number to map
     *
     * @return the mapped socket protocol as [SocketInfoProtocol]
     */
    private fun Int.toProtocol(): SocketInfoProtocol {
        return when(this) {
            0 -> IPPROTO_IP
            1 -> IPPROTO_ICMP
            2 -> IPPROTO_IGMP
            6 -> IPPROTO_TCP
            17 -> IPPROTO_UDP
            41 -> IPPROTO_IPV6
            47 -> IPPROTO_GRE
            50 -> IPPROTO_ESP
            51 -> IPPROTO_AH
            58 -> IPPROTO_ICMPV6
            132 -> IPPROTO_SCTP

            else -> IPPROTO_RAW
        }
    }

    /**
     * Method used to copy the local `IPv4` or `IPv6` address from native internet socket information
     *
     * @receiver The native internet socket information containing the local address
     *
     * @return the local address as [ByteArray], or `null` when no valid `IP` version is available
     */
    private fun in_sockinfo.toLocalAddress(): ByteArray? {
        val insiFlag = insi_vflag.toInt()

        return when {
            (insiFlag and INI_IPV4 != 0) -> insi_laddr
                .ina_46
                .i46a_addr4
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(4)

            (insiFlag and INI_IPV6 != 0) -> insi_laddr
                .ina_6
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(16)

            else -> null
        }
    }

    /**
     * Method used to copy the foreign `IPv4` or `IPv6` address from native internet socket information
     *
     * @receiver The native internet socket information containing the foreign address
     *
     * @return the foreign address as [ByteArray]
     */
    private fun in_sockinfo.toForeignAddress(): ByteArray {
        val insiFlag = insi_vflag.toInt()

        return when {
            (insiFlag and INI_IPV4 != 0) -> insi_faddr
                .ina_46
                .i46a_addr4
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(4)

            (insiFlag and INI_IPV6 != 0) -> insi_faddr
                .ina_6
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(16)

            else -> byteArrayOf()
        }
    }

    /**
     * Method used to map a native `TCP` state value to its [TcpState]
     *
     * @receiver The native `TCP` state value to map
     *
     * @return the mapped `TCP` state as [TcpState]
     */
    private fun Int.toTcpState(): TcpState {
        return when (this) {
            0 -> CLOSED
            1 -> LISTEN
            2 -> SYN_SENT
            3 -> SYN_RECV
            4 -> ESTABLISHED
            5 -> CLOSE_WAIT
            6 -> FIN_WAIT_1
            7 -> CLOSING
            8 -> LAST_ACK
            9 -> FIN_WAIT_2
            10 -> TIME_WAIT

            else -> UNKNOWN
        }
    }

}

/**
 * The `Descriptor` class is useful to collect a native file descriptor and its type
 *
 * @property fileDescriptor The numeric file descriptor
 * @property type The native type of the file descriptor
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class Descriptor(
    val fileDescriptor: Int,
    val type: UInt
)