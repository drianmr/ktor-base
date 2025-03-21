package com.drianmr.ktor.core.api.response

import com.drianmr.ktor.core.api.request.PagedQueryParams
import kotlinx.serialization.Serializable
import kotlin.math.ceil

@Serializable
data class PagedListResponse<E>(
    val meta: Meta,
    val data: List<E>,
) {

    companion object {

        fun <E> withMeta(
            pagination: PagedQueryParams,
            totalItems: Int,
            data: List<E>,
        ): PagedListResponse<E> = PagedListResponse(
            meta = Meta(pagination, totalItems),
            data = data,
        )
    }

    @Serializable
    data class Meta(
        val limit: Int,
        val page: Int,
        val totalPages: Int,
        val totalItems: Int,
        val previousPage: Boolean,
        val nextPage: Boolean,
    ) {

        constructor(
            pagination: PagedQueryParams,
            totalItems: Int,
            totalPages: Int = ceil(totalItems.toDouble() / pagination.limit.toDouble())
                .toInt()
                .coerceAtLeast(1),
        ) : this(
            limit = pagination.limit,
            page = pagination.page,
            totalItems = totalItems,
            totalPages = totalPages,
            previousPage = (pagination.page - 1) in 1..totalPages,
            nextPage = (pagination.page + 1) in 1..totalPages,
        )
    }
}
