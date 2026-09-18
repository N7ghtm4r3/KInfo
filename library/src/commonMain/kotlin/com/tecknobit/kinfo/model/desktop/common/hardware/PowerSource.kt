package com.tecknobit.kinfo.model.desktop.common.hardware

/**
 * The `PowerSource` interface defines the contract to expose battery capacity, electrical measurements, and charging state
 *
 * Measurement scales and unavailable values depend on the platform implementation
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.0
 */
interface PowerSource {

    /**
     * `name` the operating system name of the power source
     */
    val name: String

    /**
     * `deviceName` the device-reported name of the power source
     */
    val deviceName: String

    /**
     * `remainingCapacityPercent` the remaining charge, expressed from zero to one hundred on native macOS
     * and as a fraction from zero to one on `JVM`
     *
     * The system estimate can differ from the ratio between [currentCapacity] and [maxCapacity]
     */
    val remainingCapacityPercent: Double

    /**
     * `timeRemainingEstimated` the estimated remaining runtime in hours on native macOS and seconds on `JVM`
     *
     * Unavailable readings and special values follow the platform implementation
     */
    val timeRemainingEstimated: Double

    /**
     * `timeRemainingInstant` the battery-reported remaining time in hours on native macOS and seconds on `JVM`
     *
     * Charging and unavailable readings follow the platform implementation
     */
    val timeRemainingInstant: Double

    /**
     * `powerUsageRate` the signed power rate in watts on native macOS and milliwatts on `JVM`
     *
     * Positive values indicate charging and negative values indicate discharging
     */
    val powerUsageRate: Double

    /**
     * `voltage` the battery voltage in volts, with unavailable values defined by the platform implementation
     */
    val voltage: Double

    /**
     * `amperage` the signed battery current in amperes on native macOS and milliamperes on `JVM`
     *
     * Positive values indicate charging and negative values indicate discharging
     */
    val amperage: Double

    /**
     * `isPowerOnLine` whether the device is connected to an external power source
     */
    val isPowerOnLine: Boolean

    /**
     * `isCharging` whether the battery is charging
     */
    val isCharging: Boolean

    /**
     * `isDischarging` whether the battery is discharging
     */
    val isDischarging: Boolean

    /**
     * `capacityUnits` the units shared by [currentCapacity], [maxCapacity], and [designCapacity]
     */
    val capacityUnits: CapacityUnits

    /**
     * `currentCapacity` the remaining battery capacity in [capacityUnits]
     */
    val currentCapacity: Int

    /**
     * `maxCapacity` the full-charge battery capacity in [capacityUnits]
     */
    val maxCapacity: Int

    /**
     * `designCapacity` the original design capacity in [capacityUnits]
     */
    val designCapacity: Int

    /**
     * `cycleCount` the reported number of battery charge cycles
     */
    val cycleCount: Int

    /**
     * `chemistry` the battery chemistry description, or an unknown marker when unavailable
     */
    val chemistry: String

    /**
     * `manufacturer` the battery manufacturer name, which can be blank or unknown when unavailable
     */
    val manufacturer: String

    /**
     * `serialNumber` the battery serial number, which can be empty or unknown when unavailable
     */
    val serialNumber: String

    /**
     * `temperature` the battery temperature in degrees Celsius, with unavailable values defined by the platform implementation
     */
    val temperature: Double

    /**
     * `updateAttributes` the stored refresh status of the power source
     *
     * Reading this property does not trigger another refresh
     */
    val updateAttributes: Boolean

}

/**
 * The `CapacityUnits` enum is useful to represent the units shared by battery capacity measurements
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.0
 */
enum class CapacityUnits {

    /**
     * `MWH` the energy capacity expressed in milliwatt-hours
     */
    MWH,

    /**
     * `MAH` the charge capacity expressed in milliampere-hours
     */
    MAH,

    /**
     * `RELATIVE` the capacity expressed on a shared scale without a physical measurement unit
     *
     * Capacity values can be compared only when they use the same relative scale
     */
    RELATIVE

}