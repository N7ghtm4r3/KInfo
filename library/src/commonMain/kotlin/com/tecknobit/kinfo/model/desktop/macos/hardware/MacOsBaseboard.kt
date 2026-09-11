package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.computersystem.Baseboard

/**
 * The `MacOsBaseboard` interface defines the contract to expose macOS baseboard information
 *
 * Native registry lookups may leave unavailable values empty and use a hardware target identifier
 * when a board identifier is unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsBaseboard : Baseboard