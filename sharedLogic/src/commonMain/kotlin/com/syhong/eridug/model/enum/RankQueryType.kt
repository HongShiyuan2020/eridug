package com.syhong.eridug.model.enum

enum class RankQueryType(
    override val code: Long,
    desc: String,
) : BaseCodeEnum<Long> {
    DAY(1, ""),
    WEEK(code = 2, desc = ""),
    MONTH(code = 3, desc = ""),
    YEAR(code = 4, desc = ""),
    ALL(code = 5, desc = ""),
}