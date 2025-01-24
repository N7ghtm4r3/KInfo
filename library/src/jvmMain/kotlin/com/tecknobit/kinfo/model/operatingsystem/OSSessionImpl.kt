package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession

class OSSessionImpl(
    override val userName: String,
    override val terminalDevice: String,
    override val loginTime: Long,
    override val host: String
) : OSSession