package com.drianmr.ktor.core.api.type

data class MimeType(val type: Type, val subtype: Subtype) {

    companion object {

        val Wildcard = MimeType(Type.Wildcard, Subtype.Wildcard)

        // Avoid platform declaration clash
        operator fun invoke(type: String, subtype: String?) = MimeType(
            type = Type(type),
            subtype = if (subtype.isNullOrBlank()) Subtype.Wildcard else Subtype(subtype),
        )

        /**
         * Parses a string representation into a [MimeType] object.
         */
        fun parse(value: String): MimeType = value
            .also { require(it.isNotBlank()) { "Type not specified" } }
            .split('/')
            .let {
                MimeType(
                    type = it[0]
                        .takeIf { type -> type.isNotBlank() }
                        ?: "*",
                    subtype = it.getOrNull(1)
                        ?.takeIf { subtype -> subtype.isNotBlank() }
                        ?: "*",
                )
            }

        /**
         * Attempts to parse a string representation into a [MimeType] object, returning `null` on failure.
         */
        fun tryParse(value: String): MimeType? = runCatching { parse(value) }.getOrNull()
    }

    override fun toString(): String = "${type}/${subtype}"

    @JvmInline
    value class Type(val value: String) {

        companion object {
            val Wildcard = Type("*")
        }

        override fun toString(): String = value

        val isWildcard: Boolean get() = value == "*"
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
