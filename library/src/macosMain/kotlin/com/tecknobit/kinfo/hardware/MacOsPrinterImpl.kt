package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.PrinterStatus
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPrinter

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
