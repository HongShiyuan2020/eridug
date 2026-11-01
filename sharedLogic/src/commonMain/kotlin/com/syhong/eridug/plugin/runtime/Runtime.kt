package com.syhong.eridug.plugin.runtime

import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json

abstract class Runtime(open val json: Json) {
    abstract fun init()
    abstract suspend fun execute(code: String)
    abstract suspend fun executeWithResult(code: String): String
}

suspend inline fun <reified A : Any> Runtime.executeWithResult(code: String): A {
    val answer = executeWithResult(code)
    if (A::class == String::class) {
        return answer as A
    }
    return json.decodeFromString<A>(answer)
}