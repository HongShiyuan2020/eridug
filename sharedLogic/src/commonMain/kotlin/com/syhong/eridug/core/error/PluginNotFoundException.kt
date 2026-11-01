package com.syhong.eridug.core.error

class PluginNotFoundException(
    override val code: Int = 404,
    override val message: String = "Plugin not found",
    override val cause: Throwable? = null
) : BizException(code, message, cause) {
}
