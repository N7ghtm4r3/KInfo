package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsDesktopWindow

/**
 * The `MacOsDesktopWindowImpl` class is useful to provide the details of a desktop window on macOS
 *
 * @property windowId The unique identifier of the desktop window
 * @property title The title associated with the desktop window
 * @property command The command associated with the process owning the desktop window
 * @property owningProcessId The identifier of the process owning the desktop window
 * @property order The position of the desktop window in the stacking order
 * @property visible Whether the desktop window is currently visible
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsDesktopWindow
 *
 * @since 1.1.0
 */
data class MacOsDesktopWindowImpl(
    override val windowId: Long,
    override val title: String,
    override val command: String,
    override val owningProcessId: Long,
    override val order: Int,
    override val visible: Boolean
) : MacOsDesktopWindow
