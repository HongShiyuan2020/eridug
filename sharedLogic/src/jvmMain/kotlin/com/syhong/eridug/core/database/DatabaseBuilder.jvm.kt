package com.syhong.eridug.core.database

import androidx.room3.Room
import androidx.room3.RoomDatabase
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val userHome = System.getProperty("user.home")
    val os = System.getProperty("os.name").lowercase()

    val baseDir = when {
        os.contains("mac") -> File(userHome, "Library/Application Support/Eridug")
        os.contains("win") -> File(System.getenv("APPDATA") ?: userHome, "Eridug")
        else -> File(userHome, ".local/share/eridug")
    }.apply { if (!exists()) mkdirs() }

    val dbFile = File(baseDir, "eridug.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath
    )
}