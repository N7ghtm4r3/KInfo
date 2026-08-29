package com.tecknobit.kinfo.model.desktop.common.hardware.display

/**
 * The `DisplayInfo` interface defines the contract to access the decoded identification information of a display
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see Display
 *
 * @since 1.1.0
 */
interface DisplayInfo {

    /**
     * `edid` the raw or synthesized Extended Display Identification Data of the display
     */
    val edid: ByteArray

    /**
     * `isEdidSynthetic` whether the [edid] value was synthesized from the reported display attributes
     */
    val isEdidSynthetic: Boolean

    /**
     * `manufacturerID` the identifier of the display manufacturer
     */
    val manufacturerID: String

    /**
     * `productID` the identifier of the display product
     */
    val productID: String

    /**
     * `serialNo` the numeric serial number encoded in the display identification data
     */
    val serialNo: String

    /**
     * `week` the week of manufacture of the display
     */
    val week: Byte

    /**
     * `year` the year of manufacture of the display
     */
    val year: Int

    /**
     * `version` the version of the display identification data
     */
    val version: String

    /**
     * `isDigital` whether the display uses a digital input
     */
    val isDigital: Boolean

    /**
     * `hcm` the horizontal physical size of the display in centimeters
     */
    val hcm: Int

    /**
     * `vcm` the vertical physical size of the display in centimeters
     */
    val vcm: Int

    /**
     * `preferredResolution` the preferred resolution reported by the display
     */
    val preferredResolution: String

    /**
     * `model` the model name reported by the display
     */
    val model: String

    /**
     * `productSerialNumber` the product serial number descriptor reported by the display
     */
    val productSerialNumber: String

}