package com.drianmr.redis.ext

import io.lettuce.core.api.async.RedisAsyncCommands

/**
 * Defines a scope that provides asynchronous access to Redis commands.
 */
interface RedisAsyncScope {

    /**
     * The asynchronous Redis command interface for performing
     * non-blocking operations.
     */
    val commands: RedisAsyncCommands<String, String>
}
