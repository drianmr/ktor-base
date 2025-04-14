package com.drianmr.jvm.ext

import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * Encodes this string as a URL using the specified [charset].
 */
fun String.urlEncoded(charset: Charset = StandardCharsets.UTF_8): String =
    URLEncoder.encode(this, charset)
