package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalProcessor

/**
 * The `MacOsPhysicalProcessorImpl` class is useful to store a macOS physical processor description
 *
 * @property physicalPackageNumber The package number assigned to this core
 * @property physicalProcessorNumber The core number within its package
 * @property efficiency The performance class, with higher values representing higher performance
 * @property idString The platform-specific core description, which may be empty or shared by multiple cores
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsPhysicalProcessor
 *
 * @since 1.1.0
 */
data class MacOsPhysicalProcessorImpl(
    override val physicalPackageNumber: Int,
    override val physicalProcessorNumber: Int,
    override val efficiency: Int,
    override val idString: String
) : MacOsPhysicalProcessor
