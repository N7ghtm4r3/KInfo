package com.tecknobit.kinfo.model.desktop.hardware.graphicscard

import com.tecknobit.kinfo.annotations.Bridge

/**
 * Represents a graphics card in the system.
 * This interface provides details about the graphics card, including its name, device ID, vendor, version information, and VRAM size.
 *
 * @author N7ghtm4r3
 */
interface GraphicsCard {

    /**
     * The name of the graphics card (e.g., NVIDIA GeForce RTX 3080, AMD Radeon RX 6800)
     */
    val name: String

    /**
     * The unique device identifier for the graphics card.
     * This ID is typically used to distinguish the card within the system
     */
    val deviceId: String

    /**
     * The vendor of the graphics card (e.g., NVIDIA, AMD, Intel)
     */
    val vendor: String

    /**
     * The version information of the graphics card (e.g., driver version or hardware revision)
     */
    val versionInfo: String

    /**
     * The amount of VRAM (video memory) available on the graphics card, in bytes
     */
    val vRam: Long

    /**
     * Method used to open a new [GpuStats] session for sampling dynamic GPU metrics.
     * Platforms that do not support a native session return a no-op instance whose
     * metric methods return sentinel values
     *
     * @return stats session as nullable [GpuStats]
     *
     * @since 1.0.6
     */
    @Bridge
    fun createStatsSession(): GpuStats?

}