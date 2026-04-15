package com.tecknobit.kinfo.model.hardware.graphicscard

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GpuStats
import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GraphicsCard

/**
 * Implementation of the `GraphicsCard` interface.
 * This class provides details about the graphics card, including its name, device ID, vendor, version information, and VRAM size.
 *
 * @param name The name of the graphics card (e.g., NVIDIA GeForce RTX 3080, AMD Radeon RX 6800)
 * @param deviceId The unique device identifier for the graphics card
 * @param vendor The vendor of the graphics card (e.g., NVIDIA, AMD, Intel)
 * @param versionInfo The version information of the graphics card (e.g., driver version or hardware revision)
 * @param vRam The amount of VRAM (video memory) available on the graphics card, in bytes
 * @param source The original `GraphicsCard` item from oshi library to perform any bridge operations
 *
 * @author N7ghtm4r3
 *
 * @see GraphicsCard
 */
class GraphicsCardImpl(
    override val name: String,
    override val deviceId: String,
    override val vendor: String,
    override val versionInfo: String,
    override val vRam: Long,
    private val source: oshi.hardware.GraphicsCard? = null,
) : GraphicsCard {

    /**
     * Method used to open a new [GpuStats] session for sampling dynamic GPU metrics
     * Platforms that do not support a native session return a no-op instance whose
     * metric methods return sentinel values
     *
     * @return stats session as nullable [GpuStats]
     *
     * @since 1.0.6
     */
    @Bridge
    override fun createStatsSession(): GpuStats? {
        val sourceStats = source?.createStatsSession()
        return sourceStats?.use {
            val ticks = it.gpuTicks
            GpuStatsImpl(
                gpuUtilization = it.gpuUtilization,
                vramUsed = it.vramUsed,
                sharedMemoryUsed = it.sharedMemoryUsed,
                temperature = it.temperature,
                powerDraw = it.powerDraw,
                coreClockMhz = it.coreClockMhz,
                memoryClockMhz = it.memoryClockMhz,
                fanSpeedPercent = it.fanSpeedPercent,
                gpuTicks = GpuTicksImpl(
                    activeTicks = ticks.activeTicks,
                    idleTicks = ticks.idleTicks
                )
            )
        }
    }

}