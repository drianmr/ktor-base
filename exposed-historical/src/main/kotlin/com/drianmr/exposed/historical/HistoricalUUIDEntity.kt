package com.drianmr.exposed.historical

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

abstract class HistoricalUUIDEntity(
    id: EntityID<UUID>,
    table: HistoricalUUIDTable,
) : UUIDEntity(id) {
    val createdAt by table.createdAt
    val updatedAt by table.updatedAt
    var deletedAt by table.deletedAt
}
