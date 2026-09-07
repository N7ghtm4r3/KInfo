package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsFirmware

data class MacOsFirmwareImpl(
    override val manufacturer: String,
    override val name: String,
    override val description: String,
    override val version: String,
    override val releaseDate: String
) : MacOsFirmware
