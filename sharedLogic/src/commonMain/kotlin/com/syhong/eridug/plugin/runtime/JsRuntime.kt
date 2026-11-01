package com.syhong.eridug.plugin.runtime

import com.dokar.quickjs.QuickJs
import com.dokar.quickjs.binding.JsObject
import com.dokar.quickjs.binding.asyncFunction
import com.dokar.quickjs.binding.define
import com.dokar.quickjs.binding.function
import com.syhong.eridug.model.dto.ComputeRequest
import com.syhong.eridug.model.dto.createComputeRequest
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.headers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass
import kotlin.reflect.KType


class JsRuntime (
    private val httpClient: HttpClient,
    private val jsRuntimeScope: CoroutineScope,
    override val json: Json,
) : Runtime(json) {
    private val js = QuickJs.create(Dispatchers.Default)
    private val channel = Channel<ComputeRequest<String, String>>()

    private fun initJs() {
        js.asyncFunction("httpGet") { args ->
            val url = args[0] as String
            val headersObj = args.getOrNull(1) as? JsObject
            val headers : Map<String, String> = headersObj?.let { obj ->
                obj.keys.associateWith { key ->
                    obj[key]?.toString() ?: ""
                }
            } ?: emptyMap()

            val response = httpClient.get(url) {
                headers.keys.forEach { key ->
                    header(key, headers[key])
                }
            }
            return@asyncFunction response.bodyAsText()
        }
        js.asyncFunction("httpPost") { args ->
            val url = args[0] as String
            val response = httpClient.post(url)
            return@asyncFunction response.bodyAsText()
        }
        js.define("console") {
            function("info") { args ->
                Napier.i(args[0] as String)
            }
            function("debug") { args ->
                Napier.d(args[0] as String)
            }
            function("error") { args ->
                Napier.e(args[0] as String)
            }
        }
        runBlocking {
            val res = httpClient.get("https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.2.0/crypto-js.min.js")
            val cryptoCode = res.bodyAsText()
            js.evaluate<Any?>("""
                var window = globalThis
                var global = globalThis
                var self = globalThis
            """.trimIndent())
            js.evaluate<Any?>("""
                if (!globalThis.crypto) {
                    globalThis.crypto = {
                        getRandomValues: function(buffer) {
                            for (var i = 0; i < buffer.length; i++) {
                                buffer[i] = Math.floor(Math.random() * 256);
                            }
                            return buffer;
                        }
                    };
                }
            """.trimIndent())
            js.evaluate<Any?>(cryptoCode)
            js.evaluate<Any?>("""
                console.info("Js Runtime Initialized!");
            """.trimIndent())
        }
    }

    private fun loop() {
        jsRuntimeScope.launch {
            for ((data, response, responseClazz) in channel) {
                val res = js.evaluate<String?>(
                    data
                )
                if (res != null) {
                    response.complete(res)
                } else {
                    response.complete("Js Done!")
                }
            }
        }
    }

    override fun init() {
        initJs()
        loop()
    }

    override suspend fun execute(code: String) {
        channel.send(
            createComputeRequest<String, String>(code)
        )
    }

    override suspend fun executeWithResult(code: String): String {
        val req = ComputeRequest(
            data = code,
            responseClazz = String::class
        )
        channel.send(req)
        return req.response.await()
    }

    init {
        init()
    }
}

