package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsHWPartition

/**
 * The `MacOsHWPartitionImpl` class is useful to store a macOS partition or logical media description
 *
 * APFS volumes and exposed snapshots can share the reported capacity of their container
 *
 * @property identification The BSD name identifying the media entry
 * @property name The native media name, with the BSD name used when the description entry is absent
 * @property type The filesystem kind, or an empty string when no volume filesystem is reported
 * @property uuid The media UUID, or an empty string when the registry value is unavailable
 * @property size The media capacity in bytes, which does not represent the space occupied by an APFS volume
 * @property major The major device number reported by IOKit
 * @property minor The minor device number reported by IOKit
 * @property mountPoint The mounted volume path, or `unknown` when no path is available
 * @property label The volume label, or an empty string when unavailable
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHWPartition
 *
 * @since 1.1.0
 */
data class MacOsHWPartitionImpl(
    override val identification: String,
    override val name: String,
    override val type: String,
    override val uuid: String,
    override val size: Long,
    override val major: Int,
    override val minor: Int,
    override val mountPoint: String,
    override val label: String
) : MacOsHWPartition
