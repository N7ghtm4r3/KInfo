package com.tecknobit.kinfo.model.desktop.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSProcess
import com.tecknobit.kinfo.model.desktop.operatingsystem.processes.OSThread
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.InternetProtocolStats
import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.NetworkParams

interface OperatingSystem {
    val family: String
    val manufacturer: String
    val versionInfo: OSVersionInfo
    val fileSystem: FileSystem
    val internetProtocolStats: InternetProtocolStats
    val processId: Int
    val currentProcess: OSProcess
    val processCount: Int
    val threadId: Int
    val currentThread: OSThread
    val threadCount: Int
    val bitness: Int
    val systemUptime: Long
    val systemBootTime: Long
    val isElevated: Boolean
    val networkParams: NetworkParams
    val services: List<OSService>
    val sessions: List<OSSession>

    fun getProcesses(): List<OSProcess>

    fun getProcesses(pids: Collection<Int>): List<OSProcess>

    fun getProcess(pid: Int): OSProcess

    fun getOSDesktopWindows(visibleOnly: Boolean): List<OSDesktopWindow>
}