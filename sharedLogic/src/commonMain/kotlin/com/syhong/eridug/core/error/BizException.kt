package com.syhong.eridug.core.error

open class BizException(
    open val code: Int = 404,
    override val message: String = "Plugin not found!",
    override val cause: Throwable? = null
) : RuntimeException(message, cause) {
}