package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.hardware.MacOsBaseboardMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.*

/**
 * The `MacOsHardwareImpl` class is useful to provide macOS hardware information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
class MacOsHardwareImpl : MacOsHardware {

    /**
     * `platformExpertDevice` the computer system information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val platformExpertDevice: MacOsComputerSystem
        get() = TODO("Not yet implemented")

    /**
     * `baseboardRegistryEntry` the baseboard information loaded from the platform expert service on each access
     */
    override val baseboardRegistryEntry: MacOsBaseboard
        get() = loadBaseboard()

    /**
     * `romRegistryEntry` the firmware information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val romRegistryEntry: MacOsFirmware
        get() = TODO("Not yet implemented")

    /**
     * `processorInfo` the central processor information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val processorInfo: MacOsCentralProcessor
        get() = TODO("Not yet implemented")

    /**
     * `processorIdentifierInfo` the processor identification information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val processorIdentifierInfo: MacOsProcessorIdentifier
        get() = TODO("Not yet implemented")

    /**
     * `processorCaches` the processor cache information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val processorCaches: MacOsProcessorCache
        get() = TODO("Not yet implemented")

    /**
     * `logicalProcessorInfo` the logical processor information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val logicalProcessorInfo: MacOsLogicalProcessor
        get() = TODO("Not yet implemented")

    /**
     * `physicalProcessorInfo` the physical processor information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val physicalProcessorInfo: MacOsPhysicalProcessor
        get() = TODO("Not yet implemented")

    /**
     * `vmStatistics` the physical memory information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val vmStatistics: MacOsGlobalMemory
        get() = TODO("Not yet implemented")

    /**
     * `swapUsage` the virtual memory and swap information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val swapUsage: MacOsVirtualMemory
        get() = TODO("Not yet implemented")

    /**
     * `powerSourceDescription` the power source information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val powerSourceDescription: MacOsPowerSource
        get() = TODO("Not yet implemented")

    /**
     * `disk` the disk information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val disk: MacOsHWDiskStore
        get() = TODO("Not yet implemented")

    /**
     * `media` the partition information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val media: MacOsHWPartition
        get() = TODO("Not yet implemented")

    /**
     * `networkInterface` the network interface information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val networkInterface: MacOsNetworkIF
        get() = TODO("Not yet implemented")

    /**
     * `displayId` the display information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val displayId: MacOsDisplay
        get() = TODO("Not yet implemented")

    /**
     * `displayService` the display information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val displayService: MacOsDisplayInfo
        get() = TODO("Not yet implemented")

    /**
     * `usbDevice` the USB device information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val usbDevice: MacOsUsbDevice
        get() = TODO("Not yet implemented")

    /**
     * `bluetoothDevice` the Bluetooth device information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val bluetoothDevice: MacOsBluetoothDevice
        get() = TODO("Not yet implemented")

    /**
     * `destination` the printer information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val destination: MacOsPrinter
        get() = TODO("Not yet implemented")

    /**
     * `audioDeviceId` the audio device information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val audioDeviceId: MacOsSoundCard
        get() = TODO("Not yet implemented")

    /**
     * `metalDevice` the graphics card information, currently unavailable in this implementation
     *
     * @throws NotImplementedError Whenever this property is accessed
     */
    override val metalDevice: MacOsGraphicsCard
        get() = TODO("Not yet implemented")

    /**
     * Method used to load the current macOS baseboard information through [MacOsBaseboardMapper]
     *
     * @return the mapped baseboard information as [MacOsBaseboard]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    @Loader
    private fun loadBaseboard(): MacOsBaseboard {
        val macOsBaseboardMapper = MacOsBaseboardMapper()

        return macOsBaseboardMapper.mapFromNative()
    }
}