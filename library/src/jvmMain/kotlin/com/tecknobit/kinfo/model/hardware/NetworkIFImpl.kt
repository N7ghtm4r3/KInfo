package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.IfOperStatus
import com.tecknobit.kinfo.model.desktop.hardware.NetworkIF
import java.net.NetworkInterface

/**
 * Implementation of the `NetworkIF` interface.
 * This class provides details about the network interface, including its name, operational status,
 * MAC address, IP addresses, and network statistics like bytes and packets sent/received, errors, and collisions.
 *
 * @param name The name of the network interface (e.g., "eth0", "wlan0")
 * @param index The index of the network interface
 * @param displayName The display name of the network interface
 * @param ifAlias The alias for the network interface (optional)
 * @param ifOperStatus The operational status of the network interface (e.g., up, down)
 * @param mtu The Maximum Transmission Unit (MTU) for the network interface
 * @param macaddr The MAC address of the network interface
 * @param ipv4addr The IPv4 addresses assigned to the network interface
 * @param subnetMasks The subnet masks for the IPv4 addresses
 * @param ipv6addr The IPv6 addresses assigned to the network interface
 * @param prefixLengths The prefix lengths for the IPv6 addresses
 * @param ifType The type of network interface (e.g., Ethernet, Wi-Fi)
 * @param ndisPhysicalMediumType The type of physical medium (e.g., wired, wireless)
 * @param isConnectorPresent Indicates whether a connector is present on the interface
 * @param bytesRecv The total number of bytes received by the interface
 * @param bytesSent The total number of bytes sent by the interface
 * @param packetsRecv The total number of packets received by the interface
 * @param packetsSent The total number of packets sent by the interface
 * @param inErrors The total number of incoming errors on the interface
 * @param outErrors The total number of outgoing errors on the interface
 * @param inDrops The total number of incoming packets dropped by the interface
 * @param collisions The total number of collisions detected on the interface
 * @param speed The speed of the network interface in bits per second
 * @param timestamp The timestamp indicating when the network interface statistics were last updated
 * @param isKnownVmMacAddr Indicates whether the MAC address is known to belong to a virtual machine
 * @param updateAttributes Indicates whether the attributes of the network interface should be updated
 *
 * @author N7ghtm4r3
 *
 * @see NetworkIF
 */
class NetworkIFImpl(
    override val name: String,
    override val index: Int,
    override val displayName: String,
    override val ifAlias: String,
    override val ifOperStatus: IfOperStatus,
    override val mtu: Long,
    override val macaddr: String,
    override val ipv4addr: Array<String>,
    override val subnetMasks: Array<Short>,
    override val ipv6addr: Array<String>,
    override val prefixLengths: Array<Short>,
    override val ifType: Int,
    override val ndisPhysicalMediumType: Int,
    override val isConnectorPresent: Boolean,
    override val bytesRecv: Long,
    override val bytesSent: Long,
    override val packetsRecv: Long,
    override val packetsSent: Long,
    override val inErrors: Long,
    override val outErrors: Long,
    override val inDrops: Long,
    override val collisions: Long,
    override val speed: Long,
    override val timestamp: Long,
    override val isKnownVmMacAddr: Boolean,
    override val updateAttributes: Boolean,
) : NetworkIF {

    /**
     * Method to query the [source] network interface
     *
     * @param source The interface to query
     *
     * @return network interface queried as [NetworkInterface]
     */
    fun queryNetworkInterface(
        source: oshi.hardware.NetworkIF,
    ): NetworkInterface {
        return source.queryNetworkInterface()
    }

}