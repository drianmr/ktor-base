package com.drianmr.ktor.core.api.ext

import io.ktor.http.Headers
import io.ktor.server.plugins.BadRequestException

fun Headers.getOrThrow(key: String): String =
    this[key] ?: throw BadRequestException("Missing required header")
