package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsSoundCard

/**
 * The `MacOsSoundCardImpl` class is useful to store a macOS audio device snapshot
 *
 * The supplied strings are preserved without validation or normalization
 *
 * @property driverVersion The reported driver version or unavailable-data marker
 * @property name The reported audio device name
 * @property codec The reported codec description or unavailable-data marker
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsSoundCard
 *
 * @since 1.1.0
 */
data class MacOsSoundCardImpl(
    override val driverVersion: String,
    override val name: String,
    override val codec: String
) : MacOsSoundCard
