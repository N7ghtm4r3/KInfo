package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.CapacityUnits
import com.tecknobit.kinfo.model.desktop.hardware.PowerSource
import java.time.LocalDate

/**
 * Implementation of the `PowerSource` interface.
 * This class provides details about a power source, including its name, device name, capacity, voltage, amperage, and more.
 * It also offers methods for accessing information about the power source's status, such as whether it is charging or discharging,
 * as well as the estimated remaining time for use.
 *
 * @param powerSourceInfo The `PowerSource` object containing the actual power source data retrieved from the hardware.
 *
 * @author N7ghtm4r3
 *
 * @see PowerSource
 */
class PowerSourceImpl(
    private val powerSourceInfo: oshi.hardware.PowerSource,
) : PowerSource {

    /**
     * `name` The name of the power source (e.g., "Battery 1")
     */
    override val name: String = powerSourceInfo.name

    /**
     * `deviceName` The device name of the power source (e.g., "BAT0", "AC")
     */
    override val deviceName: String = powerSourceInfo.deviceName

    /**
     * `remainingCapacityPercent` The remaining battery capacity as a percentage of the total capacity (0-100%)
     */
    override val remainingCapacityPercent: Double = powerSourceInfo.remainingCapacityPercent

    /**
     * `timeRemainingEstimated` The estimated time remaining on the power source, in minutes
     */
    override val timeRemainingEstimated: Double = powerSourceInfo.timeRemainingEstimated

    /**
     * `timeRemainingInstant` The instantaneous time remaining on the power source, in minutes
     */
    override val timeRemainingInstant: Double = powerSourceInfo.timeRemainingInstant

    /**
     * `powerUsageRate` The current power usage rate of the power source in watts
     */
    override val powerUsageRate: Double = powerSourceInfo.powerUsageRate

    /**
     * `voltage` The voltage of the power source in volts
     */
    override val voltage: Double = powerSourceInfo.voltage

    /**
     * `amperage` The amperage of the power source in amperes
     */
    override val amperage: Double = powerSourceInfo.amperage

    /**
     * `isPowerOnLine` Whether the power source is currently connected to the power line (e.g., AC power)
     */
    override val isPowerOnLine: Boolean = powerSourceInfo.isPowerOnLine

    /**
     * `isCharging` Whether the power source is currently charging
     */
    override val isCharging: Boolean = powerSourceInfo.isCharging

    /**
     * `isDischarging` Whether the power source is currently discharging
     */
    override val isDischarging: Boolean = powerSourceInfo.isDischarging

    /**
     * `capacityUnits` The capacity units of the power source (e.g., "mWh", "Wh")
     */
    override val capacityUnits: CapacityUnits = CapacityUnits.valueOf(powerSourceInfo.capacityUnits.name)

    /**
     * `currentCapacity` The current capacity of the power source in the given capacity units
     */
    override val currentCapacity: Int = powerSourceInfo.currentCapacity

    /**
     * `maxCapacity` The maximum capacity of the power source in the given capacity units
     */
    override val maxCapacity: Int = powerSourceInfo.maxCapacity

    /**
     * `designCapacity` The design capacity of the power source in the given capacity units
     */
    override val designCapacity: Int = powerSourceInfo.designCapacity

    /**
     * `cycleCount` The cycle count of the power source, representing the number of charge-discharge cycles
     */
    override val cycleCount: Int = powerSourceInfo.cycleCount

    /**
     * `chemistry` The chemistry of the power source (e.g., "Li-ion", "Li-Po")
     */
    override val chemistry: String = powerSourceInfo.chemistry

    /**
     * `manufacturer` The manufacturer of the power source (e.g., "Samsung", "LG")
     */
    override val manufacturer: String = powerSourceInfo.manufacturer

    /**
     * `serialNumber` The serial number of the power source
     */
    override val serialNumber: String = powerSourceInfo.serialNumber

    /**
     * `temperature` The temperature of the power source in Celsius
     */
    override val temperature: Double = powerSourceInfo.temperature

    /**
     * `updateAttributes` Whether the attributes of the power source have been updated
     */
    override val updateAttributes: Boolean = powerSourceInfo.updateAttributes()

    /**
     * Returns the manufacture date of the power source
     *
     * @return manufacture date as [LocalDate]
     */
    fun getManufacturerDate(): LocalDate {
        return powerSourceInfo.manufactureDate
    }

}
