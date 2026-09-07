package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.IfOperStatus
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsNetworkIF

data class MacOsNetworkIFImpl(
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
    override val updateAttributes: Boolean
) : MacOsNetworkIF {

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