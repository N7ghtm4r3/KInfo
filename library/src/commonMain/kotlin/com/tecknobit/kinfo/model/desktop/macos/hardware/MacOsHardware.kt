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
     * `platformExpertDevice` the computer system information
     */
    val platformExpertDevice: MacOsComputerSystem

    /**
     * `baseboardRegistryEntry` the baseboard information
     */
    val baseboardRegistryEntry: MacOsBaseboard

    /**
     * `romRegistryEntry` the firmware information
     */
    val romRegistryEntry: MacOsFirmware

    /**
     * `processorInfo` the central processor information
     */
    val processorInfo: MacOsCentralProcessor

    /**
     * `processorIdentifierInfo` the processor identification information
     */
    val processorIdentifierInfo: MacOsProcessorIdentifier

    /**
     * `processorCaches` the list of processor cache descriptions
     */
    val processorCaches: List<MacOsProcessorCache>

    /**
     * `logicalProcessorInfo` the logical processor information
     */
    val logicalProcessorInfo: MacOsLogicalProcessor

    /**
     * `physicalProcessorInfo` the physical processor information
     */
    val physicalProcessorInfo: MacOsPhysicalProcessor

    /**
     * `vmStatistics` the physical memory information
     */
    val vmStatistics: MacOsGlobalMemory

    /**
     * `swapUsage` the virtual memory and swap information
     */
    val swapUsage: MacOsVirtualMemory

    /**
     * `powerSourceDescription` the power source information
     */
    val powerSourceDescription: MacOsPowerSource

    /**
     * `disk` the disk information
     */
    val disk: MacOsHWDiskStore

    /**
     * `media` the partition information
     */
    val media: MacOsHWPartition

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
