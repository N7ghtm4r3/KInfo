package com.tecknobit.kinfo.model.desktop.macos.hardware

interface MacOsHardware {

    val platformExpertDevice: MacOsComputerSystem

    val baseboardRegistryEntry: MacOsBaseboard

    val romRegistryEntry: MacOsFirmware

    val processorInfo: MacOsCentralProcessor

    val processorIdentifierInfo: MacOsProcessorIdentifier

    val processorCaches: MacOsProcessorCache

    val logicalProcessorInfo: MacOsLogicalProcessor

    val physicalProcessorInfo: MacOsPhysicalProcessor

    val vmStatistics: MacOsGlobalMemory

    val swapUsage: MacOsVirtualMemory

    val powerSourceDescription: MacOsPowerSource

    val disk: MacOsHWDiskStore

    val media: MacOsHWPartition

    val networkInterface: MacOsNetworkIF

    val displayId: MacOsDisplay

    val displayService: MacOsDisplayInfo

    val usbDevice: MacOsUsbDevice

    val bluetoothDevice: MacOsBluetoothDevice

    val destination: MacOsPrinter

    val audioDeviceId: MacOsSoundCard

    val metalDevice: MacOsGraphicsCard

}