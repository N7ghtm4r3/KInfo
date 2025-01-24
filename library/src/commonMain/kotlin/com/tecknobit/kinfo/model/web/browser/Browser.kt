package com.tecknobit.kinfo.model.web.browser

import com.tecknobit.kinfo.model.web.WebInfoItem

/**
 * Represents a web browser.
 * Provides details about the browser's name and version.
 *
 * This interface extends the `WebInfoItem` interface and includes a utility method `safeValue()`
 * for safely handling null values in the browser's information.
 *
 * @author N7ghtm4r3
 *
 * @see WebInfoItem
 */
interface Browser : WebInfoItem {

    /**
     * `name` The name of the browser (e.g., Chrome, Firefox).
     */
    val name: String

    /**
     * `version` The version of the browser.
     */
    val version: String

}
