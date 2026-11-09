package com.syhong.eridug.ui.plugin.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syhong.eridug.ui.plugin.component.PluginItem
import com.syhong.eridug.ui.plugin.vm.PluginViewModel
import java.awt.Button




@Composable
fun PluginPage(
    pluginViewModel: PluginViewModel = viewModel()
) {
    val uiState by pluginViewModel.uiState.collectAsState()

    return Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(uiState.pluginList) { plugin ->
                PluginItem(plugin)
            }
        }
        IconButton(
            onClick = {  },
            modifier = Modifier.align(Alignment.BottomEnd)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                "+",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}