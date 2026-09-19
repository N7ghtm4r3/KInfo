package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.mappers.hardware.gpu.MacOsGraphicCardStatsMapper
import com.tecknobit.kinfo.model.desktop.common.hardware.graphicscard.GpuStats
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsGraphicsCard

/**
 * The `MacOsGraphicsCardImpl` class is useful to store a macOS graphics card snapshot
 *
 * Values are stored without validation or unit conversion
 * [createStatsSession] returns a fresh statistics snapshot without retaining native service handles
 *
 * @property name The graphics card name supplied by the mapper
 * @property deviceId The decimal registry entry identifier, or an unknown marker when unavailable
 * @property vendor The resolved vendor name or hexadecimal identifier
 * @property versionInfo The formatted hardware revision, or an unknown marker when unavailable
 * @property vRam The supplied video-memory value, with `0` for Apple unified memory and `-1` when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsGraphicsCard
 *
 * @since 1.1.0
 */
data class MacOsGraphicsCardImpl(
    override val name: String,
    override val deviceId: String,
    override val vendor: String,
    override val versionInfo: String,
    override val vRam: Long
) : MacOsGraphicsCard {

    /**
     * Method used to retrieve a fresh GPU statistics snapshot for [deviceId]
     *
     * The identifier must match an `IOAccelerator` registry entry
     * The returned properties retain their sampled values and do not refresh automatically
     * Missing statistics and unavailable GPU tick counters are represented by zero
     *
     * @return the sampled GPU statistics as [GpuStats]
     * @throws IllegalStateException If service enumeration fails or no accelerator matches [deviceId]
     */
    @Bridge
    override fun createStatsSession(): GpuStats {
        val macOsGraphicCardStatsMapper = MacOsGraphicCardStatsMapper(
            deviceId = deviceId
        )

        return macOsGraphicCardStatsMapper.mapFromNative()
    }

}
