package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.PhysicalProcessor

/**
 * The `MacOsPhysicalProcessor` interface defines the contract to describe a physical CPU core on macOS
 *
 * The package number, core number, performance class, and platform-specific description are inherited
 * from [PhysicalProcessor]
 * The compatibility description may be shared by multiple cores and is not a unique hardware identifier
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsPhysicalProcessor : PhysicalProcessor