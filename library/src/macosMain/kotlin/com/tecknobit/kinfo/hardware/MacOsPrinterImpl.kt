package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.PrinterStatus
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPrinter

/**
 * The `MacOsPrinterImpl` class is useful to store a snapshot of a macOS printing destination
 *
 * Snapshots produced by [com.tecknobit.kinfo.mappers.hardware.MacOsPrintersMapper] contain copied CUPS values
 * The status describes the reported queue state and recognized reasons rather than a direct connectivity check
 * An unrecognized or unavailable device URI is represented by a false [isLocal] value
 *
 * @property name The destination name, optionally qualified with an instance name as `name/instance`
 * @property driverName The reported make and model, or the unknown marker when unavailable
 * @property description The human-readable description, falling back to the destination name when unavailable
 * @property status The resolved queue status, or [PrinterStatus.UNKNOWN] when no supported mapping applies
 * @property statusReason The comma-separated CUPS reason keywords, or the unknown marker when unavailable
 * @property isDefault Whether CUPS marks the destination as the default destination
 * @property isLocal Whether the device URI uses a recognized USB, parallel, or serial connection scheme
 * @property portName The device URI used to address the printer, or the unknown marker when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsPrinter
 *
 * @since 1.1.0
 */
data class MacOsPrinterImpl(
    override val name: String,
    override val driverName: String,
    override val description: String,
    override val status: PrinterStatus,
    override val statusReason: String,
    override val isDefault: Boolean,
    override val isLocal: Boolean,
    override val portName: String
) : MacOsPrinter
