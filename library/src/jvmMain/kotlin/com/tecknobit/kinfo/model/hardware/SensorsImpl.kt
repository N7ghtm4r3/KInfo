package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.Sensors

/**
 * Implementation of the `Sensors` interface.
 * This class provides access to sensor data for the computer system, including CPU temperature, fan speeds, and CPU voltage.
 *
 * @param sensorsInfo The raw sensor information provided by the hardware
 *
 * @author N7ghtm4r3
 *
 * @see Sensors
 */
class SensorsImpl(
    sensorsInfo: oshi.hardware.Sensors
) : Sensors {

    /**
     * `cpuTemperature` The current temperature of the CPU in degrees Celsius
     */
    override val cpuTemperature: Double = sensorsInfo.cpuTemperature

    /**
     * `fanSpeeds` The speeds of the fans in the system, in RPM (Revolutions per Minute)
     */
    override val fanSpeeds: IntArray = sensorsInfo.fanSpeeds

    /**
     * `cpuVoltage` The current voltage supplied to the CPU, in volts
     */
    override val cpuVoltage: Double = sensorsInfo.cpuVoltage

}
