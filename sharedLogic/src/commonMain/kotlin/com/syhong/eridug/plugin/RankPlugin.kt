package com.syhong.eridug.plugin

import com.syhong.eridug.model.dto.RankQuery
import com.syhong.eridug.model.dto.RankResponse

interface RankPlugin {
    suspend fun getRank(query: RankQuery): RankResponse
}