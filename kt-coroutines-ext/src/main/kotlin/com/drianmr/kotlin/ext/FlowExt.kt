package com.drianmr.kotlin.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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

/**
 * Executes a concurrent, indexed transformation on the elements of the [Flow] and
 * returns the results as a [List], ensuring the final output order matches the
 * original order of the input flow.
 *
 * @param transform A suspend lambda function that applies a transformation to each element.
 */
suspend inline fun <T, R> Flow<T>.concurrentOrderedMap(
    crossinline transform: suspend (T) -> R,
): List<R> = this
    .concurrentMapIndexed { index, value -> index to transform(value) }
    .toList()
    .sortedBy { (index, _) -> index }
    .map { (_, result) -> result }

/**
 * Executes a concurrent, indexed transformation on the elements of the [Flow] and
 * returns the results as a [List], ensuring the final output order matches the
 * original order of the input flow.
 *
 * @param flowOn The [CoroutineContext] (e.g., a specific Dispatcher) on which the upstream flow collection and
 * concurrent mapping should be executed.
 * @param transform A suspend lambda function that applies a transformation to each element.
 */
suspend inline fun <T, R> Flow<T>.concurrentOrderedMap(
    flowOn: CoroutineContext,
    crossinline transform: suspend (T) -> R,
): List<R> = this
    .concurrentMapIndexed { index, value -> index to transform(value) }
    .flowOn(flowOn)
    .toList()
    .sortedBy { (index, _) -> index }
    .map { (_, result) -> result }
