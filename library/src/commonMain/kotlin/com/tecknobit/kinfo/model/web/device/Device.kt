package com.tecknobit.kinfo.model.web.device

import com.tecknobit.kinfo.model.web.WebInfoItem

/**
 * Represents a device with its model, type, and vendor information.
 *
 * This interface provides properties to retrieve details about the device's model, type, and vendor.
 * It can be used to capture information about the device for system details, analytics, or diagnostics.
 *
 * @see WebInfoItem
 *
 * @author N7ghtm4r3
 */
interface Device : WebInfoItem {

    /**
     * `model` The model of the device (e.g., iPhone 12, Galaxy S21).
     */
    val model: String

    /**
     * `type` The type of the device (e.g., smartphone, tablet, laptop).
     */
    val type: String

    /**
     * `vendor` The vendor of the device (e.g., Apple, Samsung, Lenovo).
     */
    val vendor: String

}
