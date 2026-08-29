package com.tecknobit.kinfo.model.hardware.display

import com.tecknobit.kinfo.model.desktop.common.hardware.display.Display
import com.tecknobit.kinfo.model.desktop.common.hardware.display.DisplayInfo

/**
 * The `DisplayImpl` class is useful to store the identification and connection information of a display device
 *
 * @property edid The raw or synthesized Extended Display Identification Data of the display
 * @property displayInfo The decoded identification information of the display
 * @property displayPort The platform-specific system device port identifier, or `unknown` when not available
 * @property outputName The X11 output name reported by `xrandr`, or `null` when not available
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see Display
 * @see DisplayInfo
 *
 * @since 1.1.0
 */
class DisplayImpl(
    @Deprecated(
        message = "Deprecated since 1.1.0",
        replaceWith = ReplaceWith(
            "DisplayInfoImpl.edid"
        )
    )
    override val edid: ByteArray,
    override val displayInfo: DisplayInfo,
    override val displayPort: String,
    override val outputName: String?
) : Display