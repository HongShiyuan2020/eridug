package com.syhong.eridug.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RankResponse(
    val page: Page<Int>,
)