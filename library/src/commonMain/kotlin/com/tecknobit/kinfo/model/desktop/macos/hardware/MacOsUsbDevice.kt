package com.tecknobit.kinfo.model.desktop.macos.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.UsbDevice

/**
 * The `MacOsUsbDevice` interface defines the contract to expose macOS USB device information through [UsbDevice]
 *
 * The native mapping uses a registry entry identifier as the device identity, which is not persistent across reboots
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
interface MacOsUsbDevice : UsbDevice