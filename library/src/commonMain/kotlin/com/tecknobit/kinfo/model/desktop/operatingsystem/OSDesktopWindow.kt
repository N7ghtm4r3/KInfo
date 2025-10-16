package com.tecknobit.kinfo.model.desktop.operatingsystem

/**
 * `OSDesktopWindow` Represents a desktop window on the operating system.
 * Provides details about the window's properties, such as its ID, title, associated command,
 * owning process ID, order in the stack, and visibility
 *
 * @author N7ghtm4r3
 */
interface OSDesktopWindow {

    /**
     * `windowId` The unique identifier for the window
     */
    val windowId: Long

    /**
     * `title` The title of the window
     */
    val title: String

    /**
     * `command` The command or executable associated with the window
     */
    val command: String

    /**
     * `owningProcessId` The process ID of the application owning the window
     */
    val owningProcessId: Long

    /**
     * `order` The position of the window in the stacking order (e.g., topmost window)
     */
    val order: Int

    /**
     * `visible` A boolean indicating whether the window is currently visible
     */
    val visible: Boolean
}
