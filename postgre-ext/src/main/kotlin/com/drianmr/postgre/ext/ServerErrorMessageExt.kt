package com.drianmr.postgre.ext

import org.postgresql.util.ServerErrorMessage

/**
 * Attempts to extract a map of key-value pairs from the detail message of a [ServerErrorMessage].
 */
fun ServerErrorMessage?.mapDetail(): Map<String, String> {
    val detail = this?.detail ?: return mapOf()

    val keyRange = detail.indexOf('(')..detail.indexOf(')')
    val valueRange = detail.lastIndexOf('(')..detail.lastIndexOf(')')

    val keys = detail.substring(keyRange).parseList()
    val values = detail.substring(valueRange).parseList()

    assert(keys.size == values.size)
    return (keys zip values).toMap()
}

private fun String.parseList(): List<String> = removeSurrounding("(", ")")
    .split(',')
    .map { it.trim() }
