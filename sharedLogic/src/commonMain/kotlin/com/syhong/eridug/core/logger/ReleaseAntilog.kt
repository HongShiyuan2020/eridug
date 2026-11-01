package com.syhong.eridug.core.logger

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel


expect class ReleaseAntilog() : Antilog {
    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    )
}