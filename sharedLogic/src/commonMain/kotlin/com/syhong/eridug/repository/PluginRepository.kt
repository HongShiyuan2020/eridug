package com.syhong.eridug.repository

import com.syhong.eridug.core.database.AppDatabase
import com.syhong.eridug.core.error.PluginNotFoundException
import com.syhong.eridug.model.entity.PluginEntity

class PluginRepository(
    private val database: AppDatabase
) {
    private val pluginDao = database.pluginDao()

    suspend fun addPlugin(plugin: PluginEntity) : Long {
        return pluginDao.insert(plugin)
    }

    suspend fun delPlugin(pluginId: Long) : Int {
        return pluginDao.delete(pluginId)
    }

    suspend fun updatePlugin(plugin: PluginEntity): Int {
        return pluginDao.update(plugin)
    }

    suspend fun getPlugin(name: String): PluginEntity? {
        val plugin = pluginDao.getPluginByName(name)
        return plugin
    }
}