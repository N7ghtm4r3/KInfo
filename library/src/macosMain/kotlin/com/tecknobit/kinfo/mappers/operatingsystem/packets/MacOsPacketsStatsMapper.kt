package com.tecknobit.kinfo.mappers.operatingsystem.packets

import com.tecknobit.kinfo.mappers.NativeMapper
import com.tecknobit.kinfo.utils.queryUIntArraySysCtlByName

abstract class MacOsPacketsStatsMapper<P> : NativeMapper<P>() {

    protected fun retrieveNativeStats(
        systemControlKey: String
    ): UIntArray {
        return queryUIntArraySysCtlByName(
            name = systemControlKey
        ) ?: throw IllegalStateException("Could not read $systemControlKey")
    }

    /**
     * Method used to fetch and convert a native unsigned counter at the specified [index]
     *
     * @receiver The native macOS `TCP` counters
     *
     * @param index The index of the counter to fetch
     *
     * @return the fetched counter as [Long]
     */
    protected infix fun UIntArray.fetch(
        index: Int
    ): Long {
        return this[index].toLong()
    }

}