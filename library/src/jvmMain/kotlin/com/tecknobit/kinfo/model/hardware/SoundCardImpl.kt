package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.SoundCard

class SoundCardImpl(
    override val driverVersion: String,
    override val name: String,
    override val codec: String
) : SoundCard