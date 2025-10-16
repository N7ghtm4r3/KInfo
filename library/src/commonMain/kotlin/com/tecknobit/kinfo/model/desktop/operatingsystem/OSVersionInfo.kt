package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * Represents the version information of the operating system.
 * Provides details about the version, code name, and build number of the OS.
 *
 * @author N7ghtm4r3
 */
interface OSVersionInfo {

    /**
     * `version` The version of the operating system
     */
    val version: String

    /**
     * `codeName` The code name of the operating system version
     */
    val codeName: String

    /**
     * `buildNumber` The build number of the operating system
     */
    val buildNumber: String

}
