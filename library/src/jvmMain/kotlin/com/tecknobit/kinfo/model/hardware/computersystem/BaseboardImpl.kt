package com.tecknobit.kinfo.model.hardware.computersystem

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Baseboard

/**
 * Implementation of the `Baseboard` interface.
 * This class provides details about the baseboard (also known as motherboard) of the system,
 * including the manufacturer, model, version, and serial number.
 *
 * @param manufacturer The manufacturer of the baseboard (e.g., ASUS, Gigabyte)
 * @param model The model name or number of the baseboard
 * @param version The version of the baseboard
 * @param serialNumber The serial number of the baseboard
 *
 * @author N7ghtm4r3
 *
 * @see Baseboard
 */
class BaseboardImpl(
    override val manufacturer: String,
    override val model: String,
    override val version: String,
    override val serialNumber: String
) : Baseboard
