package com.tecknobit.kinfo.annotations

import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * The `Resolver` annotation is useful to mark methods responsible for resolving platform-native values
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @since 1.1.0
 */
@Retention(value = AnnotationRetention.SOURCE)
@Target(allowedTargets = [FUNCTION])
annotation class Resolver
