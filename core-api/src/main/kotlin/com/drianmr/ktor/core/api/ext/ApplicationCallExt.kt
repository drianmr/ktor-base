package com.drianmr.ktor.core.api.ext

import io.ktor.server.application.ApplicationCall

/**
 * Retrieves and parses an enum value from a query parameter
 */
inline fun <reified E : Enum<E>> ApplicationCall.queryAsEnum(key: String): E? {
    val value = request.queryParameters[key] ?: return null
    return try {
        enumValueOf<E>(value)
    } catch (_: IllegalArgumentException) {
        // Rethrow as new error
        throw IllegalArgumentException("No enum constant named '${value}' in $key")
    }
}
