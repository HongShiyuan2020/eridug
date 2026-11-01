package com.syhong.eridug.core.di.module

import com.syhong.eridug.usecase.HealthCheckUseCase
import com.syhong.eridug.usecase.PluginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<PluginUseCase> {
        PluginUseCase(get())
    }
    single<HealthCheckUseCase> {
        HealthCheckUseCase(get())
    }
}