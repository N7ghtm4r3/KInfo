package com.tecknobit.kinfo.mappers

abstract class NativeMapper<T> {

    abstract fun mapFromNative(): T

}