package com.tecknobit.kinfo.model.cpu

import com.tecknobit.kinfo.model.web.cpu.CPU

class CPUImpl(
    private val parsedCPU: com.tecknobit.kinfo.model.CPU
) : CPU {

    override val architecture: String
        get() = parsedCPU.architecture.safeValue()

}