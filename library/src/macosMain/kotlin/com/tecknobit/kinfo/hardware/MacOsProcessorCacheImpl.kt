package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.CacheType
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorCache

data class MacOsProcessorCacheImpl(
    override val level: Byte,
    override val associativity: Byte,
    override val lineSize: Short,
    override val cacheSize: Int,
    override val type: CacheType
) : MacOsProcessorCache
