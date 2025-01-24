package com.tecknobit.kinfo.model.desktop

import com.tecknobit.kinfo.model.desktop.hardware.Hardware
import com.tecknobit.kinfo.model.desktop.operatingsystem.OperatingSystem

interface DesktopInfo {
    val operatingSystem: OperatingSystem
    val hardware: Hardware
}