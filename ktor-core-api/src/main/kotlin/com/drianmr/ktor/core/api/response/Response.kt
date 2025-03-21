package com.drianmr.ktor.core.api.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val data: T,
    val message: String = "success",
) {

    companion object {

        val OK = Response(data = "OK")
    }
}
