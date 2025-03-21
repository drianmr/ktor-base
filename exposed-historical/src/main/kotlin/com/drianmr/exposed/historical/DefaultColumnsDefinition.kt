package com.drianmr.exposed.historical

import org.jetbrains.exposed.sql.Transaction

/**
 * Defines default column definitions for database tables.
 */
interface DefaultColumnsDefinition {

    /**
     * Registers default column definitions for tables.
     *
     * This function is executed within a transaction and applies the defined default columns to tables.
     */
    context(Transaction)
    fun registerDefaults()
}
