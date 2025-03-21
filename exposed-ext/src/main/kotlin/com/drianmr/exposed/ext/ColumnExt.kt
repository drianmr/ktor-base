package com.drianmr.exposed.ext

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Name of this foreign key constraint.
 */
val Column<*>.foreignKeyName: String?
    inline get() = transaction { this@foreignKeyName.foreignKey?.fkName }
