package com.tecknobit.kinfo.annotations

import kotlin.annotation.AnnotationTarget.FUNCTION

@Retention(value = AnnotationRetention.SOURCE)
@Target(allowedTargets = [FUNCTION])
annotation class Resolver
