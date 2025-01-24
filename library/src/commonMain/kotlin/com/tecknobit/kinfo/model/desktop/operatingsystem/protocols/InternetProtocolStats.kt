package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `InternetProtocolStats` Provides the statistics of the network protocol including TCP and UDP statistics
 * for both IPv4 and IPv6, as well as the list of active IP connections.
 *
 * @author N7ghtm4r3
 *
 * @see InternetProtocolStats
 */
interface InternetProtocolStats {

    /**
     * `tcpV4Stats` The TCP statistics for IPv4 protocol, including information about connections, retransmissions, etc.
     */
    val tcpV4Stats: TcpStats

    /**
     * `tcpV6Stats` The TCP statistics for IPv6 protocol, including information about connections, retransmissions, etc.
     */
    val tcpV6Stats: TcpStats

    /**
     * `udpV4Stats` The UDP statistics for IPv4 protocol, including information about datagrams sent and received, etc.
     */
    val udpV4Stats: UdpStats

    /**
     * `udpV6Stats` The UDP statistics for IPv6 protocol, including information about datagrams sent and received, etc.
     */
    val udpV6Stats: UdpStats

    /**
     * `ipConnections` A list of active IP connections, each representing a current network connection with its details.
     */
    val ipConnections: List<IPConnection>
}
