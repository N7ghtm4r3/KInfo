package com.tecknobit.kinfo.model

import android.os.Build
import com.tecknobit.kinfo.model.android.VersionCode

/**
 * `AndroidVersionCodeImpl` class implements the [VersionCode] interface to provide the integer values
 * corresponding to the version codes of various Android versions.
 *
 * These version codes are used to identify specific Android releases in the system and are mapped
 * from [Build.VERSION_CODES] constants.
 *
 * @author Swapnil Musale
 */
internal class AndroidVersionCodeImpl : VersionCode {

    /**
     * `CUR_DEVELOPMENT` the current development version of Android (for testing purposes)
     */
    override val CUR_DEVELOPMENT: Int = Build.VERSION_CODES.CUR_DEVELOPMENT

    /**
     * `CUPCAKE` the version code for Android 1.5 (API level 3)
     */
    override val CUPCAKE: Int = Build.VERSION_CODES.CUPCAKE

    /**
     * `DONUT` the version code for Android 1.6 (API level 4)
     */
    override val DONUT: Int = Build.VERSION_CODES.DONUT

    /**
     * `LOLLIPOP` the version code for Android Lollipop (API level 21)
     */
    override val LOLLIPOP: Int = Build.VERSION_CODES.LOLLIPOP

    /**
     * `LOLLIPOP_MR1` the version code for Android Lollipop MR1 (API level 22)
     */
    override val LOLLIPOP_MR1: Int = Build.VERSION_CODES.LOLLIPOP_MR1

    /**
     * `M` the version code for Android Marshmallow (API level 23)
     */
    override val M: Int = Build.VERSION_CODES.M

    /**
     * `N` the version code for Android Nougat (API level 24)
     */
    override val N: Int = Build.VERSION_CODES.N

    /**
     * `N_MR1` the version code for Android Nougat MR1 (API level 25)
     */
    override val N_MR1: Int = Build.VERSION_CODES.N_MR1

    /**
     * `O` the version code for Android Oreo (API level 26)
     */
    override val O: Int = Build.VERSION_CODES.O

    /**
     * `O_MR1` the version code for Android Oreo MR1 (API level 27)
     */
    override val O_MR1: Int = Build.VERSION_CODES.O_MR1

    /**
     * `P` the version code for Android Pie (API level 28)
     */
    override val P: Int = Build.VERSION_CODES.P

    /**
     * `Q` the version code for Android 10 (API level 29)
     */
    override val Q: Int = Build.VERSION_CODES.Q

    /**
     * `R` the version code for Android 11 (API level 30)
     */
    override val R: Int = Build.VERSION_CODES.R

    /**
     * `S` the version code for Android 12 (API level 31)
     */
    override val S: Int = Build.VERSION_CODES.S

    /**
     * `S_V2` the version code for Android 12 (API level 32)
     */
    override val S_V2: Int = Build.VERSION_CODES.S_V2

    /**
     * `TIRAMISU` the version code for Android 13 (API level 33)
     */
    override val TIRAMISU: Int = Build.VERSION_CODES.TIRAMISU

    /**
     * `UPSIDE_DOWN_CAKE` the version code for Android 14 (API level 34)
     */
    override val UPSIDE_DOWN_CAKE: Int = Build.VERSION_CODES.UPSIDE_DOWN_CAKE
    
}
