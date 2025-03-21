package com.drianmr.ktor.core.api.request

import io.ktor.server.application.ApplicationCall

data class HistoricalQueryParams(
    val includeDeleted: Boolean = false,
) {

    companion object {

        fun from(call: ApplicationCall): HistoricalQueryParams = call.request.queryParameters.let {
            HistoricalQueryParams(
                includeDeleted = it["include_deleted"]?.toBooleanStrictOrNull() == true,
            )
        }
    }
}
