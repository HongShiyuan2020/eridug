package com.syhong.eridug.ui.plugin.vm

import androidx.lifecycle.viewModelScope
import com.syhong.eridug.ui.core.viewmodel.BaseViewModel
import com.syhong.eridug.usecase.PluginUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.inject


class PluginViewModel: BaseViewModel<PluginUIState>(PluginUIState()) {
    private val pluginUseCase: PluginUseCase by inject()

    fun listPlugins() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val plugins = pluginUseCase.listPlugins()
            _uiState.value = _uiState.value.copy(pluginList = plugins, isLoading = false)
        }
    }

    fun addPlugins(type: String, code: String) {
        viewModelScope.launch {
            pluginUseCase.installPlugin(type, code)
        }
    }

    init {
        listPlugins()
    }
}