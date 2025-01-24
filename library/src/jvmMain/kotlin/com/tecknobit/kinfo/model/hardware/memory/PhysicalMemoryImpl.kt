package com.tecknobit.kinfo.model.hardware.memory

import com.tecknobit.kinfo.model.desktop.hardware.memory.PhysicalMemory

class PhysicalMemoryImpl(
    override val bankLabel: String,
    override val capacity: Long,
    override val clockSpeed: Long,
    override val manufacturer: String,
    override val memoryType: String,
    override val partNumber: String,
    override val serialNumber: String
) : PhysicalMemory