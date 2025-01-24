package com.tecknobit.kinfo.model.browser

import com.tecknobit.kinfo.model.web.browser.Browser

class BrowserImpl(
    private val parsedBrowser: com.tecknobit.kinfo.model.Browser
) : Browser {

    override val name: String
        get() = parsedBrowser.name.safeValue()

    override val version: String
        get() = parsedBrowser.version.safeValue()

}