package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsUdpStats

data class MacOsUdpStatsImpl(
    override val datagramsSent: Long,
    override val datagramsReceived: Long,
    override val datagramsNoPort: Long,
    override val datagramsReceivedErrors: Long
) : MacOsUdpStats