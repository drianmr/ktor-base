package com.drianmr.ktor.core.api.response

import kotlinx.datetime.Instant

interface HistoricalTableResponse {
    val createdAt: Instant
    val updatedAt: Instant?
    val deletedAt: Instant?
}
