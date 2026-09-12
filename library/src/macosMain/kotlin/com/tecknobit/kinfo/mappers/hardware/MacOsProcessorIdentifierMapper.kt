package com.tecknobit.kinfo.mappers.hardware

import com.tecknobit.kinfo.hardware.MacOsProcessorIdentifierImpl
import com.tecknobit.kinfo.mappers.NativeMapper

/**
 * The `MacOsProcessorIdentifierMapper` class is useful to define the native macOS processor identification mapping
 *
 * The mapping is currently unavailable and throws when invoked
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @see NativeMapper
 * @see MacOsProcessorIdentifierImpl
 *
 * @since 1.1.0
 */
class MacOsProcessorIdentifierMapper : NativeMapper<MacOsProcessorIdentifierImpl>() {

    /**
     * Method used to define the processor identification mapping operation, currently not implemented
     *
     * @return the processor identification model as [MacOsProcessorIdentifierImpl]
     * @throws NotImplementedError Whenever this method is invoked
     */
    override fun mapFromNative(): MacOsProcessorIdentifierImpl {
        TODO("Not yet implemented")
    }

}