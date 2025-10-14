@file:OptIn(ExperimentalWasmJsInterop::class)
@file:JsModule("ua-parser-js")

package com.tecknobit.kinfo.model

/**
 * External class representing the `UAParser` from the "ua-parser-js" JavaScript library.
 *
 * This class is used to parse the user agent string and extract detailed information about the browser, CPU,
 * device, engine, and operating system associated with the user agent
 *
 * @param userAgent The user agent string to be parsed by the `UAParser`
 *
 * @see UAParserResult
 *
 * @author N7ghtm4r3
 */
external class UAParser(
    userAgent: String
): JsAny {

    /**
     * Retrieves the parsed result containing detailed information about the browser, CPU, device, engine, and OS.
     *
     * @return An instance of `UAParserResult` containing the parsed data.
     */
    fun getResult(): UAParserResult
}

/**
 * Represents the result of parsing a user agent string, containing detailed information about the browser,
 * CPU, device, engine, and operating system.
 *
 * @see Browser
 * @see CPU
 * @see Device
 * @see Engine
 * @see Os
 */
external interface UAParserResult {

    /**
     * `browser` The browser information, including the browser's name and version
     */
    val browser: Browser

    /**
     * `cpu` The CPU architecture information (e.g., x86, ARM)
     */
    val cpu: CPU

    /**
     * `device` The device information, including the model, type, and vendor
     */
    val device: Device

    /**
     * `engine` The engine information, including the engine's name and version (e.g., Blink, WebKit)
     */
    val engine: Engine

    /**
     * `os` The operating system information, including the name and version (e.g., Windows, Linux, macOS)
     */
    val os: Os

}

/**
 * Represents the browser information extracted from the user agent string
 */
external interface Browser {

    /**
     * `name` The name of the browser (e.g., Chrome, Firefox)
     */
    val name: String?

    /**
     * `version` The version of the browser
     */
    val version: String?

    /**
     * `major` Major number derived from the first number in [version]
     */
    val major: String?

    /**
     * `type` Type of current browser (email, inapp, crawler)
     */
    val type: String?

}

/**
 * Represents the CPU architecture information extracted from the user agent string
 */
external interface CPU {

    /**
     * `architecture` The architecture of the CPU (e.g., x86, ARM)
     */
    val architecture: String?
}

/**
 * Represents the device information extracted from the user agent string
 */
external interface Device {

    /**
     * `model` The model of the device (e.g., iPhone 12, Galaxy S21)
     */
    val model: String?

    /**
     * `type` The type of the device (e.g., smartphone, tablet, laptop)
     */
    val type: String?

    /**
     * `vendor` The vendor of the device (e.g., Apple, Samsung, Lenovo)
     */
    val vendor: String?

}

/**
 * Represents the engine information extracted from the user agent string
 */
external interface Engine {

    /**
     * `name` The name of the engine (e.g., Blink, WebKit)
     */
    val name: String?

    /**
     * `version` The version of the engine
     */
    val version: String?

}

/**
 * Represents the operating system information extracted from the user agent string
 */
external interface Os {

    /**
     * `name` The name of the operating system (e.g., Windows, Linux, macOS)
     */
    val name: String?

    /**
     * `version` The version of the operating system
     */
    val version: String?

}