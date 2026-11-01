package com.syhong.eridug.model.enum

interface BaseCodeEnum<T> {
    val code: T
}

inline fun <reified E, T> Array<E>.getByCode(code: T): E? where E : Enum<E>, E : BaseCodeEnum<T> {
    return this.firstOrNull { it.code == code }
}

inline fun <reified E, T> findEnumByCode(code: T): E? where E : Enum<E>, E : BaseCodeEnum<T> {
    return enumValues<E>().firstOrNull { it.code == code }
}
