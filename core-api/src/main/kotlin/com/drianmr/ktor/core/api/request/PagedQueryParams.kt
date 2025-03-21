package com.drianmr.ktor.core.api.request

import io.ktor.server.application.ApplicationCall

data class PagedQueryParams(val page: Int, val limit: Int) {

    init {
        require(page > 0) { "Page must be greater than 0" }
        require(limit > 0) { "Limit must be greater than 0" }
    }

    val offset: Int = if (page > 1) (page - 1) * limit else 0

    companion object {

        fun from(call: ApplicationCall): PagedQueryParams = call.request.queryParameters.let {
            PagedQueryParams(
                page = it["page"]?.toIntOrNull() ?: 0,
                limit = it["limit"]?.toIntOrNull() ?: 0,
            )
        }
    }
}
