package com.drianmr.exposed.ext

import org.jetbrains.exposed.sql.Column
import org.postgresql.util.PSQLException
import org.postgresql.util.PSQLState

/**
 * Check if the exception is a foreign key violation on given [key].
 */
fun PSQLException.isForeignKeyViolation(key: Column<*>): Boolean =
    sqlState == PSQLState.FOREIGN_KEY_VIOLATION.state && serverErrorMessage?.constraint == key.foreignKeyName
