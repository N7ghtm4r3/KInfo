package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.hardware.*
import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.*
import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Baseboard
import com.tecknobit.kinfo.model.desktop.hardware.computersystem.ComputerSystem
import com.tecknobit.kinfo.model.desktop.hardware.computersystem.Firmware
import com.tecknobit.kinfo.model.desktop.hardware.display.Display
import com.tecknobit.kinfo.model.desktop.hardware.display.DisplayInfo
import com.tecknobit.kinfo.model.desktop.hardware.graphicscard.GraphicsCard
import com.tecknobit.kinfo.model.desktop.hardware.memory.GlobalMemory
import com.tecknobit.kinfo.model.desktop.hardware.memory.VirtualMemory
import com.tecknobit.kinfo.model.desktop.hardware.storage.HWDiskStore
import com.tecknobit.kinfo.model.desktop.hardware.storage.HWPartition

interface MacOsHardware {

    val platformExpertDevice: ComputerSystem

    val baseboardRegistryEntry: Baseboard

    val romRegistryEntry: Firmware

    val processorInfo: CentralProcessor

    val processorIdentifierInfo: ProcessorIdentifier

    val processorCaches: ProcessorCache

    val logicalProcessorInfo: LogicalProcessor

    val physicalProcessorInfo: PhysicalProcessor

    val vmStatistics: GlobalMemory

    val swapUsage: VirtualMemory

    val powerSourceDescription: PowerSource

    val disk: HWDiskStore

    val media: HWPartition

    val networkInterface: NetworkIF

    val displayId: Display

    val displayService: DisplayInfo

    val usbDevice: UsbDevice

    val bluetoothDevice: BluetoothDevice

    val destination: Printer

    val audioDeviceId: SoundCard

    val metalDevice: GraphicsCard

}