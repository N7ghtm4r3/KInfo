@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Resolver
import kotlinx.cinterop.*
import platform.Foundation.NSString
import platform.darwin.sysctlbyname
import platform.posix.size_tVar

/**
 * Method used to query a string system control value by its name
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when the query fails
 *
 * @return the queried value or the [default] fallback as [String]
 *
 * @since 1.1.0
 */
fun queryStringSysCtlByName(
    name: String,
    default: String? = null
): String? {
    return memScoped {
        val size = alloc<size_tVar>()

        val allocableSize = sysctlbyname(name, null, size.ptr, null, 0u)
        if (allocableSize != 0)
            return default

        val buffer = allocArray<ByteVar>(size.value.toInt())
        val result = sysctlbyname(name, buffer, size.ptr, null, 0u)
        if (result != 0)
            return default

        buffer.toKString()
    }
}

/**
 * Method used to convert a Core Foundation string pointer into [NSString]
 *
 * @receiver The Core Foundation string pointer to convert
 *
 * @return the converted string as [NSString]
 *
 * @since 1.1.0
 */
fun CPointer<cnames.structs.__CFString>?.toNSString(): NSString {
    return interpretObjCPointer(
        objcPtr = this.rawValue
    )
}

@Resolver
fun resolveCumulativeTime(
    seconds: Long,
    microseconds: Int
): Long {
    return ((seconds * 1000L) + (microseconds / 1000L))
}

@Resolver
fun resolveCumulativeTime(
    seconds: ULong,
    microseconds: ULong
): Long {
    return ((seconds * 1000uL) + (microseconds / 1000uL)).toLong()
}
