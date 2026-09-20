package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.hardware.*
import com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsCentralProcessorMapper
import com.tecknobit.kinfo.mappers.hardware.computersystem.MacOsComputerSystemMapper
import com.tecknobit.kinfo.mappers.hardware.disks.MacOsHWDisksStoreMapper
import com.tecknobit.kinfo.mappers.hardware.globalmemory.MacOsGlobalMemoryMapper
import com.tecknobit.kinfo.mappers.hardware.gpu.MacOsGraphicCardsMapper
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
     * `computerSystem` the machine identity, firmware, and baseboard information freshly mapped on each access
     */
    override val computerSystem: MacOsComputerSystem
        get() = loadComputerSystem()

    /**
     * `processorInfo` the central processor information, currently unavailable in this implementation
     */
    override val processorInfo: MacOsCentralProcessor
        get() = loadProcessorInfo()

    /**
     * `globalMemory` the global, virtual, and physical memory information freshly mapped on each access
     *
     * @throws IllegalStateException If swap usage or swap page statistics cannot be read
     */
    override val globalMemory: MacOsGlobalMemory
        get() = loadGlobalMemory()

    /**
     * `powerSourceDescription` the internal battery measurements freshly mapped on each access
     *
     * @throws IllegalStateException If the internal battery service is unavailable
     */
    override val powerSourceDescription: MacOsPowerSource
        get() = loadPowerSource()

    /**
     * `disks` the whole-media identities, I/O statistics, and associated partitions freshly mapped on each access
     *
     * @throws IllegalStateException If an IOKit media enumeration fails
     */
    override val disks: List<MacOsHWDiskStore>
        get() = loadDisks()

    /**
     * `networkInterface` the network interface information, currently unavailable in this implementation
     */
    override val networkInterface: MacOsNetworkIF
        get() = TODO("Not yet implemented")

    /**
     * `displaysInfo` the display identification information freshly mapped on each access
     *
     * Native data is decoded when supported, while the Apple Silicon built-in display uses synthesized data
     * Entries without the attributes required by the mapper are omitted
     *
     * @throws IllegalStateException If an IOKit display service enumeration fails
     */
    override val displaysInfo: List<MacOsDisplayInfo>
        get() = loadDisplayInfo()

    /**
     * `usbDevices` the root USB service snapshots and their nested connected devices freshly mapped on each access
     *
     * Devices for which no USB ancestor can be resolved are included at the top level
     *
     * @throws IllegalStateException If a native USB service enumeration fails
     */
    override val usbDevices: List<MacOsUsbDevice>
        get() = loadUsbDevices()

    /**
     * `bluetoothDevices` the paired Bluetooth device snapshots freshly mapped on each access
     *
     * Unmatched battery readings currently use `0`, and every device receives the default local controller name
     *
     * @throws IllegalStateException If the HID service enumeration fails
     */
    override val bluetoothDevices: List<MacOsBluetoothDevice>
        get() = loadBluetoothDevices()

    /**
     * `printers` the CUPS destination snapshots freshly mapped on each access
     *
     * An empty list is returned when no destinations are provided, including when enumeration fails without data
     */
    override val printers: List<MacOsPrinter>
        get() = loadPrinters()

    /**
     * `soundCards` the audio service snapshots freshly mapped on each access
     *
     * Each matching `IOAudio2Device` service produces one entry, which need not represent a separate physical card
     *
     * @throws IllegalStateException If the native audio service enumeration fails
     */
    override val soundCards: List<MacOsSoundCard>
        get() = loadSoundCards()

    /**
     * `graphicCards` the graphics card snapshots freshly mapped on each access
     *
     * Intel mapping selects PCI graphics controllers, while Apple Silicon mapping uses AGX accelerator services
     *
     * @throws IllegalStateException If the native graphics service enumeration fails
     */
    override val graphicCards: List<MacOsGraphicsCard>
        get() = loadGraphicsCards()

    /**
     * Method used to load the current macOS computer system information through [MacOsComputerSystemMapper]
     *
     * @return the mapped computer system information as [MacOsComputerSystem]
     * @throws IllegalStateException If the platform expert service cannot be loaded
     */
    @Loader
    private fun loadComputerSystem(): MacOsComputerSystem {
        val macOsComputerSystemMapper = MacOsComputerSystemMapper()

        return macOsComputerSystemMapper.mapFromNative()
    }

    /**
     * Method used to request the current macOS central processor information through [MacOsCentralProcessorMapper]
     *
     * The mapper is incomplete and currently throws when an unimplemented processor field is evaluated
     *
     * @return the mapped central processor information as [MacOsCentralProcessor]
     * @throws NotImplementedError When an unimplemented processor field is evaluated
     */
    @Loader
    private fun loadProcessorInfo(): MacOsCentralProcessor {
        val macOsCentralProcessorMapper = MacOsCentralProcessorMapper()

        return macOsCentralProcessorMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS memory snapshot through [MacOsGlobalMemoryMapper]
     *
     * @return the mapped global memory as [MacOsGlobalMemory]
     * @throws IllegalStateException If swap usage or swap page statistics cannot be read
     */
    @Loader
    private fun loadGlobalMemory(): MacOsGlobalMemory {
        val macOsGlobalMemoryMapper = MacOsGlobalMemoryMapper()

        return macOsGlobalMemoryMapper.mapFromNative()
    }

    /**
     * Method used to load the current internal battery information
     *
     * @return the mapped power source information as [MacOsPowerSource]
     * @throws IllegalStateException If the internal battery service is unavailable
     */
    @Loader
    private fun loadPowerSource(): MacOsPowerSource {
        val macOsPowerSourceMapper = MacOsPowerSourceMapper()

        return macOsPowerSourceMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS whole-media snapshots through [MacOsHWDisksStoreMapper]
     *
     * @return the mapped disk information as [List] of [MacOsHWDiskStore]
     * @throws IllegalStateException If an IOKit media enumeration fails
     */
    @Loader
    private fun loadDisks(): List<MacOsHWDiskStore> {
        val macOsHWDisksStoreMapper = MacOsHWDisksStoreMapper()

        return macOsHWDisksStoreMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS display identification information through [MacOsDisplaysInfoMapper]
     *
     * @return the mapped display information as [List] of [MacOsDisplayInfo]
     * @throws IllegalStateException If an IOKit display service enumeration fails
     */
    @Loader
    private fun loadDisplayInfo(): List<MacOsDisplayInfo> {
        val macOsDisplaysInfoMapper = MacOsDisplaysInfoMapper()

        return macOsDisplaysInfoMapper.mapFromNative()
    }

    /**
     * Method used to load the current USB service snapshots and their connected devices through [MacOsUsbDevicesMapper]
     *
     * @return the root devices with their nested children, or an empty list when none match, as [List] of [MacOsUsbDevice]
     * @throws IllegalStateException If a native USB service enumeration fails
     */
    @Loader
    private fun loadUsbDevices(): List<MacOsUsbDevice> {
        val macOsUsbDevicesMapper = MacOsUsbDevicesMapper()

        return macOsUsbDevicesMapper.mapFromNative()
    }

    /**
     * Method used to load paired Bluetooth device snapshots through [MacOsBluetoothDevicesMapper]
     *
     * @return the mapped snapshots, or an empty list when no paired devices are returned, as [List] of [MacOsBluetoothDevice]
     * @throws IllegalStateException If the HID service enumeration fails
     */
    @Loader
    private fun loadBluetoothDevices(): List<MacOsBluetoothDevice> {
        val macOsBluetoothDevicesMapper = MacOsBluetoothDevicesMapper()

        return macOsBluetoothDevicesMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS printing destinations through [MacOsPrintersMapper]
     *
     * @return the mapped printer snapshots, or an empty list when no destinations are provided, as [List] of [MacOsPrinter]
     */
    @Loader
    private fun loadPrinters(): List<MacOsPrinter> {
        val macOsPrintersMapper = MacOsPrintersMapper()

        return macOsPrintersMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS audio service snapshots through [MacOsSoundCardsMapper]
     *
     * @return the mapped snapshots, or an empty list when no services match, as [List] of [MacOsSoundCard]
     * @throws IllegalStateException If the native audio service enumeration fails
     */
    @Loader
    private fun loadSoundCards(): List<MacOsSoundCard> {
        val macOsSoundCardsMapper = MacOsSoundCardsMapper()

        return macOsSoundCardsMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS graphics card snapshots through [com.tecknobit.kinfo.mappers.hardware.gpu.MacOsGraphicCardsMapper]
     *
     * @return the mapped snapshots, or an empty list when no services are accepted, as [List] of [MacOsGraphicsCard]
     * @throws IllegalStateException If the native graphics service enumeration fails
     */
    @Loader
    private fun loadGraphicsCards(): List<MacOsGraphicsCard> {
        val macOsGraphicCardsMapper = MacOsGraphicCardsMapper()

        return macOsGraphicCardsMapper.mapFromNative()
    }

}
