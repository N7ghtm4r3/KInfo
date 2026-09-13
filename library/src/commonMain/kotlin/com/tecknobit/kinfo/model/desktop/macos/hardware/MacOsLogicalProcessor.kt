package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.LogicalProcessor

/**
 * The `MacOsLogicalProcessor` interface defines the contract to expose a macOS logical processor description
 *
 * Inherits the logical processor, physical core, package, NUMA node, and processor group properties
 * from [LogicalProcessor]
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsLogicalProcessor : LogicalProcessor