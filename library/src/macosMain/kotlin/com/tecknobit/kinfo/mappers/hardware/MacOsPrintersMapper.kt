@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.UNKNOWN
import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.hardware.MacOsPrinterImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsPrintersMapper.Companion.LOCAL_PRINTER_PROTOCOLS
import com.tecknobit.kinfo.model.desktop.common.hardware.PrinterStatus
import kotlinx.cinterop.*
import platform.cups.cupsFreeDests
import platform.cups.cupsGetDests2
import platform.cups.cupsGetOption
import platform.cups.cups_dest_t

/**
 * The `MacOsPrintersMapper` class is useful to map CUPS destinations to macOS printer snapshots
 *
 * Destination strings and options are copied into Kotlin values before the native destination list is released
 * Each mapping reads the current destination list without retaining native pointers
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsPrintersMapper : MacOsHardwareMapper<List<MacOsPrinterImpl>>() {

    /**
     * The companion object allows to identify the connection schemes treated as local printer connections
     */
    private companion object {

        /**
         * `LOCAL_PRINTER_PROTOCOLS` the USB, parallel, and serial URI schemes recognized as local connections
         */
        val LOCAL_PRINTER_PROTOCOLS = hashSetOf("usb", "parallel", "serial")

    }

    /**
     * Method used to retrieve and map the destinations returned by the default CUPS server
     *
     * An empty list is returned when CUPS reports no destinations or returns a null destination pointer
     * Native error details are not inspected, so an empty enumeration and a failed read are not distinguished
     * Destination names include an instance suffix when present, and unavailable text uses [UNKNOWN]
     * An unavailable description uses the destination name without its instance suffix
     *
     * @return the current printer snapshots as [List] of [MacOsPrinterImpl]
     */
    override fun mapFromNative(): List<MacOsPrinterImpl> {
        return retrieveCupsDest { count, destinations ->
            if (count == 0 || destinations == null)
                return@retrieveCupsDest emptyList()

            List(count) { index ->
                val destination = destinations[index]

                val name = destination.name?.toKString() ?: UNKNOWN
                val instance = destination.instance?.toKString()
                val uri = destination.getOption(
                    key = "device-uri"
                )
                val reasons = destination.getOption(
                    key = "printer-state-reasons"
                )

                MacOsPrinterImpl(
                    name = if (instance == null)
                        name
                    else
                        "$name/$instance",
                    driverName = destination.getOption(
                        key = "printer-make-and-model"
                    ),
                    description = destination.getOption(
                        key = "printer-info",
                        default = name
                    ),
                    status = resolveStatus(
                        state = destination.getOption(
                            key = "printer-state"
                        ),
                        reasons = reasons
                    ),
                    statusReason = reasons,
                    isDefault = destination.is_default != 0,
                    isLocal = resolveIsLocal(
                        uri = uri,
                    ),
                    portName = uri
                )
            }
        }
    }

    /**
     * Method used to resolve the printer status from a CUPS state and its reason keywords
     *
     * An `offline` reason, after removing its severity suffix, takes precedence over the check delegated to [isInError]
     * Otherwise, states `3` and `4` map to idle and printing, while other values map to unknown
     * State `5` remains unknown unless one of the recognized reason checks matches
     *
     * @param state The reported printer state, or null when unavailable
     * @param reasons The comma-separated reason keywords or the unavailable-data marker
     *
     * @return the resolved status as [PrinterStatus]
     */
    @Resolver
    private fun resolveStatus(
        state: String?,
        reasons: String
    ): PrinterStatus {
        val tokens = reasons.split(',').map { it.trim() }
        val normalized = tokens.map { token ->
            token.removeSuffix("-error").removeSuffix("-warning").removeSuffix("-report")
        }

        return when {
            "offline" in normalized -> PrinterStatus.OFFLINE

            isInError(state, tokens) -> PrinterStatus.ERROR
            state == "3" -> PrinterStatus.IDLE
            state == "4" -> PrinterStatus.PRINTING

            else -> PrinterStatus.UNKNOWN
        }
    }

    /**
     * Method used to inspect CUPS reason tokens for an error condition
     *
     * The predicate body is unfinished and the subsequent expression references an out-of-scope `state` value
     * The current implementation cannot be compiled
     *
     * @param state The current state of the printer
     * @param tokens The printer reason keywords to inspect
     *
     * @return whether a reason indicates an error as [Boolean]
     */
    private fun isInError(
        state: String?,
        tokens: List<String>
    ): Boolean {
        return tokens.any { token ->
            if (state == "5") {
                token.isNotBlank() && token != "none" && token != UNKNOWN
                        && !token.endsWith("-warning") && !token.endsWith("-report")
            } else
                token.endsWith("-error")
        }
    }

    /**
     * Method used to check whether the device URI identifies a recognized local printer connection
     *
     * The scheme is matched without case sensitivity against [LOCAL_PRINTER_PROTOCOLS]
     * Unavailable or unrecognized schemes return false and do not establish that the printer is network-based
     *
     * @param uri The device URI or the unavailable-data marker
     *
     * @return whether the URI scheme is recognized as local as [Boolean]
     */
    @Resolver
    private fun resolveIsLocal(
        uri: String
    ): Boolean {
        val protocol = uri.substringBefore(':').lowercase()

        return LOCAL_PRINTER_PROTOCOLS.contains(protocol)
    }

    /**
     * Method used to retrieve the CUPS destination list, consume it, and release its native memory
     *
     * The callback receives borrowed destinations and must not release them or retain their pointers after returning
     * The destination list is released even when the callback throws, and its native error status is not inspected
     *
     * @param T The type of result produced by the callback
     * @param usage The operation receiving the destination count and the possibly null destination pointer
     *
     * @return the callback result as [T]
     */
    private fun <T> retrieveCupsDest(
        usage: (Int, CPointer<cups_dest_t>?) -> T
    ): T {
        return memScoped {
            val output = alloc<CPointerVar<cups_dest_t>>()
            output.value = null

            val count = cupsGetDests2(
                null,
                output.ptr
            )
            val destinations = output.value

            try {
                usage(count, destinations)
            } finally {
                cupsFreeDests(
                    count,
                    destinations
                )
            }
        }
    }

    /**
     * Method used to copy a CUPS destination option into a Kotlin string
     *
     * Missing options use [default], while present empty strings are preserved
     * The destination and its option storage remain owned by the caller
     *
     * @receiver The borrowed CUPS destination containing the options to read
     * @param key The option name to look up
     * @param default The value returned when the option is unavailable
     *
     * @return the copied option or its fallback as [String]
     */
    private fun cups_dest_t.getOption(
        key: String,
        default: String = UNKNOWN
    ): String {
        val optionValue = cupsGetOption(
            key,
            num_options,
            options
        ) ?: return default

        return optionValue.toKString()
    }

}