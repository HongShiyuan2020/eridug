package com.syhong.eridug.ui.plugin.vm

import com.syhong.eridug.model.entity.PluginEntity

data class PluginUIState(
    val isLoading: Boolean = false,
    val pluginList: List<PluginEntity> = emptyList(),
) {
}