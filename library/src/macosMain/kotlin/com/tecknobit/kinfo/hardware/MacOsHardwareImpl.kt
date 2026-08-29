package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.*
import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.*
import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.Baseboard
import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.ComputerSystem
import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.Firmware
import com.tecknobit.kinfo.model.desktop.common.hardware.display.Display
import com.tecknobit.kinfo.model.desktop.common.hardware.display.DisplayInfo
import com.tecknobit.kinfo.model.desktop.common.hardware.graphicscard.GraphicsCard
import com.tecknobit.kinfo.model.desktop.common.hardware.memory.GlobalMemory
import com.tecknobit.kinfo.model.desktop.common.hardware.memory.VirtualMemory
import com.tecknobit.kinfo.model.desktop.common.hardware.storage.HWDiskStore
import com.tecknobit.kinfo.model.desktop.common.hardware.storage.HWPartition
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