package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.CacheType
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorCache

/**
 * The `MacOsProcessorCacheImpl` class is useful to represent a macOS processor cache
 *
 * @property level The cache hierarchy level
 * @property associativity The number of cache ways, or zero when unknown
 * @property lineSize The cache line size in bytes, or zero when unknown
 * @property cacheSize The cache capacity in bytes
 * @property type The kind of content stored in the cache
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsProcessorCache
 *
 * @since 1.1.0
 */
data class MacOsProcessorCacheImpl(
    override val level: Byte,
    override val associativity: Byte,
    override val lineSize: Short,
    override val cacheSize: Int,
    override val type: CacheType
) : MacOsProcessorCache
