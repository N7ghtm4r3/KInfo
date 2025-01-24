package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSService
import com.tecknobit.kinfo.model.desktop.operatingsystem.ServiceState

/**
 * Implementation of an operating system service.
 *
 * @param name The name of the service
 * @param processID The process ID associated with the service
 * @param state The current state of the service
 *
 * @author N7ghtm4r3
 *
 * @see OSService
 */
class OSServiceImpl(
    override val name: String,
    override val processID: Int,
    override val state: ServiceState
) : OSService
