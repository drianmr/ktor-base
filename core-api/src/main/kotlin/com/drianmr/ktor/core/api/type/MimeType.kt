package com.drianmr.ktor.core.api.type

data class MimeType(val type: Type, val subtype: Subtype) {

    override fun toString(): String = "${type}/${subtype}"

    companion object {

        // Avoid platform declaration clash
        operator fun invoke(type: String, subtype: String?) = MimeType(
            type = Type(type),
            subtype = if (subtype.isNullOrBlank()) Subtype.Wildcard else Subtype(subtype),
        )
    }

    @JvmInline
    value class Type(val value: String) {

        override fun toString(): String = value
    }

    @JvmInline
    value class Subtype(val value: String) {

        companion object {
            val Wildcard = Subtype("*")
        }

        override fun toString(): String = value

        val isWildcard: Boolean get() = value == "*"
    }
}
