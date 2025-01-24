package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.Display

/**
 * Implementation of the `Display` interface.
 * This class provides details about a display device, including the Extended Display Identification Data (EDID).
 * EDID contains information about the display's capabilities and characteristics.
 *
 * @param edid The Extended Display Identification Data (EDID) for the display.
 * It is a byte array that stores the capabilities and characteristics of the display device.
 *
 * @author N7ghtm4r3
 *
 * @see Display
 */
class DisplayImpl(
    override val edid: ByteArray
) : Display
