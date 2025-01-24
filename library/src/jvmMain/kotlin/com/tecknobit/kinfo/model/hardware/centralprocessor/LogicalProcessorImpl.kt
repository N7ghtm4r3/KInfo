package com.tecknobit.kinfo.model.hardware.centralprocessor

import com.tecknobit.kinfo.model.desktop.hardware.centralprocessor.LogicalProcessor

class LogicalProcessorImpl(
    override val processorNumber: Int,
    override val physicalProcessorNumber: Int,
    override val physicalPackageNumber: Int,
    override val numaNode: Int,
    override val processorGroup: Int
) : LogicalProcessor