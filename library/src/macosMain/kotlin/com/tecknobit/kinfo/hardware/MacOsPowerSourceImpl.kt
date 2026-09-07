package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.CapacityUnits
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPowerSource

data class MacOsPowerSourceImpl(
    override val name: String,
    override val deviceName: String,
    override val remainingCapacityPercent: Double,
    override val timeRemainingEstimated: Double,
    override val timeRemainingInstant: Double,
    override val powerUsageRate: Double,
    override val voltage: Double,
    override val amperage: Double,
    override val isPowerOnLine: Boolean,
    override val isCharging: Boolean,
    override val isDischarging: Boolean,
    override val capacityUnits: CapacityUnits,
    override val currentCapacity: Int,
    override val maxCapacity: Int,
    override val designCapacity: Int,
    override val cycleCount: Int,
    override val chemistry: String,
    override val manufacturer: String,
    override val serialNumber: String,
    override val temperature: Double,
    override val updateAttributes: Boolean
) : MacOsPowerSource
