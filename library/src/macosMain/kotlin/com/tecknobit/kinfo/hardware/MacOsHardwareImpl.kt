package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.hardware.*
import com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsCentralProcessorMapper
import com.tecknobit.kinfo.mappers.hardware.computersystem.MacOsComputerSystemMapper
import com.tecknobit.kinfo.mappers.hardware.disks.MacOsHWDisksStoreMapper
import com.tecknobit.kinfo.mappers.hardware.globalmemory.MacOsGlobalMemoryMapper
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
     * `usbDevices` the USB device information, currently unavailable in this implementation
     */
    override val usbDevices: List<MacOsUsbDevice>
        get() = TODO("Not yet implemented")

    /**
     * `bluetoothDevice` the Bluetooth device information, currently unavailable in this implementation
     */
    override val bluetoothDevice: MacOsBluetoothDevice
        get() = TODO("Not yet implemented")

    /**
     * `printers` the CUPS destination snapshots freshly mapped on each access
     *
     * An empty list is returned when no destinations are provided, including when enumeration fails without data
     */
    override val printers: List<MacOsPrinter>
        get() = loadPrinters()

    /**
     * `soundCards` the audio device information, currently unavailable in this implementation
     */
    override val soundCards: List<MacOsSoundCard>
        get() = loadSoundCards()

    /**
     * `graphicsCard` the graphics card information, currently unavailable in this implementation
     */
    override val graphicsCard: List<MacOsGraphicsCard>
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
     * Method used to load the current macOS printing destinations through [MacOsPrintersMapper]
     *
     * @return the mapped printer snapshots, or an empty list when no destinations are provided, as [List] of [MacOsPrinter]
     */
    @Loader
    private fun loadPrinters(): List<MacOsPrinter> {
        val macOsPrintersMapper = MacOsPrintersMapper()

        return macOsPrintersMapper.mapFromNative()
    }

    @Loader
    private fun loadSoundCards(): List<MacOsSoundCard> {
        val macOsSoundCardsMapper = MacOsSoundCardsMapper()

        return macOsSoundCardsMapper.mapFromNative()
    }

    @Loader
    private fun loadGraphicsCards(): List<MacOsGraphicsCard> {
        val macOsGraphicCardsMapper = MacOsGraphicCardsMapper()

        return macOsGraphicCardsMapper.mapFromNative()
    }

}
