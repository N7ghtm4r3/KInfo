package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsOsSession

/**
 * The `MacOsOSSessionImpl` class is useful to provide macOS user session details
 *
 * @property userName The username of the user in the session
 * @property terminalDevice The terminal device associated with the session
 * @property loginTime The login time of the user session as a timestamp in milliseconds
 * @property host The host name or `IP` address of the system where the session is active
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsOsSession
 *
 * @since 1.1.0
 */
data class MacOsOSSessionImpl(
    override val userName: String,
    override val terminalDevice: String,
    override val loginTime: Long,
    override val host: String
) : MacOsOsSession
