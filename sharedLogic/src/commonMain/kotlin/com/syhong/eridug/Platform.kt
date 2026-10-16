package com.syhong.eridug

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform