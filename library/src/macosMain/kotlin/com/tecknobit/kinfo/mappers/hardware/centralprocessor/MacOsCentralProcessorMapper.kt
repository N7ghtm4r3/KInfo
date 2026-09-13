package com.tecknobit.kinfo.mappers.hardware.centralprocessor

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsCentralProcessorImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsLogicalProcessor
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsPhysicalProcessor
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorCache
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsProcessorIdentifier

class MacOsCentralProcessorMapper : MacOsHardwareMapper<MacOsCentralProcessorImpl>() {

    override fun mapFromNative(): MacOsCentralProcessorImpl {
        return MacOsCentralProcessorImpl(
            processorIdentifier = loadProcessorIdentifier(),
            maxFreq = TODO(),
            currentFreq = TODO(),
            logicalProcessors = loadLogicalProcessor(),
            physicalProcessors = loadPhysicalProcessor(),
            processorCaches = loadProcessorCache(),
            featureFlags = TODO(),
            systemCpuLoadTicks = TODO(),
            processorCpuLoadTicks = TODO(),
            physicalPackageCount = TODO(),
            contextSwitches = TODO(),
            interrupts = TODO()
        )
    }

    /**
     * Method used to request the macOS processor identification information from its native mapper
     *
     * @return the mapped processor identification information as [MacOsProcessorIdentifier]
     */
    @Loader
    private fun loadProcessorIdentifier(): MacOsProcessorIdentifier {
        val macOsProcessorIdentifierMapper = MacOsProcessorIdentifierMapper()

        return macOsProcessorIdentifierMapper.mapFromNative()
    }

    /**
     * Method used to load macOS logical processor descriptions through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsLogicalProcessorMapper]
     *
     * @return the mapped logical processor descriptions as [List] of [MacOsLogicalProcessor]
     */
    @Loader
    private fun loadLogicalProcessor(): List<MacOsLogicalProcessor> {
        val macOsLogicalProcessorMapper = MacOsLogicalProcessorMapper()

        return macOsLogicalProcessorMapper.mapFromNative()
    }

    /**
     * Method used to load macOS physical processor descriptions through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsPhysicalProcessorMapper]
     *
     * Each call creates a mapper and reads the native counts and accepted CPU registry entries again
     *
     * @return the mapped physical processor descriptions as [List] of [MacOsPhysicalProcessor]
     * @throws IllegalStateException If the native service matching query fails
     * @throws ArithmeticException If an accepted entry is mapped with a zero package count or zero cores per package
     */
    @Loader
    private fun loadPhysicalProcessor(): List<MacOsPhysicalProcessor> {
        val macOsPhysicalProcessorMapper = MacOsPhysicalProcessorMapper()

        return macOsPhysicalProcessorMapper.mapFromNative()
    }

    /**
     * Method used to load macOS processor caches with positive mapped sizes through [com.tecknobit.kinfo.mappers.hardware.centralprocessor.MacOsProcessorCacheMapper]
     *
     * @return the mapped cache descriptions as [List] of [MacOsProcessorCache]
     */
    @Loader
    private fun loadProcessorCache(): List<MacOsProcessorCache> {
        val macOsProcessorCacheMapper = MacOsProcessorCacheMapper()

        return macOsProcessorCacheMapper.mapFromNative()
    }

}