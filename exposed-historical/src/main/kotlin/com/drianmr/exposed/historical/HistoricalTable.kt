package com.drianmr.exposed.historical

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

/**
 * Basic [Table] with historical `created_at`, `updated_at`, and `deleted_at` columns.
 */
abstract class HistoricalTable(name: String = "") : Table(name), HistoricalColumns, DefaultColumnsDefinition {

    // See https://github.com/JetBrains/Exposed/issues/342#issuecomment-405958465
    override val createdAt: Column<Instant> =
        timestamp("created_at").clientDefault { Clock.System.now() }

    override val updatedAt: Column<Instant?> =
        timestamp("updated_at").nullable()

    override val deletedAt: Column<Instant?> =
        timestamp("deleted_at").nullable()

    context(Transaction)
    override fun registerDefaults() {
        exec("ALTER TABLE $tableName ALTER COLUMN created_at SET DEFAULT now();")
    }
}
