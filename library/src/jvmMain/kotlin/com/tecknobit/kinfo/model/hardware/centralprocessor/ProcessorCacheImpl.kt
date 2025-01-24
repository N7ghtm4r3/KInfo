package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.CacheType
import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.ProcessorCache

class ProcessorCacheImpl(
    override val level: Byte,
    override val associativity: Byte,
    override val lineSize: Short,
    override val cacheSize: Int,
    override val type: CacheType
) : ProcessorCache