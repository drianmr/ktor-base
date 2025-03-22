package com.drianmr.jvm.ext

import java.util.concurrent.CompletionStage

/**
 * Completes a [CompletionStage] with [Unit] regardless of the original result.
 */
fun CompletionStage<*>.thenIgnoreResult(): CompletionStage<Unit> = thenApply { }
