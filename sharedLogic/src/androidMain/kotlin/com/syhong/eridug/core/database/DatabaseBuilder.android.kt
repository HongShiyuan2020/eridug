package com.syhong.eridug.core.database

import android.content.Context
import android.util.Log
import androidx.room3.Room
import androidx.room3.RoomDatabase
import org.koin.core.context.GlobalContext


actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext : Context = GlobalContext.get().get()
    val dbFile = appContext.getDatabasePath("eridug.db")
    Log.i("DatabaseBuilder", "Database path: ${dbFile.absolutePath}")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}