package com.tecknobit.kinfo.model.desktop.hardware

/**
 * This interface represents a printer and provides details about name, driver name,
 * description, current status, and connection information
 *
 * It also offers information about whether the printer is the default printer,
 * whether it is local or network-based, and the reason for its current status
 *
 * Integration in [oshi - #3078](https://github.com/oshi/oshi/pull/3078)
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 */
interface Printer {

    /**
     * `name` The name of the printer
     */
    val name: String

    /**
     * `driverName` The driver name or make/model of the printer
     */
    val driverName: String

    /**
     * `description` The user-friendly description of the printer
     */
    val description: String

    /**
     * `status` The current status of the printer
     */
    val status: PrinterStatus

    /**
     * `statusReason` The reason for the current printer status, if available
     * (e.g., "Paper Jam", "media-empty")
     */
    val statusReason: String

    /**
     * `isDefault` Indicates whether this is the default printer
     */
    val isDefault: Boolean

    /**
     * `isLocal` Indicates whether this is a local printer
     * (as opposed to a network printer)
     */
    val isLocal: Boolean

    /**
     * `portName` The port name or device URI of the printer
     */
    val portName: String
}

/**
 * `PrinterStatus` Represents the different states a printer can be
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 */
enum class PrinterStatus {

    /**
     * `IDLE` The printer is currently idle
     */
    IDLE,

    /**
     * `PRINTING` The printer is currently printing
     */
    PRINTING,

    /**
     * `ERROR` The printer is currently facing an error
     */
    ERROR,

    /**
     * `OFFLINE` The printer is currently offline
     */
    OFFLINE,

    /**
     * `UNKNOWN` The printer is currently in an unknown status
     */
    UNKNOWN
}
