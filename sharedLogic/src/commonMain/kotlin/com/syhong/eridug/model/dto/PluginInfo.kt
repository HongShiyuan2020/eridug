package com.syhong.eridug.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class PluginInfo(
    val version: String = "",
    val name: String = "",
)
