package com.drianmr.exposed.helper

import com.drianmr.exposed.ext.fillParameters
import org.jetbrains.exposed.sql.IColumnType
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.UUIDColumnType
import org.jetbrains.exposed.sql.VarCharColumnType
import java.util.UUID

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
     * Binds a [String] value to the prepared statement.
     */
    fun bind(value: String?, length: Int = value?.length ?: 0) {
        args.add(VarCharColumnType(length) to value)
    }

    /**
     * Binds a [UUID] value to the prepared statement.
     */
    fun bind(value: UUID?) {
        args.add(UUIDColumnType() to value)
    }

    /**
     * Builds the list of parameter values and their types.
     */
    fun build(): List<Pair<IColumnType<*>, Any?>> = args.toList()
}
