package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.CapacityUnits
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPowerSource

/**
 * The `MacOsPowerSourceImpl` class is useful to store a snapshot of macOS power source measurements
 *
 * Each instance represents a fixed snapshot of the supplied battery information
 *
 * @property name The operating system name of the power source
 * @property deviceName The device-reported name of the power source
 * @property remainingCapacityPercent The remaining charge expressed as a percentage on a scale from zero to one hundred
 * @property timeRemainingEstimated The estimated remaining runtime in hours
 * @property timeRemainingInstant The instantaneous remaining runtime in hours, with negative values indicating unavailable data
 * @property powerUsageRate The signed power rate in watts, positive when charging and negative when discharging
 * @property voltage The battery voltage in volts
 * @property amperage The signed battery current in amperes, positive when charging and negative when discharging
 * @property isPowerOnLine Whether the device is connected to an external power source
 * @property isCharging Whether the battery reports that it is charging
 * @property isDischarging Whether the battery is discharging
 * @property capacityUnits The units shared by [currentCapacity], [maxCapacity], and [designCapacity]
 * @property currentCapacity The remaining battery capacity in [capacityUnits]
 * @property maxCapacity The full-charge battery capacity in [capacityUnits]
 * @property designCapacity The original design capacity in [capacityUnits]
 * @property cycleCount The reported number of battery charge cycles
 * @property chemistry The battery chemistry description, or an unknown marker when unavailable
 * @property manufacturer The manufacturer name, which can be blank or unknown when unavailable
 * @property serialNumber The battery serial number, which can be empty when unavailable
 * @property temperature The battery temperature in degrees Celsius
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsPowerSource
 *
 * @since 1.1.0
 */
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
    override val temperature: Double
) : MacOsPowerSource {

    /**
     * `updateAttributes` whether this snapshot refreshes its attributes, always false
     */
    override val updateAttributes: Boolean = false

}
