package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.ApplicationInfo

data class ApplicationInfoImpl(
    override val name: String,
    override val version: String,
    override val vendor: String,
    override val timestamp: Long,
    override val additionalInfo: Map<String, String>
) : ApplicationInfo
