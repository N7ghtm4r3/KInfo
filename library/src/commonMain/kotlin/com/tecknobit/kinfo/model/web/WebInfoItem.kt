package com.tecknobit.kinfo.model.web

import com.tecknobit.kinfo.UNKNOWN

interface WebInfoItem {

    /**
     * Method to use a null-safe value
     *
     * @return the found value or the [UNKNOWN] value as [String]
     */
    fun String?.safeValue(): String {
        return this ?: UNKNOWN
    }

}