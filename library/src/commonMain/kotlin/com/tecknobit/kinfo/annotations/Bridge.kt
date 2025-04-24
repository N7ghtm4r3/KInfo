package com.tecknobit.kinfo.annotations

import kotlin.annotation.AnnotationTarget.*

/**
 * The `Bridge` annotation is used to mark methods that simply delegate calls to corresponding methods
 * provided by the under-the-hood library used on that platform
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.2
 */
@Retention(value = AnnotationRetention.SOURCE)
@Target(allowedTargets = [FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER])
annotation class Bridge
