package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorIdentifier

/**
 * The `MacOsProcessorIdentifierImpl` class is useful to store native macOS processor identification values
 *
 * @property cpuVendor The processor vendor or platform manufacturer
 * @property cpuName The commercial processor name
 * @property cpuFamily The architecture-specific processor family representation
 * @property cpuModel The commercial chip name on Apple Silicon or numeric CPU model on Intel
 * @property cpuStepping The processor revision, or an unknown value when unavailable
 * @property processorId The identifier composed from architecture-specific values, without hardware uniqueness guarantees
 * @property cpuIdentifier The descriptive identifier assembled from processor identification fields
 * @property isCpu64bit Whether the processor reports 64-bit capability
 * @property cpuVendorFreq The processor frequency in hertz, or zero when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsProcessorIdentifier
 *
 * @since 1.1.0
 */
data class MacOsProcessorIdentifierImpl(
    override val cpuVendor: String,
    override val cpuName: String,
    override val cpuFamily: String,
    override val cpuModel: String,
    override val cpuStepping: String,
    override val processorId: String,
    override val cpuIdentifier: String,
    override val isCpu64bit: Boolean,
    override val cpuVendorFreq: Long
) : MacOsProcessorIdentifier
