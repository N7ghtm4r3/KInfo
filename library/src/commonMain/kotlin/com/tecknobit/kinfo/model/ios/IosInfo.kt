package com.tecknobit.kinfo.model.ios

import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.Locale

/**
 * `IosInfo` interface defines the properties for retrieving detailed information about an iOS device and its system environment.
 * This interface provides access to various device and application details specific to iOS, such as the device model, system version,
 * and app information, along with system-specific properties like orientation and multitasking support.
 *
 * @author Swapnil Musale
 */
interface IosInfo {

    /**
     * `name` the name of the device (e.g., "iPhone 12")
     */
    val name: String

    /**
     * `systemName` the name of the operating system (e.g., "iOS")
     */
    val systemName: String

    /**
     * `systemVersion` the version of the operating system (e.g., "14.4")
     */
    val systemVersion: String

    /**
     * `model` the model identifier of the device (e.g., "iPhone12,1")
     */
    val model: String

    /**
     * `localizedModel` the localized model name (e.g., "iPhone")
     */
    val localizedModel: String

    /**
     * `identifierForVendor` the identifier for the vendor associated with the app (e.g., "E123456789")
     */
    val identifierForVendor: String

    /**
     * `isPhysicalDevice` indicates whether the device is a physical device or a simulator
     */
    val isPhysicalDevice: Boolean

    /**
     * `isMultitaskingSupported` indicates whether the device supports multitasking
     */
    val isMultitaskingSupported: Boolean

    /**
     * `isGeneratingDeviceOrientationNotifications` indicates whether the device is generating notifications for orientation changes
     */
    val isGeneratingDeviceOrientationNotifications: Boolean

    /**
     * `deviceOrientation` an instance of [DeviceOrientation] that provides the current orientation of the device
     */
    val deviceOrientation: DeviceOrientation

    /**
     * `appName` the name of the app (e.g., "MyApp")
     */
    val appName: String

    /**
     * `bundleId` the unique identifier for the app bundle (e.g., "com.example.myapp")
     */
    val bundleId: String

    /**
     * `appVersion` the version of the app (e.g., "1.0.0")
     */
    val appVersion: String

    /**
     * `appShortVersion` the short version of the app (e.g., "1.0")
     */
    val appShortVersion: String

    /**
     * `locale` an instance of [Locale] representing the current language and region of the device
     */
    val locale: Locale

    /**
     * `isDebug` indicates whether the app is running in debug mode
     */
    val isDebug: Boolean

}
