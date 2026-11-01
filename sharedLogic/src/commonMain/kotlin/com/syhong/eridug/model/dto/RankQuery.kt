package com.syhong.eridug.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RankQuery(
    val type: Long,
    val order: Long = 1,
    val page: Long = 1,
    val limit: Long = 100,
)