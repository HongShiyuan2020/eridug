package com.syhong.eridug.plugin.adapter

import com.syhong.eridug.plugin.runtime.Runtime
import com.syhong.eridug.plugin.runtime.executeWithResult
import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json

class JsPluginAdapter(
    override val json: Json,
    private val jsRuntime: Runtime,
) : Adapter(json) {
    override suspend fun initialize(id: Long, code: String) {
        jsRuntime.execute("""
            globalThis.plugin_${id} = new class {
                $code
            }();
            console.info(`plugin_${id}_init success!`);
        """.trimIndent())
    }

    override suspend fun execute(
        id: Long,
        func: String,
        input: String
    ): String {
        return jsRuntime.executeWithResult<String>("""
            JSON.stringify(
                await plugin_${id}.${func}(
                    JSON.parse(`$input`)
                )
            )            
        """.trimIndent())
    }
}