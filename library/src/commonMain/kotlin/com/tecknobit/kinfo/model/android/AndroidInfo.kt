@file:Suppress("PropertyName")

package com.tecknobit.kinfo.model.android

import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.Locale

/**
 * The `AndroidInfo` interface representing detailed information about an Android device and its system environment
 *
 * @author Swapnil Musale
 */
interface AndroidInfo {

    /**
     * `appName` the name of the application
     */
    val appName: String

    /**
     * `packageName` the package name of the application
     */
    val packageName: String

    /**
     * `version` the version information of the application or system, encapsulated in the Version class
     */
    val version: Version

    /**
     * `board` the board name of the device hardware (e.g., "msm8974")
     */
    val board: String

    /**
     * `bootloader` the version of the device bootloader
     */
    val bootloader: String

    /**
     * `device` the device name (e.g., "hammerhead" for Nexus 5)
     */
    val device: String

    /**
     * `display` the display identifier for the build (e.g., "KOT49H")
     */
    val display: String

    /**
     * `fingerprint` the unique identifier for the build fingerprint
     */
    val fingerprint: String

    /**
     * `hardware` the name of the device hardware (e.g., "qcom" for Qualcomm chipsets)
     */
    val hardware: String

    /**
     * `host` the host name used to build the system
     */
    val host: String

    /**
     * `id` the build ID for the software (e.g., "KTU84P")
     */
    val id: String

    /**
     * `manufacturer` the name of the device manufacturer (e.g., "Google")
     */
    val manufacturer: String

    /**
     * `model` the model name of the device (e.g., "Nexus 5")
     */
    val model: String

    /**
     * `brand` the brand name of the device (e.g., "Samsung, Xiaomi")
     */
    val brand: String

    /**
     * `product` the product name of the device (e.g., "hammerhead")
     */
    val product: String

    /**
     * `supportedAbis` an array of supported ABIs (Application Binary Interfaces) for the device
     */
    val supportedAbis: Array<String>

    /**
     * `supported32BitAbis` an array of supported 32-bit ABIs for the device
     */
    val supported32BitAbis: Array<String>

    /**
     * `supported64BitAbis` an array of supported 64-bit ABIs for the device
     */
    val supported64BitAbis: Array<String>

    /**
     * `tags` comma-separated tags associated with the build (e.g., "release", "test-keys")
     */
    val tags: String

    /**
     * `isPhysicalDevice` indicates whether the device is a physical device (`true`) or an emulator (`false`)
     */
    val isPhysicalDevice: Boolean

    /**
     * `systemFeatureList` a list of system features available on the device
     */
    val systemFeatureList: List<String>

    /**
     * `displayMetrics` display metrics containing screen properties such as density and resolution
     */
    val displayMetrics: DisplayMetrics

    /**
     * `VERSION_CODES` an enumeration of version codes, represented by the VersionCode object
     */
    val VERSION_CODES: VersionCode

    /**
     * `versionName` the version name of the application or system (e.g., "1.0.0")
     */
    val versionName: String?

    /**
     * `versionCode` the version code of the application or system, represented as a `Long`
     */
    val versionCode: Long

    /**
     * `locale` the locale information of the device (e.g., "en_US")
     */
    val locale: Locale

    /**
     * `deviceOrientation` the current orientation of the device (e.g., portrait or landscape)
     */
    val deviceOrientation: DeviceOrientation

    /**
     * `androidId` the unique Android ID of the device
     */
    val androidId: String

    /**
     * `isDebug` indicates whether the application is running in debug mode
     */
    val isDebug: Boolean

    /**
     * `androidCodename` is the codename of the Android OS version,
     * following the [official list](https://developer.android.com/reference/android/os/Build.VERSION_CODES)
     */
    val androidCodename: String

}
