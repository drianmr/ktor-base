package com.drianmr.ktor.core.api.request

import com.drianmr.ktor.core.api.type.SortOrder

data class SortQueryParams(val sort: String, val order: SortOrder = SortOrder.DESC)
