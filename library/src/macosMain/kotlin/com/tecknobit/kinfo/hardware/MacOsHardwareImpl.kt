package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.*

data class MacOsHardwareImpl(
    override val platformExpertDevice: MacOsComputerSystem,
    override val baseboardRegistryEntry: MacOsBaseboard,
    override val romRegistryEntry: MacOsFirmware,
    override val processorInfo: MacOsCentralProcessor,
    override val processorIdentifierInfo: MacOsProcessorIdentifier,
    override val processorCaches: MacOsProcessorCache,
    override val logicalProcessorInfo: MacOsLogicalProcessor,
    override val physicalProcessorInfo: MacOsPhysicalProcessor,
    override val vmStatistics: MacOsGlobalMemory,
    override val swapUsage: MacOsVirtualMemory,
    override val powerSourceDescription: MacOsPowerSource,
    override val disk: MacOsHWDiskStore,
    override val media: MacOsHWPartition,
    override val networkInterface: MacOsNetworkIF,
    override val displayId: MacOsDisplay,
    override val displayService: MacOsDisplayInfo,
    override val usbDevice: MacOsUsbDevice,
    override val bluetoothDevice: MacOsBluetoothDevice,
    override val destination: MacOsPrinter,
    override val audioDeviceId: MacOsSoundCard,
    override val metalDevice: MacOsGraphicsCard
) : MacOsHardware