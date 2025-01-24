package com.tecknobit.kinfo.model.desktop.operatingsystem

interface FileSystem {
    val fileStores: List<OSFileStore>
    val openFileDescriptors: Long
    val maxFileDescriptors: Long
    val maxFileDescriptorsPerProcess: Long

    fun getFileStores(
        localOnly: Boolean
    ): List<OSFileStore>
}