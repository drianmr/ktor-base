package com.drianmr.jvm.ext

/**
 * Retrieves an enum constant by [name] or `null` if not found.
 */
inline fun <reified E : Enum<E>> enumValueOfOrNull(name: String?): E? = try {
    name?.let { enumValueOf<E>(it) }
} catch (_: Exception) {
    null
}
