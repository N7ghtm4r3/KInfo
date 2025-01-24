package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSVersionInfo

/**
 * Represents an implementation of the `OSVersionInfo` interface.
 * Provides concrete values for the version, code name, and build number of the operating system.
 *
 * @param osVersionInfo The `OSVersionInfo` object from the `oshi.software.os.OperatingSystem` class
 *                      that provides the version, code name, and build number of the operating system.
 *
 * @author N7ghtm4r3
 *
 * @see OSVersionInfo
 */
class OSVersionInfoImpl(
    osVersionInfo: oshi.software.os.OperatingSystem.OSVersionInfo
) : OSVersionInfo {

    /**
     * `version` The version of the operating system.
     */
    override val version: String = osVersionInfo.version

    /**
     * `codeName` The code name of the operating system version.
     */
    override val codeName: String = osVersionInfo.codeName

    /**
     * `buildNumber` The build number of the operating system.
     */
    override val buildNumber: String = osVersionInfo.buildNumber
}
