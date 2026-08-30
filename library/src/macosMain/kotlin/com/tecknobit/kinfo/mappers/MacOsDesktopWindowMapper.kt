@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.mappers

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.operatingsystem.MacOsDesktopWindowImpl
import com.tecknobit.kinfo.utils.toNSString
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.interpretObjCPointer
import kotlinx.cinterop.rawValue
import platform.AppKit.NSRunningApplication
import platform.CoreGraphics.kCGWindowIsOnscreen
import platform.CoreGraphics.kCGWindowNumber
import platform.CoreGraphics.kCGWindowOwnerName
import platform.CoreGraphics.kCGWindowOwnerPID
import platform.Foundation.NSDictionary
import platform.Foundation.NSNumber

/**
 * The `MacOsDesktopWindowMapper` class is useful to map a native macOS window entry to a KInfo model
 *
 * @property pointer The pointer to the native window dictionary
 * @property index The position of the window in the native window list
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 *
 * @since 1.1.0
 */
class MacOsDesktopWindowMapper(
    private val pointer: COpaquePointer?,
    private val index: Long,
) : NativeMapper<MacOsDesktopWindowImpl>() {

    /**
     * Method used to map the native window dictionary to its [MacOsDesktopWindowImpl] model
     *
     * @return the mapped desktop window as [MacOsDesktopWindowImpl]
     */
    override fun mapFromNative(): MacOsDesktopWindowImpl {
        val dictionary = interpretObjCPointer<NSDictionary>(pointer.rawValue)

        val windowId = dictionary.objectForKey(kCGWindowNumber.toNSString()) as? NSNumber
        val title = dictionary.objectForKey(kCGWindowOwnerName.toNSString()) as? String
        val owningProcessIdRaw = dictionary.objectForKey(kCGWindowOwnerPID.toNSString()) as? NSNumber
        val owningProcessId = owningProcessIdRaw?.longValue ?: -1
        val visible = dictionary.objectForKey(kCGWindowIsOnscreen.toNSString()) as? NSNumber

        return MacOsDesktopWindowImpl(
            windowId = windowId?.longValue ?: -1,
            title = title.orEmpty(),
            command = resolveCommandFor(
                pid = owningProcessId
            ),
            owningProcessId = owningProcessId,
            order = index.toInt(),
            visible = visible?.boolValue ?: false
        )
    }

    /**
     * Method used to resolve the executable command associated with a process identifier
     *
     * @param pid The identifier of the process owning the desktop window
     *
     * @return the executable command associated with the process as [String]
     */
    @Resolver
    private fun resolveCommandFor(
        pid: Long
    ): String {
        val nsRunningApplication = NSRunningApplication.runningApplicationWithProcessIdentifier(
            pid = pid.toInt()
        )

        val executableURL = nsRunningApplication?.executableURL
        return executableURL?.path.orEmpty()
    }

}
