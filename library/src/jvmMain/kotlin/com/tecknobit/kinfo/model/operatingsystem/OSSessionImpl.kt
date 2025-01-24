package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSSession

/**
 * Represents an implementation of the `OSSession` interface.
 * Provides concrete values for the session information, including the user,
 * terminal device, login time, and host related to the session.
 *
 * @param userName The username of the user in the session.
 * @param terminalDevice The terminal device associated with the session.
 * @param loginTime The login time of the user session, represented as a timestamp in milliseconds.
 * @param host The host name or IP address of the system where the session is active.
 *
 * @author N7ghtm4r3
 *
 * @see OSSession
 */
class OSSessionImpl(
    override val userName: String,
    override val terminalDevice: String,
    override val loginTime: Long,
    override val host: String
) : OSSession