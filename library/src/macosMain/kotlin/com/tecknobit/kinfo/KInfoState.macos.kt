package com.tecknobit.kinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tecknobit.kinfo.enums.DevicePlatform
import com.tecknobit.kinfo.enums.DevicePlatform.MACOS
import com.tecknobit.kinfo.model.android.AndroidInfo
import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.desktop.macos.MacOsInfo
import com.tecknobit.kinfo.model.ios.IosInfo
import com.tecknobit.kinfo.model.web.WebInfo

/**
 * The `KInfoState` class is useful to provide information about the current macOS device
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class KInfoState actual constructor() {

    /**
     * `macOsInfo` the information about the current macOS device
     */
    actual val macOsInfo: MacOsInfo by lazy {
        MacOsInfoImpl()
    }

    /**
     * `devicePlatform` the macOS platform where the application is running
     */
    actual val devicePlatform: DevicePlatform = MACOS

    /**
     * `androidInfo` the unavailable Android information on a macOS device
     */
    actual val androidInfo: AndroidInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `iosInfo` the unavailable iOS information on a macOS device
     */
    actual val iosInfo: IosInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `desktopInfo` the unavailable `JVM` desktop information on a macOS device
     */
    actual val desktopInfo: DesktopInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `webInfo` the unavailable web information on a macOS device
     */
    actual val webInfo: WebInfo
        get() = throw Exception("trying to access incorrect platform info")

}

/**
 * Method used to remember the [KInfoState] instance during recomposition
 *
 * @return the remembered state instance as [KInfoState]
 *
 * @since 1.1.0
 */
@Composable
actual fun rememberKInfoState(): KInfoState {
    return remember { KInfoState() }
}