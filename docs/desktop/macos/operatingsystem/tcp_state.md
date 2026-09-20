Represents the states of a TCP connection

The native macOS [IP connection](internetprotocolstats/ip_connection.md#state) exposes `TcpState?`. TCP sockets use the entries below, with `UNKNOWN` for an unrecognized native state. Non-TCP sockets return `null`; the native mapper does not return `NONE`.

## Entries

| **State** | **Description** |
|-----------|-----------------|
| **UNKNOWN** | The native TCP state is not recognized |
| **CLOSED** | The TCP connection is closed |
| **LISTEN** | The socket is listening for incoming connection requests |
| **SYN_SENT** | A connection request has been sent |
| **SYN_RECV** | A connection request has been received and establishment is in progress |
| **ESTABLISHED** | The connection is established and can transfer data |
| **FIN_WAIT_1** | A request to close the connection has been sent |
| **FIN_WAIT_2** | The peer has acknowledged the request to close the connection |
| **CLOSE_WAIT** | The peer has requested closure and the local side has not yet closed |
| **CLOSING** | Both sides are closing the connection |
| **LAST_ACK** | The local side is waiting for acknowledgment of its final close request |
| **TIME_WAIT** | The connection is waiting before completing closure |
| **NONE** | A placeholder for the absence of a valid TCP state |

