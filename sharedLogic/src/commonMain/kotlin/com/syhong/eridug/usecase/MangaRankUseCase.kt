package com.syhong.eridug.usecase

import com.syhong.eridug.model.dto.RankQuery
import com.syhong.eridug.model.dto.RankResponse
import com.syhong.eridug.plugin.RankPlugin
import com.syhong.eridug.plugin.core.PluginManager

class MangaRankUseCase(
    private val pluginManager: PluginManager
) {

    suspend fun getRank(pluginName: String, rankQuery: RankQuery): RankResponse {
        val plugin : RankPlugin = pluginManager.getPlugin(pluginName)!!
        return plugin.getRank(rankQuery)
    }
}