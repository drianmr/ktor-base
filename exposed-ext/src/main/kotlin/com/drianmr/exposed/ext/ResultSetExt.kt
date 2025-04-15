package com.drianmr.exposed.ext

import kotlinx.datetime.Instant
import kotlinx.datetime.toKotlinInstant
import org.jetbrains.exposed.sql.UUIDColumnType
import java.sql.ResultSet
import java.util.UUID

/**
 * Maps the rows of this [ResultSet] to a list of type [R] using the provided [transform] function.
 */
fun <R> ResultSet.map(transform: (ResultSet) -> R): List<R> = buildList {
    while (next()) {
        add(transform(this@map))
    }
}

/**
 * Retrieves a [UUID] value from the current row of this [ResultSet] using the specified column label.
 */
fun ResultSet.getUUID(columnLabel: String): UUID {
    val type = UUIDColumnType()
    return type.readObject(this, findColumn(columnLabel)) as UUID
}

/**
 * Retrieves an [Instant] value from the current row of this [ResultSet] using the specified column label.
 */
fun ResultSet.getInstant(columnLabel: String): Instant? {
    return getTimestamp(columnLabel)?.toInstant()?.toKotlinInstant()
}

/**
 * Retrieves an enum constant of type [E] from the current row of this [ResultSet] using the specified column label.
 */
inline fun <reified E : Enum<E>> ResultSet.getEnum(columnLabel: String): E {
    val raw = getString(columnLabel)
    return enumValueOf(raw)
}
