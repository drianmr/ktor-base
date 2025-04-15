package com.drianmr.exposed.ext

import com.drianmr.exposed.helper.StatementBinder
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi

/**
 * Fills the parameters with the prepared statement using the provided [block].
 *
 * Example:
 * ```kotlin
 * preparedStatement.fillParameters {
 *     bind(123) // Bind an Int
 *     bind("abc") // Bind a String
 *     // ... other bind calls
 * }
 * ```
 */
fun PreparedStatementApi.fillParameters(block: StatementBinder.() -> Unit): PreparedStatementApi {
    val binder = StatementBinder().apply(block)
    fillParameters(binder.build())
    return this
}
