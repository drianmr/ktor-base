package com.drianmr.exposed.ext

import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.EnumerationNameColumnType
import org.jetbrains.exposed.sql.LiteralOp
import org.jetbrains.exposed.sql.UUIDColumnType
import java.util.UUID
import kotlin.enums.enumEntries

/**
 * Configures the nullability of this [ColumnType] based on the provided [value].
 *
 * This function sets the `nullable` property of the [ColumnType] to `true` if the
 * [value] is `null`, and `false` otherwise.
 */
fun <T, C : ColumnType<T>> C.withNullable(value: T?): C = apply {
    nullable = value == null
}

/**
 * Casts this [LiteralOp] to a non-nullable type.
 */
fun <T> LiteralOp<T?>.asNotNull(): LiteralOp<T & Any> = LiteralOp(
    columnType = this.columnType.apply { nullable = false },
    value = this.value!!,
)

/**
 * Returns the specified [value] as an [UUID] literal.
 */
fun uuidLiteral(value: UUID?): LiteralOp<UUID?> = LiteralOp(
    columnType = UUIDColumnType().withNullable(value),
    value = value,
)

/**
 * Returns the specified [value] as an [E] enum literal.
 */
inline fun <reified E : Enum<E>> enumLiteral(value: E?): LiteralOp<E?> = LiteralOp(
    columnType = EnumerationNameColumnType(
        klass = E::class,
        colLength = enumEntries<E>().maxOf { it.name.length },
    ).withNullable(value),
    value = value,
)
