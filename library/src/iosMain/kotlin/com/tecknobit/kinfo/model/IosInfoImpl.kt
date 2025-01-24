package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.Locale
import com.tecknobit.kinfo.model.ios.IosInfo
import platform.Foundation.*
import platform.UIKit.UIDevice.Companion.currentDevice
import kotlin.experimental.ExperimentalNativeApi

/**
 * `IosInfoImpl` is an implementation of the [IosInfo] interface that retrieves detailed information about an iOS device and application.
 * This class provides access to various system and application properties such as device name, model, system version, and app version.
 * It also includes information related to device orientation, multitasking support, and locale.
 *
 * @author Swapnil Musale
 * 
 * @see IosInfo
 */
internal class IosInfoImpl : IosInfo {

    /**
     * `name` the name of the current iOS device (e.g., "iPhone 12")
     */
    override val name: String = currentDevice.name

    /**
     * `systemName` the name of the iOS operating system (e.g., "iOS")
     */
    override val systemName: String = currentDevice.systemName

    /**
     * `systemVersion` the version of the iOS operating system (e.g., "14.4")
     */
    override val systemVersion: String = currentDevice.systemVersion

    /**
     * `model` the model identifier of the current iOS device (e.g., "iPhone12,1")
     */
    override val model: String = currentDevice.model

    /**
     * `localizedModel` the localized model name of the current iOS device (e.g., "iPhone")
     */
    override val localizedModel: String = currentDevice.localizedModel

    /**
     * `identifierForVendor` the unique identifier for the vendor associated with the app (e.g., "E123456789")
     */
    override val identifierForVendor: String = currentDevice.identifierForVendor?.UUIDString.orEmpty()

    /**
     * `isPhysicalDevice` indicates whether the current device is a physical device or a simulator
     */
    override val isPhysicalDevice: Boolean = NSProcessInfo.processInfo.environment["SIMULATOR_UDID"] == null

    /**
     * `isMultitaskingSupported` indicates whether the current iOS device supports multitasking
     */
    override val isMultitaskingSupported: Boolean = currentDevice.isMultitaskingSupported()

    /**
     * `isGeneratingDeviceOrientationNotifications` indicates whether the current device is generating notifications for orientation changes
     */
    override val isGeneratingDeviceOrientationNotifications: Boolean = currentDevice.isGeneratingDeviceOrientationNotifications()

    /**
     * `deviceOrientation` an instance of [DeviceOrientation] that provides the current orientation of the iOS device
     */
    override val deviceOrientation: DeviceOrientation by lazy {
        IosDeviceOrientationImpl()
    }

    /**
     * `appName` the name of the app (e.g., "MyApp")
     */
    override val appName: String
        get() = (NSBundle.mainBundle.infoDictionary?.get("CFBundleDisplayName")
            ?: NSBundle.mainBundle.infoDictionary?.get("CFBundleName")) as String

    /**
     * `bundleId` the unique identifier for the app bundle (e.g., "com.example.myapp")
     */
    override val bundleId: String = NSBundle.mainBundle.bundleIdentifier.orEmpty()

    /**
     * `appVersion` the version of the app (e.g., "1.0.0")
     */
    override val appVersion: String = NSBundle.mainBundle.infoDictionary?.get("CFBundleVersion") as String

    /**
     * `appShortVersion` the short version of the app (e.g., "1.0")
     */
    override val appShortVersion: String = NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as String

    /**
     * `locale` an instance of [Locale] representing the current language and region of the device
     */
    override val locale: Locale = Locale(
        languageCode = NSLocale.currentLocale.languageCode,
        region = NSLocale.currentLocale.regionCode.orEmpty()
    )

    /**
     * `isDebug` indicates whether the app is running in debug mode
     */
    @OptIn(ExperimentalNativeApi::class)
    override val isDebug: Boolean = Platform.isDebugBinary
    
}
