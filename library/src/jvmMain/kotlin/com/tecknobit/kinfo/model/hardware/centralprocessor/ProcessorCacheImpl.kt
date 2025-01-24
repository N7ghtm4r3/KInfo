package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.CacheType
import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.ProcessorCache

/**
 * `ProcessorCacheImpl` is an implementation of the `ProcessorCache` interface that represents
 * a processor's cache with its associated properties such as level, associativity, line size,
 * cache size, and type.
 *
 * This implementation provides the specific values for each of these properties for a given cache.
 *
 * @author N7ghtm4r3
 *
 * @see ProcessorCache
 */
class ProcessorCacheImpl(

    /**
     * `level` the level of the processor cache (e.g., L1, L2, L3)
     */
    override val level: Byte,

    /**
     * `associativity` the associativity of the cache (e.g., direct-mapped, 2-way, 4-way)
     */
    override val associativity: Byte,

    /**
     * `lineSize` the size of a cache line in bytes
     */
    override val lineSize: Short,

    /**
     * `cacheSize` the total size of the cache in bytes
     */
    override val cacheSize: Int,

    /**
     * `type` the type of the cache (e.g., unified, instruction, data, or trace)
     */
    override val type: CacheType

) : ProcessorCache
