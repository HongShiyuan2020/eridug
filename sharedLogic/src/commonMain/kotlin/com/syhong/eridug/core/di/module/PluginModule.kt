package com.syhong.eridug.core.di.module

import com.syhong.eridug.plugin.adapter.Adapter
import com.syhong.eridug.plugin.adapter.JsPluginAdapter
import com.syhong.eridug.plugin.core.PluginManager
import com.syhong.eridug.plugin.runtime.JsRuntime
import com.syhong.eridug.plugin.runtime.Runtime
import org.koin.core.qualifier.named
import org.koin.dsl.module

val pluginModule = module {
    single<Adapter> (named("jsPluginAdapter")) {
        JsPluginAdapter(
            get(),
            get(named("jsRuntime")),
        )
    }
    single<PluginManager> {
        PluginManager(get(), get(named("jsRuntime")), get(named("jsPluginAdapter")), get())
    }
    single<Runtime> (named("jsRuntime")) {
        JsRuntime(get(), get(named("jsRuntimeScope")), get())
    }
}