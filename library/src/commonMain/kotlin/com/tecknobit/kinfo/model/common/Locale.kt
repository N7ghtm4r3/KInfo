package com.tecknobit.kinfo.model.common

/**
 * `Locale` is a data class representing the locale information for a device, which includes the language code and the region.
 * This class is used to store and retrieve information about the device's locale settings, such as language and region.
 *
 * @property languageCode the language code of the locale (e.g., "en" for English, "fr" for French).
 * @property region the region code of the locale (e.g., "US" for the United States, "IN" for India).
 *
 * @author Swapnil Musale
 */
data class Locale(
    val languageCode: String,
    val region: String
)
