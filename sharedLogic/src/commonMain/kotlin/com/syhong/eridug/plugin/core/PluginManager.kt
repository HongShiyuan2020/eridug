package com.syhong.eridug.plugin.core

import com.syhong.eridug.core.error.PluginNotFoundException
import com.syhong.eridug.core.error.PluginTypeNotSupportException
import com.syhong.eridug.getPlatform
import com.syhong.eridug.model.dto.PluginInfo
import com.syhong.eridug.model.entity.PluginEntity
import com.syhong.eridug.plugin.Plugin
import com.syhong.eridug.plugin.adapter.Adapter
import com.syhong.eridug.plugin.impl.JsPluginImpl
import com.syhong.eridug.plugin.runtime.Runtime
import com.syhong.eridug.plugin.runtime.executeWithResult
import com.syhong.eridug.repository.PluginRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class PluginManager {
    private val plugins = mutableMapOf<String, Plugin>()
    private val json : Json
    private val jsRuntime : Runtime
    private val jsPluginAdapter : Adapter
    private val pluginRepository : PluginRepository

    constructor(
        json: Json,
        jsRuntime: Runtime,
        jsPluginAdapter: Adapter,
        pluginRepository: PluginRepository,
    ) {
        this.json = json
        this.jsRuntime = jsRuntime
        this.jsPluginAdapter = jsPluginAdapter
        this.pluginRepository = pluginRepository
    }

    suspend fun addOrUpdatePlugin(type: String, code: String): Long {
        val pluginInfo : PluginInfo
        if (type.contains("js")) {
            pluginInfo = jsRuntime.executeWithResult<PluginInfo>($$"""
                globalThis.pluginInfo = new class {
                    $$code
                }();
                (function getPluginInfo() {
                    return `{ "name": "${pluginInfo.name}", "version": "${pluginInfo.version}" }`
                })();
            """.trimIndent())
        } else {
            throw PluginTypeNotSupportException()
        }

        Napier.d(pluginInfo.toString())
        val entity = PluginEntity(
            id = 0,
            version = pluginInfo.version,
            name = pluginInfo.name,
            type = type,
            code = code,
        )

        val record = pluginRepository.getPlugin(pluginInfo.name)

        Napier.i(record.toString())

        if (record == null) {
            return pluginRepository.addPlugin(entity)
        } else {
            entity.id = record.id
            pluginRepository.updatePlugin(entity)
            return record.id
        }
    }

    suspend fun getPlugin(name: String): Plugin {
        return if (plugins.containsKey(name)) {
            plugins[name]!!
        } else {
            tryLoadPlugin(name)
        }
    }

    private suspend fun tryLoadPlugin(name: String) : Plugin {
        val entity = pluginRepository.getPlugin(name) ?: throw PluginNotFoundException()
        val plugin : Plugin
        if (entity.type.contains("js")) {
            plugin = JsPluginImpl(
                json,
                jsPluginAdapter,
                entity.id,
                entity.version,
                entity.name,
                entity.type,
                entity.code
            )
        } else {
            throw PluginTypeNotSupportException()
        }
        register(plugin)
        return plugin
    }

    suspend fun register(plugin: Plugin) {
        plugins[plugin.name] = plugin
        plugin.initialize(PluginContext(getPlatform().name))
    }
}