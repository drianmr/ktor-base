package com.drianmr.exposed.ext

import org.jetbrains.exposed.sql.AbstractQuery
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.wrapAsExpression

/**
 * Wraps a [query] as an [Expression] so that it can be used as part of an SQL statement or in another query clause.
 *
 * This is a non-null version of [wrapAsExpression].
 */
fun <T : Any> expression(query: AbstractQuery<*>): Expression<T> = object : Expression<T>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        append("(")
        query.prepareSQL(this)
        append(")")
    }
}
