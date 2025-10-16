package com.tecknobit.kinfo.model.desktop.operatingsystem.protocols

/**
 * `TcpState` Represents the different states of a TCP connection.
 *
 * This enum defines the possible states a TCP connection can be in during its lifecycle,
 * from establishing a connection to closing or being in a waiting state
 *
 * @author N7ghtm4r3
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc793">RFC 793</a> for more information on TCP states
 */
enum class TcpState {

    /**
     * `UNKNOWN` Represents an unknown state of the TCP connection
     */
    UNKNOWN,

    /**
     * `CLOSED` Represents a closed TCP connection, either because it was closed normally or due to an error
     */
    CLOSED,

    /**
     * `LISTEN` Represents a TCP socket that is listening for incoming connection requests
     */
    LISTEN,

    /**
     * `SYN_SENT` Represents the state where a TCP client has sent a SYN message to initiate a connection
     */
    SYN_SENT,

    /**
     * `SYN_RECV` Represents the state where a TCP server has received a SYN message and is waiting to establish a connection
     */
    SYN_RECV,

    /**
     * `ESTABLISHED` Represents the state where the TCP connection has been successfully established and data can be transmitted
     */
    ESTABLISHED,

    /**
     * `FIN_WAIT_1` Represents the state where the connection is being closed, and a FIN message has been sent to the other side
     */
    FIN_WAIT_1,

    /**
     * `FIN_WAIT_2` Represents the state where the connection is being closed, and the peer has acknowledged the FIN message
     */
    FIN_WAIT_2,

    /**
     * `CLOSE_WAIT` Represents the state where the local TCP stack has received a FIN from the peer and is waiting to close the connection
     */
    CLOSE_WAIT,

    /**
     * `CLOSING` Represents the state where both sides are trying to close the connection simultaneously
     */
    CLOSING,

    /**
     * `LAST_ACK` Represents the state where the local TCP stack has sent a FIN and is waiting for the final acknowledgment from the peer
     */
    LAST_ACK,

    /**
     * `TIME_WAIT` Represents the state where the connection is waiting for a timeout to ensure the remote side has received the final acknowledgment
     */
    TIME_WAIT,

    /**
     * `NONE` Represents the absence of a valid TCP state, typically used as a placeholder
     */
    NONE

}
