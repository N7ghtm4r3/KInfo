package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.web.operatingsystem.Os

class OsImpl(
    private val parsedOs : com.tecknobit.kinfo.model.Os
) : Os {

    override val name: String
        get() = parsedOs.name.safeValue()

    override val version: String
        get() = parsedOs.version.safeValue()

}