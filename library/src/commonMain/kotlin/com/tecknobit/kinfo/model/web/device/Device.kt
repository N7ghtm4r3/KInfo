package com.tecknobit.kinfo.model.web.device

import com.tecknobit.kinfo.model.web.WebInfoItem

interface Device : WebInfoItem {
    val model: String
    val type: String
    val vendor: String
}