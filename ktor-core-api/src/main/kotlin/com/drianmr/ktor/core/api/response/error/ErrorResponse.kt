package com.drianmr.ktor.core.api.response.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse<T>(
    val code: String,
    val data: T,
    val message: String = "",
)
