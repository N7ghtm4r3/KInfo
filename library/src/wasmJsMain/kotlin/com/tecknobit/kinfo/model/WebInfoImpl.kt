package com.tecknobit.kinfo.model

import com.tecknobit.kinfo.model.browser.BrowserImpl
import com.tecknobit.kinfo.model.cpu.CPUImpl
import com.tecknobit.kinfo.model.device.DeviceImpl
import com.tecknobit.kinfo.model.engine.EngineImpl
import com.tecknobit.kinfo.model.operatingsystem.OsImpl
import com.tecknobit.kinfo.model.web.WebInfo
import com.tecknobit.kinfo.model.web.browser.Browser
import com.tecknobit.kinfo.model.web.cpu.CPU
import com.tecknobit.kinfo.model.web.device.Device
import com.tecknobit.kinfo.model.web.engine.Engine
import com.tecknobit.kinfo.model.web.operatingsystem.Os
import kotlinx.browser.window

/**
 * Implements the `WebInfo` interface, providing detailed web environment information such as the user agent,
 * browser, CPU, device, engine, and operating system.
 *
 * This class uses the `UAParser` library to parse the user agent string and extract relevant details about the
 * user's web environment, including browser, CPU architecture, device model, engine, and operating system.
 *
 * @see WebInfo
 * @see Browser
 * @see CPU
 * @see Device
 * @see Engine
 * @see Os
 * @see UAParser
 *
 * @author N7ghtm4r3
 */
class WebInfoImpl : WebInfo {

    /**
     * `userAgent` The user agent string that represents the client's web browser and system.
     * This value is obtained from `window.navigator.userAgent`.
     */
    override val userAgent: String = window.navigator.userAgent

    /**
     * `browser` The browser information, including the browser's name and version.
     * This value is extracted by parsing the user agent string.
     */
    override val browser: Browser

    /**
     * `cpu` The CPU architecture information (e.g., x86, ARM).
     * This value is extracted by parsing the user agent string.
     */
    override val cpu: CPU

    /**
     * `device` The device information, including the model, type, and vendor.
     * This value is extracted by parsing the user agent string.
     */
    override val device: Device

    /**
     * `engine` The engine information, including the engine's name and version (e.g., Blink, WebKit).
     * This value is extracted by parsing the user agent string.
     */
    override val engine: Engine

    /**
     * `os` The operating system information, including the name and version (e.g., Windows, Linux, macOS).
     * This value is extracted by parsing the user agent string.
     */
    override val os: Os

    init {
        val result = UAParser(
            userAgent = userAgent
        ).getResult()
        browser = BrowserImpl(
            parsedBrowser = result.browser
        )
        cpu = CPUImpl(
            parsedCPU = result.cpu
        )
        device = DeviceImpl(
            parsedDevice = result.device
        )
        engine = EngineImpl(
            parsedEngine = result.engine
        )
        os = OsImpl(
            parsedOs = result.os
        )
    }
}