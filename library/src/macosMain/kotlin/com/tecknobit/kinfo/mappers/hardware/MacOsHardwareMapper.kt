@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.mappers.NativeMapper
import kotlinx.cinterop.*
import platform.IOKit.*

/**
 * The `MacOsHardwareMapper` class is useful to load IOKit services and read textual registry properties
 * for macOS hardware models
 *
 * @param H The type of hardware model produced by the mapper
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
abstract class MacOsHardwareMapper<H> : NativeMapper<H>() {

    protected companion object {

        /**
         * `PROPERTY_VALUE_CAPACITY` the maximum number of bytes supported by the IOKit in-band buffer
         */
        const val PROPERTY_VALUE_CAPACITY = 4096

    }

    /**
     * Method used to retrieve the first IOKit service matching the specified class name
     *
     * The caller is responsible for releasing the returned handle with `IOObjectRelease`
     *
     * @param serviceName The IOKit service class name to match
     *
     * @return the matching service handle as [io_service_t]
     * @throws IllegalStateException If no matching service handle is returned
     */
    @Loader
    protected fun loadIOService(
        serviceName: String
    ): io_service_t {
        val service = IOServiceGetMatchingService(
            kIOMainPortDefault,
            IOServiceMatching(serviceName)
        )
        if (service == 0u)
            throw IllegalStateException("Could not load $serviceName")

        return service
    }

    /**
     * Method used to read a textual registry property and try an alternative key when its value is blank
     *
     * The fallback key is read only when the first lookup fails or produces a blank value
     *
     * @receiver The registry entry containing the properties to read
     * @param key The primary property name
     * @param fallbackKey The alternative property name
     *
     * @return the primary non-blank value, the fallback value, or an empty string if both reads fail as [String]
     */
    protected fun io_registry_entry_t.readFromRegistryWithFallback(
        key: String,
        fallbackKey: String
    ): String {
        val firstAttemptValue = readFromRegistry(
            key = key
        )
        if (firstAttemptValue.isNotBlank())
            return firstAttemptValue

        return readFromRegistry(
            key = fallbackKey
        )
    }

    /**
     * Method used to read a registry property as UTF-8 text and remove trailing null characters
     *
     * Only the returned byte count is decoded and the temporary buffer is released after the lookup
     * The property must contain text rather than numeric or arbitrary binary data
     * This method uses the legacy `IORegistryEntryGetProperty` API and does not release the entry handle
     *
     * @receiver The registry entry containing the property to read
     * @param key The property name to query
     * @param default The value returned when the lookup fails or the returned size exceeds the buffer capacity
     *
     * @return the decoded text, including an empty value on a successful empty read, or the default as [String]
     */
    protected fun io_registry_entry_t.readFromRegistry(
        key: String,
        default: String = ""
    ): String {
        return memScoped {
            val buffer = allocArray<ByteVar>(PROPERTY_VALUE_CAPACITY)
            val uCapacity = PROPERTY_VALUE_CAPACITY.toUInt()
            val size = alloc<UIntVar> {
                value = uCapacity
            }

            val result = IORegistryEntryGetProperty(
                this@readFromRegistry,
                key.cstr.ptr,
                buffer,
                size.ptr
            )

            val valueSize = size.value
            if (result != 0 || valueSize > uCapacity)
                return@memScoped default

            buffer.readBytes(valueSize.toInt())
                .decodeToString()
                .trimEnd('\u0000')
        }
    }

}