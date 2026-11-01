package com.syhong.eridug

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syhong.eridug.plugin.core.PluginManager
import com.syhong.eridug.usecase.HealthCheckUseCase
import com.syhong.eridug.usecase.PluginUseCase
import org.jetbrains.compose.resources.painterResource

import eridug.sharedui.generated.resources.Res
import eridug.sharedui.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    val pluginUseCase = koinInject<PluginUseCase>()
    val healthCheckUseCase = koinInject<HealthCheckUseCase>()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                CoroutineScope(Dispatchers.Default).launch {
                    pluginUseCase.installPlugin("js", """
                        name = "plugin-test"
                        version = "0.0.1"
                        
                        health() {
                            return "health check"
                        }
                    """)

                    healthCheckUseCase.pluginHealthCheck()

                }
            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: Hello")
                }
            }
        }
    }
}