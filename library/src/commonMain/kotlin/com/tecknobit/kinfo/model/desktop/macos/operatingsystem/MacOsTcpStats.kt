package com.tecknobit.kinfo.model.desktop.macos.operatingsystem

import com.tecknobit.kinfo.model.desktop.common.operatingsystem.protocols.TcpStats

/**
 * The `MacOsTcpStats` interface defines the contract for the cumulative `TCP` statistics exposed by
 * the macOS `net.inet.tcp.stats` system control
 *
 * The macOS native variant reports `connectionsEstablished` as the total number of successful
 * connection establishments recorded by `tcps_connects`, rather than the number of sockets currently
 * in the `ESTABLISHED` or `CLOSE_WAIT` states. It also reports `outResets` from `tcps_sndctrl`, which
 * counts all sent `SYN`, `FIN`, and `RST` control segments instead of only outgoing reset segments.
 * Cross-platform implementations can therefore expose different variants for these two values
 *
 * When `net.inet.tcp.disable_access_to_stats` is enabled, macOS can protect these counters by returning
 * zeroed statistics even though the system control query succeeds
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see TcpStats
 *
 * @since 1.1.0
 */
interface MacOsTcpStats : TcpStats
