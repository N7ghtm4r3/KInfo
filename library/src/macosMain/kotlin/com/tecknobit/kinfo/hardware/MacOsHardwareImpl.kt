package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.hardware.MacOsBaseboardMapper
import com.tecknobit.kinfo.mappers.hardware.MacOsFirmwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.*

/**
 * The `MacOsHardwareImpl` class is useful to provide macOS hardware information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardware
 *
 * @since 1.1.0
 */
class MacOsHardwareImpl : MacOsHardware {

    /**
     * `platformExpertDevice` the computer system information, currently unavailable in this implementation
     */
    override val platformExpertDevice: MacOsComputerSystem
        get() = TODO("Not yet implemented")

    /**
     * `baseboardRegistryEntry` the baseboard information loaded from the platform expert service on each access
     */
    override val baseboardRegistryEntry: MacOsBaseboard
        get() = loadBaseboard()

    /**
     * `romRegistryEntry` the firmware information loaded from the device tree on each access
     */
    override val romRegistryEntry: MacOsFirmware
        get() = loadFirmware()

    /**
     * `processorInfo` the central processor information, currently unavailable in this implementation
     */
    override val processorInfo: MacOsCentralProcessor
        get() = TODO("Not yet implemented")

    /**
     * `processorIdentifierInfo` the processor identification information, currently unavailable in this implementation
     */
    override val processorIdentifierInfo: MacOsProcessorIdentifier
        get() = TODO("Not yet implemented")

    /**
     * `processorCaches` the processor cache information, currently unavailable in this implementation
     */
    override val processorCaches: MacOsProcessorCache
        get() = TODO("Not yet implemented")

    /**
     * `logicalProcessorInfo` the logical processor information, currently unavailable in this implementation
     */
    override val logicalProcessorInfo: MacOsLogicalProcessor
        get() = TODO("Not yet implemented")

    /**
     * `physicalProcessorInfo` the physical processor information, currently unavailable in this implementation
     */
    override val physicalProcessorInfo: MacOsPhysicalProcessor
        get() = TODO("Not yet implemented")

    /**
     * `vmStatistics` the physical memory information, currently unavailable in this implementation
     */
    override val vmStatistics: MacOsGlobalMemory
        get() = TODO("Not yet implemented")

    /**
     * `swapUsage` the virtual memory and swap information, currently unavailable in this implementation
     */
    override val swapUsage: MacOsVirtualMemory
        get() = TODO("Not yet implemented")

    /**
     * `powerSourceDescription` the power source information, currently unavailable in this implementation
     */
    override val powerSourceDescription: MacOsPowerSource
        get() = TODO("Not yet implemented")

    /**
     * `disk` the disk information, currently unavailable in this implementation
     */
    override val disk: MacOsHWDiskStore
        get() = TODO("Not yet implemented")

    /**
     * `media` the partition information, currently unavailable in this implementation
     */
    override val media: MacOsHWPartition
        get() = TODO("Not yet implemented")

    /**
     * `networkInterface` the network interface information, currently unavailable in this implementation
     */
    override val networkInterface: MacOsNetworkIF
        get() = TODO("Not yet implemented")

    /**
     * `displayId` the display information, currently unavailable in this implementation
     */
    override val displayId: MacOsDisplay
        get() = TODO("Not yet implemented")

    /**
     * `displayService` the display information, currently unavailable in this implementation
     */
    override val displayService: MacOsDisplayInfo
        get() = TODO("Not yet implemented")

    /**
     * `usbDevice` the USB device information, currently unavailable in this implementation
     */
    override val usbDevice: MacOsUsbDevice
        get() = TODO("Not yet implemented")

    /**
     * `bluetoothDevice` the Bluetooth device information, currently unavailable in this implementation
     */
    override val bluetoothDevice: MacOsBluetoothDevice
        get() = TODO("Not yet implemented")

    /**
     * `destination` the printer information, currently unavailable in this implementation
     */
    override val destination: MacOsPrinter
        get() = TODO("Not yet implemented")

    /**
     * `audioDeviceId` the audio device information, currently unavailable in this implementation
     */
    override val audioDeviceId: MacOsSoundCard
        get() = TODO("Not yet implemented")

    /**
     * `metalDevice` the graphics card information, currently unavailable in this implementation
     */
    override val metalDevice: MacOsGraphicsCard
        get() = TODO("Not yet implemented")

    /**
     * Method used to load the current macOS baseboard information through [MacOsBaseboardMapper]
     *
     * @return the mapped baseboard information as [MacOsBaseboard]
     */
    @Loader
    private fun loadBaseboard(): MacOsBaseboard {
        val macOsBaseboardMapper = MacOsBaseboardMapper()

        return macOsBaseboardMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS firmware information from native registry properties
     *
     * @return the mapped firmware information as [MacOsFirmware]
     */
    @Loader
    private fun loadFirmware(): MacOsFirmware {
        val macOsFirmware = MacOsFirmwareMapper()

        return macOsFirmware.mapFromNative()
    }
}
