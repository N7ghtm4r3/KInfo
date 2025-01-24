package com.tecknobit.kinfo.model.hardware.computersystem

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Firmware

class FirmwareImpl(
    override val manufacturer: String,
    override val name: String,
    override val description: String,
    override val version: String,
    override val releaseDate: String
) : Firmware