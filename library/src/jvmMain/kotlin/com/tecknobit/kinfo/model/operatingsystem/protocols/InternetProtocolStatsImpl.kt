package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.*

/**
 * `InternetProtocolStatsImpl` Implements the `InternetProtocolStats` interface to provide details about
 * the network protocol statistics, including TCP and UDP statistics for both IPv4 and IPv6,
 * as well as active IP connections.
 *
 * @param internetProtocolStatsInfo The source object that contains the raw protocol stats.
 *
 * @author N7ghtm4r3
 *
 * @see InternetProtocolStats
 */
class InternetProtocolStatsImpl(
    private val internetProtocolStatsInfo: oshi.software.os.InternetProtocolStats,
) : InternetProtocolStats {

    /**
     * `tcpV4Stats` Returns the TCP statistics for the IPv4 protocol, including established, active,
     * passive connections, and other metrics like sent/received segments.
     */
    override val tcpV4Stats: TcpStats
        get() = initTcpStats(
            source = internetProtocolStatsInfo.tcPv4Stats
        )

    /**
     * `tcpV6Stats` Returns the TCP statistics for the IPv6 protocol, including established, active,
     * passive connections, and other metrics like sent/received segments.
     */
    override val tcpV6Stats: TcpStats
        get() = initTcpStats(
            source = internetProtocolStatsInfo.tcPv6Stats
        )

    /**
     * `udpV4Stats` Returns the UDP statistics for the IPv4 protocol, including sent/received datagrams,
     * and other related metrics.
     */
    override val udpV4Stats: UdpStats
        get() = initUdpStats(
            source = internetProtocolStatsInfo.udPv4Stats
        )

    /**
     * `udpV6Stats` Returns the UDP statistics for the IPv6 protocol, including sent/received datagrams,
     * and other related metrics.
     */
    override val udpV6Stats: UdpStats
        get() = initUdpStats(
            source = internetProtocolStatsInfo.udPv6Stats
        )

    /**
     * `ipConnections` A list of active IP connections, including local and foreign addresses, ports,
     * and their respective states.
     */
    override val ipConnections: List<IPConnection>
        get() = loadIpConnections(
            sourceList = internetProtocolStatsInfo.connections
        )

    /**
     * Initializes the TCP statistics from the raw source data.
     *
     * @param source The raw TCP stats data from the source.
     * @return A [TcpStats] object containing the processed TCP stats.
     */
    private fun initTcpStats(
        source: oshi.software.os.InternetProtocolStats.TcpStats
    ): TcpStats {
        return TcpStatsImpl(
            connectionsEstablished = source.connectionsEstablished,
            connectionsActive = source.connectionsActive,
            connectionsPassive = source.connectionsPassive,
            connectionFailures = source.connectionFailures,
            connectionsReset = source.connectionsReset,
            segmentsSent = source.segmentsSent,
            segmentsReceived = source.segmentsReceived,
            segmentsRetransmitted = source.segmentsRetransmitted,
            inErrors = source.inErrors,
            outResets = source.outResets
        )
    }

    /**
     * Initializes the UDP statistics from the raw source data.
     *
     * @param source The raw UDP stats data from the source.
     * @return A [UdpStats] object containing the processed UDP stats.
     */
    private fun initUdpStats(
        source: oshi.software.os.InternetProtocolStats.UdpStats
    ): UdpStats {
        return UdpStatsImpl(
            datagramsSent = source.datagramsSent,
            datagramsReceived = source.datagramsReceived,
            datagramsNoPort = source.datagramsNoPort,
            datagramsReceivedErrors = source.datagramsReceivedErrors
        )
    }

    /**
     * Loads the IP connections from the raw source list.
     *
     * @param sourceList The raw list of IP connections.
     * @return A list of [IPConnection] objects representing the active IP connections.
     */
    private fun loadIpConnections(
        sourceList: List<oshi.software.os.InternetProtocolStats.IPConnection>
    ): List<IPConnection> {
        val result = mutableListOf<IPConnection>()
        sourceList.forEach { connection ->
            result.add(
                IPConnectionImpl(
                    type = connection.type,
                    localAddress = connection.localAddress,
                    localPort = connection.localPort,
                    foreignAddress = connection.foreignAddress,
                    foreignPort = connection.foreignPort,
                    state = TcpState.valueOf(connection.state.name),
                    transmitQueue = connection.transmitQueue,
                    receiveQueue = connection.receiveQueue,
                    owningProcessId = connection.getowningProcessId()
                )
            )
        }
        return result
    }
}
