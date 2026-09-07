@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.operatingsystem.MacOsOSThreadImpl
import kotlinx.cinterop.*
import platform.osx.PROC_PIDLISTTHREADS
import platform.osx.PROC_PIDTHREADINFO
import platform.osx.proc_pidinfo
import platform.osx.proc_threadinfo

class MacOsThreadsMapper(
    private val processId: Int
) : NativeMapper<List<MacOsOSThreadImpl>>() {

    override fun mapFromNative(): List<MacOsOSThreadImpl> {
        memScoped {
            val buffer = alloc<proc_threadinfo>()
            val size = sizeOf<proc_threadinfo>()

            val result = proc_pidinfo(
                processId,
                PROC_PIDTHREADINFO,
                1u,
                buffer.ptr,
                1000
            )

            println(
                getThreadIds(
                    processId = processId
                )
            )
        }

        TODO()
    }

    private fun getThreadIds(
        processId: Int
    ): List<UInt> {
        val size = sizeOf<ULongVar>().toInt()

        return memScoped {
            val bytes = proc_pidinfo(
                processId,
                PROC_PIDLISTTHREADS,
                0UL,
                null,
                0
            )
            val threadCount = bytes / size

            println(bytes)
            println(threadCount)

            val buffer = allocArray<UIntVar>(threadCount)
            val actualBytes = proc_pidinfo(
                processId,
                PROC_PIDLISTTHREADS,
                0UL,
                buffer,
                threadCount
            )

            val threadsCount = actualBytes / size
            List(threadsCount) { index ->
                buffer[index]
            }
        }
    }

}