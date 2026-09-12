package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.utils.isAppleSilicon

abstract class MacOsSplitHardwareMapper<H> : MacOsHardwareMapper<H>() {

    override fun mapFromNative(): H {
        if (isAppleSilicon())
            return mapForSilicon()

        return mapForIntel()
    }

    protected abstract fun mapForSilicon(): H

    protected abstract fun mapForIntel(): H

}