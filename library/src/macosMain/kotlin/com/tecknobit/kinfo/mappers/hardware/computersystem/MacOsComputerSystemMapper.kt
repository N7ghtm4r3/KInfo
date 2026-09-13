@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.tecknobit.kinfo.mappers.hardware.computersystem

import com.tecknobit.kinfo.annotations.Loader
import com.tecknobit.kinfo.hardware.MacOsComputerSystemImpl
import com.tecknobit.kinfo.mappers.hardware.MacOsHardwareMapper
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsBaseboard
import com.tecknobit.kinfo.model.desktop.macos.hardware.MacOsFirmware
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.experimental.ExperimentalNativeApi

class MacOsComputerSystemMapper : MacOsHardwareMapper<MacOsComputerSystemImpl>() {

    override fun mapFromNative(): MacOsComputerSystemImpl {
        return MacOsComputerSystemImpl(
            family = TODO(),
            manufacturer = TODO(),
            versionInfo = TODO(),
            fileSystem = TODO(),
            internetProtocolStats = TODO(),
            processId = TODO(),
            currentProcess = TODO(),
            processCount = TODO(),
            threadId = TODO(),
            currentThread = TODO(),
            threadCount = TODO(),
            bitness = TODO(),
            systemUptime = TODO(),
            systemBootTime = TODO(),
            isElevated = TODO(),
            networkParams = TODO(),
            services = TODO(),
            sessions = TODO(),
            cgroupInfo = TODO(),
            baseboard = loadBaseboard(),
            firmware = loadFirmware()
        )
    }

    /**
     * Method used to load the current macOS baseboard information through [MacOsBaseboardMapper]
     *
     * @return the mapped baseboard information as [MacOsBaseboard]
     */
    @Loader
    private fun loadBaseboard(): MacOsBaseboard {
        val macOsBaseboardMapper = MacOsBaseboardMapper()

        return macOsBaseboardMapper.mapFromNative()
    }

    /**
     * Method used to load the current macOS firmware information from native registry properties
     *
     * @return the mapped firmware information as [MacOsFirmware]
     */
    @Loader
    private fun loadFirmware(): MacOsFirmware {
        val macOsFirmwareMapper = MacOsFirmwareMapper()

        return macOsFirmwareMapper.mapFromNative()
    }

}
