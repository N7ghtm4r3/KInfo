package com.tecknobit.kinfo.model.web.cpu

import com.tecknobit.kinfo.model.web.WebInfoItem

/**
 * Represents the CPU architecture information.
 *
 * This interface provides a property to retrieve the CPU's architecture (e.g., x86, ARM).
 * It can be used to gather details about the CPU for system information or analytics.
 *
 * @see WebInfoItem
 *
 * @author N7ghtm4r3
 */
interface CPU : WebInfoItem {

    /**
     * `architecture` The architecture of the CPU (e.g., x86, ARM).
     */
    val architecture: String

}
