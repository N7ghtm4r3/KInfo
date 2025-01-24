package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * Represents a user session in the operating system.
 * Provides information about the session, including the user, terminal device,
 * login time, and host related to the session.
 *
 * @author N7ghtm4r3
 */
interface OSSession {

    /**
     * `userName` The username of the user in the session.
     */
    val userName: String

    /**
     * `terminalDevice` The terminal device associated with the session.
     */
    val terminalDevice: String

    /**
     * `loginTime` The login time of the user session, represented as a timestamp in milliseconds.
     */
    val loginTime: Long

    /**
     * `host` The host name or IP address of the system where the session is active.
     */
    val host: String

}