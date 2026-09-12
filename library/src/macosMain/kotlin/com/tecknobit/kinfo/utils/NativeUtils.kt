@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Resolver
import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Foundation.NSString
import kotlin.experimental.ExperimentalNativeApi

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

/**
 * Method used to convert signed seconds and microseconds to cumulative milliseconds
 *
 * @param seconds The signed seconds component to convert
 * @param microseconds The signed microseconds component to convert
 *
 * @return the cumulative time in milliseconds as [Long]
 *
 * @since 1.1.0
 */
@Resolver
fun resolveCumulativeTime(
    seconds: Long,
    microseconds: Int
): Long {
    return ((seconds * 1000L) + (microseconds / 1000L))
}

/**
 * Method used to convert unsigned seconds and microseconds to cumulative milliseconds
 *
 * @param seconds The unsigned seconds component to convert
 * @param microseconds The unsigned microseconds component to convert
 *
 * @return the cumulative time in milliseconds as [Long]
 *
 * @since 1.1.0
 */
@Resolver
fun resolveCumulativeTime(
    seconds: ULong,
    microseconds: ULong
): Long {
    return ((seconds * 1000uL) + (microseconds / 1000uL)).toLong()
}

/**
 * Method used to check whether the process runs natively on ARM64 or through Rosetta on Apple Silicon
 *
 * Non-ARM64 processes query `sysctl.proc_translated` and require a value of one
 * An unavailable property or failed query is treated as a non-translated process
 *
 * @return whether native ARM64 execution or Rosetta translation is detected as [Boolean]
 *
 * @see queryIntSysCtlByName
 *
 * @since 1.1.0
 */
fun isAppleSilicon(): Boolean {
    if (Platform.cpuArchitecture == CpuArchitecture.ARM64)
        return true

    val translated = queryIntSysCtlByName(
        name = "sysctl.proc_translated",
        default = 0
    )

    return translated == 1
}