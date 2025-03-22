package com.drianmr.jvm.ext

import java.util.UUID

fun String.toUUID(): UUID = UUID.fromString(this)
