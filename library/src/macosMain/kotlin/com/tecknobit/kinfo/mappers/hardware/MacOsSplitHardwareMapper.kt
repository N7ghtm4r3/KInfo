package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.utils.isAppleSilicon

/**
 * The `MacOsSplitHardwareMapper` class is useful to dispatch native hardware mapping according to Apple Silicon detection
 *
 * @param H The type of hardware model produced by either architecture-specific mapping
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see MacOsHardwareMapper
 *
 * @since 1.1.0
 */
abstract class MacOsSplitHardwareMapper<H> : MacOsHardwareMapper<H>() {

    /**
     * Method used to select the Apple Silicon mapper for native ARM64 or detected Rosetta execution
     *
     * All other detection results select the Intel mapper
     *
     * @return the architecture-specific hardware model as [H]
     */
    override fun mapFromNative(): H {
        if (isAppleSilicon())
            return mapForSilicon()

        return mapForIntel()
    }

    /**
     * Method used to map the native hardware values for Apple Silicon
     *
     * @return the mapped hardware model as [H]
     */
    protected abstract fun mapForSilicon(): H

    /**
     * Method used to map the native hardware values for Intel
     *
     * @return the mapped hardware model as [H]
     */
    protected abstract fun mapForIntel(): H

}