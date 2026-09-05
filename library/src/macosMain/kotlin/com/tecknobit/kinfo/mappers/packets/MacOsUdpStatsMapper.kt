package com.tecknobit.kinfo.mappers.packets

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.operatingsystem.MacOsUdpStatsImpl

class MacOsUdpStatsMapper : MacOsPacketsStatsMapper<MacOsUdpStatsImpl>() {

    private companion object {

        const val UDP_DATAGRAMS_RECEIVED = 0

        const val UDP_RECEIVED_ERRORS = 1

        const val UDP_BAD_CHECKSUM = 2

        const val UDP_BAD_LENGTH = 3

        const val UDP_DATAGRAMS_NO_PORT = 4

        const val UDP_DATAGRAMS_SENT = 7

    }

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

    @Resolver
    private fun UIntArray.resolveIsErrors(): Long {
        val receivedErrors = this fetch UDP_RECEIVED_ERRORS
        val badChecksum = this fetch UDP_BAD_CHECKSUM
        val badLength = this fetch UDP_BAD_LENGTH

        return receivedErrors + badChecksum + badLength
    }

}
