package com.tecknobit.kinfo.model.desktop.hardware.computersystem

/**
 * `Baseboard` represents the information of the baseboard (also known as motherboard) of a system.
 * It provides details about the manufacturer, model, version, and serial number of the baseboard.
 * This interface is useful for hardware monitoring and identification tasks.
 *
 * @author N7ghtm4r3
 */
interface Baseboard {

    /**
     * `manufacturer` the manufacturer of the baseboard (e.g., ASUS, Gigabyte)
     */
    val manufacturer: String

    /**
     * `model` the model name or number of the baseboard
     */
    val model: String

    /**
     * `version` the version of the baseboard
     */
    val version: String

    /**
     * `serialNumber` the serial number of the baseboard
     */
    val serialNumber: String

}
