package com.tecknobit.kinfo.model.desktop.hardware

/**
 * `Display` represents a display device, such as a monitor or screen.
 * It provides details about the Extended Display Identification Data (EDID) for the display.
 * EDID contains information about the display's capabilities and characteristics.
 *
 * @author N7ghtm4r3
 */
interface Display {

    /**
     * `edid` The Extended Display Identification Data (EDID) for the display.
     * It is a byte array that stores the capabilities and characteristics of the display device.
     */
    val edid: ByteArray
}
