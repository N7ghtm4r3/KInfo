package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.IfOperStatus
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsNetworkIF

/**
 * The `MacOsNetworkIFImpl` class is useful to hold a macOS network interface snapshot
 *
 * Native mapping supplies cumulative counters and pairs each IP address with its corresponding prefix length
 * Array properties retain their supplied references and participate in equality and hashing by content
 *
 * @property name The BSD interface name, or an empty string when the native name lookup fails
 * @property index The native network interface index
 * @property displayName The localized interface name, falling back to [name] when unavailable
 * @property ifOperStatus The resolved operational status, or [IfOperStatus.UNKNOWN] when it cannot be determined
 * @property mtu The maximum transmission unit in bytes
 * @property macaddr The hardware address, or an empty string when unavailable
 * @property ipv4addr The assigned numeric IPv4 addresses
 * @property subnetMasks The IPv4 prefix lengths paired with [ipv4addr], or `-1` for absent or noncontiguous netmasks
 * @property ipv6addr The assigned numeric IPv6 addresses, including their scope suffixes when present
 * @property prefixLengths The IPv6 prefix lengths paired with [ipv6addr], or `-1` for absent or noncontiguous netmasks
 * @property ifType The native interface type reported with the routing statistics
 * @property bytesRecv The cumulative number of received bytes reported by the interface
 * @property bytesSent The cumulative number of sent bytes reported by the interface
 * @property packetsRecv The cumulative number of received packets reported by the interface
 * @property packetsSent The cumulative number of sent packets reported by the interface
 * @property inErrors The cumulative number of input errors reported by the interface
 * @property outErrors The cumulative number of output errors reported by the interface
 * @property inDrops The cumulative number of dropped incoming packets reported by the interface
 * @property collisions The cumulative number of collisions reported by the interface
 * @property speed The interface speed reported by the native statistics in bits per second
 * @property timestamp The mapping timestamp in milliseconds since the Unix epoch
 * @property isKnownVmMacAddr The result of matching the MAC address against known virtual-machine prefixes
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsNetworkIF
 *
 * @since 1.1.0
 */
data class MacOsNetworkIFImpl(
    override val name: String,
    override val index: Int,
    override val displayName: String,
    override val ifOperStatus: IfOperStatus,
    override val mtu: Long,
    override val macaddr: String,
    override val ipv4addr: Array<String>,
    override val subnetMasks: Array<Short>,
    override val ipv6addr: Array<String>,
    override val prefixLengths: Array<Short>,
    override val ifType: Int,
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
    override val isKnownVmMacAddr: Boolean
) : MacOsNetworkIF {

    /**
     * `ifAlias` the empty fallback used because this implementation does not resolve an interface alias
     */
    override val ifAlias: String = ""

    /**
     * `isConnectorPresent` the fixed `false` fallback used because connector presence is not queried
     */
    override val isConnectorPresent: Boolean = false

    /**
     * `ndisPhysicalMediumType` the fixed `0` fallback for the NDIS-specific property in this macOS implementation
     */
    override val ndisPhysicalMediumType: Int = 0

    /**
     * `updateAttributes` the fixed `false` value indicating that this snapshot does not refresh its native attributes
     */
    override val updateAttributes: Boolean = false

    /**
     * Method used to compare this network interface snapshot with another value
     *
     * Equality includes all properties, including the timestamp, and compares address and prefix arrays by content
     *
     * @param other The value to compare with this snapshot
     *
     * @return whether the other value is a [MacOsNetworkIFImpl] with matching properties as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsNetworkIFImpl

        if (index != other.index) return false
        if (mtu != other.mtu) return false
        if (ifType != other.ifType) return false
        if (ndisPhysicalMediumType != other.ndisPhysicalMediumType) return false
        if (isConnectorPresent != other.isConnectorPresent) return false
        if (bytesRecv != other.bytesRecv) return false
        if (bytesSent != other.bytesSent) return false
        if (packetsRecv != other.packetsRecv) return false
        if (packetsSent != other.packetsSent) return false
        if (inErrors != other.inErrors) return false
        if (outErrors != other.outErrors) return false
        if (inDrops != other.inDrops) return false
        if (collisions != other.collisions) return false
        if (speed != other.speed) return false
        if (timestamp != other.timestamp) return false
        if (isKnownVmMacAddr != other.isKnownVmMacAddr) return false
        if (updateAttributes != other.updateAttributes) return false
        if (name != other.name) return false
        if (displayName != other.displayName) return false
        if (ifAlias != other.ifAlias) return false
        if (ifOperStatus != other.ifOperStatus) return false
        if (macaddr != other.macaddr) return false
        if (!ipv4addr.contentEquals(other.ipv4addr)) return false
        if (!subnetMasks.contentEquals(other.subnetMasks)) return false
        if (!ipv6addr.contentEquals(other.ipv6addr)) return false
        if (!prefixLengths.contentEquals(other.prefixLengths)) return false

        return true
    }

    /**
     * Method used to compute the hash code of this network interface snapshot
     *
     * Address and prefix arrays contribute their content hashes to match [equals]
     *
     * @return the hash code derived from the snapshot properties as [Int]
     */
    override fun hashCode(): Int {
        var result = index
        result = 31 * result + mtu.hashCode()
        result = 31 * result + ifType
        result = 31 * result + ndisPhysicalMediumType
        result = 31 * result + isConnectorPresent.hashCode()
        result = 31 * result + bytesRecv.hashCode()
        result = 31 * result + bytesSent.hashCode()
        result = 31 * result + packetsRecv.hashCode()
        result = 31 * result + packetsSent.hashCode()
        result = 31 * result + inErrors.hashCode()
        result = 31 * result + outErrors.hashCode()
        result = 31 * result + inDrops.hashCode()
        result = 31 * result + collisions.hashCode()
        result = 31 * result + speed.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + isKnownVmMacAddr.hashCode()
        result = 31 * result + updateAttributes.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + ifAlias.hashCode()
        result = 31 * result + ifOperStatus.hashCode()
        result = 31 * result + macaddr.hashCode()
        result = 31 * result + ipv4addr.contentHashCode()
        result = 31 * result + subnetMasks.contentHashCode()
        result = 31 * result + ipv6addr.contentHashCode()
        result = 31 * result + prefixLengths.contentHashCode()
        return result
    }
}