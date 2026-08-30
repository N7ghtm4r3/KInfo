@file:OptIn(ExperimentalForeignApi::class)

package com.tecknobit.kinfo.operatingsystem

import com.tecknobit.kinfo.annotations.Resolver
import com.tecknobit.kinfo.model.desktop.macos.operatingsystem.MacOsFileStore
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.Foundation.*
import platform.osx.*

/**
 * The `MacOsFileStoreImpl` class is useful to provide information about a macOS file store
 *
 * @property statfs The native file system statistics used to retrieve the file store details
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsFileStore
 *
 * @since 1.1.0
 */
data class MacOsFileStoreImpl(
    private val statfs: statfs
) : MacOsFileStore {

    /**
     * `mountPoint` the path where the file store is mounted
     */
    private val mountPoint = statfs.f_mntonname.toKString()

    /**
     * `blockSize` the fundamental file system block size in bytes
     */
    private val blockSize: Long = statfs.f_bsize.toLong()

    /**
     * `volumeUrl` the file URL used to retrieve the volume resource values
     */
    private val volumeUrl = NSURL.fileURLWithPath(
        path = mountPoint
    )

    /**
     * `volumeResources` the Foundation resource values associated with the volume
     */
    private val volumeResources = volumeUrl.resourceValuesForKeys(
        keys = listOf(
            NSURLVolumeLocalizedNameKey,
            NSURLVolumeNameKey,
            NSURLVolumeLocalizedFormatDescriptionKey,
            NSURLVolumeUUIDStringKey
        ),
        error = null
    )

    /**
     * `name` the localized name of the file store
     */
    override val name: String
        get() = volumeResources.resolveValueForKey(
            key = NSURLVolumeLocalizedNameKey
        )

    /**
     * `volume` the native source from which the file store is mounted
     */
    override val volume: String
        get() = statfs.f_mntfromname.toKString()

    /**
     * `label` the name assigned to the volume
     */
    override val label: String
        get() = volumeResources.resolveValueForKey(
            key = NSURLVolumeNameKey
        )

    /**
     * `logicalVolume` the empty logical volume identifier because macOS `statfs` exposes the mounted source through
     * `f_mntfromname` without providing a distinct `LVM`-style alias
     */
    override val logicalVolume: String = ""

    /**
     * `mount` the path where the file store is mounted
     */
    override val mount: String = mountPoint

    /**
     * `description` the localized description of the volume format
     */
    override val description: String
        get() = volumeResources.resolveValueForKey(
            key = NSURLVolumeLocalizedFormatDescriptionKey
        )

    /**
     * `type` the native file system type name
     */
    override val type: String
        get() = statfs.f_fstypename.toKString()

    /**
     * `options` the normalized options enabled by the native mount flags
     */
    override val options: String
        get() = resolveOptions(
            flags = statfs.f_flags
        )

    /**
     * `uuid` the persistent identifier of the volume
     */
    override val uuid: String
        get() = volumeResources.resolveValueForKey(
            key = NSURLVolumeUUIDStringKey
        )

    /**
     * `freeSpace` the free space available on the file store in bytes
     */
    override val freeSpace: Long
        get() = statfs.f_bfree.toLong() * blockSize

    /**
     * `usableSpace` the space available to non-privileged users in bytes
     */
    override val usableSpace: Long
        get() = statfs.f_bavail.toLong() * blockSize

    /**
     * `totalSpace` the total capacity of the file store in bytes
     */
    override val totalSpace: Long
        get() = statfs.f_blocks.toLong() * blockSize

    /**
     * `freeInodes` the number of free file nodes
     */
    override val freeInodes: Long
        get() = statfs.f_ffree.toLong()

    /**
     * `totalInodes` the total number of file nodes
     */
    override val totalInodes: Long
        get() = statfs.f_files.toLong()

    /**
     * `updateAttributes` whether updating the file store attributes is supported
     *
     * This value is `false` because this implementation does not provide an operation to reload the native file store
     * statistics and Foundation resource values
     */
    override val updateAttributes: Boolean = false

    /**
     * Method used to resolve a Foundation volume resource value
     *
     * @receiver The volume resource values from which to resolve the requested value
     * @param key The Foundation resource key associated with the value
     *
     * @return the resolved resource value as [String]
     */
    @Resolver
    private fun Map<Any?, *>?.resolveValueForKey(
        key: String?
    ): String {
        if (this == null || key == null)
            return ""

        return get(key).toString()
    }

    /**
     * Method used to normalize the enabled native mount flags
     *
     * @param flags The native mount flags to normalize
     *
     * @return the normalized mount options as [String]
     */
    @Resolver
    private fun resolveOptions(
        flags: UInt
    ): String {
        val normalizedFlags = buildList {
            if (flags and MNT_RDONLY.toUInt() != 0u)
                add("ro")
            if (flags and MNT_SYNCHRONOUS.toUInt() != 0u)
                add("synchronous")
            if (flags and MNT_NOEXEC.toUInt() != 0u)
                add("noexec")
            if (flags and MNT_NOSUID.toUInt() != 0u)
                add("nosuid")
            if (flags and MNT_NODEV.toUInt() != 0u)
                add("nodev")
            if (flags and MNT_LOCAL.toUInt() != 0u)
                add("local")
            if (flags and MNT_JOURNALED.toUInt() != 0u)
                add("journaled")
            if (flags and MNT_DONTBROWSE.toUInt() != 0u)
                add("nobrowse")
        }

        return normalizedFlags.joinToString(",")
    }

}
