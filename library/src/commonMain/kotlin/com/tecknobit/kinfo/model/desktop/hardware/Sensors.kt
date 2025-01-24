package com.tecknobit.kinfo.model.desktop.hardware

interface Sensors {
    val cpuTemperature: Double
    val fanSpeeds: IntArray
    val cpuVoltage: Double
}