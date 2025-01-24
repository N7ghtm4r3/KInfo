package com.tecknobit.kinfo.model.hardware

import com.tecknobit.kinfo.model.desktop.hardware.Sensors

class SensorsImpl(private val sensorsInfo: oshi.hardware.Sensors) : Sensors {

    override val cpuTemperature: Double
        get() = sensorsInfo.cpuTemperature

    override val fanSpeeds: IntArray
        get() = sensorsInfo.fanSpeeds

    override val cpuVoltage: Double
        get() = sensorsInfo.cpuVoltage
}