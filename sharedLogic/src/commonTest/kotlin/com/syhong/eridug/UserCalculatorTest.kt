package com.syhong.eridug

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UserCalculatorTest {
    @BeforeTest
    fun setUp() {

    }

    @AfterTest
    fun tearDown() {

    }

    @Test
    fun `test basic addition logic`() {
        val result = 2 + 3
        assertEquals(5, actual = result, message = "2+3 should equal 5")
    }
}