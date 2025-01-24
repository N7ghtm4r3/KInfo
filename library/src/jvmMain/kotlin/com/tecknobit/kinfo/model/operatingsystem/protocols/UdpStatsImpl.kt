package com.tecknobit.kinfo.model.operatingsystem.protocols

import com.tecknobit.kinfo.model.desktop.operatingsystem.protocols.UdpStats

class UdpStatsImpl(
    override val datagramsSent: Long,
    override val datagramsReceived: Long,
    override val datagramsNoPort: Long,
    override val datagramsReceivedErrors: Long
) : UdpStats