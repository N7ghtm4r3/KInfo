package com.tecknobit.kinfo.model.desktop.macos

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHardware
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem

interface MacOsInfo {

    val operatingSystem: MacOsOperatingSystem

    val hardware: MacOsHardware

}