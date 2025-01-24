package com.tecknobit.kinfo.model.web.cpu

import com.tecknobit.kinfo.model.web.WebInfoItem

interface CPU : WebInfoItem {
    val architecture: String
}