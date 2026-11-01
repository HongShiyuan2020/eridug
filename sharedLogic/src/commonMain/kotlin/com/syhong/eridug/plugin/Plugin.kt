package com.syhong.eridug.plugin

import com.syhong.eridug.model.dto.PluginInfo
import com.syhong.eridug.plugin.core.PluginContext

interface Plugin : RankPlugin, HealthCheckPlugin {
    val id : Long
    val version: String
    val name: String
    val type: String
    val code: String
    suspend fun initialize(context: PluginContext)
}