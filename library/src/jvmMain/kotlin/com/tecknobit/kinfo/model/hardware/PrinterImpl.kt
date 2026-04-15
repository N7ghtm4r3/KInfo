package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.Printer
import com.tecknobit.kinfo.model.desktop.hardware.PrinterStatus

/**
 * Implementation of the `Printer` interface.
 *
 * This class provides details about a printer, including its name, driver name,
 * description, current status, and connection information
 *
 * It also offers information about whether the printer is the default printer,
 * whether it is local or network-based, and the reason for its current status
 *
 * Integration in [oshi - #3078](https://github.com/oshi/oshi/pull/3078)
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 */
class PrinterImpl(
    override val name: String,
    override val driverName: String,
    override val description: String,
    override val status: PrinterStatus,
    override val statusReason: String,
    override val isDefault: Boolean,
    override val isLocal: Boolean,
    override val portName: String,
) : Printer