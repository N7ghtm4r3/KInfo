@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.globalmemory

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsVirtualMemoryImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.utils.queryItemSysCtlByName
import com.tecknobit.kinfo.utils.useMachHost
import kotlinx.cinterop.*
import platform.darwin.*

class MacOsVirtualMemoryMapper(
    private val totalRam: Long,
    private val availableRam: Long
) : MacOsHardwareMapper<MacOsVirtualMemoryImpl>() {

    override fun mapFromNative(): MacOsVirtualMemoryImpl {
        val swapUsage = loadSwapUsage()
        val swapTotal = swapUsage.total
        val swapUsed = swapUsage.used

        val swapPagesStat = loadSwapPagesStats()

        return MacOsVirtualMemoryImpl(
            swapTotal = swapTotal,
            swapUsed = swapUsed,
            virtualMax = swapTotal + totalRam,
            virtualInUse = (totalRam - availableRam) + swapUsed,
            swapPagesIn = swapPagesStat.pagesIn,
            swapPagesOut = swapPagesStat.pagesOut
        )
    }

    @Loader
    private fun loadSwapUsage(): SwapUsage {
        return queryItemSysCtlByName<SwapUsage, xsw_usage>(
            name = "vm.swapusage",
            default = null,
            returns = { _, buffer ->
                val usage = buffer.pointed

                SwapUsage(
                    used = usage.xsu_used.toLong(),
                    total = usage.xsu_total.toLong()
                )
            }
        ) ?: throw IllegalStateException("Unable to read swap usage")
    }

    @Loader
    private fun loadSwapPagesStats(): SwapPagesStat {
        return memScoped {
            val stats = alloc<vm_statistics64>()
            val count = alloc<mach_msg_type_number_tVar> {
                value = HOST_VM_INFO64_COUNT
            }

            useMachHost { host ->
                val result = host_statistics64(
                    host,
                    HOST_VM_INFO64,
                    stats.ptr.reinterpret(),
                    count.ptr
                )
                if (result != KERN_SUCCESS)
                    throw IllegalStateException("Unable to read swap pages stats")

                SwapPagesStat(
                    pagesIn = stats.swapins.toLong(),
                    pagesOut = stats.swapouts.toLong()
                )
            }
        }
    }

}

private data class SwapUsage(
    val used: Long,
    val total: Long
)

private data class SwapPagesStat(
    val pagesIn: Long,
    val pagesOut: Long
)