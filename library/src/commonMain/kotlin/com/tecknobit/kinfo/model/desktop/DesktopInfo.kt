package com.tecknobit.kinfo.model.desktop

import com.tecknobit.kinfo.TO_BE_FILLED_BY_OEM
import com.tecknobit.kinfo.model.desktop.hardware.Hardware
import com.tecknobit.kinfo.model.desktop.operatingsystem.OperatingSystem

/**
 * Represents information about the desktop environment.
 * Provides details about the operating system and hardware of the desktop.
 *
 * @author N7ghtm4r3
 */
interface DesktopInfo {

    companion object {

        /**
         * Method used to check whether a value matches with the [TO_BE_FILLED_BY_OEM] constant
         *
         * @return whether a value matches with the [TO_BE_FILLED_BY_OEM] constant as [Boolean]
         */
        fun String.isToBeFilledByOEM(): Boolean {
            return this == TO_BE_FILLED_BY_OEM
        }

        /**
         * Method used to eventually replace a [TO_BE_FILLED_BY_OEM] value with an arbitrary one
         *
         * @param useInstead Callback invoked when the value [isToBeFilledByOEM] and it is used to retrieve the
         * replacer value to use instead of the original one
         *
         * @return the original value when not matches with [TO_BE_FILLED_BY_OEM] constant, [useInstead] return value
         * when matches instead as [String]
         */
        fun String.whenIsToBeFilledByOEM(
            useInstead: () -> String
        ) : String {
            return if(isToBeFilledByOEM())
                useInstead()
            else
                this
        }

    }

    /**
     * `operatingSystem` The operating system running on the desktop.
     */
    val operatingSystem: OperatingSystem

    /**
     * `hardware` The hardware information of the desktop, including details about CPU, RAM, and other components.
     */
    val hardware: Hardware

}
