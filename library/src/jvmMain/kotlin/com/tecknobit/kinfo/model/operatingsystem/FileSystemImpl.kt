package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.annotations.Bridge
import com.tecknobit.kinfo.model.desktop.operatingsystem.FileSystem
import com.tecknobit.kinfo.model.desktop.operatingsystem.OSFileStore

/**
 * `FileSystemImpl` Implementation of the `FileSystem` interface, providing details about the file system
 * and interacting with the underlying system's file stores and descriptors.
 *
 * @param fileSystemInfo The underlying file system information from the OS library (OSHI).
 *
 * @author N7ghtm4r3
 *
 * @see FileSystem
 */
class FileSystemImpl(
    private val fileSystemInfo: oshi.software.os.FileSystem,
) : FileSystem {

    /**
     * `fileStores` A list of file stores available in the system.
     * Retrieves and transforms the file store details from the underlying file system.
     */
    override val fileStores: List<OSFileStore>
        get() = loadFileStoresList(
            sourceList = fileSystemInfo.fileStores
        )

    /**
     * `openFileDescriptors` The current number of open file descriptors for the system.
     * Directly obtained from the underlying file system info.
     */
    override val openFileDescriptors = fileSystemInfo.openFileDescriptors

    /**
     * `maxFileDescriptors` The maximum number of file descriptors available for the entire system.
     * Directly obtained from the underlying file system info.
     */
    override val maxFileDescriptors = fileSystemInfo.maxFileDescriptors

    /**
     * `maxFileDescriptorsPerProcess` The maximum number of file descriptors available per process.
     * Directly obtained from the underlying file system info.
     */
    override val maxFileDescriptorsPerProcess = fileSystemInfo.maxFileDescriptorsPerProcess

    /**
     * `getFileStores` Retrieves the file stores from the system, with an option to filter local file stores only.
     *
     * @param localOnly A flag indicating whether to return only local file stores (`true`) or all file stores (`false`).
     * @return A list of file stores matching the provided criteria.
     */
    @Bridge
    override fun getFileStores(
        localOnly: Boolean
    ): List<OSFileStore> {
        return loadFileStoresList(
            sourceList = fileSystemInfo.getFileStores(localOnly)
        )
    }

    /**
     * `loadFileStoresList` A helper function to convert raw file store data into a list of `OSFileStore` objects.
     *
     * @param sourceList The raw list of `oshi.software.os.OSFileStore` to be transformed.
     * @return A list of `OSFileStore` objects.
     */
    private fun loadFileStoresList(sourceList: List<oshi.software.os.OSFileStore>): List<OSFileStore> {
        val result: MutableList<OSFileStore> = mutableListOf()
        sourceList.forEach { fileStore ->
            result.add(
                OSFileStoreImpl(
                    name = fileStore.name,
                    volume = fileStore.volume,
                    label = fileStore.label,
                    logicalVolume = fileStore.logicalVolume,
                    mount = fileStore.mount,
                    description = fileStore.description,
                    type = fileStore.type,
                    options = fileStore.options,
                    uuid = fileStore.uuid,
                    freeSpace = fileStore.freeSpace,
                    usableSpace = fileStore.usableSpace,
                    totalSpace = fileStore.totalSpace,
                    freeInodes = fileStore.freeInodes,
                    totalInodes = fileStore.freeInodes,
                    updateAttributes = fileStore.updateAttributes()
                )
            )
        }
        return result
    }

}