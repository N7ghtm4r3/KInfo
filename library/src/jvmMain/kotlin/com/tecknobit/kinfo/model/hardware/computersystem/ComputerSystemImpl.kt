package com.tecknobit.kinfo.model.hardware.computersystem

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Baseboard
import com.tecknobit.kinfo.model.desktop.hardware.computersystem.ComputerSystem
import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Firmware

/**
 * Implementation of the `ComputerSystem` interface.
 * This class provides the system's details, such as manufacturer, model, serial number, hardware UUID,
 * firmware, and baseboard information.
 *
 * @param computerSystemInfo the system hardware information from OSHI
 *
 * @see ComputerSystem
 */
class ComputerSystemImpl(
    val computerSystemInfo: oshi.hardware.ComputerSystem
) : ComputerSystem {

    /**
     * `manufacturer` provides the manufacturer of the computer system.
     * This information is fetched from the system hardware info.
     */
    override val manufacturer: String = computerSystemInfo.manufacturer

    /**
     * `model` provides the model of the computer system.
     * This information is fetched from the system hardware info.
     */
    override val model: String = computerSystemInfo.model

    /**
     * `serialNumber` provides the serial number of the computer system.
     * This information is fetched from the system hardware info.
     */
    override val serialNumber: String = computerSystemInfo.serialNumber

    /**
     * `hardwareUUID` provides the unique hardware identifier for the system.
     * This information is fetched from the system hardware info.
     */
    override val hardwareUUID: String = computerSystemInfo.hardwareUUID

    /**
     * `firmware` returns the firmware details for the system by initializing a `FirmwareImpl` object.
     * This includes the firmware manufacturer, name, version, description, and release date.
     */
    override val firmware: Firmware
        get() = initFirmware(
            source = computerSystemInfo.firmware
        )

    /**
     * `baseboard` returns the baseboard (motherboard) details of the system by initializing a `BaseboardImpl` object.
     * This includes baseboard manufacturer, model, version, and serial number.
     */
    override val baseboard: Baseboard
        get() = initBaseBoard(
            source = computerSystemInfo.baseboard
        )

    /**
     * Initializes a `FirmwareImpl` object using the given firmware data
     *
     * @return the initialized firmware as [Firmware]
     */
    private fun initFirmware(
        source: oshi.hardware.Firmware
    ): Firmware {
        return FirmwareImpl(
            manufacturer = source.manufacturer,
            name = source.name,
            description = source.description,
            version = source.version,
            releaseDate = source.releaseDate
        )
    }

    /**
     * Initializes a `BaseboardImpl` object using the given baseboard data
     *
     * @return the initialized baseboard as [Baseboard]
     */
    private fun initBaseBoard(
        source: oshi.hardware.Baseboard
    ): Baseboard {
        return BaseboardImpl(
            manufacturer = source.manufacturer,
            model = source.model,
            version = source.version,
            serialNumber = source.serialNumber
        )
    }

}
