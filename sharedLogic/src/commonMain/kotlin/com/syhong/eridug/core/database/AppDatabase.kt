package com.syhong.eridug.core.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import com.syhong.eridug.model.dao.BookDao
import com.syhong.eridug.model.dao.PluginDao
import com.syhong.eridug.model.entity.BookEntity
import com.syhong.eridug.model.entity.PluginEntity

@Database(entities = [
    BookEntity::class,
    PluginEntity::class
], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun pluginDao(): PluginDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}