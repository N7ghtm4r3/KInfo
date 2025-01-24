package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSVersionInfo

class OSVersionInfoImpl(private val osVersionInfo: oshi.software.os.OperatingSystem.OSVersionInfo) :
    OSVersionInfo {

    override val version: String
        get() = osVersionInfo.version

    override val codeName: String
        get() = osVersionInfo.codeName

    override val buildNumber: String
        get() = osVersionInfo.buildNumber
}