package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.GraphicsCard

class GraphicsCardImpl(
    override val name: String,
    override val deviceId: String,
    override val vendor: String,
    override val versionInfo: String,
    override val vRam: Long
) : GraphicsCard