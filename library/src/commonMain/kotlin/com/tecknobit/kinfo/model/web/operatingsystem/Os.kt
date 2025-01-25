package com.tecknobit.kinfo.model.web.operatingsystem

import com.tecknobit.kinfo.model.web.WebInfoItem

/**
 * Represents the operating system information with its name and version.
 *
 * This interface provides properties to retrieve the operating system's name and version (e.g., Windows, Linux, macOS).
 * It is useful for capturing details about the operating system for system diagnostics or analytics.
 *
 * @see WebInfoItem
 *
 * @author N7ghtm4r3
 */
interface Os : WebInfoItem {

    /**
     * `name` The name of the operating system (e.g., Windows, Linux, macOS).
     */
    val name: String

    /**
     * `version` The version of the operating system.
     */
    val version: String

}
