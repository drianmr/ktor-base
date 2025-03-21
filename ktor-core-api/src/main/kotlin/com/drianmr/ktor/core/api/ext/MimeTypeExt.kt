package com.drianmr.ktor.core.api.ext

import com.drianmr.ktor.core.api.type.MimeType
import io.ktor.http.ContentType

fun ContentType.toMimeType(): MimeType = MimeType(type = contentType, subtype = contentSubtype)
