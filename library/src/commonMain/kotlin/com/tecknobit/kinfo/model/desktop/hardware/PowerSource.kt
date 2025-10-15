package com.tecknobit.kinfo.model.desktop.hardware

/**
 * Represents a power source (e.g., a battery) in the system, including details like the remaining capacity,
 * power usage, voltage, and charging status.
 *
 * @author N7ghtm4r3
 */
interface PowerSource {

    /**
     * `name` The name of the power source (e.g., "Battery 1")
     */
    val name: String

    /**
     * `deviceName` The name of the device associated with the power source
     */
    val deviceName: String

    /**
     * `remainingCapacityPercent` The remaining capacity of the power source as a percentage of the total capacity
     */
    val remainingCapacityPercent: Double

    /**
     * `timeRemainingEstimated` The estimated time remaining on the power source (in hours)
     */
    val timeRemainingEstimated: Double

    /**
     * `timeRemainingInstant` The instantaneous time remaining on the power source (in hours)
     */
    val timeRemainingInstant: Double

    /**
     * `powerUsageRate` The rate of power usage by the power source (in watts)
     */
    val powerUsageRate: Double

    /**
     * `voltage` The voltage of the power source (in volts)
     */
    val voltage: Double

    /**
     * `amperage` The amperage of the power source (in amperes)
     */
    val amperage: Double

    /**
     * `isPowerOnLine` Whether the power source is connected to an external power line
     */
    val isPowerOnLine: Boolean

    /**
     * `isCharging` Whether the power source is currently charging
     */
    val isCharging: Boolean

    /**
     * `isDischarging` Whether the power source is currently discharging
     */
    val isDischarging: Boolean

    /**
     * `capacityUnits` The units for capacity
     */
    val capacityUnits: CapacityUnits

    /**
     * `currentCapacity` The current capacity of the power source (in mAh or Ah, depending on `capacityUnits`)
     */
    val currentCapacity: Int

    /**
     * `maxCapacity` The maximum capacity of the power source (in mAh or Ah, depending on `capacityUnits`)
     */
    val maxCapacity: Int

    /**
     * `designCapacity` The designed capacity of the power source (in mAh or Ah, depending on `capacityUnits`)
     */
    val designCapacity: Int

    /**
     * `cycleCount` The number of charge cycles the power source has gone through
     */
    val cycleCount: Int

    /**
     * `chemistry` The chemistry used in the power source (e.g., Li-ion, NiMH)
     */
    val chemistry: String

    /**
     * `manufacturer` The manufacturer of the power source
     */
    val manufacturer: String

    /**
     * `serialNumber` The serial number of the power source
     */
    val serialNumber: String

    /**
     * `temperature` The temperature of the power source (in Celsius)
     */
    val temperature: Double

    /**
     * `updateAttributes` Whether the attributes of the power source should be updated
     */
    val updateAttributes: Boolean

}

/**
 * Units of Battery Capacity
 */
enum class CapacityUnits {

    /**
     * MilliWattHours (mWh).
     */
    MWH,

    /**
     * MilliAmpHours (mAh). Should be multiplied by voltage to convert to mWh.
     */
    MAH,

    /**
     * Relative units. The specific units are not defined. The ratio of current/max capacity still represents state
     * of charge and the ratio of max/design capacity still represents state of health.
     */
    RELATIVE

}