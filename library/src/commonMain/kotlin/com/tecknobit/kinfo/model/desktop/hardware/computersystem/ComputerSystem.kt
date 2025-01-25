package com.tecknobit.kinfo.model.desktop.hardware.computersystem

/**
 * `ComputerSystem` represents the details of the system hardware, including information
 * about the manufacturer, model, serial number, and UUID of the system.
 * It also provides access to firmware and baseboard information.
 *
 * @author N7ghtm4r3
 */
interface ComputerSystem {

    /**
     * `manufacturer` the manufacturer of the computer system (e.g., Dell, HP)
     */
    val manufacturer: String

    /**
     * `model` the model name or number of the computer system
     */
    val model: String

    /**
     * `serialNumber` the serial number of the computer system
     */
    val serialNumber: String

    /**
     * `hardwareUUID` a unique identifier for the hardware of the system
     */
    val hardwareUUID: String

    /**
     * `firmware` provides the firmware information of the system
     */
    val firmware: Firmware

    /**
     * `baseboard` provides the baseboard (motherboard) details of the system
     */
    val baseboard: Baseboard

}
