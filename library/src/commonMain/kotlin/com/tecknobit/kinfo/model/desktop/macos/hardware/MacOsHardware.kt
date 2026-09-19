package com.tecknobit.kinfo.model.desktop.macos.hardware

/**
 * The `MacOsHardware` interface defines the contract to expose macOS hardware information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsHardware {

    /**
     * `computerSystem` the computer system information
     */
    val computerSystem: MacOsComputerSystem

    /**
     * `processorInfo` the central processor information
     */
    val processorInfo: MacOsCentralProcessor

    /**
     * `globalMemory` the global memory information
     */
    val globalMemory: MacOsGlobalMemory

    /**
     * `powerSourceDescription` the internal battery capacity, electrical measurements, and charging state
     */
    val powerSourceDescription: MacOsPowerSource

    /**
     * `disks` the whole-media information for physical and synthesized disks and their associated partitions
     */
    val disks: List<MacOsHWDiskStore>

    /**
     * `networkInterface` the network interface information
     */
    val networkInterface: MacOsNetworkIF

    /**
     * `displaysInfo` the decoded identification information for the displays available to the native mapper
     */
    val displaysInfo: List<MacOsDisplayInfo>

    /**
     * `usbDevices` the USB device information
     */
    val usbDevices: List<MacOsUsbDevice>

    /**
     * `bluetoothDevices` the snapshots of Bluetooth devices paired with the system, including disconnected devices
     */
    val bluetoothDevices: List<MacOsBluetoothDevice>

    /**
     * `printers` the printers information
     */
    val printers: List<MacOsPrinter>

    /**
     * `soundCards` the audio device names, driver versions, and codec descriptions exposed by the macOS implementation
     */
    val soundCards: List<MacOsSoundCard>

    /**
     * `graphicCards` the graphics card identities, hardware revisions, and video-memory values exposed by macOS
     */
    val graphicCards: List<MacOsGraphicsCard>

}
