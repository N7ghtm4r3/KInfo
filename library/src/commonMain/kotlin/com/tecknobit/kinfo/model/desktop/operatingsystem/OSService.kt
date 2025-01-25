package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * `OSService` represents an operating system service
 *
 * @author n7ghtm4r3
 * @see ServiceState
 */
interface OSService {

    /**
     * `name` The name of the service.
     */
    val name: String

    /**
     * `processID` The process ID associated with the service.
     */
    val processID: Int

    /**
     * `state` The current state of the service.
     */
    val state: ServiceState
}

/**
 * Service Execution States
 *
 * @author N7ghtm4r3
 */
enum class ServiceState {
    /**
     * The service is currently running.
     */
    RUNNING,

    /**
     * The service is currently stopped.
     */
    STOPPED,

    /**
     * The service is in an unknown or other state.
     */
    OTHER
}