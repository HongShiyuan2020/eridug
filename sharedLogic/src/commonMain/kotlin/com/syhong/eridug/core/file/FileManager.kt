package com.syhong.eridug.core.file

import kotlinx.io.files.FileSystem
import kotlinx.io.files.Path

expect class FileManager() {
    suspend fun writeText(path: Path, text: String)
    suspend fun appendText(path: Path, text: String)
    suspend fun readText(path: Path): String
}