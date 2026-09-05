@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.operatingsystem.MacOsTcpStatsImpl
import com.tecknobit.kinfo.utils.queryUIntArraySysCtlByName
import kotlinx.cinterop.ExperimentalForeignApi

/**
 * The `MacOsTcpStatsMapper` class is useful to map the native macOS `net.inet.tcp.stats` counters to
 * their [MacOsTcpStatsImpl] model
 *
 * This mapper preserves the Darwin counter variants: established connections are cumulative successful
 * establishments from `tcps_connects`, while outgoing resets are represented by `tcps_sndctrl` and
 * include sent `SYN`, `FIN`, and `RST` control segments
 *
 * A successful query can still contain zeroed counters when the macOS
 * `net.inet.tcp.disable_access_to_stats` protection is enabled
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 * @see MacOsTcpStatsImpl
 *
 * @since 1.1.0
 */
class MacOsTcpStatsMapper : NativeMapper<MacOsTcpStatsImpl>() {

    private companion object {

        /**
         * `TCP_CONNECTIONS_ACTIVE` the index of the cumulative active connection attempts counter
         */
        const val TCP_CONNECTIONS_ACTIVE = 0

        /**
         * `TCP_CONNECTIONS_PASSIVE` the index of the cumulative accepted passive connections counter
         */
        const val TCP_CONNECTIONS_PASSIVE = 1

        /**
         * `TCP_CONNECTIONS_ESTABLISHED` the index of the cumulative successful establishments counter
         */
        const val TCP_CONNECTIONS_ESTABLISHED = 2

        /**
         * `TCP_CONNECTIONS_RESET` the index of the cumulative established connection drops counter
         */
        const val TCP_CONNECTIONS_RESET = 3

        /**
         * `TCP_CONNECTION_FAILURES` the index of the cumulative embryonic connection drops counter
         */
        const val TCP_CONNECTION_FAILURES = 4

        /**
         * `TCP_SEGMENTS_SENT` the index of the cumulative data segments sent counter
         */
        const val TCP_SEGMENTS_SENT = 16

        /**
         * `TCP_SEGMENTS_RETRANSMITTED` the index of the cumulative retransmitted data segments counter
         */
        const val TCP_SEGMENTS_RETRANSMITTED = 18

        /**
         * `TCP_OUT_RESETS` the index of the cumulative sent `SYN`, `FIN`, and `RST` control segments counter
         */
        const val TCP_OUT_RESETS = 24

        /**
         * `TCP_SEGMENTS_RECEIVED` the index of the cumulative data segments received in sequence counter
         */
        const val TCP_SEGMENTS_RECEIVED = 26

        /**
         * `TCP_BAD_CHECKSUM` the index of the cumulative packets rejected for bad checksums counter
         */
        const val TCP_BAD_CHECKSUM = 28

        /**
         * `TCP_BAD_OFFSET` the index of the cumulative packets rejected for bad header offsets counter
         */
        const val TCP_BAD_OFFSET = 29

        /**
         * `TCP_MEMORY_DROPS` the index of the cumulative packets dropped for insufficient memory counter
         */
        const val TCP_MEMORY_DROPS = 30

        /**
         * `TCP_SHORT_PACKETS` the index of the cumulative packets rejected for insufficient length counter
         */
        const val TCP_SHORT_PACKETS = 31

    }

    /**
     * Method used to map the native macOS `TCP` counters to their [MacOsTcpStatsImpl] model
     *
     * @return the mapped macOS `TCP` statistics as [MacOsTcpStatsImpl]
     */
    override fun mapFromNative(): MacOsTcpStatsImpl {
        val nativeStats = queryUIntArraySysCtlByName(
            name = "net.inet.tcp.stats"
        ) ?: throw IllegalStateException("Could not read tcp stats")

        return MacOsTcpStatsImpl(
            connectionsEstablished = nativeStats fetch TCP_CONNECTIONS_ESTABLISHED,
            connectionsActive = nativeStats fetch TCP_CONNECTIONS_ACTIVE,
            connectionsPassive = nativeStats fetch TCP_CONNECTIONS_PASSIVE,
            connectionFailures = nativeStats fetch TCP_CONNECTION_FAILURES,
            connectionsReset = nativeStats fetch TCP_CONNECTIONS_RESET,
            segmentsSent = nativeStats fetch TCP_SEGMENTS_SENT,
            segmentsReceived = nativeStats fetch TCP_SEGMENTS_RECEIVED,
            segmentsRetransmitted = nativeStats fetch TCP_SEGMENTS_RETRANSMITTED,
            inErrors = nativeStats.resolveIsErrors(),
            outResets = nativeStats fetch TCP_OUT_RESETS
        )
    }

    /**
     * Method used to resolve the cumulative incoming errors from the native error counters
     *
     * @receiver The native macOS `TCP` counters
     *
     * @return the cumulative incoming errors as [Long]
     */
    @Resolver
    private fun UIntArray.resolveIsErrors(): Long {
        val badChecksum = this fetch TCP_BAD_CHECKSUM
        val badOffset = this fetch TCP_BAD_OFFSET
        val memoryDrops = this fetch TCP_MEMORY_DROPS
        val shortPackets = this fetch TCP_SHORT_PACKETS

        return badChecksum + badOffset + memoryDrops + shortPackets
    }

    /**
     * Method used to fetch and convert a native unsigned counter at the specified [index]
     *
     * @receiver The native macOS `TCP` counters
     *
     * @param index The index of the counter to fetch
     *
     * @return the fetched counter as [Long]
     */
    private infix fun UIntArray.fetch(
        index: Int
    ): Long {
        return this[index].toLong()
    }

}
