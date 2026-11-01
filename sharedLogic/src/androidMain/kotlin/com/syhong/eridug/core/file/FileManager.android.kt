package com.syhong.eridug.core.file

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.files.Path
import org.koin.core.context.GlobalContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

actual class FileManager {
    private val context : Context = GlobalContext.get().get()
    actual suspend fun writeText(path: Path, text: String) {
        val file = File(context.filesDir, path.toString())
        val parentDir = file.parentFile
        if (parentDir != null) {
            if (!parentDir.exists()) {
                parentDir.mkdirs()
            }
        }
        withContext(Dispatchers.IO) {
            FileOutputStream(file, false).use {
                it.write(text.toByteArray())
            }
        }
    }

    actual suspend fun appendText(path: Path, text: String) {
        val file = File(context.filesDir, path.toString())
        val parentDir = file.parentFile
        if (parentDir != null) {
            if (!parentDir.exists()) {
                parentDir.mkdirs()
            }
        }
        withContext(Dispatchers.IO) {
            FileOutputStream(file, true).use {
                it.write(text.toByteArray())
            }
        }
    }

    actual suspend fun readText(path: Path): String {
        val file = File(context.filesDir, path.toString())
        return withContext(Dispatchers.IO) {
            FileInputStream(file).reader()
        }.use {
            it.readText()
        }
    }

}