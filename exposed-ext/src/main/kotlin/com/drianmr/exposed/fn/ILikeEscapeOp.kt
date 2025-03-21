package com.drianmr.exposed.fn

import org.jetbrains.exposed.sql.ComparisonOp
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.stringParam

class ILikeEscapeOp(
    expr1: Expression<*>,
    expr2: Expression<*>,
    val escapeChar: Char?,
) : ComparisonOp(expr1, expr2, "ILIKE") {

    override fun toQueryBuilder(queryBuilder: QueryBuilder) {
        super.toQueryBuilder(queryBuilder)
        if (escapeChar != null) {
            with(queryBuilder) {
                +" ESCAPE "
                +stringParam(escapeChar.toString())
            }
        }
    }
}
