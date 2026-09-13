package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.OperatingSystem

interface MacOsComputerSystem : OperatingSystem {

    /**
     * `baseboard` the baseboard information
     */
    val baseboard: MacOsBaseboard

    /**
     * `firmware` the firmware information
     */
    val firmware: MacOsFirmware

}