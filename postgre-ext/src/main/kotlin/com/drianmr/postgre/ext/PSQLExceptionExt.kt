package com.drianmr.postgre.ext

import org.postgresql.util.PSQLException
import org.postgresql.util.PSQLState

/**
 * Check if the exception is a unique constraint violation.
 */
fun PSQLException.isUniqueViolation(): Boolean =
    sqlState == PSQLState.UNIQUE_VIOLATION.state

/**
 * Check if the exception is a unique constraint violation on given [constraint].
 */
fun PSQLException.isUniqueViolation(constraint: String): Boolean =
    sqlState == PSQLState.UNIQUE_VIOLATION.state && serverErrorMessage?.constraint == constraint

/**
 * Check if the exception is a unique constraint violation on any given [constraints].
 */
fun PSQLException.isUniqueViolation(vararg constraints: String): Boolean =
    sqlState == PSQLState.UNIQUE_VIOLATION.state && serverErrorMessage?.constraint in constraints
