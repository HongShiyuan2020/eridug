package com.syhong.eridug.core.di.module

import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient()
    }
}