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
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
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