package com.tecknobit.kinfo.model.hardware.computersystem

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Firmware

/**
 * Implementation of the `Firmware` interface.
 * This class provides details about the firmware, including its manufacturer, name, description, version, and release date.
 *
 * @param manufacturer The manufacturer of the firmware
 * @param name The name of the firmware
 * @param description A description of the firmware
 * @param version The version of the firmware
 * @param releaseDate The release date of the firmware
 *
 * @author N7ghtm4r3
 *
 * @see Firmware
 */
class FirmwareImpl(
    override val manufacturer: String,
    override val name: String,
    override val description: String,
    override val version: String,
    override val releaseDate: String
) : Firmware
