package com.tecknobit.kinfo.model.desktop.hardware

/**
 * Represents a sound card in the system.
 * This interface provides details about the sound card's driver version, name, and codec type.
 *
 * @author N7ghtm4r3
 */
interface SoundCard {

    /**
     * `driverVersion` The version of the driver for the sound card
     */
    val driverVersion: String

    /**
     * `name` The name of the sound card (e.g., "Realtek High Definition Audio")
     */
    val name: String

    /**
     * `codec` The codec used by the sound card (e.g., "Realtek ALC1220")
     */
    val codec: String

}
