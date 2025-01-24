package com.tecknobit.kinfo.model.desktop.hardware

import com.tecknobit.kinfo.model.desktop.hardware.computersystem.ComputerSystem
import com.tecknobit.kinfo.model.desktop.hardware.memory.GlobalMemory
import com.tecknobit.kinfo.model.desktop.hardware.storage.HWDiskStore
import com.tecknobit.kinfo.model.desktop.hardware.storage.LogicalVolumeGroup
import comtecknobitkinfomodeldesktophardwarecentralprocessor.CentralProcessor

/**
 * The `Hardware` interface represents the hardware components of a system.
 * It provides details about the computer system, processors, memory, storage devices, network interfaces, displays, sensors, and more.
 * This interface is useful for obtaining comprehensive hardware information for monitoring or diagnostics.
 *
 * @author N7ghtm4r3
 */
interface Hardware {

    /**
     * `computerSystem` The details of the computer system, including manufacturer, model, and firmware.
     */
    val computerSystem: ComputerSystem

    /**
     * `centralProcessor` The details of the system's central processor (CPU), including information like cores, speed, and usage.
     */
    val centralProcessor: CentralProcessor

    /**
     * `globalMemory` The details about the system's global memory, including total size, available memory, and page size.
     */
    val globalMemory: GlobalMemory

    /**
     * `powerSources` A list of power sources available to the system (e.g., battery, AC power).
     */
    val powerSources: List<PowerSource>

    /**
     * `diskStores` A list of disk storage devices, including information on disk usage, size, and read/write operations.
     */
    val diskStores: List<HWDiskStore>

    /**
     * `logicalVolumeGroups` A list of logical volume groups configured on the system.
     */
    val logicalVolumeGroups: List<LogicalVolumeGroup>

    /**
     * `networkIFs` A list of network interfaces on the system, including details such as IP addresses, speed, and state.
     */
    val networkIFs: List<NetworkIF>

    /**
     * `displays` A list of display devices connected to the system (e.g., monitors).
     */
    val displays: List<Display>

    /**
     * `sensors` The details of system sensors (e.g., temperature, fan speed, and voltage).
     */
    val sensors: Sensors

    /**
     * `soundCards` A list of sound cards available on the system.
     */
    val soundCards: List<SoundCard>

    /**
     * `graphicsCards` A list of graphics cards available on the system.
     */
    val graphicsCards: List<GraphicsCard>

    /**
     * Returns the list of network interfaces on the system.
     * If `includeLocalInterfaces` is true, local interfaces (e.g., loopback interfaces) will also be included.
     *
     * @param includeLocalInterfaces Whether to include local network interfaces such as loopback interfaces.
     * @return A list of network interfaces on the system.
     */
    fun getNetworkIfs(
        includeLocalInterfaces: Boolean
    ): List<NetworkIF>

    /**
     * Returns the list of USB devices connected to the system.
     * If `tree` is true, the devices are returned in a hierarchical tree structure.
     *
     * @param tree Whether to return the USB devices in a hierarchical tree structure.
     * @return A list of USB devices connected to the system.
     */
    fun getUsbDevices(
        tree: Boolean
    ): List<UsbDevice>

}
