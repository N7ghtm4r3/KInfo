@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.globalmemory

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsGlobalMemoryImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalMemory
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsVirtualMemory
import com.tecknobit.kinfo.utils.queryLongSysCtlByName
import com.tecknobit.kinfo.utils.useMachHost
import kotlinx.cinterop.*
import platform.darwin.*

class MacOsGlobalMemoryMapper : MacOsHardwareMapper<MacOsGlobalMemoryImpl>() {

    override fun mapFromNative(): MacOsGlobalMemoryImpl {
        val totalMemory = loadTotalMemory()
        val pageSize = loadPageSize()
        val available = resolveAvailableMemory(
            pageSize = pageSize
        )

        return MacOsGlobalMemoryImpl(
            total = totalMemory,
            available = available,
            pageSize = pageSize,
            virtualMemory = loadVirtualMemory(
                totalRam = totalMemory,
                availableRam = available
            ),
            physicalMemory = loadPhysicalMemory()
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
                    return@memScoped 0

                val freePages = buffer.free_count.toLong()
                val inactivePages = buffer.inactive_count.toLong()

                (freePages + inactivePages) * pageSize
            }
        }
    }

    @Loader
    private fun loadVirtualMemory(
        totalRam: Long,
        availableRam: Long
    ): MacOsVirtualMemory {
        val macOsVirtualMemoryMapper = MacOsVirtualMemoryMapper(
            totalRam = totalRam,
            availableRam = availableRam
        )

        return macOsVirtualMemoryMapper.mapFromNative()
    }

    @Loader
    private fun loadPhysicalMemory(): List<MacOsPhysicalMemory> {
        val macOsPhysicalMemoryMapper = MacOsPhysicalMemoryMapper()

        return macOsPhysicalMemoryMapper.mapFromNative()
    }

}