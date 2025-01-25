package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSDesktopWindow

/**
 * `OSDesktopWindowImpl` Implementation of the `OSDesktopWindow` interface.
 * Represents a specific desktop window, providing concrete details such as its ID,
 * title, command, owning process ID, order in the stack, and visibility.
 *
 * @param windowId The unique identifier for the window.
 * @param title The title of the window.
 * @param command The command or executable associated with the window.
 * @param owningProcessId The process ID of the application owning the window.
 * @param order The position of the window in the stacking order (e.g., topmost window).
 * @param visible A boolean indicating whether the window is currently visible.
 *
 * @author N7ghtm4r3
 *
 * @see OSDesktopWindow
 */
class OSDesktopWindowImpl(
    override val windowId: Long,
    override val title: String,
    override val command: String,
    override val owningProcessId: Long,
    override val order: Int,
    override val visible: Boolean
) : OSDesktopWindow
