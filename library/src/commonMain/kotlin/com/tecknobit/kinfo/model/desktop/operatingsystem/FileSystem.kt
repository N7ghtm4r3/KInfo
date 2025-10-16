package com.tecknobit.kinfo.model.desktop.operatingsystem

import com.tecknobit.kinfo.annotations.Bridge

/**
 * `FileSystem` Represents the file system details of an operating system, providing information about file stores,
 * file descriptors, and associated file system attributes.
 *
 * @author N7ghtm4r3
 *
 * @see FileSystem
 */
interface FileSystem {

    /**
     * `fileStores` A list of file stores available in the system.
     * Each file store represents a distinct filesystem, such as an external drive or network storage.
     */
    val fileStores: List<OSFileStore>

    /**
     * `openFileDescriptors` The current number of open file descriptors for the system.
     * This is the number of files currently opened by processes
     */
    val openFileDescriptors: Long

    /**
     * `maxFileDescriptors` The maximum number of file descriptors available for the entire system.
     * This is the upper limit of file descriptors the system can handle at once
     */
    val maxFileDescriptors: Long

    /**
     * `maxFileDescriptorsPerProcess` The maximum number of file descriptors available per process.
     * This is the upper limit of file descriptors a single process can handle at once
     */
    val maxFileDescriptorsPerProcess: Long

    /**
     * Method used to retrieve the file stores of the system.
     * This method can filter to return only local file stores if specified
     *
     * @param localOnly A flag to indicate whether to return only local file stores (`true`) or all file stores (`false`)
     * @return A list of file stores that match the provided criteria
     */
    @Bridge
    fun getFileStores(
        localOnly: Boolean
    ): List<OSFileStore>

}
