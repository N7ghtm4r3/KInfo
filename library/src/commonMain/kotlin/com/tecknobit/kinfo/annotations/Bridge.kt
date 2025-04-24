package com.tecknobit.kinfo.annotations

import kotlin.annotation.AnnotationTarget.*

/**
 * The `Bridge` annotation is used to mark methods that simply delegate calls to corresponding methods
 * provided by the under-the-hood library used on that platforms
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.0.2
 */
@Retention(value = AnnotationRetention.SOURCE)
@Target(allowedTargets = [CLASS, FIELD, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, PROPERTY])
annotation class Bridge
