package com.tecknobit.kinfo.model.android

/**
 * `DisplayMetrics` interface representing the display metrics of a device.
 * It includes properties to get the dimensions and density of the screen in inches and DPI
 *
 * @author Swapnil Musale
 */
interface DisplayMetrics {

    /**
     * `widthInches` the width of the display in inches
     */
    val widthInches: Double

    /**
     * `heightInches` the height of the display in inches
     */
    val heightInches: Double

    /**
     * `xDpi` the screen's horizontal density in dots per inch (DPI)
     */
    val xDpi: Double

    /**
     * `yDpi` the screen's vertical density in dots per inch (DPI)
     */
    val yDpi: Double

}
