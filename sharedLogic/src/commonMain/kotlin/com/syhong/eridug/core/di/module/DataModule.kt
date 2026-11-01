package com.syhong.eridug.core.di.module

import androidx.room3.RoomDatabase
import com.syhong.eridug.core.database.AppDatabase
import com.syhong.eridug.core.database.createRoomDatabase
import com.syhong.eridug.core.database.getDatabaseBuilder
import com.syhong.eridug.repository.BookRepository
import com.syhong.eridug.repository.PluginRepository
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
            encodeDefaults = true
            prettyPrint = false
        }
    }
    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }
    single<AppDatabase> {
        createRoomDatabase(get())
    }
    single<BookRepository> {
        BookRepository(get())
    }
    single<PluginRepository> {
        PluginRepository(get())
    }
}