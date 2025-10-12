package com.drianmr.redis.ext

import io.lettuce.core.api.async.RedisAsyncCommands

internal class RedisAsyncScopeImpl(connection: RedisConnection) : RedisAsyncScope {

    override val commands: RedisAsyncCommands<String, String> = connection.async()
}
