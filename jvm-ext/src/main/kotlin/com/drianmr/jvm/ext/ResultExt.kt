package com.drianmr.jvm.ext

import com.drianmr.jvm.ThrowableLooper
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Checks for nested failures within a [Result] and allows handling them with an action.
 */
inline fun <T> Result<T>.checkNestedFailure(action: ThrowableLooper.(cause: Throwable) -> Unit): Result<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    exceptionOrNull()?.let { cause ->
        cause.checkNestedCause {
            action(this, cause)
        }
    }
    return this
}
