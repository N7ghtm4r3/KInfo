package com.tecknobit.kinfo.model.desktop.hardware.centralprocessor

/**
 * `ProcessorCache` interface represents a processor's cache with its properties like level, associativity,
 * line size, cache size, and cache type.
 *
 * This interface provides the details about the cache hierarchy and its characteristics, which are
 * useful for system-level performance analysis and optimization.
 *
 * @author N7ghtm4r3
 */
interface ProcessorCache {

    /**
     * `level` the level of the processor cache (e.g., L1, L2, L3)
     */
    val level: Byte

    /**
     * `associativity` the associativity of the cache (e.g., direct-mapped, 2-way, 4-way)
     */
    val associativity: Byte

    /**
     * `lineSize` the size of a cache line in bytes
     */
    val lineSize: Short

    /**
     * `cacheSize` the total size of the cache in bytes
     */
    val cacheSize: Int

    /**
     * `type` the type of the cache (e.g., unified, instruction, data, or trace)
     */
    val type: CacheType
}

/**
 * `CacheType` different types of caches used by processors
 */
enum class CacheType {

    /**
     * Unified cache, used for both instructions and data
     */
    UNIFIED,

    /**
     * Instruction cache, used for storing instructions only
     */
    INSTRUCTION,

    /**
     * Data cache, used for storing data only
     */
    DATA,

    /**
     * Trace cache, used for storing instruction traces
     */
    TRACE;

    /**
     * Provides a string representation of the cache type where the first letter is capitalized
     * and the rest are in lowercase.
     *
     * @return the string representation of the cache type as [String]
     */
    override fun toString(): String {
        return name.substring(0, 1) + name.substring(1).lowercase()
    }

}

