package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsDisplayInfo

/**
 * The `MacOsDisplayInfoImpl` class is useful to hold decoded macOS display identification information
 *
 * Equality and hashing compare the contents of [edid] together with all decoded properties
 *
 * @property edid The native or synthesized Extended Display Identification Data
 * @property isEdidSynthetic Whether [edid] was synthesized from native display attributes
 * @property manufacturerID The decoded three-letter manufacturer identifier, or an empty string when invalid
 * @property productID The product identifier formatted as a hexadecimal string
 * @property serialNo The numeric serial bytes formatted as characters or hexadecimal pairs
 * @property week The raw manufacture-week byte, with unsigned `255` represented as `-1`
 * @property year The manufacture year, with `1990` as the built-in display fallback
 * @property version The identification data version in `major.revision` format
 * @property isDigital Whether the display input is digital
 * @property hcm The horizontal physical size in centimeters, or zero when unavailable
 * @property vcm The vertical physical size in centimeters, or zero when unavailable
 * @property preferredResolution The preferred or built-in native resolution in `widthxheight` format, or an empty string
 * @property model The display name, or `unknown` when unavailable
 * @property productSerialNumber The alphanumeric serial descriptor or native attribute, or an empty string when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsDisplayInfo
 *
 * @since 1.1.0
 */
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

    /**
     * Method used to compare the display identification properties and the contents of [edid]
     *
     * @param other The object to compare with this display information
     *
     * @return whether both objects have the same type and property values as [Boolean]
     */
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

    /**
     * Method used to compute a hash from the display identification properties and the contents of [edid]
     *
     * @return the hash of the display information as [Int]
     */
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
