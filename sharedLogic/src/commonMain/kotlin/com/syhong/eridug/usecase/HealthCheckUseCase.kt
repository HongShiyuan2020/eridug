package com.syhong.eridug.usecase

import com.syhong.eridug.plugin.HealthCheckPlugin
import com.syhong.eridug.plugin.core.PluginManager
import io.github.aakira.napier.Napier

class HealthCheckUseCase(
    val pluginManager: PluginManager
) {


    suspend fun pluginHealthCheck() {
        val healthCheckPlugin : HealthCheckPlugin = pluginManager.getPlugin("plugin-test")
        val res = healthCheckPlugin.health()
        Napier.d(res)
    }
}