@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
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
 *
 * @see NativeMapper
 */
abstract class MacOsHardwareMapper<H> : NativeMapper<H>() {

    /**
     * The companion object contains the buffer capacity used for native registry property reads
     */
    protected companion object {

        /**
         * `PROPERTY_VALUE_CAPACITY` the maximum number of bytes supported by the IOKit in-band buffer
         */
        const val PROPERTY_VALUE_CAPACITY = 4096

        const val IO_PLATFORM_EXPERT_DEVICE_SERVICE = "IOPlatformExpertDevice"

        const val IO_PLATFORM_DEVICE_SERVICE = "IOPlatformDevice"

    }

    /**
     * Method used to load an IOKit service, perform an operation, and release the handle on exit
     *
     * The operation receives a borrowed handle and must not release it or use it after this method returns
     * The handle is also released when the operation throws or performs a non-local return
     *
     * @param T The type of result produced by the operation
     * @param serviceName The IOKit service class name to match
     * @param usage The operation to perform with the loaded service handle
     *
     * @return the result produced by the operation as [T]
     * @throws IllegalStateException If no matching service handle is returned
     */
    protected inline fun <T> useIOService(
        serviceName: String,
        usage: (io_service_t) -> T
    ): T {
        val service = loadIOService(
            serviceName = serviceName
        )

        return try {
            usage(service)
        } finally {
            service.release()
        }
    }

    /**
     * Method used to retrieve the first IOKit service matching the specified class name
     *
     * The caller is responsible for releasing the returned handle with `IOObjectRelease`
     *
     * @param serviceName The IOKit service class name to match
     *
     * @return the matching service handle as [io_service_t]
     *
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

    protected inline fun useIOServices(
        serviceName: String,
        crossinline usage: (Int, io_service_t) -> Unit
    ) {
        val services = loadIOServices(
            serviceName = serviceName
        )

        var service = IOIteratorNext(
            iterator = services
        )

        try {
            var index = 0
            while (service != 0u) {
                try {
                    usage(index, services)
                } finally {
                    service.release()
                }

                service = IOIteratorNext(
                    iterator = service
                )
                index++
            }
        } finally {
            services.release()
        }
    }

    @Loader
    protected fun loadIOServices(
        serviceName: String
    ): io_iterator_t {
        return memScoped {
            val buffer = alloc<io_iterator_tVar>()

            val result = IOServiceGetMatchingServices(
                kIOMainPortDefault,
                IOServiceMatching(serviceName),
                buffer.ptr
            )
            if (result != 0)
                throw IllegalStateException("Could not load $serviceName instances")

            buffer.value
        }
    }

    /**
     * Method used to load a registry entry from its plane-qualified path
     *
     * The caller is responsible for releasing a nonzero handle after use
     *
     * @param path The registry path including its plane, such as `IODeviceTree:/chosen`
     *
     * @return the registry entry handle, or zero when no entry is found, as [io_registry_entry_t]
     */
    protected fun loadRegistryFromPath(
        path: String
    ): io_registry_entry_t {
        return memScoped {
            IORegistryEntryFromPath(
                kIOMainPortDefault,
                path.cstr.ptr
            )
        }
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
    protected fun io_registry_entry_t.readStringFromRegistryWithFallback(
        key: String,
        fallbackKey: String
    ): String {
        val firstAttemptValue = readStringFromRegistry(
            key = key
        )
        if (firstAttemptValue.isNotBlank())
            return firstAttemptValue

        return readStringFromRegistry(
            key = fallbackKey
        )
    }

    /**
     * Method used to read a textual registry property with [UNKNOWN] as the lookup failure value
     *
     * Successful reads preserve empty or blank values and do not release the entry handle
     *
     * @receiver The registry entry containing the property to read
     * @param key The property name to query
     *
     * @return the decoded value, or [UNKNOWN] when the lookup fails or exceeds capacity, as [String]
     */
    protected fun io_registry_entry_t.readStringFromRegistryOrUnknown(
        key: String
    ): String {
        return readStringFromRegistry(
            key = key,
            default = UNKNOWN
        )
    }

    protected fun io_registry_entry_t.readStringFromRegistry(
        key: String,
        default: String = ""
    ): String {
        return readFromRegistry(
            key = key,
            default = default.encodeToByteArray()
        )
            .decodeToString()
            .trimEnd('\u0000')
    }

    protected fun io_registry_entry_t.readFromRegistry(
        key: String,
        default: ByteArray = byteArrayOf()
    ): ByteArray {
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
        }
    }

    /**
     * Method used to release an IOKit object handle
     *
     * A zero handle is ignored and the native release result is not propagated
     *
     * @receiver The owned handle to release after its last use
     */
    protected fun io_service_t.release() {
        if(this == 0u)
            return

        IOObjectRelease(this)
    }

}
