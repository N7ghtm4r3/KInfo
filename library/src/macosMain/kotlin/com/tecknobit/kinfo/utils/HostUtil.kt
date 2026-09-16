package com.tecknobit.kinfo.utils

import platform.darwin.mach_host_self
import platform.darwin.mach_port_deallocate
import platform.darwin.mach_task_self_
import platform.posix.mach_port_t

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