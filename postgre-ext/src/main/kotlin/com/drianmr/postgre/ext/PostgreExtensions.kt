package com.drianmr.postgre.ext

/**
 * Defines PostgreSQL extensions as SQL strings.
 */
object PostgreExtensions {

    /**
     * SQL statement to create the `uuid-ossp` extension if it doesn't already exist.
     */
    val UUID = """
        CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
        """.trimIndent()
}
