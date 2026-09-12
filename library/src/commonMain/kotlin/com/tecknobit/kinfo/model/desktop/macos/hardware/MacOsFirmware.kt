package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.Firmware

interface MacOsFirmware : Firmware {

    val isAppleSilicon: Boolean

}