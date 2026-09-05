@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.operatingsystem.MacOsIpRouteImpl
import kotlinx.cinterop.*
import platform.darwin.CTL_NET
import platform.darwin.sysctl
import platform.posix.*

/**
 * The `MacOsIPRoutesMapper` class is useful to map native macOS routing messages to KInfo route
 * models
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
class MacOsIPRoutesMapper : NativeMapper<List<MacOsIpRouteImpl>>() {

    /**
     * Method used to map the native macOS routing table entries to their [MacOsIpRouteImpl] models
     *
     * @return the mapped macOS routes as [List] of [MacOsIpRouteImpl]
     */
    override fun mapFromNative(): List<MacOsIpRouteImpl> {
        val macOsIpRoutes = mutableListOf<MacOsIpRouteImpl>()
        val nativeIpRoutes = loadAndMapFromNative()

        nativeIpRoutes.forEach { ipRoute ->
            macOsIpRoutes.add(ipRoute)
        }

        return macOsIpRoutes
    }

    /**
     * Method used to load and map the native macOS `rt_msghdr2` routing messages
     *
     * @return the mapped macOS routes as [List] of [MacOsIpRouteImpl]
     */
    @Loader
    private fun loadAndMapFromNative(): List<MacOsIpRouteImpl> {
        return memScoped {
            val mib = intArrayOf(
                CTL_NET,      // network subsystem
                PF_ROUTE,     // routing table
                0,            // all protocols
                AF_UNSPEC,    // IPv4 and IPv6
                NET_RT_DUMP2, // rt_msghdr2 format
                0             // no filter
            )
            val size = alloc<size_tVar>()

            sysctl(
                mib.refTo(0),
                mib.size.convert(),
                null,
                size.ptr,
                null,
                0u
            )

            val buffer = allocArray<ByteVar>(size.value.toInt())
            sysctl(
                mib.refTo(0),
                mib.size.convert(),
                buffer,
                size.ptr,
                null,
                0u
            )

            mapFromNativeRoutes(
                size = size,
                buffer = buffer
            )
        }
    }

    /**
     * Method used to map the native routing messages stored in the routing table buffer
     *
     * @param size The number of bytes available in the routing table buffer
     * @param buffer The native routing table buffer to map
     *
     * @return the mapped macOS routes as [List] of [MacOsIpRouteImpl]
     */
    private fun mapFromNativeRoutes(
        size: size_tVar,
        buffer: CArrayPointer<ByteVar>
    ): List<MacOsIpRouteImpl> {
        return buildList {
            val totalSize = size.value.toInt()
            val rtMsgHdr2Size = sizeOf<rt_msghdr2>()
            var offset = 0

            while (offset < totalSize) {
                val record = buffer.plus(offset)!!
                    .reinterpret<rt_msghdr2>()
                val header = record.pointed

                val messageSize = header.rtm_msglen.toInt()
                check(messageSize >= rtMsgHdr2Size && (offset + messageSize) <= totalSize)

                val mappedRoute = record.mapToMacOsIpRoute(
                    messageSize = messageSize
                )
                add(mappedRoute)

                offset += messageSize
            }
        }
    }

    /**
     * Method used to map a native macOS routing message to its [MacOsIpRouteImpl] model
     *
     * @receiver The native `rt_msghdr2` routing message to map
     *
     * @param messageSize The complete routing message size
     *
     * @return the mapped macOS route as [MacOsIpRouteImpl]
     */
    private fun CPointer<rt_msghdr2>.mapToMacOsIpRoute(
        messageSize: Int
    ): MacOsIpRouteImpl {
        val header = pointed
        val addresses = loadAddresses(
            messageSize = messageSize
        )
        val destination = addresses[RTAX_DST]
            .toIpAddress()
        val isHost = header.rtm_flags and RTF_HOST != 0

        return MacOsIpRouteImpl(
            destination = destination,
            prefixLength = addresses[RTAX_NETMASK]
                .toPrefixLength(
                    addressSize = destination.size,
                    isHost = isHost
                ),
            gateway = addresses[RTAX_GATEWAY]
                .toIpAddress(),
            interfaceName = header.rtm_index
                .toUInt()
                .toInterfaceName(),
            interfaceIndex = header.rtm_index.toInt(),
            metric = header.rtm_rmx.rmx_hopcount.toLong(),
            isGateway = header.rtm_flags and RTF_GATEWAY != 0,
            isHost = isHost
        )
    }

    /**
     * Method used to load the socket addresses appended to a native routing message
     *
     * @receiver The native `rt_msghdr2` routing message containing the addresses
     *
     * @param messageSize The complete routing message size
     *
     * @return the available socket addresses indexed by their native `RTAX` position as [Map]
     */
    private fun CPointer<rt_msghdr2>.loadAddresses(
        messageSize: Int
    ): Map<Int, CPointer<sockaddr>> {
        val addresses = mutableMapOf<Int, CPointer<sockaddr>>()
        var offset = sizeOf<rt_msghdr2>().toInt()

        for(index in 0 until RTAX_MAX) {
            if(pointed.rtm_addrs and (1 shl index) == 0)
                continue

            val address = checkNotNull(
                reinterpret<ByteVar>().plus(offset)
            ).reinterpret<sockaddr>()

            addresses[index] = address

            offset += address
                .pointed
                .sa_len
                .toInt()
                .roundUp()

            check(offset <= messageSize)
        }

        return addresses
    }

    /**
     * Method used to align a native socket address size to its 32-bit boundary
     *
     * @receiver The native socket address size
     *
     * @return the aligned socket address size as [Int]
     */
    private fun Int.roundUp(): Int {
        val alignment = sizeOf<UIntVar>().toInt()

        return if(this == 0)
            alignment
        else
            (this + alignment - 1) and -alignment
    }

    /**
     * Method used to copy an `IPv4` or `IPv6` socket address
     *
     * @receiver The native socket address to copy
     *
     * @return the copied address, or an empty array when unavailable or unsupported, as [ByteArray]
     */
    private fun CPointer<sockaddr>?.toIpAddress(): ByteArray {
        if(this == null)
            return byteArrayOf()

        return when(pointed.sa_family.toInt()) {
            AF_INET -> reinterpret<sockaddr_in>()
                .pointed
                .sin_addr
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(4)

            AF_INET6 -> reinterpret<sockaddr_in6>()
                .pointed
                .sin6_addr
                .ptr
                .reinterpret<ByteVar>()
                .readBytes(16)

            else -> byteArrayOf()
        }
    }

    /**
     * Method used to resolve the name of a native network interface
     *
     * @receiver The native network interface index
     *
     * @return the resolved interface name, or an empty string when unavailable, as [String]
     */
    private fun UInt.toInterfaceName(): String {
        return memScoped {
            val buffer = allocArray<ByteVar>(IF_NAMESIZE)

            val interfaceName = if_indextoname(
                this@toInterfaceName,
                buffer
            )

            interfaceName?.toKString() ?: ""
        }
    }

    /**
     * Method used to resolve the prefix length from a native route netmask
     *
     * @receiver The native netmask socket address
     *
     * @param addressSize The destination address size
     * @param isHost Whether the route targets a single host
     *
     * @return the resolved prefix length, or `-1` when unavailable, as [Int]
     */
    private fun CPointer<sockaddr>?.toPrefixLength(
        addressSize: Int,
        isHost: Boolean
    ): Int {
        if(this == null) {
            return if(isHost)
                addressSize * Byte.SIZE_BITS
            else
                -1
        }

        val addressOffset = if(addressSize == 4) 4 else 8
        val bytesCount = (pointed.sa_len.toInt() - addressOffset)
            .coerceIn(0, addressSize)
        if(bytesCount == 0)
            return 0

        val maskBuffer = reinterpret<ByteVar>()
            .plus(addressOffset)
        val mask = checkNotNull(maskBuffer)
            .readBytes(bytesCount)

        return mask.sumOf { byte ->
            (byte.toInt() and 0xff).countOneBits()
        }
    }

}
