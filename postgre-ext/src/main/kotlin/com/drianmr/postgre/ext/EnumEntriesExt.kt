package com.drianmr.postgre.ext

import kotlin.enums.EnumEntries

/**
 * Transform this enum entries to SQL value wrapped with ''.
 */
fun <E : Enum<E>> EnumEntries<E>.toSql(): String = joinToString(transform = { "'${it.name}'" })
