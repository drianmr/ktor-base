package com.drianmr.postgre.ext

import kotlin.enums.enumEntries

/**
 * Defines a PostgreSQL enum type.
 */
interface PostgreEnumDefinition {

    /**
     * The SQL query used to create the enum type in the database.
     */
    val query: String

    /**
     * The name of the enum type as it will be defined in the database.
     */
    val sqlName: String
}

/**
 * Creates a default [PostgreEnumDefinition] for a given enum type.
 */
inline fun <reified E : Enum<E>> DefaultPostgreEnumDefinition(sqlName: String): PostgreEnumDefinition =
    object : PostgreEnumDefinition {
        override val sqlName: String = sqlName

        // See https://stackoverflow.com/a/48382296/4739189
        override val query: String = """
            DO $$ BEGIN
                CREATE TYPE $sqlName AS ENUM(${enumEntries<E>().toSql()});
            EXCEPTION
                WHEN duplicate_object THEN null;
            END $$;
        """.trimIndent()
    }
