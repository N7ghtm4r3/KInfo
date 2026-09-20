@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.mappers.NativeMapper
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper.Companion.PROPERTY_VALUE_CAPACITY
import kotlinx.cinterop.*
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFStringCreateWithCString
import platform.CoreFoundation.kCFStringEncodingUTF8
import platform.Foundation.CFBridgingRelease
import platform.Foundation.NSNumber
import platform.IOKit.*

/**
 * The `MacOsHardwareMapper` class is useful to load IOKit services and read registry properties and dictionaries
 * for macOS hardware models
 *
 * @param H The type of hardware model produced by the mapper
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
abstract class MacOsHardwareMapper<H> : NativeMapper<H>() {

    protected companion object {

        /**
         * `PROPERTY_VALUE_CAPACITY` the maximum number of bytes supported by the IOKit in-band buffer
         */
        const val PROPERTY_VALUE_CAPACITY = 4096

        /**
         * `REGISTRY_SEPARATOR_CHARACTER` the null character separating registry string entries
         */
        const val REGISTRY_SEPARATOR_CHARACTER = '\u0000'

        /**
         * `IO_PLATFORM_EXPERT_DEVICE_SERVICE` the IOKit class name used to match the platform expert device
         */
        const val IO_PLATFORM_EXPERT_DEVICE_SERVICE = "IOPlatformExpertDevice"

        /**
         * `IO_PLATFORM_DEVICE_SERVICE` the IOKit class name used to enumerate platform devices
         */
        const val IO_PLATFORM_DEVICE_SERVICE = "IOPlatformDevice"

        /**
         * `IO_DEVICE_TREE_CHOSEN` the plane-qualified path of the chosen device tree entry
         */
        const val IO_DEVICE_TREE_CHOSEN = "IODeviceTree:/chosen"

        /**
         * `IO_MEDIA_SERVICE` the IOKit class name used to enumerate whole media, partitions, and logical media
         */
        const val IO_MEDIA_SERVICE = "IOMedia"

    }

    /**
     * Method used to load the first matching IOKit service, perform an operation, and release the handle on exit
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
        if (service == IO_OBJECT_NULL)
            throw IllegalStateException("Could not load $serviceName")

        return service
    }

    /**
     * Method used to perform an operation on each accepted IOKit service and release the owned handles
     *
     * The zero-based index counts only services accepted by the predicate and follows iterator order
     * Both callbacks receive borrowed handles and must not release them or retain them after the callback
     * Each visited service and the iterator are released even when a callback throws
     *
     * @param serviceName The IOKit service class name to match
     * @param consumeServiceIf The optional predicate selecting services to consume, or null to accept every service
     * @param usage The operation receiving the accepted-service index and current service handle
     * @throws IllegalStateException If the native matching query fails
     */
    protected inline fun useIOServices(
        serviceName: String,
        noinline consumeServiceIf: ((io_service_t) -> Boolean)? = null,
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
            while (service != IO_OBJECT_NULL) {
                try {
                    if (consumeServiceIf == null || consumeServiceIf(service)) {
                        usage(index, service)
                        index++
                    }
                } finally {
                    service.release()
                }

                service = IOIteratorNext(
                    iterator = services
                )
            }
        } finally {
            services.release()
        }
    }

    /**
     * Method used to retrieve an iterator over IOKit services matching the specified class name
     *
     * The caller must release the iterator and each service obtained from it with `IOObjectRelease`
     * A successful query may return an iterator containing no services
     *
     * @param serviceName The IOKit service class name to match
     *
     * @return the matching service iterator as [io_iterator_t]
     * @throws IllegalStateException If the native matching query returns a nonzero result
     */
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
     * Method used to perform an operation on a registry entry and release its handle on exit
     *
     * The callback must not release or retain the handle
     * Nonzero handles are released even when the operation throws
     *
     * @param T The result type produced by the operation
     * @param path The plane-qualified registry path
     * @param usage The operation receiving the borrowed handle, possibly zero when the entry is absent
     *
     * @return the operation result as [T]
     */
    protected inline fun <T> useRegistryFromPath(
        path: String,
        usage: (io_registry_entry_t) -> T
    ): T {
        val registry = loadRegistryFromPath(
            path = path
        )

        return try {
            usage(registry)
        } finally {
            registry.release()
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
     * @return the primary non-blank value, the fallback value, or [UNKNOWN] when the fallback read fails as [String]
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
            key = fallbackKey,
            default = UNKNOWN
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

    /**
     * Method used to split a registry text property into null-separated entries
     *
     * Trailing null characters are removed before splitting
     * Interior empty entries, whitespace, and any property-specific prefix are preserved
     *
     * @receiver The registry entry containing the property
     * @param key The property name to query
     * @param default The fallback list used when the decoded property is blank or unavailable
     *
     * @return the decoded entries or the fallback as [List] of [String]
     */
    protected fun io_registry_entry_t.readStringsFromRegistry(
        key: String,
        default: List<String> = emptyList()
    ): List<String> {
        val valueFromRegistry = readStringFromRegistry(
            key = key
        )
        if (valueFromRegistry.isBlank())
            return default

        return valueFromRegistry.split(REGISTRY_SEPARATOR_CHARACTER)
    }

    /**
     * Method used to read a numeric registry property as a signed 32-bit integer
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable or incompatible
     *
     * @return the converted property value or [default] as [Int]
     */
    protected fun io_registry_entry_t.readIntFromRegistry(
        key: String,
        default: Int = 0
    ): Int {
        return readPrimitiveFromRegistry(
            key = key,
            default = default,
            returns = { it.intValue }
        )
    }

    /**
     * Method used to read a numeric registry property as an unsigned 32-bit integer
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable or incompatible
     *
     * @return the converted property value or [default] as [UInt]
     */
    protected fun io_registry_entry_t.readUIntFromRegistry(
        key: String,
        default: UInt = 0u
    ): UInt {
        return readPrimitiveFromRegistry(
            key = key,
            default = default,
            returns = { it.unsignedIntValue }
        )
    }

    /**
     * Method used to read a numeric or Boolean registry property as a Boolean value
     *
     * Numeric zero represents false and nonzero values represent true
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable or incompatible
     *
     * @return the converted property value or [default] as [Boolean]
     */
    protected fun io_registry_entry_t.readBooleanFromRegistry(
        key: String,
        default: Boolean = false
    ): Boolean {
        return readPrimitiveFromRegistry(
            key = key,
            default = default,
            returns = { it.boolValue }
        )
    }

    /**
     * Method used to read a numeric registry property as a double-precision value
     *
     * Values retain their native measurement units
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable or incompatible
     *
     * @return the converted property value or [default] as [Double]
     */
    protected fun io_registry_entry_t.readDoubleFromRegistry(
        key: String,
        default: Double = 0.0
    ): Double {
        return readPrimitiveFromRegistry(
            key = key,
            default = default,
            returns = { it.doubleValue }
        )
    }

    /**
     * Method used to read a numeric registry property as a signed 64-bit integer on macOS
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable or incompatible
     *
     * @return the converted property value or [default] as [Long]
     */
    protected fun io_registry_entry_t.readLongFromRegistry(
        key: String,
        default: Long = 0
    ): Long {
        return readPrimitiveFromRegistry(
            key = key,
            default = default,
            returns = { it.longValue }
        )
    }

    /**
     * Method used to read a numeric registry property with a custom conversion
     *
     * @receiver The borrowed registry entry containing the property
     * @param T The type produced by the numeric conversion
     * @param key The property name to query
     * @param default The fallback value when the property is unavailable, incompatible, or the conversion returns null
     * @param returns The conversion applied to the numeric property
     *
     * @return the converted property value or [default] as [T]
     */
    private inline fun <T> io_registry_entry_t.readPrimitiveFromRegistry(
        key: String,
        default: T,
        returns: (NSNumber) -> T
    ): T {
        if (this == IO_OBJECT_NULL)
            return default

        val cfKey = CFStringCreateWithCString(
            null,
            key,
            kCFStringEncodingUTF8
        ) ?: return default

        val number = try {
            CFBridgingRelease(
                IORegistryEntryCreateCFProperty(this, cfKey, null, 0u)
            ) as? NSNumber
        } finally {
            CFRelease(cfKey)
        }

        return number?.let(returns) ?: default
    }

    /**
     * Method used to read a registry byte property as UTF-8 text and remove trailing null characters
     *
     * Embedded null characters and letter case are preserved and the entry handle is not released
     *
     * @receiver The registry entry containing the property
     * @param key The property name to query
     * @param default The fallback text returned when the native read fails or exceeds capacity
     *
     * @return the decoded text or fallback as [String]
     */
    protected fun io_registry_entry_t.readStringFromRegistry(
        key: String,
        default: String = ""
    ): String {
        val valueFromRegistry = readFromRegistry(
            key = key,
            default = default.encodeToByteArray()
        )

        return valueFromRegistry
            .decodeToString()
            .trimEnd(REGISTRY_SEPARATOR_CHARACTER)
    }

    /**
     * Method used to copy a registry byte property into managed memory
     *
     * The read uses [PROPERTY_VALUE_CAPACITY] bytes and leaves the entry handle owned by the caller
     *
     * @receiver The registry entry containing the property
     * @param key The property name to query
     * @param default The fallback bytes returned when the native read fails or exceeds capacity
     *
     * @return the copied property bytes or fallback as [ByteArray]
     */
    protected fun io_registry_entry_t.readFromRegistry(
        key: String,
        default: ByteArray = byteArrayOf()
    ): ByteArray {
        if (this == IO_OBJECT_NULL)
            return default

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
     * Method used to search a registry entry and its ancestors for a dictionary property in the I/O service plane
     *
     * The property is bridged to managed memory and the receiver remains owned by the caller
     * The cast does not validate the type of every dictionary key or value
     *
     * @receiver The borrowed registry entry at which the ancestor search starts
     * @param key The dictionary property name to search
     *
     * @return the bridged dictionary, or null when unavailable or not a map, as [Map]
     */
    @Suppress("UNCHECKED_CAST")
    protected fun io_registry_entry_t.findDictionaryInRegistry(
        key: String
    ): Map<String, *>? {
        return memScoped {
            val cfKey = CFStringCreateWithCString(
                null,
                key,
                kCFStringEncodingUTF8
            ) ?: return@memScoped null

            try {
                val property = IORegistryEntrySearchCFProperty(
                    this@findDictionaryInRegistry,
                    kIOServicePlane.cstr.ptr,
                    cfKey,
                    null,
                    kIORegistryIterateParents or kIORegistryIterateRecursively
                )

                CFBridgingRelease(property) as? Map<String, *>
            } finally {
                CFRelease(cfKey)
            }
        }
    }

    /**
     * Method used to read a dictionary string with [UNKNOWN] as the entry fallback
     *
     * Present values must be compatible with [String]
     *
     * @receiver The optional dictionary containing the requested string
     * @param key The entry name to read
     *
     * @return the stored string or [UNKNOWN] when the dictionary or entry is null or absent as [String]
     */
    protected fun Map<String, *>?.readStringFromDictionaryOrUnknown(
        key: String
    ): String {
        return readFromDictionary(
            key = key,
            default = UNKNOWN
        )
    }

    /**
     * Method used to read a dictionary string with a configurable entry fallback
     *
     * Present values must be compatible with [String]
     *
     * @receiver The optional dictionary containing the requested string
     * @param key The entry name to read
     * @param default The fallback string when the dictionary, entry, or value is null or absent
     *
     * @return the stored string or [default] as [String]
     */
    protected fun Map<String, *>?.readStringFromDictionary(
        key: String,
        default: String = ""
    ): String {
        return readFromDictionary(
            key = key,
            default = default
        )
    }

    /**
     * Method used to convert a dictionary number to a signed 32-bit integer
     *
     * Present values must be [NSNumber] instances
     *
     * @receiver The optional dictionary containing the requested number
     * @param key The entry name to read
     * @param default The fallback number when the dictionary, entry, or value is null or absent
     *
     * @return the converted number or [default] as [Int]
     */
    protected fun Map<String, *>?.readIntFromDictionary(
        key: String,
        default: Int = 0,
    ): Int {
        return readNSNumberFromDictionary(
            key = key,
            default = NSNumber(
                int = default
            )
        ).intValue
    }

    /**
     * Method used to convert a dictionary number to an unsigned 32-bit integer
     *
     * Present values must be [NSNumber] instances
     *
     * @receiver The optional dictionary containing the requested number
     * @param key The entry name to read
     * @param default The fallback number when the dictionary, entry, or value is null or absent
     *
     * @return the converted number or [default] as [UInt]
     */
    protected fun Map<String, *>?.readUIntFromDictionary(
        key: String,
        default: UInt = 0u,
    ): UInt {
        return readNSNumberFromDictionary(
            key = key,
            default = NSNumber(
                unsignedInt = default
            )
        ).unsignedIntValue
    }

    /**
     * Method used to convert a dictionary number to a signed 64-bit integer on macOS
     *
     * Present values must be [NSNumber] instances and retain their native measurement units
     *
     * @receiver The optional dictionary containing the requested number
     * @param key The entry name to read
     * @param default The fallback number when the dictionary, entry, or value is null or absent
     *
     * @return the converted number or [default] as [Long]
     */
    protected fun Map<String, *>?.readLongFromDictionary(
        key: String,
        default: Long = 0,
    ): Long {
        return readNSNumberFromDictionary(
            key = key,
            default = NSNumber(
                long = default
            )
        ).longValue
    }

    /**
     * Method used to convert a dictionary number to a double-precision value
     *
     * Present values must be [NSNumber] instances and retain their native measurement units
     *
     * @receiver The optional dictionary containing the requested number
     * @param key The entry name to read
     * @param default The fallback number when the dictionary, entry, or value is null or absent
     *
     * @return the converted number or [default] as [Double]
     * @throws ClassCastException If a present value is incompatible with [NSNumber]
     */
    protected fun Map<String, *>?.readDoubleFromDictionary(
        key: String,
        default: Double = 0.0,
    ): Double {
        return readNSNumberFromDictionary(
            key = key,
            default = NSNumber(
                double = default
            )
        ).doubleValue
    }

    /**
     * Method used to retrieve a dictionary number with a configurable entry fallback
     *
     * Present values must be compatible with [NSNumber]
     *
     * @receiver The optional dictionary containing the requested number
     * @param key The entry name to read
     * @param default The fallback object when the dictionary, entry, or value is null or absent
     *
     * @return the stored numeric object or [default] as [NSNumber]
     */
    private fun Map<String, *>?.readNSNumberFromDictionary(
        key: String,
        default: NSNumber = NSNumber(0),
    ): NSNumber {
        return readFromDictionary(
            key = key,
            default = default
        )
    }

    /**
     * Method used to retrieve a dictionary value with a configurable fallback
     *
     * Present values must be compatible with the requested type
     * The generic cast does not validate their runtime type
     *
     * @receiver The optional dictionary containing the requested value
     * @param T The expected value type
     * @param key The entry name to read
     * @param default The fallback when the dictionary, entry, or value is null or absent
     *
     * @return the stored value or [default] as [T]
     */
    @Suppress("UNCHECKED_CAST")
    protected fun <T> Map<String, *>?.readFromDictionary(
        key: String,
        default: T
    ): T {
        val value = this?.getOrElse(
            key = key,
            defaultValue = { default }
        )

        return (value as T) ?: default
    }

    /**
     * Method used to resolve the decimal registry entry identifier of an IOKit service
     *
     * The identifier belongs to the supplied entry and does not identify its parent or child entries
     * The service handle remains owned by the caller
     *
     * @param service The borrowed service whose registry entry identifier is requested
     *
     * @return the decimal identifier, or [UNKNOWN] when the native query fails, as [String]
     */
    @Resolver
    protected fun resolveServiceRegistryId(
        service: io_service_t
    ): String {
        return memScoped {
            val id = alloc<ULongVar>()

            val registryEntryId = IORegistryEntryGetRegistryEntryID(
                service,
                id.ptr
            )

            if (registryEntryId == 0)
                id.value.toString()
            else
                UNKNOWN
        }
    }

    /**
     * Method used to invoke an operation with the first parent entry in the specified registry plane
     *
     * The supplied service remains owned by the caller and the operation must not release the parent handle
     * The parent handle is passed to [usage] and released in `finally`, including when the operation throws
     * The operation must not use the parent handle after this method returns
     *
     * @param T The type of result produced by the operation
     * @param service The borrowed service whose first parent is requested
     * @param plane The registry plane name, such as `IOService` or `IODeviceTree`
     * @param default The fallback result when the parent lookup fails or returns no entry
     * @param usage The operation receiving the borrowed parent handle
     *
     * @return the operation result, or [default] when no parent entry is obtained, as [T]
     */
    protected inline fun <T> userRegistryParentEntry(
        service: io_service_t,
        plane: String,
        default: T? = null,
        usage: (io_registry_entry_t) -> T
    ): T? {
        val parentEntry = resolveRegistryParentEntry(
            service = service,
            plane = plane
        ) ?: return default

        return try {
            usage(parentEntry)
        } finally {
            parentEntry.release()
        }
    }

    /**
     * Method used to request the first parent entry of a service in the specified registry plane
     *
     * A successfully acquired parent handle must be released with `IOObjectRelease`
     * The handle is copied out of the temporary native storage and remains owned by the caller
     *
     * @param service The borrowed service whose first parent is requested
     * @param plane The registry plane name in which to look up the parent
     *
     * @return the parent handle, or null when the lookup fails or returns no entry, as [io_registry_entry_t]
     */
    @Resolver
    protected fun resolveRegistryParentEntry(
        service: io_service_t,
        plane: String,
    ): io_registry_entry_t? {
        return memScoped {
            val parent = alloc<io_registry_entry_tVar>()

            val result = IORegistryEntryGetParentEntry(
                service,
                plane.cstr.ptr,
                parent.ptr
            )
            if (result != kIOReturnSuccess || parent.value == IO_OBJECT_NULL)
                return@memScoped null

            parent.value
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
        if (this == IO_OBJECT_NULL)
            return

        IOObjectRelease(this)
    }

}
