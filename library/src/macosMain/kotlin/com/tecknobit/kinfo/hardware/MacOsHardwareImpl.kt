package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.hardware.MacOsPowerSourceMapper
import com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsCentralProcessorMapper
import com.tecknobit.kinfo.mappers.hardware.computersystem.MacOsComputerSystemMapper
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
     * `disk` the disk information, currently unavailable in this implementation
     */
    override val disk: MacOsHWDiskStore
        get() = TODO("Not yet implemented")

    /**
     * `networkInterface` the network interface information, currently unavailable in this implementation
     */
    override val networkInterface: MacOsNetworkIF
        get() = TODO("Not yet implemented")

    /**
     * `displayInfo` the display information, currently unavailable in this implementation
     */
    override val displayInfo: MacOsDisplay
        get() = TODO("Not yet implemented")

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

}
