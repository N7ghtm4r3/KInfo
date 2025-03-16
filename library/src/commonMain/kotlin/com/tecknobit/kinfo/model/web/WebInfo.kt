package com.tecknobit.kinfo.model.web

import com.tecknobit.kinfo.model.web.browser.Browser
import com.tecknobit.kinfo.model.web.cpu.CPU
import com.tecknobit.kinfo.model.web.device.Device
import com.tecknobit.kinfo.model.web.engine.Engine
import com.tecknobit.kinfo.model.web.operatingsystem.Os

/**
 * Represents information about the web environment, including the user agent, browser, CPU, device, engine, and operating system.
 *
 * This interface provides properties to retrieve details about the web environment, such as the user agent string,
 * browser, CPU architecture, device model and vendor, engine details, and operating system.
 *
 * @see Browser
 * @see CPU
 * @see Device
 * @see Engine
 * @see Os
 * @see WebInfoItem
 *
 * @author N7ghtm4r3
 */
interface WebInfo : WebInfoItem {

    /**
     * `userAgent` The user agent string that represents the client's web browser and system
     */
    val userAgent: String

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
