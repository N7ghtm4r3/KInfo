package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsDisplay
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsDisplayInfo

/**
 * The `MacOsDisplayImpl` class is useful to hold macOS display identification and connection information
 *
 * @property displayInfo The decoded display identification information
 * @property displayPort The supplied system device port identifier
 * @property outputName The optional output name supplied by the caller
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsDisplay
 *
 * @since 1.1.0
 */
data class MacOsDisplayImpl(
    override val displayInfo: MacOsDisplayInfo,
    override val displayPort: String,
    override val outputName: String?
) : MacOsDisplay {

    /**
     * Method used to compare display identification, connection information, and the legacy [edid] contents
     *
     * @param other The object to compare with this display
     *
     * @return whether both objects have the same type and property values as [Boolean]
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsDisplayImpl

        if (!edid.contentEquals(other.edid)) return false
        if (displayInfo != other.displayInfo) return false
        if (displayPort != other.displayPort) return false
        if (outputName != other.outputName) return false

        return true
    }

    /**
     * Method used to compute a hash from display identification, connection information, and the legacy [edid] contents
     *
     * @return the hash of the display as [Int]
     */
    override fun hashCode(): Int {
        var result = edid.contentHashCode()
        result = 31 * result + displayInfo.hashCode()
        result = 31 * result + displayPort.hashCode()
        result = 31 * result + (outputName?.hashCode() ?: 0)
        return result
    }

    /**
     * `edid` the empty legacy identification buffer retained for compatibility
     *
     * The identification bytes are exposed by [MacOsDisplayInfo.edid] through [displayInfo]
     */
    @Deprecated(
        message = "Deprecated since 1.1.0",
        replaceWith = ReplaceWith(
            "DisplayInfo.edid"
        )
    )
    override val edid: ByteArray = byteArrayOf()
}
