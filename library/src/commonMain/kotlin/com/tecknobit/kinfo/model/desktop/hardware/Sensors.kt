package com.tecknobit.kinfo.model.desktop.hardware

/**
 * Interface that provides access to various sensor data for a computer system.
 * This includes CPU temperature, fan speeds, and CPU voltage
 *
 * @author N7ghtm4r3
 *
 * @see Sensors
 */
interface Sensors {

    /**
     * `cpuTemperature` The current temperature of the CPU in degrees Celsius
     */
    val cpuTemperature: Double

    /**
     * `fanSpeeds` The speeds of the fans in the system, in RPM (Revolutions per Minute)
     */
    val fanSpeeds: IntArray

    /**
     * `cpuVoltage` The current voltage supplied to the CPU, in volts
     */
    val cpuVoltage: Double

}
