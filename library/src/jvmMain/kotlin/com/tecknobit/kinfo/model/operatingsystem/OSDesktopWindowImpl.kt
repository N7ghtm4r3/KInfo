package com.tecknobit.kinfo.model.operatingsystem

import com.tecknobit.kinfo.model.desktop.operatingsystem.OSDesktopWindow

class OSDesktopWindowImpl(
    override val windowId: Long,
    override val title: String,
    override val command: String,
    override val owningProcessId: Long,
    override val order: Int,
    override val visible: Boolean
) : OSDesktopWindow