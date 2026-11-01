package com.syhong.eridug.model.dto

import kotlinx.coroutines.CompletableDeferred
import kotlin.reflect.KClass

data class ComputeRequest<Q, A : Any> (
    val data : Q,
    val response: CompletableDeferred<A> = CompletableDeferred(),
    val responseClazz: KClass<A>
) {

}

inline fun <Q, reified A : Any> createComputeRequest(data: Q): ComputeRequest<Q, A> {
    return ComputeRequest<Q, A> (
        data = data,
        responseClazz = A::class
    )
}