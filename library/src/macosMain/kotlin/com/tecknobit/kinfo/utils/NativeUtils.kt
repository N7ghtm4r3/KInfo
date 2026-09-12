@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Resolver
import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Foundation.NSString
import platform.darwin.sysctlbyname
import platform.posix.size_tVar
import kotlin.experimental.ExperimentalNativeApi

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
    return queryItemSysCtlByName<String, ByteVar>(
        name = name,
        default = default,
        returns = { _, buffer ->
            buffer.toKString()
        }
    )
}

/**
 * Method used to query a signed integer system control value by its name
 *
 * The queried value must use the native `IntVar` representation
 * The returned buffer size is not validated before reading its first integer
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when either native query fails
 *
 * @return the queried value or the [default] fallback as [Int]
 *
 * @see queryItemSysCtlByName
 *
 * @since 1.1.0
 */
fun queryIntSysCtlByName(
    name: String,
    default: Int? = null
): Int? {
    return queryItemSysCtlByName<Int, IntVar>(
        name = name,
        default = default,
        returns = { _, buffer ->
            buffer.pointed.value
        }
    )
}

/**
 * Method used to query a system control value as an array of signed integers
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when the query fails
 *
 * @return the queried values or the [default] fallback as [IntArray]
 *
 * @since 1.1.0
 */
fun queryIntArraySysCtlByName(
    name: String,
    default: IntArray? = null
): IntArray? {
    return queryItemArraySysCtlByName<IntArray, IntVar>(
        name = name,
        default = default,
        returns = { elementsCount, buffer ->
            IntArray(elementsCount) { index ->
                buffer[index]
            }
        }
    )
}

/**
 * Method used to query a system control value as an array of unsigned integers
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when the query fails
 *
 * @return the queried values or the [default] fallback as [UIntArray]
 *
 * @since 1.1.0
 */
fun queryUIntArraySysCtlByName(
    name: String,
    default: UIntArray? = null
): UIntArray? {
    return queryItemArraySysCtlByName<UIntArray, UIntVar>(
        name = name,
        default = default,
        returns = { elementsCount, buffer ->
            UIntArray(elementsCount) { index ->
                buffer[index]
            }
        }
    )
}

/**
 * Method used to query and transform a system control value composed of equally sized native elements
 *
 * Only complete [B] elements contained in the returned byte size are exposed to [returns]
 *
 * @param T The transformed value type
 * @param B The native element type stored in the queried buffer
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when the query fails
 * @param returns The operation used to transform the element count and native buffer
 *
 * @return the transformed queried value or the [default] fallback as [T]
 *
 * @since 1.1.0
 */
inline fun <T, reified B : CVariable> queryItemArraySysCtlByName(
    name: String,
    default: T?,
    returns: (Int, CArrayPointer<B>) -> T?
): T? {
    val computeArraySize: (size_tVar) -> Long = { size ->
        size.value.toInt() / sizeOf<B>()
    }

    return queryItemSysCtlByName(
        name = name,
        default = default,
        bufferBuilder = {
            val elementsCount = computeArraySize(it)

            allocArray<B>(elementsCount)
        },
        returns = { size, buffer ->
            val elementsCount = computeArraySize(size)

            returns(elementsCount.toInt(), buffer)
        }
    )
}

/**
 * Method used to query and transform a system control value stored in a native buffer
 *
 * @param T The transformed value type
 * @param B The native buffer element type
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when the query fails
 * @param bufferBuilder The operation used to allocate the buffer from the queried byte size
 * @param returns The operation used to transform the returned byte size and native buffer
 *
 * @return the transformed queried value or the [default] fallback as [T]
 *
 * @since 1.1.0
 */
inline fun <T, reified B : CVariable> queryItemSysCtlByName(
    name: String,
    default: T?,
    bufferBuilder: MemScope.(size_tVar) -> CArrayPointer<B> = {
        allocArray<B>(it.value.toInt())
    },
    returns: (size_tVar, CArrayPointer<B>) -> T?
): T? {
    return memScoped {
        val size = alloc<size_tVar>()

        val allocableSize = sysctlbyname(name, null, size.ptr, null, 0u)
        if (allocableSize != 0)
            return default

        val buffer = bufferBuilder(size)
        val result = sysctlbyname(name, buffer, size.ptr, null, 0u)
        if (result != 0)
            return default

        returns(size, buffer)
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