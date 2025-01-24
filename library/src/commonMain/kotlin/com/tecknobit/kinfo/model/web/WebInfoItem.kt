package com.tecknobit.kinfo.model.web

import com.tecknobit.kinfo.UNKNOWN

/**
 * Represents a general web information item.
 * This interface includes a utility method `safeValue()` to return a null-safe value.
 * It can be implemented by other interfaces or classes that deal with web-related data
 *
 * @author N7ghtm4r3
 */
interface WebInfoItem {

    /**
     * Method to use a null-safe value.
     *
     * If the value is null, it returns the [UNKNOWN] value instead.
     *
     * @return the found value or the [UNKNOWN] value as [String]
     */
    fun String?.safeValue(): String {
        return this ?: UNKNOWN
    }

}
