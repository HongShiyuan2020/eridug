package com.syhong.eridug.model.enum

enum class FileOp(
    override val code: Long,
    desc: String,
): BaseCodeEnum<Long> {
    READ(1, ""),
    WRITE(2, ""),
    APPEND(3, ""),
}