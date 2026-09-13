@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.utils

import kotlinx.cinterop.*
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
 * Method used to query a signed 64-bit system control value by its name
 *
 * The queried value must use the native `LongVar` representation
 * The returned buffer size is not validated before reading its first value
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when either native query fails
 *
 * @return the queried value or the nullable [default] fallback as [Long]
 *
 * @since 1.1.0
 */
fun queryLongSysCtlByName(
    name: String,
    default: Long? = null
): Long? {
    return queryItemSysCtlByName<Long, LongVar>(
        name = name,
        default = default,
        returns = { _, buffer ->
            buffer.pointed.value
        }
    )
}

/**
 * Method used to query an unsigned 64-bit system control value by its name
 *
 * The queried value must use the native `ULongVar` representation
 * The returned buffer size is not validated before reading its first value
 *
 * @param name The name of the system control value to query
 * @param default The nullable fallback value returned when either native query fails
 *
 * @return the queried value or the nullable [default] fallback as [ULong]
 *
 * @since 1.1.0
 */
fun queryULongSysCtlByName(
    name: String,
    default: ULong? = null
): ULong? {
    return queryItemSysCtlByName<ULong, ULongVar>(
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