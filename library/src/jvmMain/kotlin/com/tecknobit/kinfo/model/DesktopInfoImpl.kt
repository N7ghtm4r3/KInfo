package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.desktop.hardware.Hardware
import com.tecknobit.kinfo.model.desktop.operatingsystem.OperatingSystem
import com.tecknobit.kinfo.model.hardware.HardwareImpl
import com.tecknobit.kinfo.model.operatingsystem.OperatingSystemImpl
import oshi.SystemInfo

class DesktopInfoImpl : DesktopInfo {

    private val systemInfo by lazy { SystemInfo() }

    private val operatingSystemImpl by lazy {
        OperatingSystemImpl(systemInfo = systemInfo)
    }

    private val hardwareImpl by lazy {
        HardwareImpl(systemInfo = systemInfo)
    }

    override val operatingSystem: OperatingSystem
        get() = operatingSystemImpl

    override val hardware: Hardware
        get() = hardwareImpl

}