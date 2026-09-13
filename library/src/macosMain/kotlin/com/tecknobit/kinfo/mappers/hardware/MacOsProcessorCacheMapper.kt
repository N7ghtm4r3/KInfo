package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsProcessorCacheImpl
import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.CacheType
import com.tecknobit.kinfo.utils.queryIntSysCtlByName
import com.tecknobit.kinfo.utils.queryULongSysCtlByName

/**
 * The `MacOsProcessorCacheMapper` class is useful to map macOS system control values to processor caches
 *
 * Only caches with a positive mapped size are included, using the global cache line size
 * Associativity is zero when its query fails and is always unknown for the mapped L3 cache
 * The global cache size queries do not distinguish performance levels
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
class MacOsProcessorCacheMapper : MacOsHardwareMapper<List<MacOsProcessorCacheImpl>>() {

    /**
     * `lineSize` the global cache line size in bytes, refreshed for each mapping operation
     */
    private var lineSize: Short = 0

    /**
     * Method used to load L1 instruction, L1 data, L2, and L3 caches with positive mapped sizes
     *
     * @return the available cache descriptions in query order as [List] of [MacOsProcessorCacheImpl]
     */
    override fun mapFromNative(): List<MacOsProcessorCacheImpl> {
        lineSize = loadLineSize()

        return buildList {
            addCache {
                loadL1InstructionCache()
            }

            addCache {
                loadL1DataCache()
            }

            addCache {
                loadL2Cache()
            }

            addCache {
                loadL3Cache()
            }
        }
    }

    /**
     * Method used to load a cache and append it only when its mapped size is positive
     *
     * @receiver The destination list of processor caches
     * @param loadCache The operation used to load the cache description
     */
    private inline fun MutableList<MacOsProcessorCacheImpl>.addCache(
        crossinline loadCache: () -> MacOsProcessorCacheImpl
    ) {
        val cache = loadCache()
        if (cache.cacheSize > 0)
            add(cache)
    }

    /**
     * Method used to query `hw.cachelinesize` and convert its byte count to [Short]
     *
     * @return the converted line size, or zero when the query fails, as [Short]
     */
    @Loader
    private fun loadLineSize(): Short {
        val queriedLineSize = queryULongSysCtlByName(
            name = "hw.cachelinesize"
        )

        return queriedLineSize?.toShort() ?: 0
    }

    /**
     * Method used to load the L1 instruction cache from `hw.l1icachesize`
     *
     * The size defaults to zero when the query fails and is converted to [Int] without range validation
     *
     * @return the cache description with the global line size and queried L1 associativity as [MacOsProcessorCacheImpl]
     */
    @Loader
    private fun loadL1InstructionCache(): MacOsProcessorCacheImpl {
        val cacheSize = queryULongSysCtlByName(
            name = "hw.l1icachesize",
            default = 0u
        )!!

        return MacOsProcessorCacheImpl(
            level = 1,
            associativity = loadL1Associativity(),
            lineSize = lineSize,
            cacheSize = cacheSize.toInt(),
            type = CacheType.INSTRUCTION
        )
    }

    /**
     * Method used to load the L1 data cache from `hw.l1dcachesize`
     *
     * The size defaults to zero when the query fails and is converted to [Int] without range validation
     *
     * @return the cache description with the global line size and queried L1 associativity as [MacOsProcessorCacheImpl]
     */
    @Loader
    private fun loadL1DataCache(): MacOsProcessorCacheImpl {
        val cacheSize = queryULongSysCtlByName(
            name = "hw.l1dcachesize",
            default = 0u
        )!!

        return MacOsProcessorCacheImpl(
            level = 1,
            associativity = loadL1Associativity(),
            lineSize = lineSize,
            cacheSize = cacheSize.toInt(),
            type = CacheType.DATA
        )
    }

    /**
     * Method used to query `machdep.cpu.cache.L1_associativity` for both L1 cache types
     *
     * @return the associativity converted to [Byte], or zero when unknown, as [Byte]
     */
    @Loader
    private fun loadL1Associativity(): Byte {
        val associativity = queryIntSysCtlByName(
            name = "machdep.cpu.cache.L1_associativity",
            default = 0
        )!!

        return associativity.toByte()
    }

    /**
     * Method used to load the unified L2 cache from `hw.l2cachesize`
     *
     * The size defaults to zero when the query fails and is converted to [Int] without range validation
     *
     * @return the cache description with the global line size and queried L2 associativity as [MacOsProcessorCacheImpl]
     */
    @Loader
    private fun loadL2Cache(): MacOsProcessorCacheImpl {
        val cacheSize = queryULongSysCtlByName(
            name = "hw.l2cachesize",
            default = 0u
        )!!

        return MacOsProcessorCacheImpl(
            level = 2,
            associativity = loadL2Associativity(),
            lineSize = lineSize,
            cacheSize = cacheSize.toInt(),
            type = CacheType.UNIFIED
        )
    }

    /**
     * Method used to query `machdep.cpu.cache.L2_associativity`
     *
     * @return the associativity converted to [Byte], or zero when unknown, as [Byte]
     */
    @Loader
    private fun loadL2Associativity(): Byte {
        val associativity = queryIntSysCtlByName(
            name = "machdep.cpu.cache.L2_associativity",
            default = 0
        )!!

        return associativity.toByte()
    }

    /**
     * Method used to load the unified L3 cache from `hw.l3cachesize`
     *
     * The size defaults to zero when the query fails and is converted to [Int] without range validation
     * Associativity is set to zero because this mapper does not retrieve it
     *
     * @return the cache description with the global line size and unknown associativity as [MacOsProcessorCacheImpl]
     */
    @Loader
    private fun loadL3Cache(): MacOsProcessorCacheImpl {
        val cacheSize = queryULongSysCtlByName(
            name = "hw.l3cachesize",
            default = 0u
        )!!

        return MacOsProcessorCacheImpl(
            level = 3,
            associativity = 0,
            lineSize = lineSize,
            cacheSize = cacheSize.toInt(),
            type = CacheType.UNIFIED
        )
    }

}