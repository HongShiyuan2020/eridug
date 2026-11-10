package com.syhong.eridug.ui.plugin.vm

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhong.eridug.ui.core.viewmodel.BaseViewModel
import com.syhong.eridug.usecase.PluginUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditPluginViewModel : BaseViewModel<EditPluginUIState>(EditPluginUIState())  {
    private val pluginUseCase : PluginUseCase by inject()

    val codeState = TextFieldState()
    val typeState = TextFieldState()


    fun onCodeChange(code: String) {
        _uiState.value = _uiState.value.copy(code = code)
    }

    fun onTypeChange(type: String) {
        _uiState.value = _uiState.value.copy(type  = type)
    }

    fun onSubmit() {
        viewModelScope.launch {
            pluginUseCase.installPlugin(typeState.text.toString(), codeState.text.toString())
        }
    }
}