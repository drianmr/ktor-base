package com.drianmr.jvm.ext

import com.drianmr.jvm.ThrowableLooper

/**
 * Checks the nested cause hierarchy of a throwable object
 * and executes specific handlers based on the encountered exception types.
 *
 * This function simplifies the process of iterating through
 * the cause chain of a throwable and handling specific exception types.
 * It takes a lambda function [builder] that configures the exception handlers
 * using the provided `ThrowableLooper` instance.
 *
 * @param builder A lambda function that configures exception handlers for the `ThrowableLooper`.
 */
inline fun Throwable.checkNestedCause(builder: ThrowableLooper.() -> Unit) {
    val looper = ThrowableLooper(this)
    looper.builder()
    looper.check()
}
