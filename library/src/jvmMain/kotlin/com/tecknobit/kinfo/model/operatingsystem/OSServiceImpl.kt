package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSService
import com.tecknobit.kinfo.model.desktop.operatingsystem.ServiceState

class OSServiceImpl(
    override val name: String,
    override val processID: Int,
    override val state: ServiceState
) : OSService