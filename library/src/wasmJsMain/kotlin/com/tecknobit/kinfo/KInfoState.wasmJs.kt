package com.tecknobit.kinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tecknobit.kinfo.DevicePlatform.WEB
import com.tecknobit.kinfo.model.WebInfoImpl
import com.tecknobit.kinfo.model.android.AndroidInfo
import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.ios.IosInfo
import com.tecknobit.kinfo.model.web.WebInfo

/**
 * `KInfoState` provides information about the current device where the application is running. This information is related
 * to the platform and the specific details that the platform offers.ù
 *
 * @author N7ghtm4r3 - Swapnil Musale
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KInfoState {

    /**
     * `webInfo` the information about a webapp running on the [DevicePlatform.WEB]
     */
    actual val webInfo: WebInfo by lazy {
        WebInfoImpl()
    }

    /**
     * `devicePlatform` the current platform where the application is running
     */
    actual val devicePlatform = WEB

    /**
     * `desktopInfo` the information about an [DevicePlatform.DESKTOP]'s device
     */
    actual val desktopInfo: DesktopInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `androidInfo` the information about an [DevicePlatform.ANDROID]'s device
     */
    actual val androidInfo: AndroidInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `iosInfo` the information about an [DevicePlatform.IOS]'s device
     */
    actual val iosInfo: IosInfo
        get() = throw Exception("trying to access incorrect platform info")

}

/**
 * Method to remember during the recomposition the current status of a [KInfoState] instance
 *
 * @return the remembered status instance as [KInfoState]
 */
@Composable
actual fun rememberKInfoState(): KInfoState {
    return remember { KInfoState() }
}