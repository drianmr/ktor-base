package com.drianmr.exposed.type

import com.drianmr.exposed.ext.postgreEnum
import org.postgresql.util.PGobject

/**
 * A helper class used internally by [postgreEnum] for storing
 * and retrieving enum values in a PostgreSQL database.
 */
class PostgreEnum<E : Enum<E>>(name: String, value: E?) : PGobject() {
    init {
        this.value = value?.name
        this.type = name
    }
}
