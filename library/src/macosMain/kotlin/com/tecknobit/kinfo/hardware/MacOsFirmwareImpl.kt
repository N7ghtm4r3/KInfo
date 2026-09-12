package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsFirmware

/**
 * The `MacOsFirmwareImpl` class is useful to store mapped macOS firmware information
 *
 * Values are stored as supplied without validation or normalization
 *
 * @property manufacturer The mapped firmware or platform manufacturer
 * @property name The mapped bootloader name or device type fallback
 * @property description The mapped firmware ABI description
 * @property version The mapped firmware version string
 * @property releaseDate The mapped release date or platform timestamp
 * @property isAppleSilicon Whether the information was produced by the Apple Silicon mapping
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsFirmware
 *
 * @since 1.1.0
 */
data class MacOsFirmwareImpl(
    override val manufacturer: String,
    override val name: String,
    override val description: String,
    override val version: String,
    override val releaseDate: String,
    override val isAppleSilicon: Boolean
) : MacOsFirmware
