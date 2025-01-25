@file:Suppress("PropertyName")

package com.tecknobit.kinfo.model.common

/**
 * `DeviceOrientation` interface defines the device orientation-related properties and methods.
 * This interface is used to represent and check the orientation of a device (such as portrait or landscape).
 * It provides constants to represent orientation states and methods to check the current orientation.
 *
 * It also includes methods to check the current orientation and to retrieve the device's orientation.
 *
 * @author Swapnil Musale
 */
interface DeviceOrientation {

    companion object {

        /**
         * `PORTRAIT` the string representing the portrait orientation of the device
         */
        const val PORTRAIT: String = "portrait"

        /**
         * `LANDSCAPE` the string representing the landscape orientation of the device
         */
        const val LANDSCAPE: String = "landscape"

    }

    /**
     * `isPortrait` returns true if the device is currently in portrait orientation
     */
    val isPortrait: Boolean

    /**
     * `isLandscape` returns true if the device is currently in landscape orientation
     */
    val isLandscape: Boolean

    /**
     * Method to get the current orientation of the device
     *
     * @return the current orientation of the device as [String]
     */
    fun getDeviceOrientation(): String

}