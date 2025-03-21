package com.drianmr.exposed.ext

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.andWhere

/**
 * Adds an `AND` condition to the `WHERE` clause of the query if the given [value] is not null.
 */
fun <T> Query.optionalAndWhere(
    value: T?,
    andPart: SqlExpressionBuilder.(value: T) -> Op<Boolean>,
): Query = if (value != null) andWhere { andPart(value) } else this

/**
 * Adds an `AND` condition to the `WHERE` clause of the query if the given [condition] is true.
 */
fun Query.conditionalAndWhere(
    condition: Boolean,
    andPart: SqlExpressionBuilder.() -> Op<Boolean>,
): Query = if (condition) andWhere(andPart) else this
