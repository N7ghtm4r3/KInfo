@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.operatingsystem

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.mappers.NativeMapper
import com.tecknobit.kinfo.model.desktop.common.operatingsystem.processes.State
import com.tecknobit.kinfo.operatingsystem.MacOsOSThreadImpl
import kotlinx.cinterop.*
import platform.osx.*
import kotlin.time.Clock

/**
 * The `MacOsThreadsMapper` class is useful to map native macOS thread information to KInfo models
 *
 * @property processId The identifier of the process owning the threads
 * @property threadCount The number of threads used to size the native address buffer
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
class MacOsThreadsMapper(
    private val processId: Int,
    private val threadCount: Int
) : NativeMapper<List<MacOsOSThreadImpl>>() {

    /**
     * Method used to map the native thread information of the process to macOS thread models
     *
     * @return the mapped threads as [List] of [MacOsOSThreadImpl]
     */
    override fun mapFromNative(): List<MacOsOSThreadImpl> {
        val threadAddresses = resolveThreadAddresses()

        return mapThreadsFromAddresses(
            threadAddresses = threadAddresses
        )
    }

    /**
     * Method used to resolve the valid native thread addresses owned by the process
     *
     * @return the native thread addresses as [List] of [ULong]
     */
    @Resolver
    private fun resolveThreadAddresses(): List<ULong> {
        return memScoped {
            val size = sizeOf<ULongVar>().toInt()
            val buffer = allocArray<ULongVar>(threadCount)

            val actualBytes = proc_pidinfo(
                processId,
                PROC_PIDLISTTHREADS,
                0uL,
                buffer,
                size * threadCount
            )

            val threadsCount = actualBytes / size
            List(threadsCount) { index ->
                buffer[index]
            }.filter { it != 0UL }
        }
    }

    /**
     * Method used to map native thread information from the related thread addresses
     *
     * @param threadAddresses The native addresses of the threads to map
     *
     * @return the mapped threads as [List] of [MacOsOSThreadImpl]
     */
    private fun mapThreadsFromAddresses(
        threadAddresses: List<ULong>
    ): List<MacOsOSThreadImpl> {
        val mappedThreads = mutableListOf<MacOsOSThreadImpl>()

        return memScoped {
            val buffer = alloc<proc_threadinfo>()
            val bufferSize = sizeOf<proc_threadinfo>().toInt()

            threadAddresses.forEachIndexed { index, threadAddress ->
                val result = proc_pidinfo(
                    processId,
                    PROC_PIDTHREADINFO,
                    threadAddress,
                    buffer.ptr,
                    bufferSize
                )
                if (result != bufferSize)
                    return@forEachIndexed

                val threadOs = buffer.toMacOsOSThread(
                    threadAddress = threadAddress,
                    threadId = index,
                )
                mappedThreads.add(threadOs)
            }

            mappedThreads
        }
    }

    /**
     * Method used to map the native thread information to its macOS model
     *
     * @receiver The native thread information to map
     *
     * @param threadAddress The native address associated with the thread information
     * @param threadId The snapshot-local identifier assigned to the thread
     *
     * @return the mapped thread as [MacOsOSThreadImpl]
     */
    private fun proc_threadinfo.toMacOsOSThread(
        threadAddress: ULong,
        threadId: Int
    ): MacOsOSThreadImpl {
        val kernelTime = pth_system_time.toMillis()
        val userTime = pth_user_time.toMillis()
        val cpuTime = kernelTime + userTime
        val cpuLoad = pth_cpu_usage.toDouble() / 1000
        val upTime = (cpuTime / cpuLoad).toLong()
        val startTime = Clock.System.now().toEpochMilliseconds() - upTime

        return MacOsOSThreadImpl(
            threadId = threadId,
            name = pth_name.toKString(),
            state = pth_run_state.resolveState(),
            threadCpuLoadCumulative = cpuLoad,
            threadCpuLoadBetweenTicks = 0.0,
            owningProcessId = processId,
            kernelTime = kernelTime,
            userTime = userTime,
            upTime = upTime,
            startTime = startTime,
            priority = pth_curpri
        )
    }

    /**
     * Method used to resolve the common thread state from its native macOS value
     *
     * @receiver The native macOS thread state to resolve
     *
     * @return the resolved state as [State]
     */
    @Resolver
    private fun Int.resolveState(): State {
        return when (this) {
            TH_STATE_RUNNING -> State.RUNNING
            TH_STATE_STOPPED, TH_STATE_HALTED -> State.STOPPED
            TH_STATE_WAITING -> State.SLEEPING
            TH_STATE_UNINTERRUPTIBLE -> State.WAITING

            else -> State.OTHER
        }
    }

    /**
     * Method used to convert the native nanosecond value to milliseconds
     *
     * @receiver The native nanosecond value to convert
     *
     * @return the converted time as [Long]
     */
    private fun ULong.toMillis(): Long {
        return this.toLong() / 1_000_000
    }

}
