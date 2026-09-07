package com.tecknobit.kinfo.hardware

import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.LogicalProcessor
import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.PhysicalProcessor
import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.ProcessorCache
import com.tecknobit.kinfo.model.desktop.common.hardware.centralprocessor.ProcessorIdentifier
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsCentralProcessor

data class MacOsCentralProcessorImpl(
    override val processorIdentifier: ProcessorIdentifier,
    override val maxFreq: Long,
    override val currentFreq: LongArray,
    override val logicalProcessors: List<LogicalProcessor>,
    override val physicalProcessors: List<PhysicalProcessor>,
    override val processorCaches: List<ProcessorCache>,
    override val featureFlags: List<String>,
    override val systemCpuLoadTicks: LongArray,
    override val processorCpuLoadTicks: Array<LongArray>,
    override val logicalProcessorCount: Int,
    override val physicalProcessorCount: Int,
    override val physicalPackageCount: Int,
    override val contextSwitches: Long,
    override val interrupts: Long
) : MacOsCentralProcessor {

    override fun getSystemCpuLoadBetweenTicks(
        oldTickets: LongArray
    ): Double {
        TODO("Not yet implemented")
    }

    override fun getSystemLoadAverage(
        nelem: Int
    ): DoubleArray {
        TODO("Not yet implemented")
    }

    override fun getSystemCpuLoad(
        delay: Long
    ): Double {
        TODO("Not yet implemented")
    }

    override fun getProcessorCpuLoadBetweenTicks(
        oldTickets: Array<LongArray>
    ): DoubleArray {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MacOsCentralProcessorImpl

        if (maxFreq != other.maxFreq) return false
        if (logicalProcessorCount != other.logicalProcessorCount) return false
        if (physicalProcessorCount != other.physicalProcessorCount) return false
        if (physicalPackageCount != other.physicalPackageCount) return false
        if (contextSwitches != other.contextSwitches) return false
        if (interrupts != other.interrupts) return false
        if (processorIdentifier != other.processorIdentifier) return false
        if (!currentFreq.contentEquals(other.currentFreq)) return false
        if (logicalProcessors != other.logicalProcessors) return false
        if (physicalProcessors != other.physicalProcessors) return false
        if (processorCaches != other.processorCaches) return false
        if (featureFlags != other.featureFlags) return false
        if (!systemCpuLoadTicks.contentEquals(other.systemCpuLoadTicks)) return false
        if (!processorCpuLoadTicks.contentDeepEquals(other.processorCpuLoadTicks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = maxFreq.hashCode()
        result = 31 * result + logicalProcessorCount
        result = 31 * result + physicalProcessorCount
        result = 31 * result + physicalPackageCount
        result = 31 * result + contextSwitches.hashCode()
        result = 31 * result + interrupts.hashCode()
        result = 31 * result + processorIdentifier.hashCode()
        result = 31 * result + currentFreq.contentHashCode()
        result = 31 * result + logicalProcessors.hashCode()
        result = 31 * result + physicalProcessors.hashCode()
        result = 31 * result + processorCaches.hashCode()
        result = 31 * result + featureFlags.hashCode()
        result = 31 * result + systemCpuLoadTicks.contentHashCode()
        result = 31 * result + processorCpuLoadTicks.contentDeepHashCode()
        return result
    }
}
