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

class WebInfoImpl : WebInfo {

    override val userAgent: String = window.navigator.userAgent

    override val browser: Browser

    override val cpu: CPU

    override val device: Device

    override val engine: Engine

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