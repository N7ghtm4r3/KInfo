@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.annotations.Resolver
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

/**
 * The `MacOsOSProcessMapper` class is useful to map native information about the current macOS process to a KInfo model
 *
 * @property processInfo The Foundation process information used to resolve process details
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
class MacOsOSProcessMapper(
    private val processInfo: NSProcessInfo
) : NativeMapper<MacOsOsProcessImpl>() {

    /**
     * Method used to map the native information about the current process to its [MacOsOsProcessImpl] model
     *
     * @return the mapped process information as [MacOsOsProcessImpl]
     */
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

    /**
     * Method used to resolve the executable path of a process
     *
     * @param processId The identifier of the process
     *
     * @return the executable path or an empty value when unavailable as [String]
     */
    @Resolver
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

    /**
     * Method used to resolve the arguments of the current process
     *
     * @return the process arguments as [List] of [String]
     */
    @Resolver
    private fun resolveArguments(): List<String> {
        return processInfo.arguments.map { argument ->
            argument.toString()
        }
    }

    /**
     * Method used to resolve the environment variables of the current process
     *
     * @return the process environment variables as [Map]
     */
    @Resolver
    private fun resolveEnvironmentVariables(): Map<String, String> {
        val nativeEnvironmentVariables = processInfo.environment

        return buildMap {
            nativeEnvironmentVariables.forEach { (key, value) ->
                put(key.toString(), value.toString())
            }
        }
    }

    /**
     * Method used to resolve the current working directory of the process
     *
     * @return the current working directory or an empty value when unavailable as [String]
     */
    @Resolver
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

    /**
     * Method used to resolve the username associated with a native user identifier
     *
     * @param userId The native user identifier to resolve
     *
     * @return the resolved username or an empty value when unavailable as [String]
     */
    @Resolver
    private fun resolveUser(
        userId: UInt
    ): String {
        val result = getpwuid(userId)?.pointed ?: return ""
        val name = result.pw_name?.toKString()

        return name.orEmpty()
    }

    /**
     * Method used to resolve the group name associated with a native group identifier
     *
     * @param groupId The native group identifier to resolve
     *
     * @return the resolved group name or an empty value when unavailable as [String]
     */
    @Resolver
    private fun resolveGroup(
        groupId: UInt
    ): String {
        val result = getgrgid(groupId)?.pointed ?: return ""
        val name = result.gr_name?.toKString()

        return name.orEmpty()
    }

    /**
     * Method used to resolve the process state associated with a native status value
     *
     * @param status The native process status to resolve
     *
     * @return the resolved process state as [State]
     */
    @Resolver
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

    /**
     * Method used to resolve the resource usage of a process
     *
     * @param processId The identifier of the process
     *
     * @return the resolved resource usage as [ResourcesUsage]
     */
    @Resolver
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

    /**
     * Method used to resolve the open file limits of the current process
     *
     * @return the resolved open file limits as [FileLimits]
     */
    @Resolver
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

    /**
     * Method used to resolve the CPU times and context switches of the current process
     *
     * @return the resolved process times as [ProcessTimes]
     */
    @Resolver
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

    /**
     * Method used to resolve the cumulative CPU load of the process
     *
     * @param userTime The time spent by the process in user mode in milliseconds
     * @param kernelTime The time spent by the process in kernel mode in milliseconds
     * @param startTime The process start time in milliseconds since the Unix epoch
     *
     * @return the cumulative CPU load as [Double]
     */
    @Resolver
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

    /**
     * Method used to resolve the process bitness from its native flags
     *
     * @param flags The native process flags to inspect
     *
     * @return the resolved process bitness as [Int]
     */
    @Resolver
    private fun resolveBitness(
        flags: UInt
    ): Int {
        val is64Bit = flags and PROC_FLAG_LP64.toUInt() != 0u

        return if (is64Bit) 64 else 32
    }

    /**
     * Method used to resolve the affinity mask supported by the available logical processors
     *
     * @return the resolved affinity mask as [Long]
     */
    @Resolver
    private fun resolveAffinityMask(): Long {
        val logicalProcessors = processInfo.processorCount.toInt()

        return when {
            logicalProcessors <= 0 -> 0L
            logicalProcessors >= 64 -> -1L
            else -> (1L shl logicalProcessors) - 1L
        }
    }

}

/**
 * The `ResourcesUsage` class is useful to collect native resource usage values for a process
 *
 * @property privateResidentMemory The private resident memory used by the process in bytes
 * @property bytesRead The number of bytes read by the process
 * @property bytesWritten The number of bytes written by the process
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class ResourcesUsage(
    val privateResidentMemory: Long = -1L,
    val bytesRead: Long = -1L,
    val bytesWritten: Long = -1L
)

/**
 * The `FileLimits` class is useful to collect the open file limits of a process
 *
 * @property softOpenFileLimit The soft limit on the number of open files
 * @property hardOpenFileLimit The hard limit on the number of open files
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class FileLimits(
    val softOpenFileLimit: Long = -1L,
    val hardOpenFileLimit: Long = -1L
)

/**
 * The `ProcessTimes` class is useful to collect CPU times and context switch counts for a process
 *
 * @property userTime The time spent by the process in user mode in milliseconds
 * @property kernelTime The time spent by the process in kernel mode in milliseconds
 * @property voluntaryContextSwitches The number of voluntary context switches performed by the process
 * @property involuntaryContextSwitches The number of involuntary context switches performed by the process
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class ProcessTimes(
    val userTime: Long = -1L,
    val kernelTime: Long = -1L,
    val voluntaryContextSwitches: Long = -1L,
    val involuntaryContextSwitches: Long = -1L
)
