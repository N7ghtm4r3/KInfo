package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.helpers.CpuTicksRegistry
import kotlin.time.Clock

/**
 * Method used to resolve the CPU load of a thread between registered tick samples
 *
 * The CPU load reported by macOS is returned when no valid sample spanning at least one second is available
 *
 * @param processId The identifier of the process owning the thread
 * @param threadAddress The native address used to identify the thread samples
 * @param userTime The time spent by the thread in user mode in milliseconds
 * @param kernelTime The time spent by the thread in kernel mode in milliseconds
 * @param processCpuLoadCumulative The CPU load reported by macOS used when a valid interval is unavailable
 *
 * @return the CPU load between registered tick samples as [Double]
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
@Resolver
fun resolveThreadCpuLoadBetweenTicks(
    processId: Int,
    threadAddress: ULong,
    userTime: Long,
    kernelTime: Long,
    processCpuLoadCumulative: Double
): Double {
    val threadKey = (31 * processId) + threadAddress.hashCode()

    return resolveProcessCpuLoadBetweenTicks(
        processId = threadKey,
        userTime = userTime,
        kernelTime = kernelTime,
        processCpuLoadCumulative = processCpuLoadCumulative
    )
}

/**
 * Method used to resolve the CPU load of a process between registered tick samples
 *
 * The cumulative CPU load is returned when no valid sample spanning at least one second is available
 *
 * @param processId The identifier of the process
 * @param userTime The time spent by the process in user mode in milliseconds
 * @param kernelTime The time spent by the process in kernel mode in milliseconds
 * @param processCpuLoadCumulative The cumulative CPU load used when a valid interval is unavailable
 *
 * @return the CPU load between registered tick samples as [Double]
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
@Resolver
fun resolveProcessCpuLoadBetweenTicks(
    processId: Int,
    userTime: Long,
    kernelTime: Long,
    processCpuLoadCumulative: Double
): Double {
    return CpuTicksRegistry.use {
        val currentCpuTime = userTime + kernelTime
        val currentDeltaTime = Clock.System.now().toEpochMilliseconds()

        val previousCpuTick = retrievePreviousCpuTick(
            pid = processId,
            defaultValue = CpuTicksRegistry.CpuTick(
                cpuTime = currentCpuTime,
                timestamp = currentDeltaTime
            )
        )

        val deltaCpuTime = currentCpuTime - previousCpuTick.cpuTime
        val deltaTime = currentDeltaTime - previousCpuTick.timestamp
        if (deltaCpuTime < 0L || deltaTime < 1000L)
            return@use processCpuLoadCumulative

        registerCpuTick(
            processId = processId,
            cpuTime = currentCpuTime,
            timestamp = currentDeltaTime
        )

        deltaCpuTime / deltaTime.toDouble()
    }
}
