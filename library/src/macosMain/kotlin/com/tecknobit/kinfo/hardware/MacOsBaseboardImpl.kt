package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBaseboard

data class MacOsBaseboardImpl(
    override val manufacturer: String,
    override val model: String,
    override val version: String,
    override val serialNumber: String
) : MacOsBaseboard
