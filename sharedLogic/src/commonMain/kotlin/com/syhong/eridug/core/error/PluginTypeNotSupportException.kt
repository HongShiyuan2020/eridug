package com.syhong.eridug.core.error

class PluginTypeNotSupportException(
    override val code: Int = 403,
    override val message: String = "Plugin only support js code",
    override val cause: Throwable? = null
) : BizException(code, message, cause) {
}