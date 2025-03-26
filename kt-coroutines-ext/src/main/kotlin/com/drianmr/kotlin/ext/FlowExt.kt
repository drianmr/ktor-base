package com.drianmr.kotlin.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

/**
 * Transforms each element of the flow concurrently using the given [transform] function,
 * providing the index of each element.
 */
inline fun <T, R> Flow<T>.concurrentMapIndexed(
    crossinline transform: suspend (Int, T) -> R,
): Flow<R> = channelFlow {
    collectIndexed { index, value ->
        launch {
            send(transform(index, value))
        }
    }
}
