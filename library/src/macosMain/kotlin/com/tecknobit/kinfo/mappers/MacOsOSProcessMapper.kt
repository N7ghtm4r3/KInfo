@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.State
import com.tecknobit.kinfo.operatingsystem.MacOsOsProcessImpl
import com.tecknobit.kinfo.utils.resolveCumulativeTime
import kotlinx.cinterop.*
import kotlinx.cinterop.ByteVar
import platform.Foundation.NSProcessInfo
import platform.darwin.*
import platform.osx.*
import platform.posix.*
import kotlin.time.Clock

class MacOsOSProcessMapper(
    private val processInfo: NSProcessInfo
) : NativeMapper<MacOsOsProcessImpl>() {

    override fun mapFromNative(): MacOsOsProcessImpl {
        return memScoped {
            val buffer = alloc<proc_taskallinfo>()
            val bufferSize = sizeOf<proc_taskallinfo>().toInt()

            val result = proc_pidinfo(
                pid = getpid(),
                flavor = PROC_PIDTASKALLINFO,
                arg = 0uL,
                buffer = buffer.ptr,
                buffersize = bufferSize
            )
            if (result != bufferSize)
                throw IllegalStateException("Cannot read proc pid info")

            val pbsd = buffer.pbsd
            val ptinfo = buffer.ptinfo
            val processId = pbsd.pbi_pid.toInt()
            val arguments = resolveArguments()
            val userId = pbsd.pbi_uid
            val groupId = pbsd.pbi_gid
            val resourcesUsage = resolveResourcesUsage(
                processId = processId
            )
            val fileLimits = resolveFileLimits()
            val processTimes = resolveProcessTimes()

            val userTime = processTimes.userTime
            val kernelTime = processTimes.kernelTime
            val startTime = resolveCumulativeTime(
                seconds = pbsd.pbi_start_tvsec,
                microseconds = pbsd.pbi_start_tvusec
            )
            val majorFaults = ptinfo.pti_pageins.toLong()

            MacOsOsProcessImpl(
                name = pbsd.pbi_name.toKString(),
                path = resolveProcessPath(
                    processId = processId
                ),
                commandLine = arguments[0],
                arguments = arguments,
                environmentVariables = resolveEnvironmentVariables(),
                currentWorkingDirectory = resolveCurrentWorkingDirectory(),
                user = resolveUser(
                    userId = userId
                ),
                userId = userId.toString(),
                group = resolveGroup(
                    groupId = groupId
                ),
                groupId = groupId.toString(),
                state = resolveProcessState(
                    status = pbsd.pbi_status
                ),
                processId = processId,
                parentProcessId = pbsd.pbi_ppid.toInt(),
                threadCount = ptinfo.pti_threadnum,
                priority = ptinfo.pti_priority,
                virtualSize = ptinfo.pti_virtual_size.toLong(),
                residentMemory = ptinfo.pti_resident_size.toLong(),
                privateResidentMemory = resourcesUsage.privateResidentMemory,
                kernelTime = kernelTime,
                userTime = userTime,
                startTime = startTime,
                bytesRead = resourcesUsage.bytesRead,
                bytesWritten = resourcesUsage.bytesWritten,
                openFiles = pbsd.pbi_nfiles.toLong(),
                softOpenFileLimit = fileLimits.softOpenFileLimit,
                hardOpenFileLimit = fileLimits.hardOpenFileLimit,
                processCpuLoadCumulative = resolveCumulativeCpuLoad(
                    userTime = userTime,
                    kernelTime = kernelTime,
                    startTime = startTime
                ),
                processCpuLoadBetweenTicks = 1.0,
                bitness = resolveBitness(
                    flags = pbsd.pbi_flags
                ),
                affinityMask = resolveAffinityMask(),
                threadDetails = emptyList(),
                minorFaults = ptinfo.pti_faults.toLong() - majorFaults,
                majorFaults = majorFaults,
                contextSwitches = ptinfo.pti_csw.toLong(),
                voluntaryContextSwitches = processTimes.voluntaryContextSwitches,
                involuntaryContextSwitches = processTimes.involuntaryContextSwitches
            )
        }
    }

    private fun resolveProcessPath(
        processId: Int
    ): String {
        return memScoped {
            val bufferSize = PROC_PIDPATHINFO_MAXSIZE
            val buffer = allocArray<ByteVar>(
                length = bufferSize
            )

            val actual = proc_pidpath(
                pid = processId,
                buffer = buffer,
                buffersize = bufferSize.toUInt()
            )
            if (actual <= 0)
                return ""

            buffer.toKString()
        }
    }

    private fun resolveArguments(): List<String> {
        return processInfo.arguments.map { argument ->
            argument.toString()
        }
    }

    private fun resolveEnvironmentVariables(): Map<String, String> {
        val nativeEnvironmentVariables = processInfo.environment

        return buildMap {
            nativeEnvironmentVariables.forEach { (key, value) ->
                put(key.toString(), value.toString())
            }
        }
    }

    private fun resolveCurrentWorkingDirectory(): String {
        return memScoped {
            val bufferSize = PATH_MAX
            val buffer = allocArray<ByteVar>(bufferSize)

            val result = getcwd(
                buffer,
                bufferSize.toULong()
            ) ?: return@memScoped ""

            result.toKString()
        }
    }

    private fun resolveUser(
        userId: UInt
    ): String {
        val result = getpwuid(userId)?.pointed ?: return ""
        val name = result.pw_name?.toKString()

        return name.orEmpty()
    }

    private fun resolveGroup(
        groupId: UInt
    ): String {
        val result = getgrgid(groupId)?.pointed ?: return ""
        val name = result.gr_name?.toKString()

        return name.orEmpty()
    }

    private fun resolveProcessState(
        status: UInt
    ): State {
        return when (status.toInt()) {
            SIDL -> State.NEW
            SRUN -> State.RUNNING
            SSLEEP -> State.SLEEPING
            SSTOP -> State.STOPPED
            SZOMB -> State.ZOMBIE
            else -> State.OTHER
        }
    }

    private fun resolveResourcesUsage(
        processId: Int
    ): ResourcesUsage {
        return memScoped {
            val buffer = alloc<rusage_info_v2>()

            val result = proc_pid_rusage(
                processId,
                RUSAGE_INFO_V2,
                buffer.ptr.reinterpret()
            )
            if (result != 0)
                return@memScoped ResourcesUsage()

            ResourcesUsage(
                privateResidentMemory = buffer.ri_phys_footprint.toLong(),
                bytesRead = buffer.ri_diskio_bytesread.toLong(),
                bytesWritten = buffer.ri_diskio_byteswritten.toLong()
            )
        }
    }

    private fun resolveFileLimits(): FileLimits {
        return memScoped {
            val buffer = alloc<rlimit>()

            val result = getrlimit(
                RLIMIT_NOFILE,
                buffer.ptr
            )
            if (result != 0)
                return@memScoped FileLimits()

            FileLimits(
                softOpenFileLimit = buffer.rlim_cur.toLong(),
                hardOpenFileLimit = buffer.rlim_max.toLong()
            )
        }
    }

    private fun resolveProcessTimes(): ProcessTimes {
        return memScoped {
            val buffer = alloc<rusage>()

            val result = getrusage(
                RUSAGE_SELF,
                buffer.ptr
            )
            if (result != 0)
                return@memScoped ProcessTimes()

            ProcessTimes(
                userTime = resolveCumulativeTime(
                    seconds = buffer.ru_utime.tv_sec,
                    microseconds = buffer.ru_utime.tv_usec
                ),
                kernelTime = resolveCumulativeTime(
                    seconds = buffer.ru_stime.tv_sec,
                    microseconds = buffer.ru_stime.tv_usec
                ),
                voluntaryContextSwitches = buffer.ru_nvcsw,
                involuntaryContextSwitches = buffer.ru_nivcsw,
            )
        }
    }

    private fun resolveCumulativeCpuLoad(
        userTime: Long,
        kernelTime: Long,
        startTime: Long
    ): Double {
        val currentTime = Clock.System.now().toEpochMilliseconds()
        val upTime = currentTime - startTime
        if (upTime == 0L)
            return 0.0

        return (userTime + kernelTime).toDouble() / upTime
    }

    private fun resolveBitness(
        flags: UInt
    ): Int {
        val is64Bit = flags and PROC_FLAG_LP64.toUInt() != 0u

        return if (is64Bit) 64 else 32
    }

    private fun resolveAffinityMask(): Long {
        val logicalProcessors = processInfo.processorCount.toInt()

        return when {
            logicalProcessors <= 0 -> 0L
            logicalProcessors >= 64 -> -1L
            else -> (1L shl logicalProcessors) - 1L
        }
    }

}

private data class ResourcesUsage(
    val privateResidentMemory: Long = -1L,
    val bytesRead: Long = -1L,
    val bytesWritten: Long = -1L
)

private data class FileLimits(
    val softOpenFileLimit: Long = -1L,
    val hardOpenFileLimit: Long = -1L
)

private data class ProcessTimes(
    val userTime: Long = -1L,
    val kernelTime: Long = -1L,
    val voluntaryContextSwitches: Long = -1L,
    val involuntaryContextSwitches: Long = -1L
)