package com.tecknobit.kinfo.model.web.engine

import com.tecknobit.kinfo.model.web.WebInfoItem

interface Engine : WebInfoItem {
    val name: String
    val version: String
}