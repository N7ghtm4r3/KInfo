@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsDisplayInfoImpl
import kotlinx.cinterop.*
import platform.AppKit.NSScreen
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFStringCreateWithCString
import platform.CoreFoundation.kCFStringEncodingUTF8
import platform.CoreGraphics.*
import platform.Foundation.CFBridgingRelease
import platform.Foundation.NSData
import platform.Foundation.NSNumber
import platform.IOKit.*
import kotlin.math.roundToInt

/**
 * The `MacOsDisplaysInfoMapper` class is useful to decode native display information on Intel and Apple Silicon
 *
 * Native `EDID` base blocks must pass the header, version, revision, and checksum checks
 * Apple Silicon built-in display information is assembled from registry attributes and Core Graphics values
 * and carries a synthesized identification block
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsSplitHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsDisplaysInfoMapper : MacOsSplitHardwareMapper<List<MacOsDisplayInfoImpl>>() {

    /**
     * Method used to decode external display data and synthesize information for an Apple Silicon built-in display
     *
     * External entries require a supported `EDID` block from `IOPortTransportStateDisplayPort`
     * At most one built-in entry is assembled from `IOMobileFramebuffer` attributes with a valid manufacturer identifier
     * Core Graphics values supplement its attributes when exactly one online built-in display is found
     *
     * @return the available display information as [List] of [MacOsDisplayInfoImpl]
     * @throws IllegalStateException If an IOKit display service enumeration fails
     */
    override fun mapForSilicon(): List<MacOsDisplayInfoImpl> {
        val displays = mutableListOf<MacOsDisplayInfoImpl>()

        useIOServices("IOPortTransportStateDisplayPort") { _, service ->
            val edidData = service.loadDisplayProperty(
                key = "EDID"
            ) as? NSData ?: return@useIOServices
            val edid = mapOf(kIODisplayEDIDKey to edidData).resolveEdid()
                ?.takeUnless { it.isNotSupported() }
                ?: return@useIOServices

            displays.add(edid.mapDisplayInfo())
        }

        val builtInDisplayId = resolveDisplayIds().singleOrNull { displayId ->
            CGDisplayIsBuiltin(displayId).toLong() != 0L
        }
        var builtInAdded = false

        useIOServices("IOMobileFramebuffer") { _, service ->
            if (builtInAdded || service.readBooleanFromRegistry(key = "external"))
                return@useIOServices

            val displayAttributes = service.loadBuiltInDisplayAttributes()
                ?: return@useIOServices

            val productAttributes = displayAttributes.readFromDictionary<Map<String, *>?>(
                key = "ProductAttributes",
                default = null
            ) ?: return@useIOServices

            val vendorNumber = productAttributes.readUIntFromDictionary(
                key = "LegacyManufacturerID"
            )
            val manufacturerID = vendorNumber.asManufacturerId()
            if (manufacturerID.isBlank())
                return@useIOServices

            val productNumber = builtInDisplayId?.let {
                CGDisplayModelNumber(it)
            }?.and(0xFFFFu) ?: 0u
            val serialNumber = builtInDisplayId?.let {
                CGDisplaySerialNumber(it)
            } ?: 0u
            val screenSize = builtInDisplayId?.let {
                loadScreenSize(
                    displayId = it
                )
            }
            val widthPixels = service.readIntFromRegistry(
                key = "DisplayWidth"
            )
            val heightPixels = service.readIntFromRegistry(
                key = "DisplayHeight"
            )
            val model = productAttributes.readStringFromDictionary(
                key = "ProductName"
            ).ifBlank {
                builtInDisplayId?.let {
                    resolveLocalizedDisplayName(it)
                }.orEmpty()
            }.ifBlank { UNKNOWN }

            val displayInfo = MacOsDisplayInfoImpl(
                edid = byteArrayOf(),
                isEdidSynthetic = true,
                manufacturerID = manufacturerID,
                productID = productNumber.toString(16),
                serialNo = serialNumber.asSerialNo(),
                week = productAttributes.readIntFromDictionary(
                    key = "WeekOfManufacture"
                ).takeIf { it in 0..54 || it == 255 }?.toByte() ?: 0,
                year = productAttributes.readIntFromDictionary(
                    key = "YearOfManufacture",
                    default = 1990
                ).takeIf { it in 1990..2245 } ?: 1990,
                version = "1.4",
                isDigital = true,
                hcm = (screenSize?.hcm?.takeIf { it > 0 }
                    ?: displayAttributes.readIntFromDictionary(key = "MaxHorizontalImageSize")).coerceIn(0, 255),
                vcm = (screenSize?.vcm?.takeIf { it > 0 }
                    ?: displayAttributes.readIntFromDictionary(key = "MaxVerticalImageSize")).coerceIn(0, 255),
                preferredResolution = if (widthPixels > 0 && heightPixels > 0) "${widthPixels}x${heightPixels}" else "",
                model = model,
                productSerialNumber = productAttributes.readStringFromDictionary(
                    key = "AlphanumericSerialNumber"
                )
            )

            val display = displayInfo.copy(
                edid = displayInfo.synthesizeEdid(
                    vendorNumber = vendorNumber,
                    productNumber = productNumber,
                    serialNumber = serialNumber
                )
            )

            displays.add(display)

            builtInAdded = true
        }

        return displays
    }

    /**
     * Method used to decode Intel display information from `IODisplayConnect` service dictionaries
     *
     * Entries without a supported `EDID` base block are omitted
     *
     * @return the available display information as [List] of [MacOsDisplayInfoImpl]
     * @throws IllegalStateException If the IOKit display service enumeration fails
     */
    override fun mapForIntel(): List<MacOsDisplayInfoImpl> {
        val displays = mutableListOf<MacOsDisplayInfoImpl>()

        useIOServices("IODisplayConnect") { _, service ->
            val dictionary = service.findDisplayInfoDictionary() ?: return@useIOServices
            val edid = dictionary.resolveEdid()
                ?.takeUnless { it.isNotSupported() }
                ?: return@useIOServices

            displays.add(edid.mapDisplayInfo(dictionary))
        }

        return displays
    }

    /**
     * Method used to decode a native identification block with optional dictionary overrides
     *
     * @receiver The native `EDID` bytes for which [isNotSupported] returns false
     * @param dictionary The display attributes overriding decoded values when present
     *
     * @return the decoded display information retaining the native byte array as [MacOsDisplayInfoImpl]
     */
    @Resolver
    private fun ByteArray.mapDisplayInfo(
        dictionary: Map<String, *> = emptyMap<String, Any>()
    ): MacOsDisplayInfoImpl {
        val vendorNumber = dictionary.readUIntFromDictionary(
            key = kDisplayVendorID,
            default = ((u8(8) shl 8) or u8(9)).toUInt()
        )
        val productNumber = dictionary.readUIntFromDictionary(
            key = kDisplayProductID,
            default = (u8(10) or (u8(11) shl 8)).toUInt()
        )
        val serialNumber = dictionary.readUIntFromDictionary(
            key = kDisplaySerialNumber,
            default = (0..3).fold(0u) { serial, index ->
                serial or (u8(12 + index).toUInt() shl (index * 8))
            }
        )

        return MacOsDisplayInfoImpl(
            edid = this,
            isEdidSynthetic = false,
            manufacturerID = vendorNumber.asManufacturerId(),
            productID = productNumber.toString(16),
            serialNo = serialNumber.asSerialNo(),
            week = dictionary.readIntFromDictionary(
                key = kDisplayWeekOfManufacture,
                default = u8(16)
            ).toByte(),
            year = dictionary.readIntFromDictionary(
                key = kDisplayYearOfManufacture,
                default = u8(17) + 1990
            ),
            version = resolveVersion(),
            isDigital = dictionary.isDigital(
                edid = this
            ),
            hcm = dictionary.readIntFromDictionary(
                key = kDisplayHorizontalImageSize,
                default = u8(21) * 10
            ).toDouble().asCm(),
            vcm = dictionary.readIntFromDictionary(
                key = kDisplayVerticalImageSize,
                default = u8(22) * 10
            ).toDouble().asCm(),
            preferredResolution = resolvePreferredResolution(),
            model = dictionary.resolveName()
                .takeUnless { it == UNKNOWN } ?: resolveDescriptorText(0xFC).ifBlank { UNKNOWN },
            productSerialNumber = dictionary.readStringFromDictionary(
                key = kDisplaySerialString
            ).ifBlank { resolveDescriptorText(0xFF) }
        )
    }

    /**
     * Method used to read the first matching text descriptor from the four base-block descriptor slots
     *
     * Text ends at the first newline or null character and surrounding whitespace is removed
     *
     * @receiver The `EDID` bytes containing a complete base block
     * @param type The descriptor tag to match
     *
     * @return the descriptor text, or an empty string when no descriptor matches, as [String]
     */
    @Resolver
    private fun ByteArray.resolveDescriptorText(type: Int): String {
        for (offset in 54..108 step 18) {
            if (u8(offset) == 0 && u8(offset + 1) == 0 && u8(offset + 2) == 0 &&
                u8(offset + 3) == type && u8(offset + 4) == 0
            ) {
                return copyOfRange(offset + 5, offset + 18).decodeToString()
                    .substringBefore('\n').substringBefore('\u0000').trim()
            }
        }

        return ""
    }

    /**
     * Method used to load a native registry property and transfer its value to managed memory
     *
     * @receiver The borrowed registry entry containing the property
     * @param key The native property name to query
     *
     * @return the bridged property, or null when the key or property cannot be loaded, as [Any]
     */
    @Loader
    private fun io_registry_entry_t.loadDisplayProperty(key: String): Any? {
        val cfKey = CFStringCreateWithCString(null, key, kCFStringEncodingUTF8) ?: return null

        return try {
            val property = IORegistryEntryCreateCFProperty(this, cfKey, null, 0u) ?: return null
            CFBridgingRelease(property)
        } finally {
            CFRelease(cfKey)
        }
    }

    /**
     * Method used to load built-in display attributes from a framebuffer or its immediate device-tree parent
     *
     * The parent handle is released after the fallback lookup and the receiver remains owned by the caller
     *
     * @receiver The borrowed framebuffer service handle
     *
     * @return the display attributes, or null when neither entry provides a dictionary, as [Map]
     */
    @Loader
    @Suppress("UNCHECKED_CAST")
    private fun io_service_t.loadBuiltInDisplayAttributes(): Map<String, *>? {
        val attributes = loadDisplayProperty(
            key = "DisplayAttributes"
        ) as? Map<String, *>
        if (attributes != null)
            return attributes

        return userRegistryParentEntry(
            service = this,
            plane = "IODeviceTree",
            usage = { parent ->
                parent.loadDisplayProperty(
                    key = "DisplayAttributes"
                ) as? Map<String, *>
            }
        )
    }

    /**
     * Method used to find the localized AppKit screen name associated with a Core Graphics display identifier
     *
     * @param displayId The identifier to match against the screen device description
     *
     * @return the localized name, or an empty string when no screen matches, as [String]
     */
    @Resolver
    private fun resolveLocalizedDisplayName(displayId: CGDirectDisplayID): String {
        return NSScreen.screens.filterIsInstance<NSScreen>().firstOrNull { screen ->
            (screen.deviceDescription["NSScreenNumber"] as? NSNumber)?.unsignedIntValue == displayId
        }?.localizedName.orEmpty()
    }

    /**
     * Method used to synthesize a 128-byte digital `EDID` 1.4 block from built-in display information
     *
     * The block includes identification values, physical size, text descriptors, and a base-block checksum
     * Resolution dimensions fitting 12 bits are stored without a complete timing descriptor or preferred-timing flag
     * The native resolution therefore remains available through [MacOsDisplayInfoImpl.preferredResolution]
     *
     * @receiver The decoded attributes to encode in the synthesized block
     * @param vendorNumber The packed manufacturer identifier
     * @param productNumber The numeric product identifier encoded in the lowest 16 bits
     * @param serialNumber The numeric serial identifier encoded in little-endian order
     *
     * @return the synthesized identification block as [ByteArray]
     */
    private fun MacOsDisplayInfoImpl.synthesizeEdid(
        vendorNumber: UInt,
        productNumber: UInt,
        serialNumber: UInt
    ): ByteArray {
        val bytes = ByteArray(128)
        byteArrayOf(0, -1, -1, -1, -1, -1, -1, 0).copyInto(bytes)
        bytes[8] = (vendorNumber shr 8).toByte()
        bytes[9] = vendorNumber.toByte()
        bytes[10] = productNumber.toByte()
        bytes[11] = (productNumber shr 8).toByte()
        repeat(4) { index ->
            bytes[12 + index] = (serialNumber shr (index * 8)).toByte()
        }
        bytes[16] = week
        bytes[17] = (year - 1990).toByte()
        bytes[18] = 1
        bytes[19] = 4
        bytes[20] = 0x80.toByte()
        bytes[21] = hcm.toByte()
        bytes[22] = vcm.toByte()
        for (index in 38..53)
            bytes[index] = 1

        val resolution = preferredResolution.split('x').mapNotNull { it.toIntOrNull() }
        if (resolution.size == 2 && resolution.all { it in 1..4095 }) {
            bytes[56] = resolution[0].toByte()
            bytes[58] = ((resolution[0] shr 8) shl 4).toByte()
            bytes[59] = resolution[1].toByte()
            bytes[61] = ((resolution[1] shr 8) shl 4).toByte()
        }
        bytes.writeDescriptorText(
            offset = 72,
            type = 0xFC,
            value = model
        )

        if (productSerialNumber.isNotEmpty()) {
            bytes.writeDescriptorText(
                offset = 90,
                type = 0xFF,
                value = productSerialNumber
            )
        }
        bytes[127] = (-bytes.sumOf { it.toInt() and 0xFF }).toByte()

        return bytes
    }

    /**
     * Method used to write a text descriptor into a prepared identification buffer
     *
     * Text is limited to 13 characters, unsupported characters become `?`, and shorter values end with a newline
     * Remaining text bytes are padded with spaces
     *
     * @receiver The mutable buffer with space for the complete descriptor and its header already cleared
     * @param offset The starting byte index of the 18-byte descriptor
     * @param type The descriptor tag to encode
     * @param value The text to encode as printable ASCII
     */
    private fun ByteArray.writeDescriptorText(offset: Int, type: Int, value: String) {
        this[offset + 3] = type.toByte()
        fill(0x20.toByte(), offset + 5, offset + 18)
        val text = value.take(13).map { if (it in ' '..'~') it.code.toByte() else '?'.code.toByte() }
        text.forEachIndexed { index, byte -> this[offset + 5 + index] = byte }
        if (text.size < 13)
            this[offset + 5 + text.size] = 0x0A
    }

    /**
     * Method used to enumerate online display identifiers through Core Graphics
     *
     * @return the online identifiers, or an empty list when either query fails or no displays are reported,
     * as [List] of [CGDirectDisplayID]
     */
    @Resolver
    private fun resolveDisplayIds(): List<CGDirectDisplayID> {
        return memScoped {
            val count = alloc<UIntVar>()
            count.value = 0u

            val countResult = CGGetOnlineDisplayList(
                0u,
                null,
                count.ptr
            )
            if(countResult != kCGErrorSuccess)
                return@memScoped emptyList()

            val capacity = count.value
            if (capacity == 0u)
                return@memScoped emptyList()

            val buffer = allocArray<CGDirectDisplayIDVar>(
                capacity.toInt()
            )
            val listResult = CGGetOnlineDisplayList(
                capacity,
                buffer,
                count.ptr
            )
            if(listResult != kCGErrorSuccess)
                return@memScoped emptyList()

            List(count.value.toInt()) { index ->
                buffer[index]
            }
        }
    }

    /**
     * Method used to load the reported physical display dimensions and round them to centimeters
     *
     * @param displayId The Core Graphics identifier of the display to measure
     *
     * @return the converted physical dimensions as [ScreenSize]
     */
    @Loader
    private fun loadScreenSize(
        displayId: CGDirectDisplayID
    ): ScreenSize {
        val screenSize = CGDisplayScreenSize(displayId)

        return screenSize.useContents {
            ScreenSize(
                hcm = width.asCm(),
                vcm = height.asCm()
            )
        }
    }

    /**
     * Method used to create an IOKit display information dictionary with the preferred display name
     *
     * @receiver The borrowed display service handle used for the lookup
     *
     * @return the bridged display dictionary, or null when unavailable or incompatible, as [Map]
     */
    @Loader
    @Suppress("UNCHECKED_CAST")
    private fun io_service_t.findDisplayInfoDictionary(): Map<String, *>? {
        val dictionary = IODisplayCreateInfoDictionary(
            this,
            kIODisplayOnlyPreferredName
        ) ?: return null

        return CFBridgingRelease(dictionary) as? Map<String, *>
    }

    /**
     * Method used to copy native `EDID` data from a display information dictionary
     *
     * The data must contain at least 128 bytes and fit in a Kotlin array
     * Header, version, and checksum validation is performed separately
     *
     * @receiver The dictionary containing the native identification data
     *
     * @return the complete bytes, or null when missing, unreadable, or outside the supported size, as [ByteArray]
     */
    @Resolver
    private fun Map<String, *>.resolveEdid(): ByteArray? {
        val edidData = this.readFromDictionary<NSData?>(
            key = kIODisplayEDIDKey,
            default = null
        ) ?: return null
        if (edidData.length < 128uL || edidData.length > Int.MAX_VALUE.toULong())
            return null

        return edidData.bytes
            ?.reinterpret<ByteVar>()
            ?.readBytes(edidData.length.toInt())
    }

    /**
     * Method used to check whether an identification block fails the supported base-block requirements
     *
     * The checks require at least 128 bytes, the standard header, version 1.3 or 1.4, and a valid base-block checksum
     * Extension blocks are not validated
     *
     * @receiver The identification bytes to validate
     *
     * @return whether the base block is invalid or unsupported as [Boolean]
     */
    private fun ByteArray.isNotSupported(): Boolean {
        if (size < 128)
            return true

        val header = byteArrayOf(0, -1, -1, -1, -1, -1, -1, 0)
        val headerIsNotValid = !copyOfRange(0, 8).contentEquals(header)
        val majorVersionIsNotSupported = u8(18) != 1
        val revisionIsNotSupported = u8(19) !in 3..4
        val checksumIsNotValid = ((0 until 128).sumOf { u8(it) } and 0xFF) != 0

        return headerIsNotValid || majorVersionIsNotSupported || revisionIsNotSupported || checksumIsNotValid
    }

    /**
     * Method used to decode a packed manufacturer identifier into its three uppercase letters
     *
     * @receiver The unsigned identifier containing three 5-bit letter codes
     *
     * @return the manufacturer code, or an empty string when its bits or letter codes are invalid, as [String]
     */
    private fun UInt.asManufacturerId(): String {
        if (this > 0x7FFFu)
            return ""

        val letters = listOf(10, 5, 0).map { shift ->
            ((this shr shift) and 31u).toInt()
        }
        if (letters.any { it !in 1..26 })
            return ""

        return letters.joinToString("") { letter ->
            ('A'.code + letter - 1).toChar().toString()
        }
    }

    /**
     * Method used to format numeric serial bytes from the most significant byte to the least significant byte
     *
     * Bytes converted to letters or digits are retained as characters and other bytes become uppercase hexadecimal pairs
     *
     * @receiver The numeric serial identifier to format
     *
     * @return the formatted serial identifier as [String]
     */
    private fun UInt.asSerialNo(): String {
        return (24 downTo 0 step 8).joinToString("") { shift ->
            val byte = ((this shr shift) and 0xFFu).toByte()
            val character = byte.toInt().toChar()

            if (character.isLetterOrDigit())
                character.toString()
            else
                (byte.toInt() and 0xFF).toString(16).padStart(2, '0').uppercase()
        }
    }

    /**
     * Method used to format the major version and revision stored in an identification block
     *
     * @receiver The `EDID` bytes containing the version fields
     *
     * @return the version in `major.revision` format as [String]
     */
    @Resolver
    private fun ByteArray.resolveVersion(): String {
        return "${u8(18)}.${u8(19)}"
    }

    /**
     * Method used to resolve the digital input flag from native attributes or the identification block
     *
     * @receiver The display dictionary whose numeric or Boolean input flag takes precedence
     * @param edid The identification bytes supplying the input flag when the dictionary value is unavailable or incompatible
     *
     * @return whether the resolved input is digital as [Boolean]
     */
    @Resolver
    private fun Map<String, *>.isDigital(
        edid: ByteArray
    ): Boolean {
        val digitalValue = readFromDictionary<Any?>(
            key = kIODisplayIsDigitalKey,
            default = null
        )

        return when (digitalValue) {
            is NSNumber -> digitalValue.boolValue
            is Boolean -> digitalValue
            else -> (edid.u8(20) and 0x80) != 0
        }
    }

    /**
     * Method used to decode the preferred resolution from the first detailed timing descriptor
     *
     * The preferred-timing flag and a nonzero pixel clock are required
     * Interlaced timings report twice the stored vertical active size as the frame height
     *
     * @receiver The `EDID` bytes containing a complete base block
     *
     * @return the resolution in `widthxheight` format, or an empty string when the required timing is absent, as [String]
     */
    @Resolver
    private fun ByteArray.resolvePreferredResolution(): String{
        return if ((u8(24) and 2) != 0 && (u8(54) or u8(55)) != 0) {
            val width = u8(56) or ((u8(58) and 0xF0) shl 4)
            val height = u8(59) or ((u8(61) and 0xF0) shl 4)
            val frameHeight = if ((u8(71) and 0x80) != 0)
                height * 2
            else
                height

            "${width}x${frameHeight}"
        } else {
            ""
        }
    }

    /**
     * Method used to read the first localized product name provided by the display information dictionary
     *
     * @receiver The display dictionary containing the localized product names
     *
     * @return the first name, or [UNKNOWN] when absent or blank, as [String]
     */
    @Resolver
    private fun Map<String, *>.resolveName(): String {
        val productNames = readFromDictionary<Map<String, String>>(
            key = kDisplayProductName,
            default = emptyMap()
        )

        return productNames.values.firstOrNull()
            .orEmpty()
            .ifBlank { UNKNOWN }
    }

    /**
     * Method used to interpret a signed byte as an unsigned value between zero and 255
     *
     * @receiver The byte array containing the value to read
     * @param index The existing byte index to read
     *
     * @return the unsigned byte value as [Int]
     */
    private fun ByteArray.u8(index: Int): Int {
        return this[index].toInt() and 0xFF
    }

    /**
     * Method used to convert millimeters to the nearest whole centimeter
     *
     * @receiver The physical length expressed in millimeters
     *
     * @return the rounded length in centimeters as [Int]
     */
    private fun Double.asCm(): Int {
        return (this / 10).roundToInt()
    }

}

/**
 * The `ScreenSize` class is useful to hold the physical display dimensions in centimeters
 *
 * @property hcm The horizontal physical size in centimeters
 * @property vcm The vertical physical size in centimeters
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class ScreenSize(
    val hcm: Int,
    val vcm: Int
)