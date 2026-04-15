package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.web.operatingsystem.Os

/**
 * Implements the `Os` interface, providing the operating system name and version information.
 *
 * This class extracts the operating system's name and version from the provided `com.tecknobit.kinfo.model.Os` object,
 * ensuring null-safe values are used via the `safeValue()` method from the `WebInfoItem` interface.
 *
 * @param parsedOs The `Os` object containing the parsed operating system information, used to extract the name and version.
 *
 * @see Os
 *
 * @author N7ghtm4r3
 */
class OsImpl(
    parsedOs: com.tecknobit.kinfo.model.Os
) : Os {

    /**
     * `name` The name of the operating system (e.g., Windows, Linux, macOS).
     * This value is safely extracted from the provided `parsedOs` object.
     */
    override val name: String = parsedOs.name.safeValue()

    /**
     * `version` The version of the operating system.
     * This value is safely extracted from the provided `parsedOs` object.
     */
    override val version: String = parsedOs.version.safeValue()

}
