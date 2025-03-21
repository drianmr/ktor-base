package com.drianmr.exposed.historical

import kotlinx.datetime.Instant

abstract class HistoricalEntity {
    abstract val createdAt: Instant?
    abstract val updatedAt: Instant?
    abstract val deletedAt: Instant?
}
