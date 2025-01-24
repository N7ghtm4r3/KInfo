package com.tecknobit.kinfo.model

import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.LocaleManagerCompat
import androidx.core.content.pm.PackageInfoCompat
import com.tecknobit.equinoxcompose.utilities.context.AppContext
import com.tecknobit.kinfo.BuildConfig
import com.tecknobit.kinfo.model.android.AndroidInfo
import com.tecknobit.kinfo.model.android.DisplayMetrics
import com.tecknobit.kinfo.model.android.Version
import com.tecknobit.kinfo.model.android.VersionCode
import com.tecknobit.kinfo.model.common.DeviceOrientation
import com.tecknobit.kinfo.model.common.Locale

/**
 * The `AndroidInfoImpl` class providing an implementation of the `AndroidInfo` interface.
 * It retrieves detailed information about an Android device and its system environment.
 *
 * @author Swapnil Musale
 *
 * @see AndroidInfo
 */
internal class AndroidInfoImpl : AndroidInfo {

    internal companion object {

        /**
         * `HARDWARE_GENERIC` represents a "generic" hardware, typically used for emulators.
         */
        const val HARDWARE_GENERIC = "generic"

        /**
         * `HARDWARE_GOLDFISH` represents a device with "goldfish" hardware, used in some emulators.
         */
        const val HARDWARE_GOLDFISH = "goldfish"

        /**
         * `HARDWARE_RANCHU` represents a device with "ranchu" hardware, used in some emulators.
         */
        const val HARDWARE_RANCHU = "ranchu"

        /**
         * `MODEL_GOOGLE_SDK` represents a model "google_sdk", used for Google's SDK devices.
         */
        const val MODEL_GOOGLE_SDK = "google_sdk"

        /**
         * `MODEL_EMULATOR` represents a model "Emulator", used to identify an emulator.
         */
        const val MODEL_EMULATOR = "Emulator"

        /**
         * `MODEL_ANDROID_SDK_X86` represents a model "Android SDK built for x86", used for x86 emulators.
         */
        const val MODEL_ANDROID_SDK_X86 = "Android SDK built for x86"

        /**
         * `MANUFACTURER_GENYMOTION` represents the manufacturer "Genymotion", a platform used for emulation.
         */
        const val MANUFACTURER_GENYMOTION = "Genymotion"

        /**
         * `PRODUCT_SDK` represents the product "sdk", used to identify an SDK emulator.
         */
        const val PRODUCT_SDK = "sdk"

        /**
         * `PRODUCT_VBOX86P` represents the product "vbox86p", used in some emulator configurations.
         */
        const val PRODUCT_VBOX86P = "vbox86p"

        /**
         * `PRODUCT_EMULATOR` represents the product "emulator", used to identify an emulator.
         */
        const val PRODUCT_EMULATOR = "emulator"

    }

    /**
     * `appContext` lazy initialization of the application context
     */
    private val appContext by lazy {
        AppContext.get()
    }

    /**
     * `packageManager` lazy initialization of the PackageManager for the app context
     */
    private val packageManager: PackageManager by lazy {
        appContext.packageManager
    }

    /**
     * `packageInfo` lazy initialization of the application's PackageInfo
     */
    private val packageInfo: PackageInfo by lazy {
        packageManager.getPackageInfo(appContext.packageName, 0)
    }

    /**
     * `androidVersion` lazy initialization of the AndroidVersion object
     */
    private val androidVersion: Version by lazy {
        AndroidVersionImpl()
    }

    /**
     * `androidVersionCode` lazy initialization of the VersionCode object
     */
    private val androidVersionCode: VersionCode by lazy {
        AndroidVersionCodeImpl()
    }

    /**
     * `androidDisplayMetrics` lazy initialization of the DisplayMetrics object
     */
    private val androidDisplayMetrics: DisplayMetrics by lazy {
        AndroidDisplayMetricsImpl()
    }

    /**
     * `androidDeviceOrientation` lazy initialization of the DeviceOrientation object
     */
    private val androidDeviceOrientation: DeviceOrientation by lazy {
        AndroidDeviceOrientation()
    }

    /**
     * `appName` the name of the application
     */
    override val appName: String = packageInfo.applicationInfo?.loadLabel(packageManager)?.toString().orEmpty()

    /**
     * `packageName` the package name of the application
     */
    override val packageName: String = appContext.packageName

    /**
     * `version` the version information of the application or system
     */
    override val version: Version= androidVersion

    /**
     * `board` the board name of the device hardware
     */
    override val board: String = Build.BOARD

    /**
     * `bootloader` the version of the device bootloader
     */
    override val bootloader: String = Build.BOOTLOADER

    /**
     * `device` the device name
     */
    override val device: String = Build.DEVICE

    /**
     * `display` the display identifier for the build
     */
    override val display: String = Build.DISPLAY

    /**
     * `fingerprint` the unique identifier for the build fingerprint
     */
    override val fingerprint: String = Build.FINGERPRINT

    /**
     * `hardware` the name of the device hardware
     */
    override val hardware: String = Build.HARDWARE

    /**
     * `host` the host name used to build the system
     */
    override val host: String = Build.HOST

    /**
     * `id` the build ID for the software
     */
    override val id: String = Build.ID

    /**
     * `manufacturer` the name of the device manufacturer
     */
    override val manufacturer: String = Build.MANUFACTURER

    /**
     * `model` the model name of the device
     */
    override val model: String = Build.MODEL

    /**
     * `product` the product name of the device
     */
    override val product: String = Build.PRODUCT

    /**
     * `supportedAbis` an array of supported ABIs for the device
     */
    override val supportedAbis: Array<String> = Build.SUPPORTED_ABIS

    /**
     * `supported32BitAbis` an array of supported 32-bit ABIs for the device
     */
    override val supported32BitAbis: Array<String> = Build.SUPPORTED_32_BIT_ABIS

    /**
     * `supported64BitAbis` an array of supported 64-bit ABIs for the device
     */
    override val supported64BitAbis: Array<String> = Build.SUPPORTED_64_BIT_ABIS

    /**
     * `tags` comma-separated tags associated with the build
     */
    override val tags: String = Build.TAGS

    /**
     * `isPhysicalDevice` indicates whether the device is a physical device
     */
    override val isPhysicalDevice: Boolean = getIsPhysicalDevice()

    /**
     * `systemFeatureList` a list of system features available on the device
     */
    override val systemFeatureList: List<String> = getSystemFeatures()

    /**
     * `displayMetrics` display metrics containing screen properties
     */
    override val displayMetrics: DisplayMetrics = androidDisplayMetrics

    /**
     * `VERSION_CODES` an enumeration of version codes
     */
    override val VERSION_CODES: VersionCode= androidVersionCode

    /**
     * `versionName` the version name of the application or system
     */
    override val versionName: String = packageInfo.versionName

    /**
     * `versionCode` the version code of the application or system
     */
    override val versionCode: Long = PackageInfoCompat.getLongVersionCode(packageInfo)

    /**
     * `locale` the locale information of the device
     */
    override val locale: Locale
        get() {
            val locale = LocaleManagerCompat.getSystemLocales(appContext).get(0)
            return Locale(
                languageCode = locale?.language.orEmpty(),
                region = locale?.country.orEmpty()
            )
        }

    /**
     * `deviceOrientation` the current orientation of the device
     */
    override val deviceOrientation: DeviceOrientation = androidDeviceOrientation

    /**
     * `androidId` the unique Android ID of the device
     */
    override val androidId: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(
            appContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )

    /**
     * `isDebug` indicates whether the application is running in debug mode
     */
    override val isDebug: Boolean = BuildConfig.DEBUG

    /**
     * Method to determine whether the device is physical or an emulator
     *
     * @return whether the device is physical or an emulator as [Boolean]
     */
    private fun getIsPhysicalDevice(): Boolean {
        return !((Build.BRAND.startsWith(HARDWARE_GENERIC) && Build.DEVICE.startsWith(HARDWARE_GENERIC))
                || Build.FINGERPRINT.startsWith(HARDWARE_GENERIC)
                || Build.FINGERPRINT.startsWith(UNKNOWN)
                || Build.HARDWARE.contains(HARDWARE_GOLDFISH)
                || Build.HARDWARE.contains(HARDWARE_RANCHU)
                || Build.MODEL.contains(MODEL_GOOGLE_SDK)
                || Build.MODEL.contains(MODEL_EMULATOR)
                || Build.MODEL.contains(MODEL_ANDROID_SDK_X86)
                || Build.MANUFACTURER.contains(MANUFACTURER_GENYMOTION)
                || Build.PRODUCT.contains(PRODUCT_SDK)
                || Build.PRODUCT.contains(PRODUCT_VBOX86P)
                || Build.PRODUCT.contains(PRODUCT_EMULATOR))
    }

    /**
     * Method to obtain a list of system features available on the device
     *
     * @return list of system features available on the device as [List] of [String]
     */
    private fun getSystemFeatures(): List<String> {
        return appContext.packageManager.systemAvailableFeatures
            .filterNot {
                it.name == null
            }.map {
                it.name
            }
    }
    
}
