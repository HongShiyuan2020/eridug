package com.syhong.eridug.usecase

import com.syhong.eridug.model.entity.PluginEntity
import com.syhong.eridug.plugin.Plugin
import com.syhong.eridug.plugin.core.PluginManager

class PluginUseCase(
    private val pluginManager: PluginManager
) {
    suspend fun installPlugin(type: String, code: String) : Long {
        return pluginManager.addOrUpdatePlugin(type, code)
    }

    suspend fun uninstallPlugin(pluginName: String) {
        return pluginManager.removePlugin(pluginName)
    }

    suspend fun listPlugins() : List<PluginEntity> {
        return pluginManager.listPlugins()
    }
}
