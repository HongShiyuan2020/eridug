package com.syhong.eridug.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Page<T>(
    val data: List<T>,
    val page: Long = 1,
    val limit: Long = 10,
    val total: Long,
) {
}