package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.PhysicalProcessor

class PhysicalProcessorImpl(
    override val physicalPackageNumber: Int,
    override val physicalProcessorNumber: Int,
    override val efficiency: Int,
    override val idString: String
) : PhysicalProcessor