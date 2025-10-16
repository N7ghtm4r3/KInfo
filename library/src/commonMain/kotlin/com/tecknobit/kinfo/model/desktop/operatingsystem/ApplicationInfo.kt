package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * Represents common information about an installed application across different operating systems. This class provides
 * standardized access to essential application details while allowing flexibility for OS-specific fields via an
 * additional information map
 *
 * @author N7ghtm4r3
 *
 * @since 1.0.2
 */
interface ApplicationInfo {

    /**
     * `name` The name of the application
     */
    val name: String

    /**
     * `version` The version of the application
     */
    val version: String

    /**
     * `vendor` The vendor or publisher of the application
     */
    val vendor: String

    /**
     * `timestamp` The installation or last modified timestamp of the application in milliseconds since epoch.
     * This represents the `Unix` timestamp
     *
     * - On Windows, this corresponds to the application's install date
     * - On Linux, it represents the package's installation or last modified time
     * - On macOS, it reflects the last modification timestamp of the application bundle
     */
    val timestamp: Long

    /**
     * `additionalInfo` A map containing additional application details such as install location, source, etc.
     *
     * Keys are field names, and values are corresponding details
     */
    val additionalInfo: Map<String, String>

}