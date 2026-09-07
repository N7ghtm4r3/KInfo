package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsDisplayInfo

data class MacOsDisplayInfoImpl(
    override val edid: ByteArray,
    override val isEdidSynthetic: Boolean,
    override val manufacturerID: String,
    override val productID: String,
    override val serialNo: String,
    override val week: Byte,
    override val year: Int,
    override val version: String,
    override val isDigital: Boolean,
    override val hcm: Int,
    override val vcm: Int,
    override val preferredResolution: String,
    override val model: String,
    override val productSerialNumber: String
) : MacOsDisplayInfo {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsDisplayInfoImpl

        if (isEdidSynthetic != other.isEdidSynthetic) return false
        if (week != other.week) return false
        if (year != other.year) return false
        if (isDigital != other.isDigital) return false
        if (hcm != other.hcm) return false
        if (vcm != other.vcm) return false
        if (!edid.contentEquals(other.edid)) return false
        if (manufacturerID != other.manufacturerID) return false
        if (productID != other.productID) return false
        if (serialNo != other.serialNo) return false
        if (version != other.version) return false
        if (preferredResolution != other.preferredResolution) return false
        if (model != other.model) return false
        if (productSerialNumber != other.productSerialNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isEdidSynthetic.hashCode()
        result = 31 * result + week
        result = 31 * result + year
        result = 31 * result + isDigital.hashCode()
        result = 31 * result + hcm
        result = 31 * result + vcm
        result = 31 * result + edid.contentHashCode()
        result = 31 * result + manufacturerID.hashCode()
        result = 31 * result + productID.hashCode()
        result = 31 * result + serialNo.hashCode()
        result = 31 * result + version.hashCode()
        result = 31 * result + preferredResolution.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + productSerialNumber.hashCode()
        return result
    }
}
