package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.graphicscard.GpuStats
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGraphicsCard

data class MacOsGraphicsCardImpl(
    override val name: String,
    override val deviceId: String,
    override val vendor: String,
    override val versionInfo: String,
    override val vRam: Long
) : MacOsGraphicsCard {

    override fun createStatsSession(): GpuStats? {
        TODO("Not yet implemented")
    }

}
