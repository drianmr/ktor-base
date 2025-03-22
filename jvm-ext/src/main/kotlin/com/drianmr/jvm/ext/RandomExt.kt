package com.drianmr.jvm.ext

import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

val KotlinSecureRandom: Random = SecureRandom().asKotlinRandom()
