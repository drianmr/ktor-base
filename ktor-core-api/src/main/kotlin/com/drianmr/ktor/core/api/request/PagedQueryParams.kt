package com.drianmr.ktor.core.api.request

import com.drianmr.ktor.core.api.request.PagedQueryParams.Companion.DEFAULT_LIMIT
import com.drianmr.ktor.core.api.request.PagedQueryParams.Companion.DEFAULT_PAGE
import io.ktor.server.application.ApplicationCall

data class PagedQueryParams(val page: Int, val limit: Int) {

    init {
        require(page > 0) { "Page must be greater than 0" }
        require(limit > 0) { "Limit must be greater than 0" }
    }

    /**
     * Calculates the starting offset based on the current [page] and [limit].
     */
    val offset: Int = if (page > 1) (page - 1) * limit else 0

    companion object {

        const val DEFAULT_PAGE = 1
        const val DEFAULT_LIMIT = 10

        /**
         * Extracts and parses pagination parameters.
         *
         * Missing or invalid query values default to [DEFAULT_PAGE] and [DEFAULT_LIMIT].
         */
        fun from(call: ApplicationCall): PagedQueryParams = call.request.queryParameters.let {
            PagedQueryParams(
                page = it["page"]?.toIntOrNull() ?: DEFAULT_PAGE,
                limit = it["limit"]?.toIntOrNull() ?: DEFAULT_LIMIT,
            )
        }

        /**
         * Conditionally extracts optional pagination parameters.
         *
         * Returns `null` if **both** `page` and `limit` are missing from the query. If one is present,
         * any missing or invalid value defaults to [DEFAULT_PAGE] or [DEFAULT_LIMIT].
         */
        fun optionalFrom(call: ApplicationCall): PagedQueryParams? = call.request.queryParameters.let {
            val page = it["page"]?.toIntOrNull()
            val limit = it["limit"]?.toIntOrNull()

            if (page == null && limit == null) {
                return@let null
            }

            PagedQueryParams(
                page = page ?: DEFAULT_PAGE,
                limit = limit ?: DEFAULT_LIMIT,
            )
        }
    }
}
