package com.syhong.eridug.ui.plugin.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syhong.eridug.ui.plugin.vm.EditPluginViewModel

@Composable
fun EditPluginPage(
    viewModel: EditPluginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                state = viewModel.typeState,
                label = { Text("type") },
                modifier = Modifier.fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 8.dp)
            )
            TextField(
                state = viewModel.codeState,
                lineLimits = TextFieldLineLimits.MultiLine(),
                modifier = Modifier.fillMaxWidth()
                    .defaultMinSize(Dp.Unspecified, 200.dp)
                    .weight(1f)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.align(Alignment.End),
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                ) {
                    Text("取消")
                }
                Button(
                    onClick = {
                        viewModel.onSubmit()
                    }
                ) {
                    Text("保存")
                }
            }
        }
    }
}