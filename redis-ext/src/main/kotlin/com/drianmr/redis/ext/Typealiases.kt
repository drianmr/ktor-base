package com.drianmr.redis.ext

import io.lettuce.core.api.StatefulRedisConnection

typealias RedisConnection = StatefulRedisConnection<String, String>
