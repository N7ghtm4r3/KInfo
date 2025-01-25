package com.tecknobit.kinfo.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.tecknobit.kinfo.model.android.Version

/**
 * `AndroidVersionImpl` class provides the implementation of the `Version` interface.
 * This class retrieves version-related information of the Android operating system from
 * the `Build.VERSION` class, including base OS, SDK version, release information, incremental version,
 * security patch level, and more. It handles different versions of Android using appropriate API levels.
 *
 * This class makes use of the `@RequiresApi` annotations to indicate which Android API levels are required
 * for accessing certain properties of the `Build.VERSION`.
 *
 * @author Swapnil Musale
 *
 * @see Version
 */
internal class AndroidVersionImpl : Version {

    /**
     * `baseOs` returns the base operating system version of the Android device
     *
     * @throws [ApiLevelException] if the API level is below Marshmallow (API 23)
     */
    override val baseOs: String
        @RequiresApi(Build.VERSION_CODES.M)
        get() = Build.VERSION.BASE_OS

    /**
     * `sdkInt` returns the integer value of the current Android SDK version
     */
    override val sdkInt: Int
        get() = Build.VERSION.SDK_INT

    /**
     * `codeName` returns the code name of the current Android version (e.g., "Pie", "Q", "R")
     */
    override val codeName: String
        get() = Build.VERSION.CODENAME

    /**
     * `release` returns the release version of the Android operating system as a string
     */
    override val release: String
        get() = Build.VERSION.RELEASE

    /**
     * `incremental` returns the incremental version string for minor updates of the current Android version
     */
    override val incremental: String
        get() = Build.VERSION.INCREMENTAL

    /**
     * `releaseOrCodeName` returns either the release version or the code name of the Android version
     *
     * @throws [ApiLevelException] if the API level is below Android 12 (API 31)
     */
    override val releaseOrCodeName: String
        @RequiresApi(Build.VERSION_CODES.R)
        get() = Build.VERSION.RELEASE_OR_CODENAME

    /**
     * `releaseOrPreviewDisplay` returns either the release version or the preview display name if the device is in preview mode
     *
     * @throws [ApiLevelException] if the API level is below Android 14 (API 34)
     */
    override val releaseOrPreviewDisplay: String
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        get() = Build.VERSION.RELEASE_OR_PREVIEW_DISPLAY

    /**
     * `securityPatch` returns the security patch level of the current Android version
     *
     * @throws [ApiLevelException] if the API level is below Marshmallow (API 23)
     */
    override val securityPatch: String
        @RequiresApi(Build.VERSION_CODES.M)
        get() = Build.VERSION.SECURITY_PATCH

    /**
     * `mediaPerformanceClass` returns the media performance class of the Android device
     *
     * @throws [ApiLevelException] if the API level is below Android 12 (API 31)
     */
    override val mediaPerformanceClass: Int
        @RequiresApi(Build.VERSION_CODES.S)
        get() = Build.VERSION.MEDIA_PERFORMANCE_CLASS

    /**
     * `previewSdkInt` returns the SDK version for preview releases of Android
     *
     * @throws [ApiLevelException] if the API level is below Marshmallow (API 23)
     */
    override val previewSdkInt: Int
        @RequiresApi(Build.VERSION_CODES.M)
        get() = Build.VERSION.PREVIEW_SDK_INT

}
