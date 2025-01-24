package com.tecknobit.kinfo.model.web.browser

import com.tecknobit.kinfo.model.web.WebInfoItem

interface Browser : WebInfoItem {
    val name: String
    val version: String
}