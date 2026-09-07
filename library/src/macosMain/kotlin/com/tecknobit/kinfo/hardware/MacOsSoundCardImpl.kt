package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsSoundCard

data class MacOsSoundCardImpl(
    override val driverVersion: String,
    override val name: String,
    override val codec: String
) : MacOsSoundCard
