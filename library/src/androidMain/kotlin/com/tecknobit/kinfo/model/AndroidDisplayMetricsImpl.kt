package com.tecknobit.kinfo.model

import android.content.res.Resources
import com.tecknobit.kinfo.model.android.DisplayMetrics

/**
 * `AndroidDisplayMetricsImpl` class provides the implementation for the `DisplayMetrics` interface.
 * This class retrieves and calculates the display metrics of an Android device, such as screen width,
 * height, and DPI (dots per inch) values for both horizontal and vertical axes.
 *
 * It uses the system's `DisplayMetrics` to get the raw pixel values and then calculates the screen dimensions
 * in inches based on the DPI values.
 *
 * @author Swapnil Musale
 *
 * @see DisplayMetrics
 */
internal class AndroidDisplayMetricsImpl : DisplayMetrics {

    /**
     * `displayMetrics` holds the system's display metrics, which includes data like screen width, height, and DPI
     */
    private val displayMetrics: android.util.DisplayMetrics? = Resources.getSystem().displayMetrics

    /**
     * `widthPx` the width of the display in pixels
     */
    private val widthPx: Double = displayMetrics?.widthPixels?.toDouble() ?: 0.0

    /**
     * `heightPx` the height of the display in pixels
     */
    private val heightPx: Double = displayMetrics?.heightPixels?.toDouble() ?: 0.0

    /**
     * `xDpi` the horizontal density of the display in dots per inch (DPI)
     */
    override val xDpi: Double
        get() = displayMetrics?.xdpi?.toDouble() ?: 0.0

    /**
     * `yDpi` the vertical density of the display in dots per inch (DPI)
     */
    override val yDpi: Double
        get() = displayMetrics?.ydpi?.toDouble() ?: 0.0

    /**
     * `widthInches` the width of the display in inches
     * This value is calculated using the pixel width and horizontal DPI
     */
    override val widthInches: Double
        get() = widthPx / xDpi

    /**
     * `heightInches` the height of the display in inches
     * This value is calculated using the pixel height and vertical DPI
     */
    override val heightInches: Double
        get() = heightPx / yDpi

}
