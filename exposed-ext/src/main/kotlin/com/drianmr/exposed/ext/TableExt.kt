package com.drianmr.exposed.ext

import com.drianmr.exposed.type.PostgreEnum
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

/**
 * Default money format with precision of 17 and scale of 2
 */
fun Table.customMoney(name: String, precision: Int = 17, scale: Int = 2): Column<BigDecimal> =
    decimal(name = name, precision = precision, scale = scale)

/**
 * Creates a custom column for storing enum values as strings in a PostgreSQL database table.
 */
inline fun <reified E : Enum<E>> Table.postgreEnum(
    columnName: String,
    sqlName: String,
): Column<E> = customEnumeration(
    name = columnName,
    sql = sqlName,
    fromDb = { value -> enumValueOf<E>(value as String) },
    toDb = { enum -> PostgreEnum(sqlName, enum) },
)
