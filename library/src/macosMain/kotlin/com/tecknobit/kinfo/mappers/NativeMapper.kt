package com.tecknobit.kinfo.mappers

/**
 * The `NativeMapper` class is useful to map platform-native values to KInfo models
 *
 * @param T The type of model produced by the mapper
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
abstract class NativeMapper<T> {

    /**
     * Method used to map the platform-native values to their KInfo model
     *
     * @return the mapped model as [T]
     */
    abstract fun mapFromNative(): T

}
