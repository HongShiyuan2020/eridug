package com.syhong.eridug.model.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import com.syhong.eridug.model.entity.PluginEntity

@Dao
interface PluginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plugin: PluginEntity): Long
    @Query("DELETE FROM plugins WHERE id = :pluginId")
    suspend fun delete(pluginId: Long): Int
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(plugin: PluginEntity): Int
    @Query("SELECT * FROM plugins WHERE id = :pluginId")
    suspend fun getPluginById(pluginId: Long): PluginEntity?
    @Query("SELECT * FROM plugins WHERE name = :name")
    suspend fun getPluginByName(name: String): PluginEntity?
}