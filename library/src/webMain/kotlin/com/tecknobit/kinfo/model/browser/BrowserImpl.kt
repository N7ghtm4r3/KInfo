package com.tecknobit.kinfo.model.browser

import com.tecknobit.kinfo.model.web.browser.Browser

/**
 * Implements the `Browser` interface, providing concrete values for the browser's name and version.
 *
 * This class extracts the browser's name and version from the provided `com.tecknobit.kinfo.model.Browser` object,
 * ensuring null-safe values are used via the `safeValue()` method from the `WebInfoItem` interface.
 *
 * @param parsedBrowser The `Browser` object containing the parsed browser information, used to extract the name and version.
 *
 * @see Browser
 *
 * @author N7ghtm4r3
 */
class BrowserImpl(
    parsedBrowser: com.tecknobit.kinfo.model.Browser
) : Browser {

    /**
     * The name of the browser
     *
     * This value is safely extracted from the provided `parsedBrowser` object
     */
    override val name: String = parsedBrowser.name.safeValue()

    /**
     * The version of the browser
     *
     * This value is safely extracted from the provided `parsedBrowser` object
     */
    override val version: String = parsedBrowser.version.safeValue()

    /**
     * `major` Major number derived from the first number in [version]
     *
     * This value is safely extracted from the provided `parsedBrowser` object
     */
    override val major: String = parsedBrowser.major.safeValue()

    /**
     * `type` Type of current browser (email, inapp, crawler)
     *
     * This value is safely extracted from the provided `parsedBrowser` object
     */
    override val type: String = parsedBrowser.type.safeValue()

}
