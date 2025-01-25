package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.GraphicsCard

/**
 * Implementation of the `GraphicsCard` interface.
 * This class provides details about the graphics card, including its name, device ID, vendor, version information, and VRAM size.
 *
 * @param name The name of the graphics card (e.g., NVIDIA GeForce RTX 3080, AMD Radeon RX 6800)
 * @param deviceId The unique device identifier for the graphics card
 * @param vendor The vendor of the graphics card (e.g., NVIDIA, AMD, Intel)
 * @param versionInfo The version information of the graphics card (e.g., driver version or hardware revision)
 * @param vRam The amount of VRAM (video memory) available on the graphics card, in bytes
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
    override val vRam: Long
) : GraphicsCard
