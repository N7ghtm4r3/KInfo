@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsGlobalMemoryImpl
import com.tecknobit.kinfo.hardware.MacOsVirtualMemoryImpl
import com.tecknobit.kinfo.utils.queryLongSysCtlByName
import kotlinx.cinterop.*
import platform.darwin.*
import platform.posix.mach_port_t

class MacOsGlobalMemoryMapper : MacOsHardwareMapper<MacOsGlobalMemoryImpl>() {

    override fun mapFromNative(): MacOsGlobalMemoryImpl {
        val pageSize = loadPageSize()

        return MacOsGlobalMemoryImpl(
            total = loadTotalMemory(),
            available = resolveAvailableMemory(
                pageSize = pageSize
            ),
            pageSize = pageSize,
            virtualMemory = MacOsVirtualMemoryImpl(
                swapTotal = 0,
                swapUsed = 0,
                virtualMax = 0,
                virtualInUse = 0,
                swapPagesIn = 0,
                swapPagesOut = 0
            ),
            physicalMemory = emptyList()
        )
    }

    @Loader
    private fun loadTotalMemory(): Long {
        return queryLongSysCtlByName(
            name = "hw.memsize",
            default = 0
        )!!
    }

    @Loader
    private fun loadPageSize(): Long {
        return memScoped {
            val buffer = alloc<vm_size_tVar>()
            val host = mach_host_self()

            useMachHost {
                val result = host_page_size(
                    host,
                    buffer.ptr
                )
                if (result != KERN_SUCCESS)
                    return@memScoped 0

                buffer.value.toLong()
            }
        }
    }

    @Resolver
    private fun resolveAvailableMemory(
        pageSize: Long
    ): Long {
        return memScoped {
            val buffer = alloc<vm_statistics64>()
            val count = alloc<mach_msg_type_number_tVar> {
                value = HOST_VM_INFO64_COUNT
            }

            useMachHost { host ->
                val result = host_statistics64(
                    host,
                    HOST_VM_INFO64,
                    buffer.ptr.reinterpret(),
                    count.ptr
                )
                if (result != KERN_SUCCESS)
                    return 0

                val freePages = buffer.free_count.toLong()
                val inactivePages = buffer.inactive_count.toLong()

                (freePages + inactivePages) * pageSize
            }
        }
    }

    private inline fun <T> useMachHost(
        usage: (mach_port_t) -> T
    ): T {
        val host = mach_host_self()

        return try {
            usage(host)
        } finally {
            mach_port_deallocate(
                mach_task_self_,
                host
            )
        }
    }

}