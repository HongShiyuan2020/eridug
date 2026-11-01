package com.syhong.eridug.core.di.module

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
val scopeModule = module {
    single<CoroutineScope> (named("jsRuntimeScope")) {
        CoroutineScope(newSingleThreadContext("jsRuntimeScope"))
    }
}