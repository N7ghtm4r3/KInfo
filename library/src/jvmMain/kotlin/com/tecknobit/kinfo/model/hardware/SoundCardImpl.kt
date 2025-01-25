package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.SoundCard

/**
 * Implementation of the `SoundCard` interface.
 * This class provides the details of a sound card, including the driver version, name, and codec.
 *
 * @param driverVersion The version of the sound card's driver.
 * @param name The name of the sound card (e.g., "Realtek High Definition Audio").
 * @param codec The codec used by the sound card (e.g., "Realtek ALC1220").
 *
 * @author N7ghtm4r3
 *
 * @see SoundCard
 */
class SoundCardImpl(
    override val driverVersion: String,
    override val name: String,
    override val codec: String
) : SoundCard
