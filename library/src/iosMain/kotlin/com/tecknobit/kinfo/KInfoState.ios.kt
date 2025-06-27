package com.tecknobit.kinfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.interop.LocalUIViewController
import com.tecknobit.kinfo.enums.DevicePlatform.IOS
import com.tecknobit.kinfo.model.IosInfoImpl
import com.tecknobit.kinfo.model.android.AndroidInfo
import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.ios.IosInfo
import com.tecknobit.kinfo.model.web.WebInfo
import platform.UIKit.interfaceOrientation

/**
 * `KInfoState` provides information about the current device where the application is running. This information is related
 * to the platform and the specific details that the platform offers.
 *
 * @author N7ghtm4r3 - Swapnil Musale
 *
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KInfoState {

    /**
     * `iosInfo` the information about an [com.tecknobit.kinfo.enums.DevicePlatform.IOS]'s device
     */
    actual val iosInfo: IosInfo by lazy {
        IosInfoImpl()
    }

    /**
     * `devicePlatform` the current platform where the application is running
     */
    actual val devicePlatform = IOS

    /**
     * `androidInfo` the information about an [com.tecknobit.kinfo.enums.DevicePlatform.ANDROID]'s device
     */
    actual val androidInfo: AndroidInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `desktopInfo` the information about an [com.tecknobit.kinfo.enums.DevicePlatform.DESKTOP]'s device
     */
    actual val desktopInfo: DesktopInfo
        get() = throw Exception("trying to access incorrect platform info")

    /**
     * `webInfo` the information about a webapp running on the [com.tecknobit.kinfo.enums.DevicePlatform.WEB]
     */
    actual val webInfo: WebInfo
        get() = throw Exception("trying to access incorrect platform info")

}

/**
 * Method to remember during the recomposition the current status of a [KInfoState] instance
 *
 * @return the remembered status instance as [KInfoState]
 */
@Composable
actual fun rememberKInfoState(): KInfoState {
    val orientation = LocalUIViewController.current.interfaceOrientation
    return remember(orientation) { KInfoState() }
}