package com.tecknobit.kinfo.model.desktop.hardware

/**
 * Represents a network interface on the system
 *
 * @author N7ghtm4r3
 */
interface NetworkIF {

    /**
     * `name` The name of the network interface (e.g., eth0, wlan0)
     */
    val name: String

    /**
     * `index` The index of the network interface
     */
    val index: Int

    /**
     * `displayName` The display name of the network interface
     */
    val displayName: String

    /**
     * `ifAlias` The alias of the network interface, if available
     */
    val ifAlias: String

    /**
     * `ifOperStatus` The operational status of the network interface
     */
    val ifOperStatus: IfOperStatus

    /**
     * `mtu` The maximum transmission unit (MTU) size of the network interface
     */
    val mtu: Long

    /**
     * `macaddr` The MAC address of the network interface
     */
    val macaddr: String

    /**
     * `ipv4addr` The list of IPv4 addresses assigned to the network interface
     */
    val ipv4addr: Array<String>

    /**
     * `subnetMasks` The list of subnet masks for the network interface
     */
    val subnetMasks: Array<Short>

    /**
     * `ipv6addr` The list of IPv6 addresses assigned to the network interface
     */
    val ipv6addr: Array<String>

    /**
     * `prefixLengths` The list of prefix lengths corresponding to the IPv6 addresses
     */
    val prefixLengths: Array<Short>

    /**
     * `ifType` The type of the network interface (e.g., Ethernet, Wi-Fi)
     */
    val ifType: Int

    /**
     * `ndisPhysicalMediumType` The NDIS physical medium type for the network interface
     */
    val ndisPhysicalMediumType: Int

    /**
     * `isConnectorPresent` Whether a connector is present on the network interface
     */
    val isConnectorPresent: Boolean

    /**
     * `bytesRecv` The total number of bytes received by the network interface
     */
    val bytesRecv: Long

    /**
     * `bytesSent` The total number of bytes sent by the network interface
     */
    val bytesSent: Long

    /**
     * `packetsRecv` The total number of packets received by the network interface
     */
    val packetsRecv: Long

    /**
     * `packetsSent` The total number of packets sent by the network interface
     */
    val packetsSent: Long

    /**
     * `inErrors` The total number of input errors on the network interface
     */
    val inErrors: Long

    /**
     * `outErrors` The total number of output errors on the network interface
     */
    val outErrors: Long

    /**
     * `inDrops` The total number of input packets dropped by the network interface
     */
    val inDrops: Long

    /**
     * `collisions` The total number of collisions on the network interface
     */
    val collisions: Long

    /**
     * `speed` The speed of the network interface in bits per second
     */
    val speed: Long

    /**
     * `timestamp` The timestamp of the last update for the network interface
     */
    val timestamp: Long

    /**
     * `isKnownVmMacAddr` Whether the MAC address is known to belong to a virtual machine
     */
    val isKnownVmMacAddr: Boolean

    /**
     * `updateAttributes` Whether the network interface attributes should be updated
     */
    val updateAttributes: Boolean

}

/**
 * The current operational state of a network interface.
 *
 *
 * As described in RFC 2863.
 */
enum class IfOperStatus(
    val value: Int
) {
    /**
     * Up and operational. Ready to pass packets.
     */
    UP(1),

    /**
     * Down and not operational. Not ready to pass packets.
     */
    DOWN(2),

    /**
     * In some test mode.
     */
    TESTING(3),

    /**
     * The interface status is unknown.
     */
    UNKNOWN(4),

    /**
     * The interface is not up, but is in a pending state, waiting for some external event.
     */
    DORMANT(5),

    /**
     * Some component is missing
     */
    NOT_PRESENT(6),

    /**
     * Down due to state of lower-layer interface(s).
     */
    LOWER_LAYER_DOWN(7);

}