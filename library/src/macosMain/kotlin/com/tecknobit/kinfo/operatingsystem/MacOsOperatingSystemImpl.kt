package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOperatingSystem
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSDesktopWindow
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSFileStore
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSVersionInfo
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPConnection
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.IPRoute
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.TcpStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats

data class MacOsOperatingSystemImpl(
    override val operatingSystemVersion: OSVersionInfo,
    override val statfs: OSFileStore,
    override val cgWindowId: OSDesktopWindow,
    override val utmpx: OSSession,
    override val procTaskAllInfo: OSProcess,
    override val procThreadInfo: OSThread,
    override val socketFdInfo: IPConnection,
    override val rtMsgHdr2: IPRoute,
    override val tcpStat: TcpStats,
    override val udpStat: UdpStats
) : MacOsOperatingSystem