package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.Firmware

/**
 * The `MacOsFirmware` interface defines the contract to expose macOS firmware information and its platform mapping
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see Firmware
 *
 * @since 1.1.0
 */
interface MacOsFirmware : Firmware {

    /**
     * `isAppleSilicon` whether the firmware information uses the Apple Silicon mapping
     *
     * The native mapper derives this flag from the executable architecture rather than detecting Rosetta hardware
     */
    val isAppleSilicon: Boolean

}