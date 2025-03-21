package com.drianmr.exposed.ext

import com.drianmr.exposed.fn.ILikeEscapeOp
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.LikePattern
import org.jetbrains.exposed.sql.stringParam

/**
 * Checks if this expression matches the specified [pattern] (case-insensitive).
 */
infix fun <T : String?> Expression<T>.ilike(pattern: String): ILikeEscapeOp =
    ilike(LikePattern(pattern))

/**
 * Checks if this expression matches the specified [pattern] (case-insensitive).
 */
infix fun <T : String?> Expression<T>.ilike(pattern: LikePattern): ILikeEscapeOp =
    ILikeEscapeOp(this, stringParam(pattern.pattern), pattern.escapeChar)
