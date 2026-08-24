package com.tecknobit.kinfo.model.hardware.display

import com.tecknobit.kinfo.model.desktop.hardware.display.DisplayInfo

/**
 * The `DisplayInfoImpl` data class is useful to store the decoded identification information of a display
 *
 * @property edid The raw or synthesized Extended Display Identification Data of the display
 * @property isEdidSynthetic Whether the [edid] value was synthesized from the reported display attributes
 * @property manufacturerID The identifier of the display manufacturer
 * @property productID The identifier of the display product
 * @property serialNo The numeric serial number encoded in the display identification data
 * @property week The week of manufacture of the display
 * @property year The year of manufacture of the display
 * @property version The version of the display identification data
 * @property isDigital Whether the display uses a digital input
 * @property hcm The horizontal physical size of the display in centimeters
 * @property vcm The vertical physical size of the display in centimeters
 * @property preferredResolution The preferred resolution reported by the display
 * @property model The model name reported by the display
 * @property productSerialNumber The product serial number descriptor reported by the display
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see DisplayInfo
 *
 * @since 1.1.0
 */
data class DisplayInfoImpl(
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
) : DisplayInfo {

    /**
     * Method used to check whether another object contains the same display information
     *
     * @param other The object to compare with this instance
     *
     * @return whether the objects contain the same display information as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DisplayInfoImpl

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
     * Method used to compute the hash code from the display information
     *
     * @return the computed hash code as [Int]
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