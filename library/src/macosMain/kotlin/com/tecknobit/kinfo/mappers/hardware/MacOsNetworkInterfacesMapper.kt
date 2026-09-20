@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsNetworkIFImpl
import com.tecknobit.kinfo.model.desktop.common.hardware.IfOperStatus
import com.tecknobit.kinfo.utils.toNSString
import kotlinx.cinterop.*
import platform.CoreFoundation.CFArrayGetCount
import platform.CoreFoundation.CFArrayGetValueAtIndex
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFStringRef
import platform.SystemConfiguration.SCNetworkInterfaceCopyAll
import platform.SystemConfiguration.SCNetworkInterfaceGetBSDName
import platform.SystemConfiguration.SCNetworkInterfaceGetHardwareAddressString
import platform.SystemConfiguration.SCNetworkInterfaceGetLocalizedDisplayName
import platform.darwin.*
import platform.posix.*
import kotlin.time.Clock
import kotlinx.cinterop.ByteVar as NativeByteVar
import platform.posix.sockaddr_in as PosixSockaddrIn
import platform.posix.sockaddr_in6 as PosixSockaddrIn6

/**
 * The `MacOsNetworkInterfacesMapper` class is useful to map macOS network interface identities, addresses, and statistics
 *
 * Routing statistics, assigned addresses, and descriptive names are queried separately, so the result is not an atomic
 * snapshot of the network configuration
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsNetworkInterfacesMapper : MacOsHardwareMapper<List<MacOsNetworkIFImpl>>() {

    private companion object {

        /**
         * `LINK_ADDRESS_HEADER_SIZE` the number of fixed header bytes preceding the interface name in a link address
         */
        const val LINK_ADDRESS_HEADER_SIZE = 8

        /**
         * `KNOWN_VM_MAC_PREFIXES` the uppercase MAC prefixes used to identify known virtual-machine address patterns
         *
         * A matching prefix is a heuristic and does not establish whether an interface belongs to a virtual machine
         */
        val KNOWN_VM_MAC_PREFIXES = setOf(
            "00:50:56", "00:0C:29", "00:05:69", "00:1C:14", // VMware
            "00:03:FF", "00:15:5D", // Microsoft
            "00:16:3E", // Xen
            "52:54:00", "00:1A:4A", // QEMU/KVM
            "00:1C:42", "08:00:27", "00:0F:4B", // Parallels, VirtualBox, Virtual Iron
            "02:42:AC", // Docker
            "FA:16:3E", "02:00:17", // OpenStack, oVirt
            "02:81:6F", "06:05:B0", "06:A2:90", "42:01:0A" // Cloud platforms
        )

    }

    /**
     * Method used to map the current macOS network interfaces with their addresses, counters, and operational status
     *
     * Every result receives the same timestamp in milliseconds since the Unix epoch
     * The status-query socket is shared across the mapping and closed on exit when successfully opened
     *
     * @return the mapped interfaces, or an empty list when the routing query fails or yields no entries,
     * as [List] of [MacOsNetworkIFImpl]
     * @throws IllegalStateException If an interface message has an unsupported version or an undersized header
     */
    override fun mapFromNative(): List<MacOsNetworkIFImpl> {
        val interfaceDetails = loadInterfaceDetails()
        val timestamp = Clock.System.now().toEpochMilliseconds()
        val descriptor = socket(
            AF_INET,
            SOCK_DGRAM,
            0
        )

        return try {
            loadNetworkInterfaces { networkInterface ->
                val ifmData = networkInterface.ifm_data
                val index = networkInterface.ifm_index
                val name = resolveInterfaceName(
                    index = index
                )
                val details = interfaceDetails[name] ?: InterfaceDetails()
                val displayName = details.displayName.ifEmpty {
                    name
                }
                val operStatus = resolveOperStatus(
                    name = name,
                    flags = networkInterface.ifm_flags,
                    descriptor = descriptor
                )

                val macAddress = details.macaddr
                val macPrefix = macAddress.take(8).uppercase()
                val isKnownVmMacAddr = macAddress.length == 17 && macPrefix in KNOWN_VM_MAC_PREFIXES

                MacOsNetworkIFImpl(
                    name = name,
                    index = index.toInt(),
                    displayName = displayName,
                    ifOperStatus = operStatus,
                    mtu = ifmData.ifi_mtu.toLong(),
                    macaddr = macAddress,
                    ipv4addr = details.ipv4.keys.toTypedArray(),
                    subnetMasks = details.ipv4.values.toTypedArray(),
                    ipv6addr = details.ipv6.keys.toTypedArray(),
                    prefixLengths = details.ipv6.values.toTypedArray(),
                    ifType = ifmData.ifi_type.toInt(),
                    bytesRecv = ifmData.ifi_ibytes.toLong(),
                    bytesSent = ifmData.ifi_obytes.toLong(),
                    packetsRecv = ifmData.ifi_ipackets.toLong(),
                    packetsSent = ifmData.ifi_opackets.toLong(),
                    inErrors = ifmData.ifi_ierrors.toLong(),
                    outErrors = ifmData.ifi_oerrors.toLong(),
                    inDrops = ifmData.ifi_iqdrops.toLong(),
                    collisions = ifmData.ifi_collisions.toLong(),
                    speed = ifmData.ifi_baudrate.toLong(),
                    timestamp = timestamp,
                    isKnownVmMacAddr = isKnownVmMacAddr
                )
            }
        } finally {
            if (descriptor >= 0)
                close(descriptor)
        }
    }

    /**
     * Method used to load routing interface messages and transform their native headers into network interface snapshots
     *
     * The header passed to [transform] borrows memory from the temporary routing buffer
     * The transformation must not retain the header or any pointer into that buffer after this method returns
     * Invalid message boundaries stop parsing and preserve the entries already mapped
     *
     * @param transform The operation used to map each borrowed interface header while the native buffer is available
     *
     * @return the transformed interfaces, or an empty list when the routing buffer cannot be loaded,
     * as [List] of [MacOsNetworkIFImpl]
     * @throws IllegalStateException If an interface message has an unsupported version or an undersized header
     */
    @Loader
    private inline fun loadNetworkInterfaces(
        transform: (if_msghdr2) -> MacOsNetworkIFImpl
    ): List<MacOsNetworkIFImpl> {
        return memScoped {
            val mib = intArrayOf(
                CTL_NET,
                PF_ROUTE,
                0,
                0,
                NET_RT_IFLIST2,
                0
            )
            val length = alloc<size_tVar> {
                value = 0u
            }

            val mibBufferResult = sysctl(
                mib.refTo(0),
                mib.size.convert(),
                null,
                length.ptr,
                null,
                0u
            )
            if (mibBufferResult != 0 || length.value !in 1uL..Int.MAX_VALUE.toULong())
                return@memScoped emptyList()

            val capacity = length.value.toInt()
            val buffer = allocArray<NativeByteVar>(capacity)
            val loadBufferResult = sysctl(
                mib.refTo(0),
                mib.size.convert(),
                buffer,
                length.ptr,
                null,
                0u
            )
            if (loadBufferResult != 0 || length.value > capacity.toULong())
                return@memScoped emptyList()

            val totalSize = length.value.toInt()
            buildList {
                var offset = 0

                while (offset < totalSize) {
                    val remaining = totalSize - offset
                    if (remaining < 4)
                        break

                    val header = (buffer + offset)!!
                        .reinterpret<if_msghdr2>()
                        .pointed

                    val messageSize = header.ifm_msglen.toInt()
                    if (messageSize !in 4..remaining)
                        break

                    if (header.ifm_type.toInt() == RTM_IFINFO2) {
                        check(header.ifm_version.toInt() == RTM_VERSION)
                        check(messageSize.toLong() >= sizeOf<if_msghdr2>())

                        val networkInterface = transform(header)
                        add(networkInterface)
                    }

                    offset += messageSize
                }
            }
        }
    }

    /**
     * Method used to resolve the BSD name associated with a native network interface index
     *
     * @param index The native index of the network interface
     *
     * @return the interface name, or an empty string when the lookup fails, as [String]
     */
    @Resolver
    private fun resolveInterfaceName(
        index: u_short
    ): String {
        return memScoped {
            val buffer = allocArray<NativeByteVar>(IF_NAMESIZE)

            val result = if_indextoname(
                index.toUInt(),
                buffer
            )

            result?.toKString().orEmpty()
        }
    }

    /**
     * Method used to collect assigned addresses and descriptive metadata indexed by the BSD interface name
     *
     * Each address is paired with its prefix length, with `-1` representing an absent or noncontiguous netmask
     * Descriptive metadata is merged through [loadInterfaces], including when address enumeration fails
     * The native address list is released after its entries have been collected
     *
     * @return the available interface details indexed by name as [Map] of [String] to [InterfaceDetails]
     */
    @Loader
    private fun loadInterfaceDetails(): Map<String, InterfaceDetails> {
        val details = mutableMapOf<String, InterfaceDetails>()

        memScoped {
            val head = alloc<CPointerVar<ifaddrs>> {
                value = null
            }
            val result = getifaddrs(head.ptr)
            if (result != 0)
                return@memScoped

            try {
                var current = head.value
                while (current != null) {
                    val entry = current.pointed
                    current = entry.ifa_next
                    val name = entry.ifa_name?.toKString() ?: continue
                    val address = entry.ifa_addr ?: continue
                    val item = details.getOrPut(
                        key = name,
                        defaultValue = { InterfaceDetails() }
                    )

                    when (val family = address.pointed.sa_family.toInt()) {
                        AF_LINK -> {
                            item.macaddr = resolveMacAddress(
                                address = address
                            )
                        }

                        AF_INET, AF_INET6 -> {
                            val ipAddress = resolveIpAddress(
                                address = address
                            ) ?: continue
                            val prefix = resolvePrefixLength(
                                mask = entry.ifa_netmask,
                                family = family
                            )
                            val addresses = if (family == AF_INET)
                                item.ipv4
                            else
                                item.ipv6

                            addresses[ipAddress] = prefix
                        }
                    }
                }
            } finally {
                freeifaddrs(head.value)
            }
        }

        details.loadInterfaces()

        return details
    }

    /**
     * Method used to merge SystemConfiguration interface names and hardware addresses into the collected details
     *
     * Interfaces with a BSD name are added when absent, and their display names are replaced with the current native value
     * Existing MAC addresses are preserved, while empty ones receive the available native hardware address
     * The map is unchanged when enumeration is unavailable, and a copied interface array is released on exit
     *
     * @receiver The mutable interface details indexed by BSD name to enrich with descriptive metadata
     */
    @Loader
    private fun MutableMap<String, InterfaceDetails>.loadInterfaces() {
        val interfaces = SCNetworkInterfaceCopyAll() ?: return

        try {
            val interfacesCount = CFArrayGetCount(interfaces)
            for (index in 0L until interfacesCount) {
                val interfacePointer = CFArrayGetValueAtIndex(
                    interfaces,
                    index
                ) ?: continue
                val scInterface = interfacePointer.reinterpret<cnames.structs.__SCNetworkInterface>()
                val nativeName = SCNetworkInterfaceGetBSDName(scInterface)
                val name = nativeName
                    ?.toNSString()
                    ?.toString() ?: continue
                val item = this.getOrPut(
                    key = name,
                    defaultValue = { InterfaceDetails() }
                )

                val nativeDisplayName = SCNetworkInterfaceGetLocalizedDisplayName(scInterface)
                item.displayName = nativeDisplayName.sanitize()

                if (item.macaddr.isEmpty()) {
                    val nativeMacAddress = SCNetworkInterfaceGetHardwareAddressString(scInterface)
                    item.macaddr = nativeMacAddress.sanitize()
                }
            }
        } finally {
            CFRelease(interfaces)
        }
    }

    /**
     * Method used to convert an optional Core Foundation string into a Kotlin string
     *
     * The original string contents are preserved and the native reference remains owned by the caller
     *
     * @receiver The borrowed native string reference, or null when no value is available
     *
     * @return the converted value, or an empty string when the reference is null, as [String]
     */
    private fun CFStringRef?.sanitize(): String {
        return this?.toNSString()
            ?.toString()
            .orEmpty()
    }

    /**
     * Method used to format the hardware address stored in a native link-layer socket address
     *
     * The supplied address remains owned by the caller
     *
     * @param address The link-layer address containing the fixed header, interface name, and hardware address bytes
     *
     * @return the lowercase hexadecimal address separated by colons, or an empty string when absent or truncated,
     * as [String]
     */
    @Resolver
    private fun resolveMacAddress(
        address: CPointer<sockaddr>
    ): String {
        val length = address.pointed.sa_len.toInt()
        if (length < LINK_ADDRESS_HEADER_SIZE)
            return ""

        val bytes = address.reinterpret<UByteVar>()
        val nameLength = bytes[5].toInt()
        val addressLength = bytes[6].toInt()
        val addressOffset = LINK_ADDRESS_HEADER_SIZE + nameLength
        val addressEnd = addressOffset + addressLength
        if (addressLength == 0 || addressEnd > length)
            return ""

        val addressIndices = 0 until addressLength
        return addressIndices.joinToString(
            separator = ":"
        ) { index ->
            val addressByte = bytes[addressOffset + index]
            val hexadecimalByte = addressByte.toString(
                radix = 16
            )

            hexadecimalByte.padStart(
                length = 2,
                padChar = '0'
            )
        }
    }

    /**
     * Method used to format a native IP address numerically without resolving a hostname
     *
     * Scoped IPv6 addresses retain the scope suffix supplied by the native formatter
     * The supplied address remains owned by the caller
     *
     * @param address The IPv4 or IPv6 socket address to format
     *
     * @return the numeric address, or null when formatting fails, as [String]
     */
    @Resolver
    private fun resolveIpAddress(
        address: CPointer<sockaddr>
    ): String? {
        return memScoped {
            val buffer = allocArray<NativeByteVar>(NI_MAXHOST)
            val addressSize = address.pointed.sa_len.toUInt()
            val result = getnameinfo(
                address,
                addressSize,
                buffer,
                NI_MAXHOST.convert(),
                null,
                0u,
                NI_NUMERICHOST
            )

            if (result == 0)
                buffer.toKString()
            else
                null
        }
    }

    /**
     * Method used to count the contiguous leading bits of an IPv4 or IPv6 netmask
     *
     * Compact native netmasks are copied into zero-filled storage before their address bytes are inspected
     * The supplied mask remains owned by the caller
     *
     * @param mask The native netmask, or null when no mask is available
     * @param family The address family of the associated IP address, either `AF_INET` or `AF_INET6`
     *
     * @return the prefix length, or `-1` when the mask is absent or noncontiguous, as [Short]
     */
    @Resolver
    private fun resolvePrefixLength(
        mask: CPointer<sockaddr>?,
        family: Int
    ): Short {
        if (mask == null)
            return (-1).toShort()

        return memScoped {
            val storage = alloc<PosixSockaddrIn6>()
            val storageSize = sizeOf<PosixSockaddrIn6>()
            val isIpv4 = family == AF_INET
            val structureSize = if (isIpv4)
                sizeOf<PosixSockaddrIn>()
            else
                storageSize

            val maskSize = mask.pointed.sa_len.toLong()
            val copySize = minOf(
                a = maskSize,
                b = structureSize
            )

            memset(
                storage.ptr,
                0,
                storageSize.convert()
            )
            memcpy(
                storage.ptr,
                mask,
                copySize.convert()
            )

            val bytes = if (isIpv4) {
                val address = storage.ptr.reinterpret<PosixSockaddrIn>().pointed.sin_addr
                address.ptr.reinterpret()
            } else
                storage.sin6_addr.ptr.reinterpret<UByteVar>()

            val byteCount = if (isIpv4)
                4
            else
                16
            var prefix = 0
            var encounteredZero = false

            for (index in 0 until byteCount) {
                val byteValue = bytes[index].toInt()
                for (bit in 7 downTo 0) {
                    val bitMask = 1 shl bit
                    val isZeroBit = byteValue and bitMask == 0
                    if (isZeroBit) {
                        encounteredZero = true
                    } else {
                        if (encounteredZero)
                            return@memScoped (-1).toShort()

                        prefix++
                    }
                }
            }

            prefix.toShort()
        }
    }

    /**
     * Method used to resolve the operational status from interface flags and the native media status
     *
     * Administratively disabled interfaces map to [IfOperStatus.DOWN] and running loopback interfaces map to
     * [IfOperStatus.UP], while other interfaces require a valid media status
     * A missing query socket, invalid name, failed query, or unavailable media status maps to [IfOperStatus.UNKNOWN]
     * The supplied socket remains owned by the caller
     *
     * @param name The BSD interface name used by the media query
     * @param flags The native interface flags reported by the routing message
     * @param descriptor The borrowed datagram socket descriptor, or a negative value when opening the socket failed
     *
     * @return the resolved interface status as [IfOperStatus]
     */
    @Resolver
    private fun resolveOperStatus(
        name: String,
        flags: Int,
        descriptor: Int
    ): IfOperStatus {
        val isAdministrativelyUp = flags and IFF_UP != 0
        if (!isAdministrativelyUp)
            return IfOperStatus.DOWN

        val isLoopback = flags and IFF_LOOPBACK != 0
        val isRunning = flags and IFF_RUNNING != 0
        if (isLoopback && isRunning)
            return IfOperStatus.UP

        val encodedName = name.encodeToByteArray()
        if (encodedName.isEmpty() || encodedName.size >= IFNAMSIZ)
            return IfOperStatus.UNKNOWN

        if (descriptor < 0)
            return IfOperStatus.UNKNOWN

        return memScoped {
            val request = alloc<ifmediareq>()
            val requestSize = sizeOf<ifmediareq>()
            memset(
                request.ptr,
                0,
                requestSize.convert()
            )
            encodedName.forEachIndexed { index, character ->
                request.ifm_name[index] = character
            }

            val result = ioctl(
                descriptor,
                SIOCGIFMEDIA,
                request.ptr
            )
            if (result != 0)
                return@memScoped IfOperStatus.UNKNOWN

            val mediaStatus = request.ifm_status
            val isStatusValid = mediaStatus and IFM_AVALID != 0
            if (!isStatusValid)
                return@memScoped IfOperStatus.UNKNOWN

            val isLinkActive = mediaStatus and IFM_ACTIVE != 0
            if (isLinkActive)
                IfOperStatus.UP
            else
                IfOperStatus.DOWN
        }
    }

}

/**
 * The `InterfaceDetails` class is useful to collect interface metadata before mapping the native routing statistics
 *
 * @property displayName The localized interface name, or an empty string when unavailable
 * @property macaddr The hardware address, or an empty string when unavailable
 * @property ipv4 The IPv4 addresses paired with prefix lengths, using `-1` for an absent or noncontiguous netmask
 * @property ipv6 The IPv6 addresses paired with prefix lengths, using `-1` for an absent or noncontiguous netmask
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
private data class InterfaceDetails(
    var displayName: String = "",
    var macaddr: String = "",
    val ipv4: MutableMap<String, Short> = linkedMapOf(),
    val ipv6: MutableMap<String, Short> = linkedMapOf()
)
