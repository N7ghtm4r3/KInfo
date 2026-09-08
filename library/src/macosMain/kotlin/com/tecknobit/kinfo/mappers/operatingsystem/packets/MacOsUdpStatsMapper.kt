package com.tecknobit.kinfo.mappers.operatingsystem.packets

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.operatingsystem.MacOsUdpStatsImpl

/**
 * The `MacOsUdpStatsMapper` class is useful to map native macOS `UDP` counters to KInfo models
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsPacketsStatsMapper
 *
 * @since 1.1.0
 */
class MacOsUdpStatsMapper : MacOsPacketsStatsMapper<MacOsUdpStatsImpl>() {

    /**
     * The companion object contains the indexes of the native macOS `UDP` counters
     */
    private companion object {

        /**
         * `UDP_DATAGRAMS_RECEIVED` the index of the received datagrams counter
         */
        const val UDP_DATAGRAMS_RECEIVED = 0

        /**
         * `UDP_RECEIVED_ERRORS` the index of the received errors counter
         */
        const val UDP_RECEIVED_ERRORS = 1

        /**
         * `UDP_BAD_CHECKSUM` the index of the invalid checksum counter
         */
        const val UDP_BAD_CHECKSUM = 2

        /**
         * `UDP_BAD_LENGTH` the index of the invalid length counter
         */
        const val UDP_BAD_LENGTH = 3

        /**
         * `UDP_DATAGRAMS_NO_PORT` the index of the datagrams without a matching destination port counter
         */
        const val UDP_DATAGRAMS_NO_PORT = 4

        /**
         * `UDP_DATAGRAMS_SENT` the index of the sent datagrams counter
         */
        const val UDP_DATAGRAMS_SENT = 7

    }

    /**
     * Method used to map the native macOS `UDP` counters to their model
     *
     * @return the mapped `UDP` statistics as [MacOsUdpStatsImpl]
     */
    override fun mapFromNative(): MacOsUdpStatsImpl {
        val nativeStats = retrieveNativeStats(
            systemControlKey = "net.inet.udp.stats"
        )

        return MacOsUdpStatsImpl(
            datagramsSent = nativeStats fetch UDP_DATAGRAMS_SENT,
            datagramsReceived = nativeStats fetch UDP_DATAGRAMS_RECEIVED,
            datagramsNoPort = nativeStats fetch UDP_DATAGRAMS_NO_PORT,
            datagramsReceivedErrors = nativeStats.resolveIsErrors()
        )
    }

    /**
     * Method used to resolve the total number of errors detected while receiving `UDP` datagrams
     *
     * @receiver The native macOS `UDP` counters
     *
     * @return the total number of received errors as [Long]
     */
    @Resolver
    private fun UIntArray.resolveIsErrors(): Long {
        val receivedErrors = this fetch UDP_RECEIVED_ERRORS
        val badChecksum = this fetch UDP_BAD_CHECKSUM
        val badLength = this fetch UDP_BAD_LENGTH

        return receivedErrors + badChecksum + badLength
    }

}
