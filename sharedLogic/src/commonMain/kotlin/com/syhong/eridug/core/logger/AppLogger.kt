package com.syhong.eridug.core.logger

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object AppLogger {
    fun setup(isDebug: Boolean) {
        if (isDebug) {
            Napier.base(DebugAntilog())
        } else {
            Napier.base(ReleaseAntilog())
        }
        Napier.i { "AppLogger Setup! \n IsDebug: $isDebug" }
    }
}