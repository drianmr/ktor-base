package com.drianmr.redis.ext

import kotlinx.coroutines.future.await
import java.util.concurrent.CompletionStage

/**
 * Creates a [RedisAsyncScope] instance bound to the given [RedisConnection].
 */
fun createRedisAsyncScope(connection: RedisConnection): RedisAsyncScope = RedisAsyncScopeImpl(connection)

/**
 * Awaits the completion of this [CompletionStage] and triggers the execution
 * of the current Redis transaction within the given [RedisAsyncScope].
 */
context(RedisAsyncScope)
suspend fun <R> CompletionStage<R>.awaitExec(): R = this
    .also { commands.exec() }
    .await()
