package com.syhong.eridug.ui.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<S>(state: S) : ViewModel(), KoinComponent {
    protected val _uiState = MutableStateFlow(state)
    val uiState = _uiState.asStateFlow()
}