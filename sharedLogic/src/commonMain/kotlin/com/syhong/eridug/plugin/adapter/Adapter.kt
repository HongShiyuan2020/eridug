package com.syhong.eridug.plugin.adapter

import com.syhong.eridug.model.dto.PluginInfo
import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json

abstract class Adapter(
    open val json: Json
) {
    abstract suspend fun initialize(id: Long, code: String)
    abstract suspend fun execute(id: Long, func: String, input: String): String
}

suspend inline fun <reified Q : Any, reified A : Any> Adapter.execute(id: Long, func: String, input: Q): A {
    val resText = execute(id, func, json.encodeToString(input))
    if (A::class == String::class) {
        return resText as A
    }
    return json.decodeFromString<A>(resText)
}

