package com.tecknobit.kinfo.model.desktop.operatingsystem

interface ApplicationInfo {

    val name: String

    val version: String

    val vendor: String

    val timestamp: Long

    val additionalInfo: Map<String, String>

}