package com.tecknobit.kinfo.model.desktop.common.hardware.display

/**
 * The `Display` interface defines the contract to access the identification and connection information of a display
 * device
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

    /**
     * `displayPort` the platform-specific system device port identifier, or `unknown` when not available
     *
     * @since 1.1.0
     */
    val displayPort: String

    /**
     * `outputName` the X11 output name reported by `xrandr`, or `null` when not available
     *
     * @since 1.1.0
     */
    val outputName: String?

}