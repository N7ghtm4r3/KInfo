package com.tecknobit.kinfo

import com.tecknobit.kinfo.model.UAParser
import com.tecknobit.kinfo.model.UAParserResult

/**
 * Method used to retrieve the result parsed by the `UAParser`
 *
 * @param userAgent The user agent string to parse by the `UAParser`
 *
 * @return The result of the parsing as [UAParserResult]
 *
 * @since 1.0.6
 */
actual fun getUaParserResult(
    userAgent: String
): UAParserResult {
    val uaParser = UAParser(
        userAgent = userAgent
    )
    return uaParser.getResult()
}