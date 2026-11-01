package com.syhong.eridug

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.syhong.eridug.core.di.initKoin
import com.syhong.eridug.core.logger.AppLogger

fun main() = application {

    val isDebug = System.getProperty("jpackage.app-path") == null

    initKoin()
    if (isDebug) {
        AppLogger.setup(true)
    } else {
        AppLogger.setup(false)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Eridug",
    ) {
        App()
    }
}