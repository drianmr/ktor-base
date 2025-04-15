package com.drianmr.exposed.helper

import com.drianmr.exposed.ext.fillParameters
import org.jetbrains.exposed.sql.IColumnType
import org.jetbrains.exposed.sql.IntegerColumnType

/**
 * A utility class for binding parameter values to a prepared statement in a type-safe manner.
 *
 * This class is used in conjunction with the [fillParameters] function to populate
 * the parameters of a prepared SQL statement. It maintains a list of parameter
 * values along with their corresponding [IColumnType] to ensure correct data
 * handling by the database driver.
 */
class StatementBinder {

    /**
     * The internal list of parameter values and their associated column types.
     */
    private val args = mutableListOf<Pair<IColumnType<*>, Any?>>()

    /**
     * Binds an [Int] value to the prepared statement.
     */
    fun bind(value: Int) {
        args.add(IntegerColumnType() to value)
    }

    /**
     * Builds the list of parameter values and their types.
     */
    fun build(): List<Pair<IColumnType<*>, Any?>> = args.toList()
}
