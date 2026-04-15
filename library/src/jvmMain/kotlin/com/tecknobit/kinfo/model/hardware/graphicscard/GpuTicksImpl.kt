package com.tecknobit.kinfo.model.hardware.graphicscard

import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuTicks

class GpuTicksImpl(
    override val activeTicks: Long,
    override val idleTicks: Long,
) : GpuTicks