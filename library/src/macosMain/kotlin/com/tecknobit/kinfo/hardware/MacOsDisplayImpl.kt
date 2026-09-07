package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.display.DisplayInfo
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsDisplay

data class MacOsDisplayImpl(
    @Deprecated("Deprecated since 1.1.0", replaceWith = ReplaceWith("DisplayInfo.edid"))
    override val edid: ByteArray,
    override val displayInfo: DisplayInfo,
    override val displayPort: String,
    override val outputName: String?
) : MacOsDisplay {
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

    override fun hashCode(): Int {
        var result = edid.contentHashCode()
        result = 31 * result + displayInfo.hashCode()
        result = 31 * result + displayPort.hashCode()
        result = 31 * result + (outputName?.hashCode() ?: 0)
        return result
    }
}
