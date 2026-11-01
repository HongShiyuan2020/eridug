package com.syhong.eridug.plugin

import com.dokar.quickjs.QuickJs
import com.dokar.quickjs.binding.function
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class QuickJSTest {
    @Test
    fun quickjsTest() {
        val quickJs = QuickJs.create(Dispatchers.Default)
        val bytecode = quickJs.compile(
        """
                function add(a, b) {
                    return a + b;
                }
            """.trimIndent()
        )
        runBlocking {
            quickJs.evaluate<Any?>(bytecode)
            quickJs.close()
        }
    }
}