package com.tecknobit.kinfo.utils

import platform.darwin.mach_host_self
import platform.darwin.mach_port_deallocate
import platform.darwin.mach_task_self_
import platform.posix.mach_port_t

/**
 * Method used to perform an operation with a Mach host port and deallocate the acquired right on exit
 *
 * The acquired right is deallocated even when the callback throws
 * The callback must not deallocate the borrowed right
 *
 * @param T The result type produced by the operation
 * @param usage The operation receiving the borrowed host port
 *
 * @return the operation result as [T]
 *
 * @since 1.1.0
 */
inline fun <T> useMachHost(
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