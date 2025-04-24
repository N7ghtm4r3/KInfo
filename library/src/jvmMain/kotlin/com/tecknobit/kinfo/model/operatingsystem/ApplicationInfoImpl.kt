package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.ApplicationInfo

/**
 * Represents the implementation of the `ApplicationInfo` interface.
 *
 * Provides common information about an installed application across different operating systems. This class provides
 * standardized access to essential application details while allowing flexibility for OS-specific fields via an
 * additional information map
 *
 * @property name The name of the application
 * @property version The version of the application
 * @property vendor The vendor or publisher of the application
 * @property timestamp The installation or last modified timestamp of the application in milliseconds since epoch.
 * This represents the `Unix` timestamp.
 * - On Windows, this corresponds to the application's install date
 * - On Linux, it represents the package's installation or last modified time
 * - On macOS, it reflects the last modification timestamp of the application bundle
 * @property additionalInfo A map containing additional application details such as install location, source, etc. Keys are
 * field names, and values are corresponding details
 *
 * @see ApplicationInfo
 *
 * @author N7ghtm4r3
 *
 * @since 1.0.2
 */
data class ApplicationInfoImpl(
    override val name: String,
    override val version: String,
    override val vendor: String,
    override val timestamp: Long,
    override val additionalInfo: Map<String, String>
) : ApplicationInfo
