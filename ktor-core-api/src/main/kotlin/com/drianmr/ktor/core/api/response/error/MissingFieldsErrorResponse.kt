package com.drianmr.ktor.core.api.response.error

import kotlinx.serialization.Serializable

@Serializable
data class MissingFieldsErrorResponse(
    val missingFields: List<String>,
)
