package com.tecknobit.kinfo.model.desktop.hardware.computersystem

/**
 * Represents the firmware of a system.
 * This interface provides details about the firmware, including its manufacturer, name, description, version, and release date.
 * It is useful for hardware monitoring and system diagnostics.
 *
 * @author N7ghtm4r3
 */
interface Firmware {

    /**
     * `manufacturer` represents the manufacturer of the firmware.
     * This could be a company like "Microsoft", "Apple", or "BIOS".
     */
    val manufacturer: String

    /**
     * `name` represents the name of the firmware.
     * This could refer to the specific firmware version or model, such as "BIOS 1.2" or "UEFI".
     */
    val name: String

    /**
     * `description` gives a detailed description of the firmware.
     * This may include information about its functionality, features, and special notes.
     */
    val description: String

    /**
     * `version` represents the version number of the firmware.
     * This typically follows a format like "1.0", "2.5.3", or similar.
     */
    val version: String

    /**
     * `releaseDate` represents the release date of the firmware.
     * This provides the date when the firmware version was officially released.
     */
    val releaseDate: String

}
