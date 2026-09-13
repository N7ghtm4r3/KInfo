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
     * `powerSourceDescription` the power source information
     */
    val powerSourceDescription: MacOsPowerSource

    /**
     * `disk` the disk information
     */
    val disk: MacOsHWDiskStore

    /**
     * `networkInterface` the network interface information
     */
    val networkInterface: MacOsNetworkIF

    /**
     * `displayId` the display information
     */
    val displayId: MacOsDisplay

    /**
     * `displayService` the display information
     */
    val displayService: MacOsDisplayInfo

    /**
     * `usbDevice` the USB device information
     */
    val usbDevice: MacOsUsbDevice

    /**
     * `bluetoothDevice` the Bluetooth device information
     */
    val bluetoothDevice: MacOsBluetoothDevice

    /**
     * `destination` the printer information
     */
    val destination: MacOsPrinter

    /**
     * `audioDeviceId` the audio device information
     */
    val audioDeviceId: MacOsSoundCard

    /**
     * `metalDevice` the graphics card information
     */
    val metalDevice: MacOsGraphicsCard

}
