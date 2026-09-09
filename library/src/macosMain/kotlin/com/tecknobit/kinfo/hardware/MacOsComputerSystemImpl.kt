package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.*
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.InternetProtocolStats
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.NetworkParams
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsComputerSystem
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsDesktopWindow
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOSProcess
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOSThread
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsVersionInfo

data class MacOsComputerSystemImpl(
    override val family: String,
    override val manufacturer: String,
    override val versionInfo: MacOsVersionInfo,
    override val fileSystem: FileSystem,
    override val internetProtocolStats: InternetProtocolStats,
    override val processId: Int,
    override val currentProcess: MacOsOSProcess,
    override val processCount: Int,
    override val threadId: Int,
    override val currentThread: MacOsOSThread,
    override val threadCount: Int,
    override val bitness: Int,
    override val systemUptime: Long,
    override val systemBootTime: Long,
    override val isElevated: Boolean,
    override val networkParams: NetworkParams,
    override val services: List<OSService>,
    override val sessions: List<OSSession>,
    override val cgroupInfo: CgroupInfo
) : MacOsComputerSystem {

    override fun getProcesses(): List<MacOsOSProcess> {
        TODO("Not yet implemented")
    }

    override fun getProcesses(
        pids: Collection<Int>
    ): List<MacOsOSProcess> {
        TODO("Not yet implemented")
    }

    override fun getProcess(
        pid: Int
    ): MacOsOSProcess? {
        TODO("Not yet implemented")
    }

    override fun getOSDesktopWindows(
        visibleOnly: Boolean
    ): List<MacOsDesktopWindow> {
        TODO("Not yet implemented")
    }

    override fun parseNestedStatistics(
        procFile: String,
        vararg keys: String
    ): Map<String, Map<String, Long>> {
        TODO("Not yet implemented")
    }

    override fun parseStatistics(
        procFile: String,
        separator: Regex
    ): Map<String, Long> {
        TODO("Not yet implemented")
    }

    override fun queryInstalledApps(): List<ApplicationInfo> {
        TODO("Not yet implemented")
    }
}
