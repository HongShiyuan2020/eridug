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

    suspend fun uninstallPlugin(pluginId: Plugin) {

    }

    suspend fun updatePlugin(plugin: Plugin) {

    }
}
