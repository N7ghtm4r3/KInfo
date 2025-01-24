package com.tecknobit.kinfo.model.hardware.computersystem

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Baseboard

class BaseboardImpl(
    override val manufacturer: String,
    override val model: String,
    override val version: String,
    override val serialNumber: String
) : Baseboard