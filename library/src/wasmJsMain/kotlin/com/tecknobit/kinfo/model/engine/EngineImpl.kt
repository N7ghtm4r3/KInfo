package com.tecknobit.kinfo.model.engine

import com.tecknobit.kinfo.model.web.engine.Engine

/**
 * Implements the `Engine` interface, providing the engine name and version information.
 *
 * This class extracts the engine's name and version from the provided `com.tecknobit.kinfo.model.Engine` object,
 * ensuring null-safe values are used via the `safeValue()` method from the `WebInfoItem` interface.
 *
 * @param parsedEngine The `Engine` object containing the parsed engine information, used to extract the name and version.
 *
 * @see Engine
 *
 * @author N7ghtm4r3
 */
class EngineImpl(
    parsedEngine: com.tecknobit.kinfo.model.Engine
) : Engine {

    /**
     * `name` The name of the engine (e.g., Blink, WebKit).
     * This value is safely extracted from the provided `parsedEngine` object.
     */
    override val name: String = parsedEngine.name.safeValue()

    /**
     * `version` The version of the engine.
     * This value is safely extracted from the provided `parsedEngine` object.
     */
    override val version: String = parsedEngine.version.safeValue()

}
