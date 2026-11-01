package com.syhong.eridug.plugin.impl

import com.syhong.eridug.model.dto.PluginInfo
import com.syhong.eridug.model.dto.RankQuery
import com.syhong.eridug.model.dto.RankResponse
import com.syhong.eridug.plugin.Plugin
import com.syhong.eridug.plugin.adapter.Adapter
import com.syhong.eridug.plugin.adapter.JsPluginAdapter
import com.syhong.eridug.plugin.adapter.execute
import com.syhong.eridug.plugin.core.PluginContext
import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json

class JsPluginImpl(
    private val  json: Json,
    private val  jsPluginAdapter: Adapter,
    override val id: Long,
    override val version: String,
    override val name: String,
    override val type: String,
    override val code: String
) : Plugin {

    override suspend fun getRank(query: RankQuery): RankResponse {
        val res = jsPluginAdapter.execute<RankQuery, RankResponse>(id, "getRank", query)
        Napier.d(res.toString())
        return res
    }

    override suspend fun initialize(context: PluginContext) {
        jsPluginAdapter.initialize(id, code)
    }

    override suspend fun health(): String {
        val res = jsPluginAdapter.execute<Unit, String>(id, func="health", input = Unit)
        return res
    }
}