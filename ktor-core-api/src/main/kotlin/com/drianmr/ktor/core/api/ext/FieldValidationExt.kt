package com.drianmr.ktor.core.api.ext

import com.drianmr.common.validator.MinimumLengthString
import com.drianmr.common.validator.NonNegativeNumber
import com.drianmr.common.validator.NonPositiveNumber
import com.drianmr.common.validator.PresentOrFutureDate
import com.drianmr.common.validator.RegexString
import com.drianmr.common.validator.RequiredString
import com.drianmr.common.validator.StrictMoney
import com.drianmr.common.validator.StrictPositiveNumber
import com.drianmr.common.validator.validate
import io.ktor.server.plugins.BadRequestException
import kotlinx.datetime.LocalDate
import java.math.BigDecimal

/**
 * Validates a string to ensure it's not empty.

 * @param field The name of the field being validated (used in the exception message).
 *
 * @throws BadRequestException if the string is empty or null.
 */
fun String?.validateAsRequired(field: String) {
    validate(RequiredString) {
        throw BadRequestException("Field '$field' cannot be empty")
    }
}

/**
 * Validates a string to ensure it represents a valid email address.
 *
 * @param field The name of the field being validated (used in the exception message). Defaults to "email".
 *
 * @throws BadRequestException if the string is empty/null or has an invalid email format.
 */
fun String?.validateAsEmail(field: String = "email") {
    validate(
        RequiredString,
        RegexString.Email,
    ) { validator ->
        when (validator) {
            is RequiredString ->
                throw BadRequestException("Field '$field' cannot be empty")
            is RegexString ->
                throw BadRequestException("Invalid email format")
            else -> {}
        }
    }
}

/**
 * Validates a string to ensure it represents a secure password with minimum of 8 characters.
 *
 * @param field The name of the field being validated (used in the exception message). Defaults to "password".
 *
 * @throws BadRequestException if the string is empty/null or doesn't meet the minimum length requirement.
 */
fun String?.validateAsPassword(field: String = "password") {
    validate(
        RequiredString,
        MinimumLengthString(min = 8),
    ) { validator ->
        when (validator) {
            is RequiredString ->
                throw BadRequestException("Field '$field' cannot be empty")
            is MinimumLengthString ->
                throw BadRequestException("Minimum password length is 8 characters")
            else -> {}
        }
    }
}

/**
 * Validates a number as non-negative money value.
 *
 * @param field The name of the field being validated.
 *
 * @throws BadRequestException on validation error.
 */
fun Number.validateAsMoney(field: String) {
    validate(NonNegativeNumber) {
        throw BadRequestException("Field '$field' must be non-negative")
    }
}

/**
 * Validates a number to ensure it represents a strictly positive monetary value.
 *
 * @param field The name of the field being validated.
 *
 * @throws BadRequestException on validation error.
 */
fun BigDecimal.validateAsStrictMoney(field: String) {
    validate(StrictMoney) {
        throw BadRequestException("Field '$field' cannot be zero or negative")
    }
}

/**
 * Validates a number as non-positive money value.
 *
 * @param field The name of the field being validated.
 *
 * @throws BadRequestException on validation error.
 */
fun Number.validateAsDiscount(field: String = "discount") {
    validate(NonPositiveNumber) {
        throw BadRequestException("Field '$field' must be non-positive")
    }
}

/**
 * Validates a number to ensure it represents a strictly positive quantity.
 *
 * @param field The name of the field being validated.
 *
 * @throws BadRequestException if the number is not strictly positive (zero or negative).
 */
fun Number.validateAsQuantity(field: String = "quantity") {
    validate(StrictPositiveNumber) {
        throw BadRequestException("The quantity for field '$field' cannot be zero or negative.")
    }
}

/**
 * Validates the size of a list to ensure it does not exceed a maximum allowed length.
 *
 * @param maxLength The maximum allowed size for the list.
 * @param field The name of the field being validated (used in the exception message).
 *
 * @throws BadRequestException if the list size exceeds the [maxLength].
 * The exception message includes the field name and the maximum allowed length.
 */
fun List<*>.validateMaxLength(maxLength: Int, field: String) {
    if (size > maxLength) {
        throw BadRequestException("Field '$field' exceeded maximum length of $maxLength")
    }
}

/**
 * Validates all elements in a list of strings to ensure they are not empty or null.
 *
 * @param field The name of the field being validated (used in the exception message).
 *
 * @throws BadRequestException if any element in the list is empty or null.
 * The exception message includes the field name and the index of the failing element.
 */
fun List<String>.validateAsRequired(field: String) {
    forEachIndexed { index, value ->
        value.validate(RequiredString) {
            throw BadRequestException("Field '$field' at index $index cannot be empty")
        }
    }
}

/**
 * Validates a [LocalDate] to ensure it is a present or future date.
 *
 * @param field The name of the field being validated (used in the exception message).
 *
 * @throws BadRequestException if the [LocalDate] is a past date.
 */
fun LocalDate.validateAsPresentOrFutureDate(field: String) {
    validate(PresentOrFutureDate) {
        throw BadRequestException("Field '$field' must be a present or future date.")
    }
}
