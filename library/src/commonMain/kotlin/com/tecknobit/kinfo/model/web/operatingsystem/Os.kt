package com.tecknobit.kinfo.model.web.operatingsystem

import com.tecknobit.kinfo.model.web.WebInfoItem

interface Os : WebInfoItem {
    val name: String
    val version: String
}