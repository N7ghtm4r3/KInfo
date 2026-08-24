package com.tecknobit.kinfo.model.desktop.hardware.display

/**
 * The `Display` interface defines the contract to access the identification information of a display device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see DisplayInfo
 */
interface Display {

    /**
     * `edid` the raw or synthesized Extended Display Identification Data of the display
     */
    @Deprecated(
        message = "Deprecated since 1.1.0",
        replaceWith = ReplaceWith(
            "DisplayInfo.edid"
        )
    )
    val edid: ByteArray

    /**
     * `displayInfo` the decoded identification information of the display
     *
     * @since 1.1.0
     */
    val displayInfo: DisplayInfo

}