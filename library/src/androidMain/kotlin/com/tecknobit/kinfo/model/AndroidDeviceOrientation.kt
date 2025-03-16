package com.tecknobit.kinfo.model

import android.content.res.Configuration
import com.tecknobit.equinoxcore.utilities.AppContext
import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.DeviceOrientation.Companion.LANDSCAPE
import com.tecknobit.kinfo.model.common.DeviceOrientation.Companion.PORTRAIT

/**
 * `AndroidDeviceOrientation` class implements the [DeviceOrientation] interface to handle device orientation specifically for Android devices.
 * This class retrieves the current orientation of the device (portrait, landscape, or unknown) based on the system configuration.
 * It checks the device's orientation and provides properties and methods to determine whether the device is in portrait or landscape mode.
 *
 * It uses the `Resources.configuration.orientation` property to determine the current orientation of the device.
 *
 * @author Swapnil Musale
 *
 * @see DeviceOrientation
 */
internal class AndroidDeviceOrientation : DeviceOrientation {

    /**
     * `orientation` holds the current orientation of the device retrieved from the system configuration
     */
    private val orientation: Int = AppContext.get().resources.configuration.orientation

    /**
     * `isPortrait` checks if the device is currently in portrait mode
     *
     * @return true if the device is in portrait orientation, false otherwise
     */
    override val isPortrait: Boolean
        get() = orientation == Configuration.ORIENTATION_PORTRAIT

    /**
     * `isLandscape` checks if the device is currently in landscape mode
     *
     * @return true if the device is in landscape orientation, false otherwise
     */
    override val isLandscape: Boolean
        get() = orientation == Configuration.ORIENTATION_LANDSCAPE

    /**
     * Method to get the current orientation of the device
     *
     * @return the current orientation of the device as [String]
     */
    override fun getDeviceOrientation(): String {
        return when (orientation) {
            0 -> UNKNOWN
            1 -> PORTRAIT
            2 -> LANDSCAPE
            else -> UNKNOWN
        }
    }

}
