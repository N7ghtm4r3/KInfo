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

/**
 * The `MacOsGlobalMemoryMapper` class is useful to map physical, virtual, and available memory information from macOS
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsGlobalMemoryMapper : MacOsHardwareMapper<MacOsGlobalMemoryImpl>() {

    /**
     * Method used to map a fresh snapshot of macOS global memory
     *
     * Available memory is estimated from free and inactive pages
     * The native queries are independent and do not form an atomic snapshot
     *
     * @return the mapped memory snapshot as [MacOsGlobalMemoryImpl]
     * @throws IllegalStateException If swap usage or swap page statistics cannot be read
     */
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

    /**
     * Method used to read the total physical memory from `hw.memsize`
     *
     * @return the total memory in bytes, or zero when the query fails, as [Long]
     */
    @Loader
    private fun loadTotalMemory(): Long {
        return queryLongSysCtlByName(
            name = "hw.memsize",
            default = 0
        )!!
    }

    /**
     * Method used to read the Mach host memory page size
     *
     * @return the page size in bytes, or zero when the native query fails, as [Long]
     */
    @Loader
    private fun loadPageSize(): Long {
        return memScoped {
            val buffer = alloc<vm_size_tVar>()

            useMachHost { host ->
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

    /**
     * Method used to estimate available memory from free and inactive pages
     *
     * @param pageSize The memory page size in bytes
     *
     * @return the estimated available memory in bytes, or zero when the query fails, as [Long]
     */
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

    /**
     * Method used to map virtual memory using the supplied physical memory snapshot
     *
     * @param totalRam The total physical memory in bytes
     * @param availableRam The estimated available physical memory in bytes
     *
     * @return the mapped virtual memory as [MacOsVirtualMemory]
     * @throws IllegalStateException If swap usage or swap page statistics cannot be read
     */
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

    /**
     * Method used to map physical memory for the detected macOS architecture
     *
     * @return the mapped modules as [List] of [MacOsPhysicalMemory]
     */
    @Loader
    private fun loadPhysicalMemory(): List<MacOsPhysicalMemory> {
        val macOsPhysicalMemoryMapper = MacOsPhysicalMemoryMapper()

        return macOsPhysicalMemoryMapper.mapFromNative()
    }

}