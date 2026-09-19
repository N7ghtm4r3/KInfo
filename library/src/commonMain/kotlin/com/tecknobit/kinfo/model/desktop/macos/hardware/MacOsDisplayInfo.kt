package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.display.DisplayInfo

/**
 * The `MacOsDisplayInfo` interface defines the contract to access decoded display identification information on macOS
 *
 * The inherited [DisplayInfo.edid] value may contain native data or data synthesized from display attributes
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsDisplayInfo : DisplayInfo