@file:Suppress("PropertyName")

package com.tecknobit.kinfo.model.android

/**
 * `VersionCode` interface defines the integer values corresponding to the version codes of various Android versions.
 * These version codes are used to identify specific Android releases in the system.
 *
 * This interface includes constants for major Android versions and their respective minor releases (e.g., "Lollipop", "Nougat").
 * These values can be used to perform version checks or comparisons in Android applications.
 *
 * @author Swapnil Musale
 */
interface VersionCode {

    /**
     * `CUR_DEVELOPMENT` the current development version of Android (for testing purposes)
     */
    val CUR_DEVELOPMENT: Int

    /**
     * `CUPCAKE` the version code for Android 1.5 (API level 3)
     */
    val CUPCAKE: Int

    /**
     * `DONUT` the version code for Android 1.6 (API level 4)
     */
    val DONUT: Int

    /**
     * `LOLLIPOP` the version code for Android Lollipop (API level 21)
     */
    val LOLLIPOP: Int

    /**
     * `LOLLIPOP_MR1` the version code for Android Lollipop MR1 (API level 22)
     */
    val LOLLIPOP_MR1: Int

    /**
     * `M` the version code for Android Marshmallow (API level 23)
     */
    val M: Int

    /**
     * `N` the version code for Android Nougat (API level 24)
     */
    val N: Int

    /**
     * `N_MR1` the version code for Android Nougat MR1 (API level 25)
     */
    val N_MR1: Int

    /**
     * `O` the version code for Android Oreo (API level 26)
     */
    val O: Int

    /**
     * `O_MR1` the version code for Android Oreo MR1 (API level 27)
     */
    val O_MR1: Int

    /**
     * `P` the version code for Android Pie (API level 28)
     */
    val P: Int

    /**
     * `Q` the version code for Android 10 (API level 29)
     */
    val Q: Int

    /**
     * `R` the version code for Android 11 (API level 30)
     */
    val R: Int

    /**
     * `S` the version code for Android 12 (API level 31)
     */
    val S: Int

    /**
     * `S_V2` the version code for Android 12 (API level 32)
     */
    val S_V2: Int

    /**
     * `TIRAMISU` the version code for Android 13 (API level 33)
     */
    val TIRAMISU: Int

    /**
     * `UPSIDE_DOWN_CAKE` the version code for Android 14 (API level 34)
     */
    val UPSIDE_DOWN_CAKE: Int
}
