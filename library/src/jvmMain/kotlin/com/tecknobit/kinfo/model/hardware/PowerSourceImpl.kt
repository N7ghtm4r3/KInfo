package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.CapacityUnits
import com.tecknobit.kinfo.model.desktop.common.hardware.PowerSource
import java.time.LocalDate

/**
 * The `PowerSourceImpl` class is useful to capture the power source measurements supplied by [oshi.hardware.PowerSource]
 *
 * Measurements are exposed as a fixed snapshot using the units reported by the underlying source
 *
 * @property powerSourceInfo The underlying power source information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see PowerSource
 *
 * @since 1.0.0
 */
class PowerSourceImpl(
    private val powerSourceInfo: oshi.hardware.PowerSource,
) : PowerSource {

    /**
     * `name` the operating system name of the power source
     */
    override val name: String = powerSourceInfo.name

    /**
     * `deviceName` the device-reported name of the power source
     */
    override val deviceName: String = powerSourceInfo.deviceName

    /**
     * `remainingCapacityPercent` the estimated remaining charge as a fraction from zero to one
     *
     * The estimate can differ from the ratio between [currentCapacity] and [maxCapacity]
     */
    override val remainingCapacityPercent: Double = powerSourceInfo.remainingCapacityPercent

    /**
     * `timeRemainingEstimated` the estimated remaining runtime in seconds
     *
     * Minus one indicates that the estimate is being calculated and minus two indicates unlimited runtime
     */
    override val timeRemainingEstimated: Double = powerSourceInfo.timeRemainingEstimated

    /**
     * `timeRemainingInstant` the battery-reported remaining time in seconds
     *
     * While charging, the value can represent the remaining time to reach full charge
     */
    override val timeRemainingInstant: Double = powerSourceInfo.timeRemainingInstant

    /**
     * `powerUsageRate` the signed power rate in milliwatts, positive when charging and negative when discharging
     */
    override val powerUsageRate: Double = powerSourceInfo.powerUsageRate

    /**
     * `voltage` the battery voltage in volts, or minus one when unknown
     */
    override val voltage: Double = powerSourceInfo.voltage

    /**
     * `amperage` the signed battery current in milliamperes, positive when charging and negative when discharging
     */
    override val amperage: Double = powerSourceInfo.amperage

    /**
     * `isPowerOnLine` whether the device is connected to an external power source
     */
    override val isPowerOnLine: Boolean = powerSourceInfo.isPowerOnLine

    /**
     * `isCharging` whether the battery is charging
     */
    override val isCharging: Boolean = powerSourceInfo.isCharging

    /**
     * `isDischarging` whether the battery is discharging
     */
    override val isDischarging: Boolean = powerSourceInfo.isDischarging

    /**
     * `capacityUnits` the units shared by [currentCapacity], [maxCapacity], and [designCapacity]
     */
    override val capacityUnits: CapacityUnits = CapacityUnits.valueOf(powerSourceInfo.capacityUnits.name)

    /**
     * `currentCapacity` the remaining battery capacity in [capacityUnits]
     */
    override val currentCapacity: Int = powerSourceInfo.currentCapacity

    /**
     * `maxCapacity` the full-charge battery capacity in [capacityUnits]
     */
    override val maxCapacity: Int = powerSourceInfo.maxCapacity

    /**
     * `designCapacity` the original design capacity in [capacityUnits]
     */
    override val designCapacity: Int = powerSourceInfo.designCapacity

    /**
     * `cycleCount` the reported number of battery charge cycles, or minus one when unknown
     */
    override val cycleCount: Int = powerSourceInfo.cycleCount

    /**
     * `chemistry` the battery chemistry description
     */
    override val chemistry: String = powerSourceInfo.chemistry

    /**
     * `manufacturer` the battery manufacturer name
     */
    override val manufacturer: String = powerSourceInfo.manufacturer

    /**
     * `serialNumber` the battery serial number
     */
    override val serialNumber: String = powerSourceInfo.serialNumber

    /**
     * `temperature` the battery temperature in degrees Celsius, or zero when unknown
     */
    override val temperature: Double = powerSourceInfo.temperature

    /**
     * `updateAttributes` the result of refreshing the wrapped source during construction
     *
     * Refreshing the source does not update the measurements stored in this snapshot
     */
    override val updateAttributes: Boolean = powerSourceInfo.updateAttributes()

    /**
     * Method used to retrieve the battery manufacture date
     *
     * @return the manufacture date, or null when unavailable, as [LocalDate]
     */
    fun getManufacturerDate(): LocalDate? {
        return powerSourceInfo.manufactureDate
    }

}
