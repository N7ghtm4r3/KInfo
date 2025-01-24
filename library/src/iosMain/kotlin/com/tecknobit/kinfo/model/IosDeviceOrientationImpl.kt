package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.DeviceOrientation.Companion.LANDSCAPE
import com.tecknobit.kinfo.model.common.DeviceOrientation.Companion.PORTRAIT
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene

/**
 * `IosDeviceOrientationImpl` is an implementation of the [DeviceOrientation] interface specific to iOS devices.
 * This class retrieves the current orientation of the iOS device by accessing the window's interface orientation from the UIKit framework.
 * It uses `UIApplication.sharedApplication.connectedScenes` to determine the device's orientation.
 *
 * The class provides properties and methods to check whether the device is in portrait or landscape mode.
 *
 * @author N7ghtm4r3 - Swapnil Musale
 */
internal class IosDeviceOrientationImpl : DeviceOrientation {

    /**
     * `windows` represents the list of windows currently active in the iOS application.
     */
    private val windows = (UIApplication.sharedApplication.connectedScenes.first() as UIWindowScene).windows

    /**
     * `windowOrientation` holds the current interface orientation of the first window, which is converted to an integer value.
     */
    private val windowOrientation = (windows.first() as UIWindow).windowScene?.interfaceOrientation?.toInt()

    /**
     * `isPortrait` checks if the device is currently in portrait mode.
     *
     * @return true if the device is in portrait orientation, false otherwise.
     */
    override val isPortrait: Boolean
        get() = windowOrientation == 1

    /**
     * `isLandscape` checks if the device is currently in landscape mode.
     *
     * @return true if the device is in landscape orientation, false otherwise.
     */
    override val isLandscape: Boolean
        get() = windowOrientation == 3 || windowOrientation == 4

    /**
     * Method to get the current orientation of the device
     *
     * @return the current orientation of the device as [String]
     */
    override fun getDeviceOrientation(): String {
        return when (windowOrientation) {
            0 -> UNKNOWN
            1 -> PORTRAIT
            3, 4 -> LANDSCAPE
            else -> UNKNOWN
        }
    }

}
