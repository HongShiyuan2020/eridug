package com.syhong.eridug.model.enum

enum class OrderType(
    override val code: Long,
    desc: String,
) : BaseCodeEnum<Long> {
    ASC(1, ""),
    DESC(2, ""),
}