@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsVersionInfo
import com.tecknobit.kinfo.operatingsystem.MacOsCodename.*
import com.tecknobit.kinfo.utils.queryStringSysCtlByName
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.Foundation.NSOperatingSystemVersion

/**
 * The `MacOsVersionInfoImpl` class is useful to provide the version details of the current macOS operating system
 *
 * @property operatingSystemVersion The native operating system version used to retrieve the version details
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
data class MacOsVersionInfoImpl(
    private val operatingSystemVersion: CValue<NSOperatingSystemVersion>
) : MacOsVersionInfo {

    /**
     * `major` the major component of the macOS version
     */
    val major: Long

    /**
     * `minor` the minor component of the macOS version
     */
    val minor: Long

    /**
     * `patch` the patch component of the macOS version
     */
    val patch: Long
    
    init {
        operatingSystemVersion.useContents { 
            major = majorVersion
            minor = minorVersion
            patch = patchVersion
        }
    }
    
    /**
     * `version` the formatted macOS version
     */
    override val version: String
        get() = "$major.$minor.$patch"

    /**
     * `codeName` the codename associated with the macOS version
     */
    override val codeName: String
        get() = resolveCodeName()

    /**
     * `buildNumber` the build number associated with the macOS version
     */
    override val buildNumber: String
        get() = resolveBuildNumber()
    
    /**
     * Method used to resolve the codename associated with the macOS version
     *
     * @return the resolved codename as [String]
     */
    private fun resolveCodeName(): String {
        val codeName = when (major) {
            26L -> TAHOE
            15L -> SEQUOIA
            14L -> SONOMA
            13L -> VENTURA
            12L -> MONTEREY
            11L -> BIG_SUR
            10L -> when(minor) {
                16L -> BIG_SUR
                15L -> CATALINA
                14L -> MOJAVE
                13L -> HIGH_SIERRA
                12L -> SIERRA
                11L -> EL_CAPITAN
                10L -> YOSEMITE
                9L -> MAVERICKS
                8L -> MOUNTAIN_LION
                7L -> LION
                6L -> SNOW_LEOPARD
                5L -> LEOPARD
                4L -> TIGER
                3L -> PANTHER
                2L -> JAGUAR
                1L -> PUMA
                else -> CHEETAH
            }

            else -> UNKNOWN
        }

        return codeName.displayName
    }

    /**
     * Method used to resolve the build number associated with the macOS version
     *
     * @return the resolved build number as [String]
     */
    private fun resolveBuildNumber(): String {
        return queryStringSysCtlByName(
            name = "kern.osversion",
            default = "Unknown"
        )!!
    }

}

/**
 * The `MacOsCodename` enum is useful to represent the available macOS codenames
 *
 * @property displayName The display name of the macOS codename
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
enum class MacOsCodename(
    val displayName: String
) {

    /**
     * `TAHOE` the Tahoe codename value
     */
    TAHOE(
        displayName = "Tahoe"
    ),

    /**
     * `SEQUOIA` the Sequoia codename value
     */
    SEQUOIA(
        displayName = "Sequoia"
    ),

    /**
     * `SONOMA` the Sonoma codename value
     */
    SONOMA(
        displayName = "Sonoma"
    ),

    /**
     * `VENTURA` the Ventura codename value
     */
    VENTURA(
        displayName = "Ventura"
    ),

    /**
     * `MONTEREY` the Monterey codename value
     */
    MONTEREY(
        displayName = "Monterey"
    ),

    /**
     * `BIG_SUR` the Big Sur codename value
     */
    BIG_SUR(
        displayName = "Big Sur"
    ),

    /**
     * `CATALINA` the Catalina codename value
     */
    CATALINA(
        displayName = "Catalina"
    ),

    /**
     * `MOJAVE` the Mojave codename value
     */
    MOJAVE(
        displayName = "Mojave"
    ),

    /**
     * `HIGH_SIERRA` the High Sierra codename value
     */
    HIGH_SIERRA(
        displayName = "High Sierra"
    ),

    /**
     * `SIERRA` the Sierra codename value
     */
    SIERRA(
        displayName = "Sierra"
    ),

    /**
     * `EL_CAPITAN` the El Capitan codename value
     */
    EL_CAPITAN(
        displayName = "El Capitan"
    ),

    /**
     * `YOSEMITE` the Yosemite codename value
     */
    YOSEMITE(
        displayName = "Yosemite"
    ),

    /**
     * `MAVERICKS` the Mavericks codename value
     */
    MAVERICKS(
        displayName = "Mavericks"
    ),

    /**
     * `MOUNTAIN_LION` the Mountain Lion codename value
     */
    MOUNTAIN_LION(
        displayName = "Mountain Lion"
    ),

    /**
     * `LION` the Lion codename value
     */
    LION(
        displayName = "Lion"
    ),

    /**
     * `SNOW_LEOPARD` the Snow Leopard codename value
     */
    SNOW_LEOPARD(
        displayName = "Snow Leopard"
    ),

    /**
     * `LEOPARD` the Leopard codename value
     */
    LEOPARD(
        displayName = "Leopard"
    ),

    /**
     * `TIGER` the Tiger codename value
     */
    TIGER(
        displayName = "Tiger"
    ),

    /**
     * `PANTHER` the Panther codename value
     */
    PANTHER(
        displayName = "Panther"
    ),

    /**
     * `JAGUAR` the Jaguar codename value
     */
    JAGUAR(
        displayName = "Jaguar"
    ),

    /**
     * `PUMA` the Puma codename value
     */
    PUMA(
        displayName = "Puma"
    ),

    /**
     * `CHEETAH` the Cheetah codename value
     */
    CHEETAH(
        displayName = "Cheetah"
    ),

    /**
     * `UNKNOWN` the value used when the macOS codename is not recognized
     */
    UNKNOWN(
        displayName = "Unknown"
    )

}