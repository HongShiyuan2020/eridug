package com.syhong.eridug.core.di.module

import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.newSingleThreadContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
val scopeModule = module {
    single<CoroutineScope> (named("jsRuntimeScope")) {
        val handler = CoroutineExceptionHandler { _, e ->
            Napier.e(e.toString())
        }
        CoroutineScope(newSingleThreadContext("jsRuntimeScope") + SupervisorJob() + handler)
    }
}