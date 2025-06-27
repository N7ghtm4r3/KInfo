@file:Suppress("FunctionName")

package com.tecknobit.kinfo

import androidx.compose.runtime.Composable
import com.tecknobit.kinfo.enums.DevicePlatform
import com.tecknobit.kinfo.enums.DevicePlatform.*
import com.tecknobit.kinfo.model.android.AndroidInfo
import com.tecknobit.kinfo.model.desktop.DesktopInfo
import com.tecknobit.kinfo.model.ios.IosInfo
import com.tecknobit.kinfo.model.web.WebInfo

/**
 * `KInfoState` provides information about the current device where the application is running. This information is related
 * to the platform and the specific details that the platform offers.
 *
 * @author N7ghtm4r3 - Swapnil Musale
 *
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KInfoState() {

    /**
     * `androidInfo` the information about an [DevicePlatform.ANDROID]'s device
     */
    val androidInfo: AndroidInfo

    /**
     * `iosInfo` the information about an [DevicePlatform.IOS]'s device
     */
    val iosInfo: IosInfo

    /**
     * `desktopInfo` the information about an [DevicePlatform.DESKTOP]'s device
     */
    val desktopInfo: DesktopInfo

    /**
     * `webInfo` the information about a webapp running on the [DevicePlatform.WEB]
     */
    val webInfo: WebInfo

    /**
     * `devicePlatform` the current platform where the application is running
     */
    val devicePlatform: DevicePlatform

}

/**
 * Method to display different contents based on the current platform where this method has been invoked
 *
 * @param kInfoState The state used to retrieve the device information
 * @param onAndroid The content to display on an [DevicePlatform.ANDROID] device
 * @param onIos The content to display on an [DevicePlatform.IOS] device
 * @param onDesktop The content to display on a [DevicePlatform.DESKTOP] device
 * @param onWeb The content to display on an [DevicePlatform.WEB] device
 */
@Composable
fun OnPlatform(
    kInfoState: KInfoState = rememberKInfoState(),
    onAndroid: @Composable ((AndroidInfo) -> Unit)? = null,
    onIos: @Composable ((IosInfo) -> Unit)? = null,
    onDesktop: @Composable ((DesktopInfo) -> Unit)? = null,
    onWeb: @Composable ((WebInfo) -> Unit)? = null,
) {
    when(kInfoState.devicePlatform) {
        ANDROID -> onAndroid?.invoke(kInfoState.androidInfo)
        IOS -> onIos?.invoke(kInfoState.iosInfo)
        DESKTOP -> onDesktop?.invoke(kInfoState.desktopInfo)
        WEB -> onWeb?.invoke(kInfoState.webInfo)
    }
}

/**
 * Method to execute different actions based on the current platform where this method has been invoked
 *
 * @param kInfoState The state used to retrieve the device information
 * @param onAndroid The action to execute on an [DevicePlatform.ANDROID] device
 * @param onIos The action to execute on an [DevicePlatform.IOS] device
 * @param onDesktop The action to execute on a [DevicePlatform.DESKTOP] device
 * @param onWeb The action to execute on an [DevicePlatform.WEB] device
 */
fun onPlatform(
    kInfoState: KInfoState = KInfoState(),
    onAndroid: ((AndroidInfo) -> Unit)? = null,
    onIos: ((IosInfo) -> Unit)? = null,
    onDesktop: ((DesktopInfo) -> Unit)? = null,
    onWeb: ((WebInfo) -> Unit)? = null
) {
    when(kInfoState.devicePlatform) {
        ANDROID -> onAndroid?.invoke(kInfoState.androidInfo)
        IOS -> onIos?.invoke(kInfoState.iosInfo)
        DESKTOP -> onDesktop?.invoke(kInfoState.desktopInfo)
        WEB -> onWeb?.invoke(kInfoState.webInfo)
    }
}

/**
 * Method to remember during the recomposition the current status of a [KInfoState] instance
 *
 * @return the remembered status instance as [KInfoState]
 */
@Composable
expect fun rememberKInfoState(): KInfoState