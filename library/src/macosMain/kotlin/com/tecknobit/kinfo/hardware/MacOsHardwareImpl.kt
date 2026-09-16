package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.annotations.Loader
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
     * `globalMemory` the global memory information, currently unavailable in this implementation
     */
    override val globalMemory: MacOsGlobalMemory
        get() = loadGlobalMemory()

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

    @Loader
    private fun loadGlobalMemory(): MacOsGlobalMemory {
        val macOsGlobalMemoryMapper = MacOsGlobalMemoryMapper()

        return macOsGlobalMemoryMapper.mapFromNative()
    }

}
