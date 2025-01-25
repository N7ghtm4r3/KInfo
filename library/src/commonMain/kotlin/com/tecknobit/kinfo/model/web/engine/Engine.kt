package com.tecknobit.kinfo.model.web.engine

import com.tecknobit.kinfo.model.web.WebInfoItem

/**
 * Represents the engine information with its name and version.
 *
 * This interface provides properties to retrieve the engine's name and version (e.g., Blink, WebKit).
 * It is useful for capturing details about the rendering engine used in web browsers or other systems.
 *
 * @see WebInfoItem
 *
 * @author N7ghtm4r3
 */
interface Engine : WebInfoItem {

    /**
     * `name` The name of the engine (e.g., Blink, WebKit).
     */
    val name: String

    /**
     * `version` The version of the engine.
     */
    val version: String

}
