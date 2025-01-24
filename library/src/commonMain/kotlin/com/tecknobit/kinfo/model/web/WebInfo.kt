package com.tecknobit.kinfo.model.web

import com.tecknobit.kinfo.model.web.browser.Browser
import com.tecknobit.kinfo.model.web.cpu.CPU
import com.tecknobit.kinfo.model.web.device.Device
import com.tecknobit.kinfo.model.web.engine.Engine
import com.tecknobit.kinfo.model.web.operatingsystem.Os

interface WebInfo {
    val userAgent: String
    val browser: Browser
    val cpu: CPU
    val device: Device
    val engine: Engine
    val os: Os
}