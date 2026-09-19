package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.storage.HWPartition

/**
 * The `MacOsHWPartition` interface defines the contract to expose macOS partition and logical media information
 *
 * The inherited [HWPartition] contract provides media identity, filesystem details, capacity, and mount information
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsHWPartition : HWPartition