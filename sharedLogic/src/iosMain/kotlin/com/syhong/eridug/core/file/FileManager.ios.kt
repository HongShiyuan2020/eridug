package com.syhong.eridug.core.file

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.io.files.Path
import platform.Foundation.NSApplicationDirectory
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSFileHandle
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class FileManager actual constructor() {

    private val nsFileManager = NSFileManager.defaultManager

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun writeText(path: Path, text: String) {
        val base = nsFileManager.URLForDirectory(
            directory = NSApplicationSupportDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = true,
            error = null
        )

        val path = Path(base?.path.toString(), path.toString())

        if (!nsFileManager.fileExistsAtPath(path.parent.toString())) {
            nsFileManager.createDirectoryAtPath(
                Path(
                        base.toString(), path.parent.toString()).toString(),
                    true,
                    null,
                    null
                )
        }
    }

    actual suspend fun appendText(path: Path, text: String) {
    }

    actual suspend fun readText(path: Path): String {
        TODO("Not yet implemented")
    }
}