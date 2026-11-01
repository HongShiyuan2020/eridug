package com.syhong.eridug.core.logger

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

actual class ReleaseAntilog actual constructor() : Antilog() {
    actual override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
    }
}