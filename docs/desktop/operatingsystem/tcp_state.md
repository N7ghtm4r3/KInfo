Represents the different states of a **TCP** connection.

This enum defines the possible states a **TCP** connection can be in during its lifecycle,
from establishing a connection to closing or being in a waiting state

## Entries

| **State**       | **Description**                                                                                                                    |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------|
| **UNKNOWN**     | Represents an unknown state of the TCP connection                                                                                  |
| **CLOSED**      | Represents a closed TCP connection, either because it was closed normally or due to an error                                       |
| **LISTEN**      | Represents a TCP socket that is listening for incoming connection requests                                                         |
| **SYN_SENT**    | Represents the state where a TCP client has sent a SYN message to initiate a connection                                            |
| **SYN_RECV**    | Represents the state where a TCP server has received a SYN message and is waiting to establish a connection                        |
| **ESTABLISHED** | Represents the state where the TCP connection has been successfully established and data can be transmitted                        |
| **FIN_WAIT_1**  | Represents the state where the connection is being closed, and a FIN message has been sent to the other side                       |
| **FIN_WAIT_2**  | Represents the state where the connection is being closed, and the peer has acknowledged the FIN message                           |
| **CLOSE_WAIT**  | Represents the state where the local TCP stack has received a FIN from the peer and is waiting to close the connection             |
| **CLOSING**     | Represents the state where both sides are trying to close the connection simultaneously                                            |
| **LAST_ACK**    | Represents the state where the local TCP stack has sent a FIN and is waiting for the final acknowledgment from the peer            |
| **TIME_WAIT**   | Represents the state where the connection is waiting for a timeout to ensure the remote side has received the final acknowledgment |
| **NONE**        | Represents the absence of a valid TCP state, typically used as a placeholder                                                       |

These entries are according to the [RFC 793](https://www.rfc-editor.org/rfc/rfc793) document