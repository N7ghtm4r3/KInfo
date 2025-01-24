package com.tecknobit.kinfo.model.engine

import com.tecknobit.kinfo.model.web.engine.Engine

class EngineImpl(
    private val parsedEngine: com.tecknobit.kinfo.model.Engine
) : Engine {

    override val name: String
        get() = parsedEngine.name.safeValue()

    override val version: String
        get() = parsedEngine.version.safeValue()

}