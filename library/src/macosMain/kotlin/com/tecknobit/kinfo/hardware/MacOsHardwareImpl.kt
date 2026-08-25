package com.tecknobit.kinfo.hardware

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
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHardware

data class MacOsHardwareImpl(
    override val platformExpertDevice: ComputerSystem,
    override val baseboardRegistryEntry: Baseboard,
    override val romRegistryEntry: Firmware,
    override val processorInfo: CentralProcessor,
    override val processorIdentifierInfo: ProcessorIdentifier,
    override val processorCaches: ProcessorCache,
    override val logicalProcessorInfo: LogicalProcessor,
    override val physicalProcessorInfo: PhysicalProcessor,
    override val vmStatistics: GlobalMemory,
    override val swapUsage: VirtualMemory,
    override val powerSourceDescription: PowerSource,
    override val disk: HWDiskStore,
    override val media: HWPartition,
    override val networkInterface: NetworkIF,
    override val displayId: Display,
    override val displayService: DisplayInfo,
    override val usbDevice: UsbDevice,
    override val bluetoothDevice: BluetoothDevice,
    override val destination: Printer,
    override val audioDeviceId: SoundCard,
    override val metalDevice: GraphicsCard
) : MacOsHardware