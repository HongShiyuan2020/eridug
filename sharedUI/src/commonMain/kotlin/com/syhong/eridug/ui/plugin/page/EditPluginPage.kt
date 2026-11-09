package com.syhong.eridug.ui.plugin.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syhong.eridug.ui.plugin.vm.EditPluginViewModel

@Composable
fun EditPluginPage(
    viewModel: EditPluginViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            
        }
    }
}