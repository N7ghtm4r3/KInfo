package com.tecknobit.kinfo.model.android

/**
 * `Version` interface provides details about the version of the Android operating system.
 * It includes information such as the base OS, SDK version, release details, incremental build version,
 * security patch level, and other relevant version-related data.
 *
 * This interface exposes various properties related to the Android OS version that can be used
 * to retrieve information about the current version, preview SDK, and the media performance class.
 *
 * @author Swapnil Musale
 */
interface Version {

    /**
     * `baseOs` the base operating system version of the Android device
     */
    val baseOs: String

    /**
     * `sdkInt` the integer value of the current Android SDK version
     */
    val sdkInt: Int

    /**
     * `codeName` the code name of the current Android version (e.g., "Pie", "Q", "R")
     */
    val codeName: String

    /**
     * `release` the string value representing the release version of the Android operating system
     */
    val release: String

    /**
     * `incremental` the incremental version string, often used for identifying minor updates
     */
    val incremental: String

    /**
     * `releaseOrCodeName` returns either the release version or the code name of the Android version
     */
    val releaseOrCodeName: String

    /**
     * `releaseOrPreviewDisplay` returns either the release version or preview display name (if in preview mode)
     */
    val releaseOrPreviewDisplay: String

    /**
     * `securityPatch` the security patch level of the current Android version
     */
    val securityPatch: String

    /**
     * `mediaPerformanceClass` the media performance class of the Android device (used to indicate the media performance tier)
     */
    val mediaPerformanceClass: Int

    /**
     * `previewSdkInt` the SDK version of the preview release (if any)
     */
    val previewSdkInt: Int

}
