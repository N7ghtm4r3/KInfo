@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware.globalmemory

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsVirtualMemoryImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.utils.queryItemSysCtlByName
import com.tecknobit.kinfo.utils.useMachHost
import kotlinx.cinterop.*
import platform.darwin.*

/**
 * The `MacOsVirtualMemoryMapper` class is useful to map macOS swap statistics and derive virtual memory estimates
 *
 * @property totalRam The total physical memory snapshot in bytes
 * @property availableRam The available physical memory estimate in bytes
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsVirtualMemoryMapper(
    private val totalRam: Long,
    private val availableRam: Long
) : MacOsHardwareMapper<MacOsVirtualMemoryImpl>() {

    /**
     * Method used to combine native swap statistics with the supplied RAM snapshot
     *
     * The virtual maximum is total RAM plus total swap
     * Virtual memory in use is total RAM minus available RAM plus used swap
     *
     * @return the virtual memory snapshot as [MacOsVirtualMemoryImpl]
     * @throws IllegalStateException If swap usage or swap page statistics cannot be read
     */
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

    /**
     * Method used to read swap usage from `vm.swapusage`
     *
     * @return the used and total swap space in bytes as [SwapUsage]
     * @throws IllegalStateException If the system control query fails
     */
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

    /**
     * Method used to read cumulative swap page counters from Mach host statistics
     *
     * @return the swap page counters as [SwapPagesStat]
     * @throws IllegalStateException If the host statistics query fails
     */
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

/**
 * The `SwapUsage` class is useful to store native swap space measurements
 *
 * @property used The used swap space in bytes
 * @property total The total swap space in bytes
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class SwapUsage(
    val used: Long,
    val total: Long
)

/**
 * The `SwapPagesStat` class is useful to store cumulative native swap page counters
 *
 * @property pagesIn The number of pages swapped in
 * @property pagesOut The number of pages swapped out
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class SwapPagesStat(
    val pagesIn: Long,
    val pagesOut: Long
)