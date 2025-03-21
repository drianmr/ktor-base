package com.drianmr.exposed.historical

import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.Column

interface HistoricalColumns {
    val createdAt: Column<Instant>
    val updatedAt: Column<Instant?>
    val deletedAt: Column<Instant?>
}
