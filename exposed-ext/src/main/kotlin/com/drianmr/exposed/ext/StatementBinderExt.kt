package com.drianmr.exposed.ext

import com.drianmr.exposed.helper.StatementBinder
import org.jetbrains.exposed.sql.IColumnType

/**
 * Creates the list of bound parameters needed for executing a prepared database statement.
 */
fun bindStatement(block: StatementBinder.() -> Unit): List<Pair<IColumnType<*>, Any?>> =
    StatementBinder().apply(block).build()
