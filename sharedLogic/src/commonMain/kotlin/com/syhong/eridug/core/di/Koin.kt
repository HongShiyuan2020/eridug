package com.syhong.eridug.core.di

import com.syhong.eridug.core.di.module.dataModule
import com.syhong.eridug.core.di.module.fileModule
import com.syhong.eridug.core.di.module.networkModule
import com.syhong.eridug.core.di.module.platformModules
import com.syhong.eridug.core.di.module.pluginModule
import com.syhong.eridug.core.di.module.scopeModule
import com.syhong.eridug.core.di.module.useCaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

val appModule = module {
    includes(
        useCaseModule,
        fileModule,
        scopeModule,
        pluginModule,
        dataModule,
        networkModule,
        platformModules()
    )
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(appModule)
    }
}