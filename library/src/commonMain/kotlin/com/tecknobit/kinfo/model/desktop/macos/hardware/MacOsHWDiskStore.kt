package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.storage.HWDiskStore

/**
 * The `MacOsHWDiskStore` interface defines the contract to expose macOS disk information
 *
 * The inherited [HWDiskStore] contract provides device identity, capacity, I/O statistics, and associated partitions
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsHWDiskStore : HWDiskStore