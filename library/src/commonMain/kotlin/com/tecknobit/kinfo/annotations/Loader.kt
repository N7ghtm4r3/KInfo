package com.tecknobit.kinfo.annotations

import kotlin.annotation.AnnotationTarget.*

/**
 * The `Loader` annotation marks methods responsible for loading KInfo data from the original source.
 *
 * These methods map the data provided by the underlying library into the facade models exposed by KInfo.
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.6
 */
@Retention(value = AnnotationRetention.SOURCE)
@Target(allowedTargets = [FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER])
annotation class Loader
